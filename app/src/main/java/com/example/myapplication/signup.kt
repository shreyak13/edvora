package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class signup : AppCompatActivity() {
    private var auth:FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val inputEmail = findViewById(R.id.signInemail) as EditText
        val inputPassword = findViewById(R.id.signInpassword) as EditText

      val button:Button=findViewById(R.id.button)
      //  button.setOnClickListener({
         //   val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.in/"))
           // startActivity(i)
        //})

        val image=findViewById(R.id.imageButton) as ImageView
         auth = FirebaseAuth.getInstance()
        image.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.figma.com/"))
            startActivity(i)
        }

        val saml=findViewById(R.id.samlsso) as TextView
        saml.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.figma.com/login"))
            startActivity(i)
        }

        val create=findViewById<TextView>(R.id.createOne)
        create.setOnClickListener {
            val intent = Intent(this, signIn::class.java)
            startActivity(intent)
        }
        val privacy=findViewById(R.id.privacyPolicy) as TextView
        privacy.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://policies.google.com/privacy"))
            startActivity(i)
        }
        val terms=findViewById(R.id.terms) as TextView
        terms.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://policies.google.com/terms"))
            startActivity(i)
        }
        val forgetPassword=findViewById<TextView>(R.id.forgetPassword)
        forgetPassword.setOnClickListener {
            val intent = Intent(this, forgetpassword::class.java)
            startActivity(intent)
        }



        button!!.setOnClickListener(View.OnClickListener {
            val email = inputEmail!!.text.toString().trim()
            val password = inputPassword!!.text.toString().trim()

            if (TextUtils.isEmpty(email)){
                Toast.makeText(applicationContext,"Please Entre your email.",Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Please Enter your Password", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }


            auth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener {
                        task ->

                    if (!task.isSuccessful){
                        if (password.length < 6){
                            inputPassword!!.setError(getString(R.string.minimum_password))
                        }else{
                            Toast.makeText(this,getString(R.string.auth_failed),Toast.LENGTH_LONG).show()
                        }
                    }else{
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                })
        })

    }

    }
