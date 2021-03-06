package jp.hacks.smartbread.ui.aiface

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import jp.hacks.smartbread.R
import jp.hacks.smartbread.databinding.FaceFragmentBinding
import jp.hacks.smartbread.ui.main.tts.TTSService
import jp.hacks.smartbread.ui.main.tts.TTSServiceImpl
import jp.hacks.smartbread.ui.main.wake.BurnBreadUsecaseImpl
import kotlinx.android.synthetic.main.face_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*
* 可愛い顔を動かすためのFragment。
*/
internal class FaceFragment : Fragment() {

    companion object {
        fun newInstance() = FaceFragment()
    }

    private lateinit var viewModel: FaceViewModel
    private lateinit var binding: FaceFragmentBinding
    private lateinit var ttsService: TTSService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FaceFragmentBinding.inflate(
            LayoutInflater.from(
                requireContext()
            ),
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(FaceViewModel::class.java)

        ttsService = TTSServiceImpl(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        change_eye_button.setOnClickListener {
            viewModel.changeEyeState()
        }

        val newsAdapter = NewsAdapter(emptyList(), requireContext())
        viewModel.newsLiveData.observeForever {
            newsAdapter.insert(it)
            newsAdapter.notifyDataSetChanged()
            if (it.count() != 0) {
                binding.loadNewsMaterialButton.visibility = View.GONE
            }
        }
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.newsRecyclerView.adapter = newsAdapter

        binding.loadNewsMaterialButton.setOnClickListener {
            viewModel.loadNews()
        }

        binding.faceFragmentStartWakeUpTaskButton.setOnClickListener {
            //            val autoWakeService = AutoWakeService(requireContext())
            val burnBreadUsecase = BurnBreadUsecaseImpl(requireContext(), this.ttsService)
            GlobalScope.launch {
                burnBreadUsecase.startBurn()
            }
        }
    }

    private fun setupViewModel() {
        viewModel.eyeIsCount.observe(this, Observer {
            val resource = if (it) R.drawable.eye_close else R.drawable.eye
            Glide.with(this).load(resource).into(right_eye_image)
            Glide.with(this).load(resource).into(left_eye_image)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewModel()
    }
}
