package com.example.kisshtproject.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kisshtproject.R
import com.example.kisshtproject.adapter.PhotosAdapter
import com.example.kisshtproject.service.ServiceHelper
import com.example.kisshtproject.viewmodel.BaseViewModelFactory
import com.example.kisshtproject.viewmodel.PhotosActivityViewModel
import kotlinx.android.synthetic.main.activity_task_two.*

class TaskTwoActivity : AppCompatActivity() {

    private lateinit var viewModel: PhotosActivityViewModel
    private lateinit var mGridLayoutManager: GridLayoutManager
    private lateinit var adapter: PhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_two)
        title = getString(R.string.title_gallery)

        initViewModel()
        initData()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, BaseViewModelFactory(ServiceHelper.getService())).get(PhotosActivityViewModel::class.java)

        viewModel.obsvShowLoader().observe(this, {
            progress.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

        viewModel.obsvGetErrorMsg().observe(this, {
            var errMsg = it
            if (errMsg.isNullOrEmpty()) {
                errMsg = resources.getString(R.string.error_network_error)
            }
            Toast.makeText(this@TaskTwoActivity, errMsg, Toast.LENGTH_LONG).show()
        })

        viewModel.obsvGetPhotos().observe(this, {
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun initData() {
        initAdapter()
        viewModel.getPhotos()
    }

    private fun initAdapter() {
        mGridLayoutManager = GridLayoutManager(this, 3);
        rvPhotos.layoutManager = mGridLayoutManager;
        adapter = PhotosAdapter(this)
        adapter.setData(ArrayList())
        rvPhotos.adapter = adapter
    }
}