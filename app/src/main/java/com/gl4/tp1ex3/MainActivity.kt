package com.gl4.tp1ex3

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var btnLoad : Button
    private val REQUEST_CODE = 123

    private fun openGallery() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.custum_dialog, null)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        val Photos = dialogView.findViewById<ImageView>(R.id.imgPhotos)
        val Gallery = dialogView.findViewById<ImageView>(R.id.imgGallery)
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(dialogView)

        val dialog = alertDialogBuilder.create()


        btnCancel.setOnClickListener {

            dialog.dismiss()
        }
        Photos.setOnClickListener {

            dialog.dismiss()

            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_CODE)
        }
        Gallery.setOnClickListener {

            dialog.dismiss()


            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_CODE)
        }



        dialog.show()
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            val uriImage = data?.data
            imageView.setImageURI(uriImage)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLoad = findViewById(R.id.btnload)
        imageView = findViewById(R.id.imageView)
        btnLoad.setOnClickListener { view ->
            openGallery()
        }

    }
}