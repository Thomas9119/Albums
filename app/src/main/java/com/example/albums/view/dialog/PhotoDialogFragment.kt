package com.example.albums.view.dialog

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.albums.R
import com.example.albums.data.model.FileModel
import com.example.albums.data.model.FileType
import com.example.albums.databinding.DialogFragmentPhotoBinding
import com.example.albums.utils.ScreenUtil
import java.io.File

class PhotoDialogFragment : DialogFragment() {

    private var _binding: DialogFragmentPhotoBinding? = null
    private val binding: DialogFragmentPhotoBinding get() = _binding!!

    companion object {
        private const val KEY_DATA = "KEY_DATA"
        fun newInstance(fileModel: FileModel): PhotoDialogFragment {
            val args = Bundle()
            args.putParcelable(KEY_DATA, fileModel)
            val fragment = PhotoDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = arguments?.getParcelable<FileModel>(KEY_DATA)
        val width = ScreenUtil.getScreenWidth(binding.root.context)
        item?.let { photo ->
            if (photo.type == FileType.TYPE_IMAGE) {
                Glide.with(binding.root.context).load(photo.path)
                    .into(binding.ivPhoto)
            } else {
                val uri = Uri.fromFile(File(photo.path))
                Glide.with(binding.root.context).load(uri)
                    .thumbnail(0.1f)
                    .apply(
                        RequestOptions()
                            .override(width, width)
                    ).into(binding.ivPhoto)
            }
        }

        binding.ivBack.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }
}