package dev.moataz.flickrim.view


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.moataz.flickrim.viewmodel.MainViewModel
import dev.moataz.flickrim.R
import dev.moataz.flickrim.model.repository.remote.sync.SyncTask
import dev.moataz.flickrim.view.adapter.PhotosAdapter
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var photosAdapter: PhotosAdapter
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            mainViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val action =
           ListFragmentDirections.actionListFragmentToFullScreenkFragment()


        val display = (activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
            .defaultDisplay
        val rotation = display.rotation

        val onCatClicked: (imageUrl: String) -> Unit = { imageUrl ->
            action.imageUrl = imageUrl
            findNavController().navigate(action)
        }

        photosAdapter = PhotosAdapter(onCatClicked)

        initializeList(rotation)
        observeLiveData()



        SyncTask.startSyncTask(mainViewModel)
    }


    private fun observeLiveData() {
        //observe live data emitted by view model
        mainViewModel.getPhotoss().observe(this, Observer {
            photosAdapter.submitList(it)
            list.post {
                swipe.isRefreshing =false

            }

        })



    }

    private fun initializeList(rotation: Int) {
        swipe.setOnRefreshListener{

            mainViewModel.refreshLayout()

        }
        if (rotation == 0 || rotation == 90) {
            list.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        }else {
            list.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

        list.adapter = photosAdapter
    }


    }

