package com.example.deswita.service

import android.content.AsyncTaskLoader
import android.content.Context
import com.example.deswita.models.User
import com.google.firebase.database.*

class LoginLoader(context: Context?, var username: String, var password: String) : AsyncTaskLoader<User?>(context!!){
    private var ref: DatabaseReference = FirebaseDatabase.getInstance("https://deswita-c5da3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("USER")
    override fun loadInBackground(): User? {
        var userResult : User? = null
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    snapshot.children.forEach { data ->
                        val user = data.getValue(User::class.java)
                        if(user?.username ==  username && user.password == password){
                            userResult = user
                            return
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
        return userResult
    }

    override fun deliverResult(user: User?) {
        if (isStarted) {
            super.deliverResult(user)
        }
    }

    override fun onStartLoading() {
        forceLoad()
    }

    override fun onStopLoading() {
        cancelLoad()
    }

    override fun onReset() {
        super.onReset()
        onStopLoading()
    }
}