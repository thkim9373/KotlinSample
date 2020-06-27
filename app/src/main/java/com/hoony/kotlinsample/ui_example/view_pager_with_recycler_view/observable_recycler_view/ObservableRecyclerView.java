/*
 * Copyright 2014 Soichiro Kashima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view.observable_recycler_view;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view.layout.OrientationConstraintLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView that its scroll position can be observed.
 * Before using this, please consider to use the RecyclerView.OnScrollListener
 * provided by the support library officially.
 */
public class ObservableRecyclerView extends RecyclerView implements Scrollable {

    // Fields that should be saved onSaveInstanceState
    private int mPrevFirstVisiblePosition;
    private int mPrevFirstVisibleChildHeight = -1;
    private int mPrevScrolledChildrenHeight;
    private int mPrevScrollY;
    private int mScrollY;
    private SparseIntArray mChildrenHeights;

    // Fields that don't need to be saved onSaveInstanceState
    private ObservableScrollViewCallbacks mCallbacks;
    private List<ObservableScrollViewCallbacks> mCallbackCollection;
    private ScrollState mScrollState;
    private boolean mFirstScroll;
    private boolean mDragging;
    private boolean mIntercepted;
    private MotionEvent mPrevMoveEvent;
    private ViewGroup mTouchInterceptionViewGroup;

    public ObservableRecyclerView(Context context) {
        this(context, null);
    }

