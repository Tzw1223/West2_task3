package com.example.groupchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.group_item.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val img_id = intent.getIntExtra("picture",0)
        val name_g = intent.getStringExtra("name_g")
        val name_c = intent.getStringExtra("name_c")
        Log.d("DetailActivity","id_ is $img_id")
        groupImage.setImageResource(img_id)
        groupName.text = name_g
        creatorName.text = name_c
    }
}