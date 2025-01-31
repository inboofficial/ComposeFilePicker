package ir.inbo.libs.filepicker.fragments

import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment : Fragment() {

    open val uiScope = CoroutineScope(Dispatchers.Main)

    companion object {

        val FILE_TYPE = "FILE_TYPE"
    }
}// Required empty public constructor
