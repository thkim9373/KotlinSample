package com.hoony.kotlinsample.custom_view.radio_group

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.Checkable
import androidx.constraintlayout.widget.ConstraintLayout
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ViewCustomRadioButtonBinding


class CustomRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleRes), Checkable {

    fun interface OnCheckChangedListener {
        fun onCheckChanged(view: CustomRadioButton, isChecked: Boolean)
    }

    private val binding = ViewCustomRadioButtonBinding
        .inflate(
            LayoutInflater.from(context),
            this
        )

    var listener: OnCheckChangedListener? = null

    init {
        val outValue = TypedValue()
        getContext().theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        setBackgroundResource(outValue.resourceId)

        if (attrs != null) {

            val attributeSet = context.obtainStyledAttributes(attrs, R.styleable.CustomRadioButton)

            binding.text.text = attributeSet.getString(R.styleable.CustomRadioButton_text)

            attributeSet.recycle()
        }
        this.setOnClickListener {
            if (!this.isChecked) this.isChecked = true
        }
        binding.button.setOnCheckedChangeListener { _, isChecked ->
            if (this.isChecked != isChecked) this.isChecked = isChecked
        }
    }

    private var check: Boolean = false

    override fun setChecked(checked: Boolean) {
        this.check = checked
        updateRadioButton()
        listener?.onCheckChanged(this, isChecked)
    }

    override fun isChecked(): Boolean = check

    override fun toggle() {
        this.isChecked = !this.isChecked
    }

    private fun updateRadioButton() {
        binding.button.isChecked = check
    }
}