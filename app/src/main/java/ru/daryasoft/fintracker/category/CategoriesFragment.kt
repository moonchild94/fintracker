package ru.daryasoft.fintracker.category

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.daryasoft.fintracker.R

/**
 * Фрагмент для отображения категорий.
 */
class CategoriesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_fragment_categories)
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CategoriesFragment()
    }
}
