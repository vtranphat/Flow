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
import android.net.Uri
import android.os.Bundle
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
import com.flowapp.nzchos.flow.R.styleable.AlertDialog
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import org.jetbrains.anko.Android
import java.io.File
import org.jetbrains.anko.support.v4.startActivityForResult
import java.security.Permissions


class fragmentAdd : Fragment() {


    private val TAG = "PhotoFragment"

    //constant
    private val PHOTO_FRAGMENT_NUM = 1
    private val GALLERY_FRAGMENT_NUM = 2
    private val CAMERA_REQUEST_CODE = 5

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        Log.d(TAG, "onCreateView: started.")

        val btnLaunchCamera = view.findViewById(R.id.cameraButton) as Button
        btnLaunchCamera.setOnClickListener(View.OnClickListener {
            Log.d(TAG, "onClick: launching camera.")

            Log.d(TAG, "onClick: starting camera")
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)


        })

        return view

    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE)
        {
            Log.d(TAG, "onActivityResult: done taking a photo.")
            Log.d(TAG, "onActivityResult: attempting to navigate to final share screen.")
            //navigate to the final share screen to publish photo
            photoImageView.setImageBitmap(data.extras.get("data") as Bitmap)

        }
    }
























/**
    val CAMERA_REQUEST_CODE = 0
    //var packageManager: PackageManager? = context?.getPackageManager()
    //var packageManager = context?.getPackageManager()
    var packageManager: PackageManager? = getActivity()?.getPackageManager() ?: null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println(packageManager)
        cameraButton.setOnClickListener {
            println("dfsjhskh")
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (callCameraIntent.resolveActivity(packageManager) !=null) {
                startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                if(resultCode == Activity.RESULT_OK && data != null) {
                    photoImageView.setImageBitmap(data.extras.get("data") as Bitmap)
                }
            }
            else -> {
                Toast.makeText(getActivity(), "Unrecognized request code", Toast.LENGTH_LONG).show()
            }
        }
    }
    **/

}