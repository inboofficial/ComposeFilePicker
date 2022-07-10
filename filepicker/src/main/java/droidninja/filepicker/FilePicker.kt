package droidninja.filepicker

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.IntegerRes
import droidninja.filepicker.models.FileType
import droidninja.filepicker.models.sort.SortingTypes
import java.util.ArrayList

/**
 * Created by droidNinja on 29/07/16.
 */
class FilePicker private constructor(
    private val activityResultLauncher: ActivityResultLauncher<Intent>
) {


    private val mPickerOptionsBundle: Bundle = Bundle()

    fun setImageSizeLimit(fileSize: Int): FilePicker {
        PickerManager.imageFileSize = fileSize
        return this
    }

    fun setVideoSizeLimit(fileSize: Int): FilePicker {
        PickerManager.videoFileSize = fileSize
        return this
    }

    fun setMaxCount(maxCount: Int): FilePicker {
        PickerManager.setMaxCount(maxCount)
        return this
    }

    fun setActivityTheme(theme: Int): FilePicker {
        PickerManager.theme = theme
        return this
    }

    fun setActivityTitle(title: String): FilePicker {
        PickerManager.title = title
        return this
    }

    /**
     * @param spanType it could be [FilePickerConst.SPAN_TYPE.FOLDER_SPAN] (for folder screen)
     * or [FilePickerConst.SPAN_TYPE.DETAIL_SPAN] (for details screen)
     * @param count span count in integer, defaults for Folder is 2 and Details is 3
     */
    fun setSpan(spanType: FilePickerConst.SPAN_TYPE, count: Int): FilePicker {
        PickerManager.spanTypes[spanType] = count
        return this
    }

    fun setSelectedFiles(selectedPhotos: ArrayList<Uri>): FilePicker {
        mPickerOptionsBundle.putParcelableArrayList(
            FilePickerConst.KEY_SELECTED_MEDIA,
            selectedPhotos
        )
        return this
    }

    fun enableVideoPicker(status: Boolean): FilePicker {
        PickerManager.setShowVideos(status)
        return this
    }

    fun enableImagePicker(status: Boolean): FilePicker {
        PickerManager.setShowImages(status)
        return this
    }

    fun enableSelectAll(status: Boolean): FilePicker {
        PickerManager.enableSelectAll(status)
        return this
    }

    fun setCameraPlaceholder(@DrawableRes drawable: Int): FilePicker {
        PickerManager.cameraDrawable = drawable
        return this
    }

    fun showGifs(status: Boolean): FilePicker {
        PickerManager.isShowGif = status
        return this
    }

    fun showFolderView(status: Boolean): FilePicker {
        PickerManager.isShowFolderView = status
        return this
    }

    fun enableDocSupport(status: Boolean): FilePicker {
        PickerManager.isDocSupport = status
        return this
    }

    fun enableCameraSupport(status: Boolean): FilePicker {
        PickerManager.isEnableCamera = status
        return this
    }


    fun withOrientation(@IntegerRes orientation: Int): FilePicker {
        PickerManager.orientation = orientation
        return this
    }

    @JvmOverloads
    fun addFileSupport(
        title: String, extensions: Array<String>,
        @DrawableRes drawable: Int = R.drawable.icon_file_unknown
    ): FilePicker {
        PickerManager.addFileType(FileType(title, extensions, drawable))
        return this
    }

    fun sortDocumentsBy(type: SortingTypes): FilePicker {
        PickerManager.sortingType = type
        return this
    }

    fun pickPhoto(context: Activity) {
        mPickerOptionsBundle.putInt(FilePickerConst.EXTRA_PICKER_TYPE, FilePickerConst.MEDIA_PICKER)
        start(context, activityResultLauncher)
    }

    fun pickPhoto(context: Fragment) {
        mPickerOptionsBundle.putInt(FilePickerConst.EXTRA_PICKER_TYPE, FilePickerConst.MEDIA_PICKER)
        start(context, activityResultLauncher)
    }

    fun pickFile(context: Activity) {
        mPickerOptionsBundle.putInt(FilePickerConst.EXTRA_PICKER_TYPE, FilePickerConst.DOC_PICKER)
        start(context, activityResultLauncher)
    }

    fun pickFile(context: Fragment) {
        mPickerOptionsBundle.putInt(FilePickerConst.EXTRA_PICKER_TYPE, FilePickerConst.DOC_PICKER)
        start(context, activityResultLauncher)
    }

    fun build(pickerType: Int, context: Activity): Intent {
        val intent = Intent(context, FilePickerActivity::class.java)
        mPickerOptionsBundle.putInt(FilePickerConst.EXTRA_PICKER_TYPE, pickerType)
        intent.putExtras(mPickerOptionsBundle)
        return intent
    }

    private fun start(context: Activity, activityResultLauncher: ActivityResultLauncher<Intent>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    FilePickerConst.PERMISSIONS_FILE_PICKER
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.permission_filepicker_rationale),
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
        }

        val intent = Intent(context, FilePickerActivity::class.java)
        intent.putExtras(mPickerOptionsBundle)

        activityResultLauncher.launch(intent)
    }

    private fun start(fragment: Fragment, activityResultLauncher: ActivityResultLauncher<Intent>) {
        fragment.context?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(
                        it,
                        FilePickerConst.PERMISSIONS_FILE_PICKER
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(
                        fragment.context, it
                            .resources
                            .getString(R.string.permission_filepicker_rationale), Toast.LENGTH_SHORT
                    ).show()
                    return
                }
            }

            val intent = Intent(fragment.activity, FilePickerActivity::class.java)
            intent.putExtras(mPickerOptionsBundle)

            activityResultLauncher.launch(intent)
        }
    }

    companion object {
        @JvmStatic
        fun builder(
            activityResultLauncher: ActivityResultLauncher<Intent>
        ) = FilePicker(activityResultLauncher)
    }
}
