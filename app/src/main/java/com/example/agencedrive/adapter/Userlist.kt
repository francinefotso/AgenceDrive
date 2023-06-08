package com.example.agencedrive.adapter

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agencedrive.R
import com.example.agencedrive.models.Agences
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

lateinit var recyclerView: RecyclerView
lateinit var list: ArrayList<News>
lateinit var DatabaseInfo: DatabaseReference
lateinit var adapter: MyAdapter
class Userlist(contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initializeViews()
    }
    private fun initializeViews(){
        setContentView(R.layout.fragment_home)
        recyclerView = findViewById(R.id.recycleview)
        DatabaseInfo  = FirebaseDatabase.getInstance().getReference("Agences")
        list= ArrayList<News>()
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = adapter

        DatabaseInfo.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(Agences::class.java)
                list.add(user)
//                adapter.notifyDataSetChanget()


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

//    class Post(val author: String, val title: String) {
//        // ...
//    }
//
//    // Get a reference to our posts
//    val database = FirebaseDatabase.getInstance()
//    val ref = database.getReference("server/saving-data/fireblog/posts")
//
//// Attach a listener to read the data at our posts reference
//    ref.addValueEventListener(object : ValueEventListener {
//        override fun onDataChange(dataSnapshot: DataSnapshot) {
//            val post = dataSnapshot.getValue(Post::class.java)
//            println(post)
//        }
//
//        override fun onCancelled(databaseError: DatabaseError) {
//            println("The read failed: " + databaseError.code)
//        }
//    })

}

private fun <E> ArrayList<E>.add(element: Agences?) {

}
