package ir.inbo.libs.filepicker.fragments

interface PhotoPickerFragmentListener {
    fun onItemSelected()
    fun setToolbarTitle(count: Int)
}