package com.example.albums.view.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.albums.databinding.FragmentAlbumsListBinding
import com.example.albums.utils.StoreUtil
import com.example.albums.view.album.adapter.AlbumItemAdapter

/**
 * A fragment representing a list of Items.
 */
class AlbumsFragment : Fragment() {

    private var columnCount = 2
    private var albumItemAdapter: AlbumItemAdapter? = null
    private var _binding: FragmentAlbumsListBinding? = null
    private val binding: FragmentAlbumsListBinding get() = _binding!!

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
        _binding = FragmentAlbumsListBinding.inflate(inflater, container, false)

        // Set the adapter
        binding.list.apply {
            albumItemAdapter = AlbumItemAdapter {
                //todo
            }
            layoutManager = GridLayoutManager(context, 2)
            adapter = albumItemAdapter
        }

        val albums = StoreUtil.getAlbumPhoto(requireContext())
        albumItemAdapter?.addData(albums)
        return binding.root
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            AlbumsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}