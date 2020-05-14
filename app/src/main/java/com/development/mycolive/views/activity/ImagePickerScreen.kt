package com.development.mycolive.views.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.development.mycolive.constant.ApiConstant
import com.nguyenhoanglam.imagepicker.model.Config
import com.nguyenhoanglam.imagepicker.model.Image
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker


class ImagePickerScreen : AppCompatActivity() {
    private var images = ArrayList<Image>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           imagePicK()
    }

    private fun imagePicK(){
        ImagePicker.with(this)
                .setFolderMode(true)
                .setFolderTitle("Album")
                .setRootDirectoryName(Config.ROOT_DIR_DCIM)
                .setDirectoryName("Image Picker")
                .setMultipleMode(true)
                .setShowNumberIndicator(true)
                .setMaxSize(1)
                .setLimitMessage("You can select up to 10 images")
                .setSelectedImages(images)
                .setRequestCode(100)
                .start();
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == Activity.RESULT_OK && data != null) {
            val images: ArrayList<Image> = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES)
            // Do stuff with image's path or id. For example:

            val returnIntent = Intent()
            returnIntent.putExtra(ApiConstant.IMAGE_PICK, images)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
            /*for (image in images) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, image.id.toString())
                    Glide.with(context)
                            .load(uri)
                            .apply(options)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(holder.imageView)
                } else {
                    Glide.with(context)
                            .load(path)
                            .apply(options)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(holder.imageView)
                }
            }*/
        }
        super.onActivityResult(requestCode, resultCode, data)   // This line is REQUIRED in fragment mode
    }
}


