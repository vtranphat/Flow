package com.flowapp.nzchos.flow

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.annotation.Nullable
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_add.*
import com.flowapp.nzchos.flow.R
import com.flowapp.nzchos.flow.R.id.image
import com.flowapp.nzchos.flow.R.styleable.AlertDialog
import com.hendraanggrian.pikasso.picasso
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_tag.*
import org.jetbrains.anko.Android
import java.io.File
import org.jetbrains.anko.support.v4.startActivityForResult
import java.security.Permissions
import java.util.*


class fragmentAdd : Fragment() {


    private val TAG = "PhotoFragment"

    //constant
    private val PHOTO_FRAGMENT_NUM = 1
    private val GALLERY_FRAGMENT_NUM = 2
    private val CAMERA_REQUEST_CODE = 5
    private var imageName : String = ""

    private var mCurrentPhotoPath: String = ""

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        Log.d(TAG, "onCreateView: started.")

        val btnLaunchCamera = view.findViewById(R.id.cameraButton) as Button
        btnLaunchCamera.setOnClickListener(View.OnClickListener {

            validatePermissions()
        })

        return view

    }
    // PERMISSIONS
    private fun validatePermissions() {
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(object: PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        launchCamera()
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?,
                                                                    token: PermissionToken?) {
                        println("Accepter perme")
                       /* AlertDialog.Builder(activity)
                                .setTitle(R.string.storage_permission_rationale_title)
                                .setMessage(R.string.storage_permition_rationale_message)
                                .setNegativeButton(android.R.string.cancel,
                                        { dialog, _ ->
                                            dialog.dismiss()
                                            token?.cancelPermissionRequest()
                                        })
                                .setPositiveButton(android.R.string.ok,
                                        { dialog, _ ->
                                            dialog.dismiss()
                                            token?.continuePermissionRequest()
                                        })
                                .setOnDismissListener({ token?.cancelPermissionRequest() })
                                .show()*/
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        /*Snackbar.make(mainContainer!!,
                                R.string.storage_permission_denied_message,
                                Snackbar.LENGTH_LONG)
                                .show()*/
                    }
                })
                .check()
    }

    private fun processCapturedPhoto() {

        val cursor = activity?.contentResolver?.query(Uri.parse(mCurrentPhotoPath),
                Array(1) {android.provider.MediaStore.Images.ImageColumns.DATA},
                null, null, null)
        cursor?.moveToFirst()
        val photoPath = cursor?.getString(0)
        cursor?.close()
        val file = File(photoPath)
        val uri = Uri.fromFile(file)
        println("SHABAT " + uri)
        // COnvert String ->
        //val bitmap = MediaStore.Images.Media.getBitmap(activity?.getContentResolver(), uri)
        val bitmap = MediaStore.Images.Media.getBitmap(activity?.getContentResolver(), uri)
        photoImageView?.setImageBitmap(bitmap)
        val intent = Intent(activity, TagActivity::class.java)
        intent.putExtra("photoSrc", uri)
        intent.setData(uri)
        startActivity(intent)
        //picasso.load(uri).into(photoImageView)
    }

    //LAUNCH CAMERA
    private fun launchCamera() {
        Log.d(TAG, "onClick: launching camera.")

        Log.d(TAG, "onClick: starting camera")
        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        val fileUri = activity?.contentResolver
                ?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(cameraIntent.resolveActivity(activity?.packageManager) != null) {
            mCurrentPhotoPath = fileUri.toString()
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        }

        //Fresco.initialize(activity?.applicationContext)
    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE)
        {
            /*Log.d(TAG, "onActivityResult: done taking a photo.")
            Log.d(TAG, "onActivityResult: attempting to navigate to final share screen.")
            //navigate to the final share screen to publish photo
            photoImageView?.setImageBitmap(data.extras.get("data") as Bitmap)
            val photoData = data.extras.get("data") as Bitmap
            val intent = Intent(activity, TagActivity::class.java)
            intent.putExtra("photoSrc", photoData)
            startActivity(intent)*/

            processCapturedPhoto()

        }
    }

}