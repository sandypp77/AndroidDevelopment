package com.example.androiddevelopment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androiddevelopment.databinding.ItemJobBinding
import com.example.androiddevelopment.databinding.ItemJobListBinding

class JobAdapterList(private var jobList: ArrayList<JobModelList>): RecyclerView.Adapter<JobAdapterList.ViewHolder>() {
    inner class ViewHolder(val binding: ItemJobListBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemJobListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val job = jobList[position]
        with(holder){
            Glide.with(binding.ivJob.context).load(job.jobImage).into(binding.ivJob)
            binding.tvJob.text = job.jobTitle
            binding.tvTitle.text = job.jobCompany
        }
    }

    override fun getItemCount(): Int {
        return jobList.size
    }
}