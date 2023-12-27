package com.example.securityscreen.presentation.security_screen

import android.content.Context
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.securityscreen.R
import com.example.securityscreen.data.PinCodeIntent
import com.example.securityscreen.data.PinCodeView
import com.example.securityscreen.databinding.FragmentSecurityBinding
import com.example.securityscreen.presentation.base.BaseFragment
import kotlinx.coroutines.launch

class SecurityFragment : BaseFragment<FragmentSecurityBinding>(FragmentSecurityBinding::inflate),
    PinCodeView {

    private val viewModel: PinCodeViewModel by viewModels()
    private lateinit var adapter: KeyboardRecyclerAdapter
    private lateinit var circleViews: List<ImageView>

    override fun start() {
        openKeyboard()
        circleViews = listOf(
            binding.circle1,
            binding.circle2,
            binding.circle3,
            binding.circle4
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                // ui update
                if (state.pinError) {
                    showPinError()
                }
            }
        }

    }

    override fun clickListener() {
        binding.apply {
                edText.addTextChangedListener {
                    viewModel.processIntent(PinCodeIntent.SubmitPin(it.toString()))
                    onPinCodeChanged(it.toString())
                }

            edText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val pin = edText.text.toString()
                    viewModel.processIntent(PinCodeIntent.SubmitPin(pin))
                    onPinCodeChanged(pin)
                    true
                } else {
                    false
                }
            }

        }
    }

    private fun adapter() = with(binding) {
        adapter = KeyboardRecyclerAdapter()
        recyclerKeyboard.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerKeyboard.adapter = adapter
    }


    private fun openKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.edText, InputMethodManager.SHOW_IMPLICIT)
    }
    override fun showPinError() {
        Toast.makeText(requireContext(), "Wrong pin code", Toast.LENGTH_SHORT).show()

        circleViews.forEach { it.setBackgroundResource(R.drawable.circle_empty) }
    }

    private fun updateCircleColor(position: Int, filled: Boolean) {
        val drawableRes = if (filled) R.drawable.circle_filled else R.drawable.circle_empty
        circleViews[position].setBackgroundResource(drawableRes)
    }

    private fun onPinCodeChanged(pinCode: String) {
        for (i in circleViews.indices) {
            val filled = i < pinCode.length
            updateCircleColor(i, filled)
        }

        for (i in pinCode.length until circleViews.size) {
            updateCircleColor(i, false)
        }
    }

    override fun navigateToNextScreen() {

    }

}