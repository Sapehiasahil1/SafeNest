package com.example.safenest

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.safenest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val permission = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.READ_CONTACTS
    )

    val permissionCode = 78
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()

        val currentUser = FirebaseAuth.getInstance().currentUser
        val name = currentUser?.displayName.toString()
        val email = currentUser?.email.toString()
        val phoneNumber = currentUser?.toString()
        val imageUrl = currentUser?.photoUrl.toString()

        val db = Firebase.firestore

        val user = hashMapOf(
            "name" to name,
            "email" to email,
            "phoneNumber" to phoneNumber,
            "imageUrl" to imageUrl
        )

        db.collection("users").document(email).set(user).addOnSuccessListener {  }.addOnFailureListener {  }


        askForPermission()

        binding.bottomBar.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.nav_guard -> {
                    inflateFragment(GuardFragment.newInstance())
                }
                R.id.nav_home -> {
                    inflateFragment(HomeFragment.newInstance())
                }
                R.id.nav_dashboard -> {
                    inflateFragment(MapsFragment())
                }
                R.id.nav_profile -> {
                    inflateFragment(ProfileFragment.newInstance())
                }
            }

            true
        }

        binding.bottomBar.selectedItemId = R.id.nav_home
    }

    private fun askForPermission() {

        ActivityCompat.requestPermissions(this, permission, permissionCode)
    }


    private fun inflateFragment(newInstance: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newInstance)
        transaction.commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == permissionCode) {
            
            if(allPermissionGranted()){
//                openCamera()
            }else{
                Toast.makeText(this, "Accept the Permissions", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openCamera() {
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivity(intent)
    }

    private fun allPermissionGranted(): Boolean {
        for (item in permission) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    item
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }

        return true
    }
}