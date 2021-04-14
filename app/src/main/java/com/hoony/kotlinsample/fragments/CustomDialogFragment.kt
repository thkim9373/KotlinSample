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
import androidx.fragment.app.DialogFragment
import com.hoony.kotlinsample.databinding.FragmentCustomDialogBinding

class CustomDialogFragment(
    val title: String,
    private val contents: List<String> = listOf(),
    private val positiveText: String? = null,
    private val negativeTests: List<String> = listOf(),
) : DialogFragment() {

    private var _binding: FragmentCustomDialogBinding? = null
    val binding: FragmentCustomDialogBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // make white background transparent
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        _binding = FragmentCustomDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            topContainer.apply {
                addView(
                    TextView(requireContext()).apply {
                        text = title
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                        typeface = Typeface.DEFAULT_BOLD
                        setTextColor(Color.WHITE)
                        gravity = Gravity.CENTER
                    }
                )
                contents.forEach {
                    addView(
                        TextView(requireContext()).apply {
                            text = it
                            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                            setTextColor(Color.parseColor("#BDBDBD"))
                            gravity = Gravity.CENTER
                            setPadding(0, 20, 0, 0)
                        }
                    )
                }
            }
            container.apply {
                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.MarginLayoutParams.WRAP_CONTENT,
                    ViewGroup.MarginLayoutParams.WRAP_CONTENT
                )
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
                        orientation = getButtonLayoutOriental()
                        addView(
                            TextView(requireContext()).apply {
                                layoutParams = ViewGroup.MarginLayoutParams(
                                    ViewGroup.MarginLayoutParams.MATCH_PARENT,
                                    dpToPx(52)
                                )
                                text = positiveText
                                setTextColor(Color.WHITE)
                                gravity = Gravity.CENTER
                                setPadding(0, 0, 0, 20)
                                setOnClickListener {
                                    dismiss()
                                }
                            }
                        )
                        negativeTests.forEach {
                            addView(
                                View(requireContext()).apply {
                                    layoutParams = ViewGroup.MarginLayoutParams(
                                        if (getButtonLayoutOriental() == LinearLayout.VERTICAL) {
                                            ViewGroup.MarginLayoutParams.MATCH_PARENT
                                        } else {
                                            dpToPx(1)
                                        },
                                        if (getButtonLayoutOriental() == LinearLayout.VERTICAL) {
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
                                    layoutParams = ViewGroup.MarginLayoutParams(
                                        ViewGroup.MarginLayoutParams.MATCH_PARENT,
                                        dpToPx(52)
                                    )
                                    setPadding(0, 0, 0, dpToPx(6))
                                    setOnClickListener {
                                        dismiss()
                                    }
                                }
                            )
                        }
                    }
                )
            }
        }
    }

    private fun getButtonLayoutOriental(): Int = if (positiveText?.length ?: 0 >= 7) {
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