    public ObservableRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ObservableRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        setFocusableInTouchMode(false);
    }

    /* span (= column) 추가 */
    private int mSpans = 1;

    public void setSpans(int spans) {
        mSpans = spans;
    }

    public int getSpans() {
        return mSpans;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        mPrevFirstVisiblePosition = ss.prevFirstVisiblePosition;
        mPrevFirstVisibleChildHeight = ss.prevFirstVisibleChildHeight;
        mPrevScrolledChildrenHeight = ss.prevScrolledChildrenHeight;
        mPrevScrollY = ss.prevScrollY;
        mScrollY = ss.scrollY;
        mChildrenHeights = ss.childrenHeights;
        super.onRestoreInstanceState(ss.getSuperState());
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.prevFirstVisiblePosition = mPrevFirstVisiblePosition;
        ss.prevFirstVisibleChildHeight = mPrevFirstVisibleChildHeight;
        ss.prevScrolledChildrenHeight = mPrevScrolledChildrenHeight;
        ss.prevScrollY = mPrevScrollY;
        ss.scrollY = mScrollY;
        ss.childrenHeights = mChildrenHeights;
        return ss;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (hasNoCallbacks()) {
            return;
        }
        if (getChildCount() > 0) {
            int firstVisiblePosition = getChildAdapterPosition(getChildAt(0));
            int lastVisiblePosition = getChildAdapterPosition(getChildAt(getChildCount() - 1));
            for (int i = firstVisiblePosition, j = 0; i <= lastVisiblePosition; i++, j++) {
                View child = getChildAt(j);
                if (child != null) {
                    if (mChildrenHeights.indexOfKey(i) < 0 || (child.getHeight() != mChildrenHeights.get(i))) {
                        mChildrenHeights.put(i, child.getHeight());
                    }
                }
            }

            View firstVisibleChild = getChildAt(0);
            if (firstVisibleChild != null) {
                if (mPrevFirstVisiblePosition < firstVisiblePosition) {
                    // scroll down
                    int skippedChildrenHeight = 0;
                    if (firstVisiblePosition - mPrevFirstVisiblePosition != 1) {
                        for (int i = firstVisiblePosition - 1; i > mPrevFirstVisiblePosition; i--) {
                            if (0 < mChildrenHeights.indexOfKey(i)) {
                                skippedChildrenHeight += mChildrenHeights.get(i);
                            } else {
                                // Approximate each item's height to the first visible child.
                                // It may be incorrect, but without this, scrollY will be broken
                                // when scrolling from the bottom.
                                skippedChildrenHeight += firstVisibleChild.getHeight();
                            }
                        }
                    }
                    mPrevScrolledChildrenHeight += mPrevFirstVisibleChildHeight + skippedChildrenHeight;
                    mPrevFirstVisibleChildHeight = firstVisibleChild.getHeight();
                } else if (firstVisiblePosition < mPrevFirstVisiblePosition) {
                    // scroll up
                    int skippedChildrenHeight = 0;
                    if (mPrevFirstVisiblePosition - firstVisiblePosition != 1) {
                        for (int i = mPrevFirstVisiblePosition - 1; i > firstVisiblePosition; i--) {
                            if (0 < mChildrenHeights.indexOfKey(i)) {
                                skippedChildrenHeight += mChildrenHeights.get(i);
                            } else {
                                // Approximate each item's height to the first visible child.
                                // It may be incorrect, but without this, scrollY will be broken
                                // when scrolling from the bottom.
                                skippedChildrenHeight += firstVisibleChild.getHeight();
                            }
                        }
                    }
                    mPrevScrolledChildrenHeight -= firstVisibleChild.getHeight() + skippedChildrenHeight;
                    mPrevFirstVisibleChildHeight = firstVisibleChild.getHeight();
                } else if (firstVisiblePosition == 0) {
                    mPrevFirstVisibleChildHeight = firstVisibleChild.getHeight();
                    mPrevScrolledChildrenHeight = 0;
                }
                if (mPrevFirstVisibleChildHeight < 0) {
                    mPrevFirstVisibleChildHeight = 0;
                }
                mScrollY = mPrevScrolledChildrenHeight - firstVisibleChild.getTop() + getPaddingTop();
                mPrevFirstVisiblePosition = firstVisiblePosition;

                dispatchOnScrollChanged(mScrollY, mFirstScroll, mDragging);
                if (mFirstScroll) {
                    mFirstScroll = false;
                }

                if (mPrevScrollY < mScrollY) {
                    //down
                    mScrollState = ScrollState.UP;
                } else if (mScrollY < mPrevScrollY) {
                    //up
                    mScrollState = ScrollState.DOWN;
                } else {
                    mScrollState = ScrollState.STOP;
                }
                mPrevScrollY = mScrollY;
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        try {
            super.onLayout(changed, l, t, r, b);
        } catch (Exception e) {
//      WLog.logException(WhyTheHellRecyclerException.fromRecyclerView(this, e));
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
//        Log.d("touch", "recycler view - dispatchTouchEvent result : " + result);
//        if(isHorizontalScroll && viewPager2 != null) {
//            Log.d("touch", "send touch event ev.action : " + ev.getAction());
//            viewPager2.dispatchTouchEvent(ev);
//        }
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (hasNoCallbacks()) {
            Log.d("touch", "recycler view - onInterceptTouchEvent : hasNoCallbacks");
            return super.onInterceptTouchEvent(ev);
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("touch", "recycler view - onInterceptTouchEvent : ACTION_DOWN");
                // Whether or not motion events are consumed by children,
                // flag initializations which are related to ACTION_DOWN events should be executed.
                // Because if the ACTION_DOWN is consumed by children and only ACTION_MOVEs are
                // passed to parent (this view), the flags will be invalid.
                // Also, applications might implement initialization codes to onDownMotionEvent,
                // so call it here.
                mFirstScroll = mDragging = true;
                dispatchOnDownMotionEvent();
                break;
            case MotionEvent.ACTION_UP:
                Log.d("touch", "recycler view - onInterceptTouchEvent : ACTION_UP");
                break;
        }
        boolean result = super.onInterceptTouchEvent(ev);
        Log.d("touch", "recycler view - onInterceptTouchEvent  result : " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

//        Log.d("touch", "recycler view - onTouchEvent result : " + false);
//        return false;

        if ((((OrientationConstraintLayout) getParent()).getOrientation() == OrientationConstraintLayout.Orientation.HORIZONTAL) &&
                !(ev.getActionMasked() == MotionEvent.ACTION_UP || ev.getActionMasked() == MotionEvent.ACTION_CANCEL)) {
            Log.d("touch", "recycler view - onTouchEvent orientation is horizontal");
            return false;
        }

        if (hasNoCallbacks()) {
            boolean result = super.onTouchEvent(ev);
            Log.d("touch", "recycler view - onTouchEvent result : " + result);
            return result;
        }

        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Log.d("touch", "recycler view - onTouchEvent : ACTION_UP");
                mIntercepted = false;
                mDragging = false;
                dispatchOnUpOrCancelMotionEvent(mScrollState);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("touch", "recycler view - onTouchEvent : ACTION_MOVE");
                if (mPrevMoveEvent == null) {
                    mPrevMoveEvent = ev;
                }
                float diffY = ev.getY() - mPrevMoveEvent.getY();
                mPrevMoveEvent = MotionEvent.obtainNoHistory(ev);
                if (getCurrentScrollY() - diffY <= 0) {
                    // Can't scroll anymore.

                    if (mIntercepted) {
                        // Already dispatched ACTION_DOWN event to parents, so stop here.
                        Log.d("touch", "recycler view - onTouchEvent result : " + false);
                        return false;
                    }

                    // Apps can set the interception target other than the direct parent.
                    final ViewGroup parent;
                    if (mTouchInterceptionViewGroup == null) {
                        parent = (ViewGroup) getParent();
                    } else {
                        parent = mTouchInterceptionViewGroup;
                    }

                    // Get offset to parents. If the parent is not the direct parent,
                    // we should aggregate offsets from all of the parents.
                    float offsetX = 0;
                    float offsetY = 0;
                    for (View v = this; v != null && v != parent; v = (View) v.getParent()) {
                        offsetX += v.getLeft() - v.getScrollX();
                        offsetY += v.getTop() - v.getScrollY();
                    }
                    final MotionEvent event = MotionEvent.obtainNoHistory(ev);
                    event.offsetLocation(offsetX, offsetY);

                    if (parent.onInterceptTouchEvent(event)) {
                        mIntercepted = true;

                        // If the parent wants to intercept ACTION_MOVE events,
                        // we pass ACTION_DOWN event to the parent
                        // as if these touch events just have began now.
                        event.setAction(MotionEvent.ACTION_DOWN);

                        // Return this onTouchEvent() first and set ACTION_DOWN event for parent
                        // to the queue, to keep events sequence.
                        post(new Runnable() {
                            @Override
                            public void run() {
                                parent.dispatchTouchEvent(event);
                            }
                        });
                        Log.d("touch", "recycler view - onTouchEvent result : " + false);
                        return false;
                    }
                    // Even when this can't be scrolled anymore,
                    // simply returning false here may cause subView's click,
                    // so delegate it to super.
                    boolean result = super.onTouchEvent(ev);
                    Log.d("touch", "recycler view - onTouchEvent result : " + result);
                    return result;
                }
                break;
        }
        boolean result = super.onTouchEvent(ev);
        Log.d("touch", "recycler view - onTouchEvent result : " + result);
        return result;
    }

    /**
     * 하나의 콜백만 set 되기 때문에, 되도록이면 #addScrollViewCallbacks()를 이용하시길. written by hugh.
     */
    @Override
    public void setScrollViewCallbacks(ObservableScrollViewCallbacks listener) {
        mCallbacks = listener;
    }

    /**
     * 여러 개의 콜백이 등록 가능함(Callback의 레퍼런스로 중복 체크함). written by hugh.
     */
    @Override
    public void addScrollViewCallbacks(ObservableScrollViewCallbacks listener) {
        if (mCallbackCollection == null) {
            mCallbackCollection = new ArrayList<>();
        }

        if (!mCallbackCollection.contains(listener)) { /* added by Hugh */
            mCallbackCollection.add(listener);
        }
    }

    @Override
    public void removeScrollViewCallbacks(ObservableScrollViewCallbacks listener) {
        if (mCallbackCollection != null) {
            mCallbackCollection.remove(listener);
        }
    }

    @Override
    public void clearScrollViewCallbacks() {
        if (mCallbackCollection != null) {
            mCallbackCollection.clear();
        }
    }

    @Override
    public void setTouchInterceptionViewGroup(ViewGroup viewGroup) {
        mTouchInterceptionViewGroup = viewGroup;
    }

    @Override
    public void scrollVerticallyTo(int y) {
        View firstVisibleChild = getChildAt(0);
        if (firstVisibleChild != null) {
            int baseHeight = firstVisibleChild.getHeight();
            int position = y / baseHeight;
            scrollVerticallyToPosition(position);
        }
    }

    /**
     * <p>Same as {@linkplain #scrollToPosition(int)} but it scrolls to the position not only make
     * the position visible.</p>
     * <p>It depends on {@code LayoutManager} how {@linkplain #scrollToPosition(int)} works,
     * and currently we know that {@linkplain LinearLayoutManager#scrollToPosition(int)} just
     * make the position visible.</p>
     * <p>In LinearLayoutManager, scrollToPositionWithOffset() is provided for scrolling to the position.
     * This method checks which LayoutManager is set,
     * and handles which method should be called for scrolling.</p>
     * <p>Other know classes (StaggeredGridLayoutManager and GridLayoutManager) are not tested.</p>
     *
     * @param position Position to scroll.
     */
    public void scrollVerticallyToPosition(int position) {
        LayoutManager lm = getLayoutManager();

        if (lm != null && lm instanceof LinearLayoutManager) {
            ((LinearLayoutManager) lm).scrollToPositionWithOffset(position, 0);
        } else {
            scrollToPosition(position);
        }
    }

    @Override
    public int getCurrentScrollY() {
        return mScrollY;
    }

    private void init() {
        mChildrenHeights = new SparseIntArray();
    }

    private void dispatchOnDownMotionEvent() {
        if (mCallbacks != null) {
            mCallbacks.onDownMotionEvent();
        }
        if (mCallbackCollection != null) {
            for (int i = 0; i < mCallbackCollection.size(); i++) {
                ObservableScrollViewCallbacks callbacks = mCallbackCollection.get(i);
                if (callbacks != null) {
                    callbacks.onDownMotionEvent();
                }
            }
        }
    }

    private void dispatchOnScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        if (mCallbacks != null) {
            mCallbacks.onScrollChanged(scrollY, firstScroll, dragging);
        }
        if (mCallbackCollection != null) {
            for (int i = 0; i < mCallbackCollection.size(); i++) {
                ObservableScrollViewCallbacks callbacks = mCallbackCollection.get(i);
                if (callbacks != null) {
                    callbacks.onScrollChanged(scrollY, firstScroll, dragging);
                }
            }
        }
    }

    private void dispatchOnUpOrCancelMotionEvent(ScrollState scrollState) {
        if (mCallbacks != null) {
            mCallbacks.onUpOrCancelMotionEvent(scrollState);
        }
        if (mCallbackCollection != null) {
            for (int i = 0; i < mCallbackCollection.size(); i++) {
                ObservableScrollViewCallbacks callbacks = mCallbackCollection.get(i);
                if (callbacks != null) {
                    callbacks.onUpOrCancelMotionEvent(scrollState);
                }
            }
        }
    }

    private boolean hasNoCallbacks() {
        return mCallbacks == null && mCallbackCollection == null;
    }

    /**
     * This saved state class is a Parcelable and should not extend
     * {@link BaseSavedState} nor {@link android.view.AbsSavedState}
     * because its super class AbsSavedState's constructor
     * {android.view.AbsSavedState#AbsSavedState(Parcel)} currently passes null
     * as a class loader to read its superstate from Parcelable.
     * This causes {@link android.os.BadParcelableException} when restoring saved states.
     * <p>
     * The super class "RecyclerView" is a part of the support library,
     * and restoring its saved state requires the class loader that loaded the RecyclerView.
     * It seems that the class loader is not required when restoring from RecyclerView itself,
     * but it is required when restoring from RecyclerView's subclasses.
     */
    static class SavedState implements Parcelable {
        public static final SavedState EMPTY_STATE = new SavedState() {
        };

        int prevFirstVisiblePosition;
        int prevFirstVisibleChildHeight = -1;
        int prevScrolledChildrenHeight;
        int prevScrollY;
        int scrollY;
        SparseIntArray childrenHeights;

        // This keeps the parent(RecyclerView)'s state
        Parcelable superState;

        /**
         * Called by EMPTY_STATE instantiation.
         */
        private SavedState() {
            superState = null;
        }

        /**
         * Called by onSaveInstanceState.
         */
        SavedState(Parcelable superState) {
            this.superState = superState != EMPTY_STATE ? superState : null;
        }

        /**
         * Called by CREATOR.
         */
        private SavedState(Parcel in) {
            // Parcel 'in' has its parent(RecyclerView)'s saved state.
            // To restore it, class loader that loaded RecyclerView is required.
            Parcelable superState = in.readParcelable(RecyclerView.class.getClassLoader());
            this.superState = superState != null ? superState : EMPTY_STATE;

            prevFirstVisiblePosition = in.readInt();
            prevFirstVisibleChildHeight = in.readInt();
            prevScrolledChildrenHeight = in.readInt();
            prevScrollY = in.readInt();
            scrollY = in.readInt();
            childrenHeights = new SparseIntArray();
            final int numOfChildren = in.readInt();
            if (0 < numOfChildren) {
                for (int i = 0; i < numOfChildren; i++) {
                    final int key = in.readInt();
                    final int value = in.readInt();
                    childrenHeights.put(key, value);
                }
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            out.writeParcelable(superState, flags);

            out.writeInt(prevFirstVisiblePosition);
            out.writeInt(prevFirstVisibleChildHeight);
            out.writeInt(prevScrolledChildrenHeight);
            out.writeInt(prevScrollY);
            out.writeInt(scrollY);
            final int numOfChildren = childrenHeights == null ? 0 : childrenHeights.size();
            out.writeInt(numOfChildren);
            if (0 < numOfChildren) {
                for (int i = 0; i < numOfChildren; i++) {
                    out.writeInt(childrenHeights.keyAt(i));
                    out.writeInt(childrenHeights.valueAt(i));
                }
            }
        }

        public Parcelable getSuperState() {
            return superState;
        }

        public static final Creator<SavedState> CREATOR
                = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
