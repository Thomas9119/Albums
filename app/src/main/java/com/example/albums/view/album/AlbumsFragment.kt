package com.example.albums.view.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.albums.databinding.FragmentAlbumsListBinding
import com.example.albums.utils.StoreUtil
import com.example.albums.view.album.adapter.AlbumItemAdapter
import com.example.albums.view.picture.PicturesViewModel

/**
 * A fragment representing a list of Items.
 */
class AlbumsFragment : Fragment() {

    private var columnCount = 2
    private var albumItemAdapter: AlbumItemAdapter? = null
    private var _binding: FragmentAlbumsListBinding? = null
    private val binding: FragmentAlbumsListBinding get() = _binding!!

    private val albumsViewModel: AlbumsViewModel by viewModels()

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        // Set the adapter
        binding.list.apply {
            albumItemAdapter = AlbumItemAdapter {
                //todo
            }
            layoutManager = GridLayoutManager(context, 2)
            adapter = albumItemAdapter
        }
    }

    private fun setupObserver() {
        albumsViewModel.files.observe(viewLifecycleOwner) {
            albumItemAdapter?.addData(it)
        }
        albumsViewModel.getAlbums(requireContext())
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