package com.example.albums.view.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albums.R
import com.example.albums.data.model.FileModel
import com.example.albums.data.model.FileType
import com.example.albums.utils.AlbumItemDecoration
import com.example.albums.utils.FileUtils
import com.example.albums.view.dialog.PhotoDialogFragment
import java.io.File

/**
 * A fragment representing a list of Items.
 */
class PicturesFragment : Fragment() {

    private var columnCount = 1
    private var photoItemAdapter: PhotoItemAdapter? = null

    private val picturesViewModel: PicturesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_pictures_list, container, false)

        val albumItemDecoration = AlbumItemDecoration(
            4,
            columnCount
        )
        photoItemAdapter = PhotoItemAdapter {
//            it.path?.let { path ->
//                if (it.type == FileType.TYPE_IMAGE) {
//                    FileUtils.openFile(requireContext(), File(path))
//                } else {
//                    FileUtils.openVideo(requireContext(), File(path))
//                }
//            }

            showDialog(it)
        }
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = photoItemAdapter
                addItemDecoration(albumItemDecoration)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {
        picturesViewModel.files.observe(viewLifecycleOwner) {
            photoItemAdapter?.addData(it)
        }
        picturesViewModel.getListData(requireContext())
    }

    private fun showDialog(fileModel: FileModel) {
        val dialogFragment = PhotoDialogFragment.newInstance(fileModel)
        dialogFragment.show(childFragmentManager, "picture")
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            PicturesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}