package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class forgetpassword : AppCompatActivity() {
    private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgetpassword)
        val email = findViewById(R.id.reemail) as EditText
        val btnresetPass = findViewById(R.id.reset) as Button
        val btnback = findViewById(R.id.cancel) as TextView
        btnback!!.setOnClickListener(View.OnClickListener {
            finish()
        })

        auth = FirebaseAuth.getInstance()

        btnresetPass!!.setOnClickListener(View.OnClickListener {
            val email = email!!.text.toString().trim()
            if (TextUtils.isEmpty(email)){
                Toast.makeText(applicationContext,"Enter your email ",Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }


            auth!!.sendPasswordResetEmail(email)
                .addOnCompleteListener(OnCompleteListener {
                        task ->
                    if (task.isSuccessful){
                        Toast.makeText(this,"We have to sent you instraction in your email",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Failed t sent to reset Email",Toast.LENGTH_SHORT ).show()
                    }

                })

        })
    }
}