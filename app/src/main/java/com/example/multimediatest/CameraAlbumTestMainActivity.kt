package com.example.multimediatest

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.activitytest.databinding.ActivityCameraAlbumTestMainBinding
import java.io.File
import java.io.IOException

class CameraAlbumTestMainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "CameraAlbumTestMainAct"
        val Factory = viewModelFactory {
            initializer {
                CameraViewModel(createSavedStateHandle())
            }
        }
    }

    private lateinit var imageUri: Uri
    private lateinit var outputImage: File
    private lateinit var binding: ActivityCameraAlbumTestMainBinding
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var cameraViewModel: CameraViewModel
    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCameraAlbumTestMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the ViewModel
        cameraViewModel = ViewModelProvider(this, Factory)[CameraViewModel::class.java]

        // Restore the bitmap if it exists
        cameraViewModel.currentBitmap?.let {
            binding.imageView.setImageBitmap(it)
        } ?: run {
            //Handle null in the viewModel
            Log.w(TAG, "The currentBitmap is null in the viewModel")
        }

        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            isGranted:  Boolean ->
            if (isGranted) {
                takePhoto()
            } else {
                // Handle the case where the user denies the permission
                Log.e(TAG, "Camera permission denied")
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }

        cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult -> onCameraResult(result)
        }

        binding.takePhotoBtn.setOnClickListener {
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)  == PackageManager.PERMISSION_GRANTED -> {
                takePhoto()
            }

            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) -> {
                // Show an explanation to the user *asynchronously*
                // After the user sees the explanation, try again to request the permission.
                Log.e(TAG, "Camera permission explanation needed")
            } else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun takePhoto() {
        try {
            // Create File object to store the image
            outputImage = File(externalCacheDir, "output_image.jpg")
            if (outputImage.exists()) {
                outputImage.delete()
            }
            outputImage.createNewFile()
            imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(this, "com.example.multimediatest.fileprovider", outputImage)
            } else {
                Uri.fromFile(outputImage)
            }

            // Start Camera app
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            }
//            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            cameraLauncher.launch(intent)
        } catch (e: IOException) {
            Log.e(TAG, "Error creating image file", e)
            Toast.makeText(this, "Error creating image file", Toast.LENGTH_SHORT).show()
        }
    }
    /*@Deprecated("Deprecated in Java")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    // Display the photo
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                    bitmap?.let {
                        val rotatedBitmap = rotateIfRequired(it)
                        cameraViewModel.currentBitmap = rotatedBitmap
                        binding.imageView.setImageBitmap(rotatedBitmap)
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error decoding or displaying image", e)
                }
            }  else {
                Log.e(TAG, "Error taking photo")
            }
        }
    }*/
    private fun onCameraResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                // Display the photo
                val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                if (bitmap != null) {
                    val rotatedBitmap = rotateIfRequired(bitmap)
                    if(rotatedBitmap!=null) {
                        cameraViewModel.currentBitmap = rotatedBitmap
                        binding.imageView.setImageBitmap(rotatedBitmap)
                    }else{
                        Log.e(TAG, "Error rotating or using the image")
                        Toast.makeText(this, "Error rotating or using the image", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e(TAG, "Error decoding image. Bitmap is null.")
                    Toast.makeText(this, "Error decoding image", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error decoding or displaying image", e)
                Toast.makeText(this, "Error decoding or displaying image", Toast.LENGTH_SHORT).show()
            }
        } else {
            Log.e(TAG, "Error taking photo. Result code: ${result.resultCode}")
            Toast.makeText(this, "Error taking photo", Toast.LENGTH_SHORT).show()
        }
    }


    private fun rotateIfRequired(bitmap: Bitmap): Bitmap? {

        try {
            val exif = ExifInterface(outputImage.path)
            val orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )

            return when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
                else -> bitmap
            }
        } catch (e: IOException) {
            Log.e(TAG, "Error rotating image", e)
            return null
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap = Bitmap.createBitmap(bitmap, 0,0, bitmap.width, bitmap.height, matrix, true)
        bitmap.recycle() // Recycle the bitmap object
        return rotatedBitmap
    }
}