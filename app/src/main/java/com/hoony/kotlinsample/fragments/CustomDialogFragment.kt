package com.hoony.kotlinsample.fragments

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.FragmentCustomDialogBinding
import com.hoony.kotlinsample.fragments.CustomDialogFragment.Type.Common

class CustomDialogFragment : DialogFragment() {

    sealed class Type {
        data class Common(
            val title: String,
            val contents: ArrayList<String> = arrayListOf(),
            val positiveText: String? = null,
            val negativeTests: List<String> = arrayListOf()
        ) : Type()
    }

    companion object {
        private const val KEY_TITLE = "title"
        private const val KEY_CONTENTS = "contents"
        private const val KEY_POSITIVE_BUTTON_TEXT = "positive button text"
        private const val KEY_NEGATIVE_BUTTON_TEXTS = "negative button texts"

        fun newInstance(
            type: Type
        ): CustomDialogFragment =
            CustomDialogFragment().apply {
                when (type) {
                    is Common -> {
                        arguments = bundleOf(
                            KEY_TITLE to type.title,
                            KEY_CONTENTS to type.contents,
                            KEY_POSITIVE_BUTTON_TEXT to type.positiveText,
                            KEY_NEGATIVE_BUTTON_TEXTS to type.negativeTests
                        )
                    }
                }
            }

        private const val BUTTON_HEIGHT = 52
    }

    private var _binding: FragmentCustomDialogBinding? = null
    val binding: FragmentCustomDialogBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentCustomDialogBinding.inflate(inflater)

        dialog?.window?.apply {
            // make white background transparent
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title: String
        val contents: List<String>
        val positiveText: String
        val negativeTests: List<String>

        arguments.let {
            title = it?.getString(KEY_TITLE).orEmpty()
            contents = it?.getStringArrayList(KEY_CONTENTS).orEmpty()
            positiveText = it?.getString(KEY_POSITIVE_BUTTON_TEXT).orEmpty()
            negativeTests = it?.getStringArrayList(KEY_NEGATIVE_BUTTON_TEXTS).orEmpty()
        }

        binding.apply {
            topContainer.apply {
                addView(
                    getTitleTextView().apply {
                        text = title
                    }
                )
                contents.forEach {
                    addView(
                        getContentTextView().apply {
                            text = it
                        }
                    )
                }
            }
            container.apply {
//                layoutParams = ViewGroup.MarginLayoutParams(
//                    ViewGroup.MarginLayoutParams.WRAP_CONTENT,
//                    ViewGroup.MarginLayoutParams.WRAP_CONTENT
//                )
                addView(
                    View(requireContext()).apply {
                        layoutParams = ViewGroup.MarginLayoutParams(
                            ViewGroup.MarginLayoutParams.MATCH_PARENT,
                            dpToPx(1)
                        )
                        setBackgroundColor(Color.parseColor("#38393D"))
                    }
                )
                addView(
                    LinearLayout(requireContext()).apply {
                        orientation = getButtonLayoutOriental(positiveText, negativeTests)
                        weightSum = 2f
                        addView(
                            TextView(requireContext()).apply {
                                layoutParams = LinearLayout.LayoutParams(
                                    ViewGroup.MarginLayoutParams.MATCH_PARENT,
                                    dpToPx(52)
                                ).apply {
                                    gravity = Gravity.TOP
                                    weight = 1f
                                }
                                text = positiveText
                                setTextColor(Color.WHITE)
                                gravity = Gravity.CENTER
                                setOnClickListener {
                                    dismiss()
                                }
                                background = ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.background_custom_dialog_middle_button,
                                    null
                                )
                            }
                        )
                        negativeTests.forEach {
                            addView(
                                View(requireContext()).apply {
                                    layoutParams = ViewGroup.MarginLayoutParams(
                                        if (getButtonLayoutOriental(
                                                positiveText,
                                                negativeTests
                                            ) == LinearLayout.VERTICAL
                                        ) {
                                            ViewGroup.MarginLayoutParams.MATCH_PARENT
                                        } else {
                                            dpToPx(1)
                                        },
                                        if (getButtonLayoutOriental(
                                                positiveText,
                                                negativeTests
                                            ) == LinearLayout.VERTICAL
                                        ) {
                                            dpToPx(1)
                                        } else {
                                            ViewGroup.MarginLayoutParams.MATCH_PARENT
                                        }
                                    )
                                    setBackgroundColor(Color.parseColor("#38393D"))
                                }
                            )
                            addView(
                                TextView(requireContext()).apply {
                                    text = it
                                    setTextColor(Color.WHITE)
                                    gravity = Gravity.CENTER
                                    layoutParams = LinearLayout.LayoutParams(
                                        ViewGroup.MarginLayoutParams.MATCH_PARENT,
                                        dpToPx(52)
                                    ).apply {
                                        weight = 1f
                                    }
                                    setOnClickListener {
                                        dismiss()
                                    }
                                    background = ResourcesCompat.getDrawable(
                                        resources,
                                        R.drawable.background_custom_dialog_middle_button,
                                        null
                                    )
                                }
                            )
                        }
                    }
                )
            }
        }
    }

    private fun getTitleTextView(): TextView =
        TextView(requireContext()).apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

    private fun getContentTextView(): TextView =
        TextView(requireContext()).apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            setTextColor(Color.parseColor("#BDBDBD"))
            gravity = Gravity.CENTER
            setPadding(0, 20, 0, 0)
        }

    private fun getButtonLayoutOriental(positiveText: String, negativeTests: List<String>): Int =
        if (positiveText.length >= 7) {
            LinearLayout.VERTICAL
        } else {
            negativeTests.forEach {
                if (it.length >= 7) {
                    return LinearLayout.VERTICAL
                }
            }
            LinearLayout.HORIZONTAL
        }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

//    fun Context.pxToDp(px: Int): Int {
//        return (px / resources.displayMetrics.density).toInt()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}