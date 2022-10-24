package com.example.androiddevelopment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevelopment.databinding.ImageSliderBinding

class SliderAdapter(private val items:List<ImageData>):RecyclerView.Adapter<SliderAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(val binding: ImageSliderBinding):RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ImageSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val slider = items[position]
        with(holder){
            binding.sliderImage.setImageResource(slider.sliderImage)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}