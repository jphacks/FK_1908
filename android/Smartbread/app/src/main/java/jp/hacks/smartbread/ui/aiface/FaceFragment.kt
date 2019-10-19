package jp.hacks.smartbread.ui.aiface

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide

import jp.hacks.smartbread.R
import kotlinx.android.synthetic.main.face_fragment.*

/*
* 可愛い顔を動かすためのFragment。
*/
class FaceFragment : Fragment() {

    companion object {
        fun newInstance() = FaceFragment()
    }

    private lateinit var viewModel: FaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.face_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        change_eye_button.setOnClickListener {
            viewModel.changeEyeState()
        }
    }

    private fun setupViewModel(){
        viewModel.eyeIsCount.observe(this, Observer {
            val resource = if(it) R.drawable.eye_close else R.drawable.eye
            Glide.with(this).load(resource).into(right_eye_image)
            Glide.with(this).load(resource).into(left_eye_image)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FaceViewModel::class.java)
        setupViewModel()
    }
}
