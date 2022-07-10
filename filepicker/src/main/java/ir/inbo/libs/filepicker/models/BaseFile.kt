package ir.inbo.libs.filepicker.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseFile(open var id: Long = 0,
                    open var name: String,
                    open var path: Uri
) : Parcelable