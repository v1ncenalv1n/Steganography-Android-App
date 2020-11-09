package com.mister.steganography.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.ayush.imagesteganographylibrary.Text.AsyncTaskCallback.TextDecodingCallback
import com.ayush.imagesteganographylibrary.Text.ImageSteganography
import com.ayush.imagesteganographylibrary.Text.TextDecoding

import com.mister.steganography.R
import kotlinx.android.synthetic.main.fragment_decode.*
import java.io.IOException

class DecodeFragment : Fragment(), TextDecodingCallback, View.OnClickListener {
    private val SELECT_PICTURE = 100
    private var pathfile: Uri? = null
    private var original_image: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_decode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_openimage_decode).setOnClickListener(this)
        view.findViewById<Button>(R.id.button_decode).setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.button_openimage_decode->{
                image_chooser()
            }
            R.id.button_decode->{
                if(pathfile!=null){
                    if(edittext_key_decode.text!=null){
                        val imagesteganographydecode = ImageSteganography(edittext_key_decode.text.toString(),original_image)
                        val textDecoding =  TextDecoding(activity, this)
                        textDecoding.execute(imagesteganographydecode)
                    }
                    else{
                        Toast.makeText(activity,"Masukan Secret Key", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(activity,"Masukan Gambar", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === SELECT_PICTURE && resultCode === Activity.RESULT_OK && android.R.attr.data != null) {
            pathfile = data?.data
            try {
                original_image = MediaStore.Images.Media.getBitmap(activity?.contentResolver,pathfile!!)
                image_decode.setImageBitmap(original_image)
            } catch (e: IOException) {
                Log.d("MainActivity", "Error : $e")
            }
        }
    }

    private fun image_chooser() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE)
    }

    override fun onStartTextEncoding() {
        TODO("Not yet implemented")
    }

    override fun onCompleteTextEncoding(p0: ImageSteganography?) {
        if(p0!=null && p0.isDecoded) {
            if (!p0.isSecretKeyWrong){
                edittext_message_decode.setText(p0.message)
                Toast.makeText(activity,"Decoded", Toast.LENGTH_SHORT).show()
            }
            else{
                edittext_message_decode.setText("")
                Toast.makeText(activity,"Secret Key Wrong",Toast.LENGTH_SHORT).show()
            }
        }
        else{
            edittext_message_decode.setText("")
            Toast.makeText(activity,"Not Decode",Toast.LENGTH_SHORT).show()
        }
    }
}
