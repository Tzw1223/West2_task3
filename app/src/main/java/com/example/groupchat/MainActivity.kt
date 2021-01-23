package com.example.groupchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class Group(val nameG: String, val nameC: String, val imageId: Int)
class GroupAdapter(val groupList: MutableList<Group>):
        RecyclerView.Adapter<GroupAdapter.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val groupImage: ImageView = view.findViewById(R.id.groupImage)
        val groupName: TextView = view.findViewById(R.id.groupName)
        val creatorName: TextView = view.findViewById(R.id.creatorName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.group_item,parent,false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener{

            val position = viewHolder.adapterPosition
            val group = groupList[position]
            val intent = Intent(view.context, DetailActivity::class.java)

            Log.d("GroupAdapter","id is ${group.imageId}")
            intent.putExtra("picture",group.imageId)
            intent.putExtra("name_g",group.nameG)
            intent.putExtra("name_c",group.nameC)
            view.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val group = groupList[position]
        holder.groupImage.setImageResource(group.imageId)
        holder.groupName.text = group.nameG
        holder.creatorName.text = group.nameC
    }

    override fun getItemCount() = groupList.size
}
class MainActivity : AppCompatActivity() {
    private val groupList = ArrayList<Group>()
    private val adapter : GroupAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initGroups()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = GroupAdapter(groupList)
        recyclerView.adapter = adapter
    }

    private fun initGroups(){
        repeat(2){
            groupList.add(Group("123","张三",R.drawable.img_1))
            groupList.add(Group("456","李四",R.drawable.img_2))
            groupList.add(Group("789","王五",R.drawable.img_3))
            groupList.add(Group("147","熊大",R.drawable.img_4))
            groupList.add(Group("158","熊二",R.drawable.img_5))
        }
    }
    private fun deleteGroups(){
        groupList.clear()
        val adapter = GroupAdapter(groupList)
        recyclerView.adapter = adapter
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> if(resultCode == RESULT_OK){
                val nameg = data?.getStringExtra("name_g").toString()
                val namec = data?.getStringExtra("name_c").toString()
                if(nameg.isNotEmpty() && namec.isNotEmpty()){
                    groupList.add(0,Group(nameg,namec,R.drawable.img_1)) //默认头像为img_1
                    val adapter = GroupAdapter(groupList)
                    recyclerView.adapter = adapter
                    adapter?.notifyDataSetChanged()
                    recyclerView.scrollToPosition(0)
                }
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item -> {
                val intent = Intent(this, AddActivity::class.java)
                startActivityForResult(intent, 1)
            }
            R.id.delete_item -> deleteGroups()
            R.id.exit_item -> finish()
        }
        return true
    }
}