package ir.inbo.libs.filepicker.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
class FileType constructor(
        var title: String,
        var extensions : Array<String>,
        @DrawableRes
        var drawable: Int
) : Parcelable