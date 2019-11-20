package dev.moataz.flickrim.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import dev.moataz.flickrim.R
import kotlinx.android.synthetic.main.fragment_full_screenk.*

class FullScreenkFragment : Fragment() {

    private lateinit var image: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_full_screenk, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            image = FullScreenkFragmentArgs.fromBundle(it).imageUrl?:""
            Picasso.get().load(image).placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder).fit().centerInside().into(full_image)
        }


    }


}
