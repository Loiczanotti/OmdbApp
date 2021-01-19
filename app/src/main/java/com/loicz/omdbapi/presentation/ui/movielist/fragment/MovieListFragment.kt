package com.loicz.omdbapi.presentation.ui.movielist.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.storage.FirebaseStorage
import com.loicz.omdbapi.R
import com.loicz.omdbapi.presentation.component.SnackBarComponent
import com.loicz.omdbapi.presentation.extension.observeSafe
import com.loicz.omdbapi.presentation.ui.base.BaseFragment
import com.loicz.omdbapi.presentation.ui.movielist.adapter.MovieListAdapter
import com.loicz.omdbapi.presentation.ui.movielist.viewmodel.MovieListFragmentViewModel
import kotlinx.android.synthetic.main.fragment_list_movie.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

private const val CHOOSE_FILE_REQUESTCODE = 200

class MovieListFragment : BaseFragment() {

    private val movieListFragmentViewModel: MovieListFragmentViewModel by viewModel()
    private val movieListAdapter: MovieListAdapter by inject()
    private val snackBarComponent: SnackBarComponent by inject()
    private var lastSearch: String? = null

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.setIconifiedByDefault(false)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchData(it)
                } ?: movieListAdapter.clearList()
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.isBlank()) {
                    lastSearch = null
                    movieListAdapter.clearList()
                }
                return false
            }
        })
    }


    override fun layoutId(): Int = R.layout.fragment_list_movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(fragment_list_movie_toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.fragment_movie_list_toolbar_title)
        setHasOptionsMenu(true)

        setUpRecyclerView()
        initiateViewModelObservers()
        setUpViews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CHOOSE_FILE_REQUESTCODE && resultCode == RESULT_OK
            && data != null
            && data.data != null
        ) {
            val filePath = data.data
            filePath?.let { uploadImageToFirebase(fileUri = it) }
        }
    }

    private fun uploadImageToFirebase(fileUri: Uri) {
        val fileName = UUID.randomUUID().toString() + ".jpg"
        val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

        refStorage.putFile(fileUri)
            .addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                    snackBarComponent.showSnackBarSuccess(
                        fragment_list_movie_container,
                        "L'image a été chargée avec succès",
                        requireContext()
                    )
                }
            }
            .addOnFailureListener { e ->
                print(e.message)
                snackBarComponent.showSnackBarError(
                    fragment_list_movie_container,
                    "L'image n'a pas pu être chargée",
                    requireContext()
                )
            }
    }

    private fun setUpViews() {
        om_fragment_movie_list_add_pj.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "*/*"
            val i = Intent.createChooser(intent, "File")
            startActivityForResult(i, CHOOSE_FILE_REQUESTCODE)
        }
    }

    private fun setUpRecyclerView() {
        val onItemClickListener: (String) -> Unit = {
            val direction =
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailActivity(it)
            findNavController().navigate(direction)
        }
        fragment_list_movie_recycler_view.adapter = movieListAdapter
        fragment_list_movie_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        fragment_list_movie_swipe_refresh_layout.setOnRefreshListener {
            lastSearch?.let {
                searchData(it)
            } ?: movieListFragmentViewModel.updateRefresh(false)
        }
        movieListAdapter.onItemClickListener = onItemClickListener

    }

    private fun searchData(search: String) {
        movieListFragmentViewModel.searchMovieListFromTitle(search)
        lastSearch = search
    }

    private fun initiateViewModelObservers() {
        movieListFragmentViewModel.movieList.observeSafe(this) {
            movieListAdapter.updateList(it)
        }

        movieListFragmentViewModel.viewState.observeSafe(this) {
            fragment_list_movie_swipe_refresh_layout.isRefreshing = it.displayRefresh
        }

        movieListFragmentViewModel.errorLiveData.observeSafe(this) {
            it.message?.let { message ->
                view?.let { view ->
                    snackBarComponent.showSnackBarError(
                        view,
                        message,
                        requireContext()
                    )
                }
            }
        }
    }

}