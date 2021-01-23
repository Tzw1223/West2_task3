package com.example.groupchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        send_button.setOnClickListener{
            val intent = Intent()
            val nameG = edit_group.text.toString()
            val nameC = edit_creator.text.toString()
            intent.putExtra("name_g",nameG)
            intent.putExtra("name_c",nameC)
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}