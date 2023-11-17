package com.example.fff.epoxy

import com.example.fff.R
import com.example.fff.databinding.ModelLoadingBinding

class LoadingEpoxyModel : ViewBindingKotlinModel<ModelLoadingBinding>(R.layout.model_loading) {

    override fun ModelLoadingBinding.bind() {
        // nothing to do
    }
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}