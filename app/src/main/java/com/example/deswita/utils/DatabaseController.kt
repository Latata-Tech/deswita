package com.example.deswita.utils

import android.content.Context
import android.widget.Toast
import com.example.deswita.models.User
import com.google.firebase.database.*

class DatabaseController (activityContext: Context) {
    private var ref: DatabaseReference = FirebaseDatabase.getInstance("https://deswita-c5da3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("USER")
    private var context = activityContext

    fun saveUser(name: String, username: String, password: String) {
        val userID = ref.push().key.toString()
        val userData = User(userID,name, username, password)

        ref.child(userID).setValue(userData).apply {
            addOnCompleteListener {
                Toast.makeText(context, "Success Register", Toast.LENGTH_SHORT).show()
            }
            addOnFailureListener {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getUser(username: String, password: String) : Boolean {
        var result: Boolean = false
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    snapshot.children.forEach { data ->
                        val user = data.getValue(User::class.java)
                        if(user?.username == username && user?.password == password){
                            result = true
                            return
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
        Thread.sleep(1000L)
        return result
    }

}