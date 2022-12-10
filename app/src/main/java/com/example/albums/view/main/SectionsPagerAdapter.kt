package com.example.albums.view.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.albums.R
import com.example.albums.view.album.AlbumsFragment
import com.example.albums.view.picture.PicturesFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_pictures,
    R.string.tab_albums,
    R.string.tab_stories,
    R.string.tab_shared
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when(position) {
            0 -> {
                PicturesFragment.newInstance(3)
            }
            1 -> {
                AlbumsFragment.newInstance(1)
            }
            else -> PlaceholderFragment.newInstance(position + 1)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 4 total pages.
        return 4
    }
}