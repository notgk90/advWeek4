package com.ubaya.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.advweek4.R
import com.ubaya.advweek4.databinding.FragmentStudentDetailBinding
import com.ubaya.advweek4.util.loadImage
import com.ubaya.advweek4.viewmodel.DetailViewModel
import com.ubaya.advweek4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonUpdateListener, ButtonNotifListener {
    private lateinit var dataBinding: FragmentStudentDetailBinding

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if(arguments != null){
            var id = StudentDetailFragmentArgs.fromBundle(requireArguments()).idStudent
            detailViewModel.setID(id)
        }
        detailViewModel.fetch()

        dataBinding.listener = this
        dataBinding.notif = this

        ObserveDetailViewModel()
    }

    fun ObserveDetailViewModel(){
        detailViewModel.studentLd.observe(viewLifecycleOwner, Observer {
            var student = it
            dataBinding.student = it
            /*txtId.setText(it.id)
            txtName.setText(it.name)
            txtBod.setText(it.dob)
            txtPhone.setText(it.phone)
            imageViewDetail.loadImage(it.photoUrl.toString(), progressBarDetail)
            btnNotif.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(student.name.toString(),
                            "A new notification created",
                            R.drawable.ic_baseline_error_24)
                    }
            }*/
        })
    }

    override fun onButtonUpdate(v: View) {
        Toast.makeText(v.context, "Data Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }

    override fun onButtonNotif(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "five seconds")
                MainActivity.showNotification(dataBinding.student.name.toString(),
                    "A new notification created",
                    R.drawable.ic_baseline_error_24)
            }
    }
}