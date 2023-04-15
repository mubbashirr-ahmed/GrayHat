package com.assesment.grayhat.data.repository

import android.util.Log
import com.assesment.grayhat.data.Model.Items
import com.assesment.grayhat.util.UiState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RepositoryImp(val database: FirebaseDatabase) : IRepository {

    override fun getItems(result: (UiState<List<Items>>)->Unit) {
        val listener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = arrayListOf<Items>()
                for (childSnapshot in snapshot.children) {
                    val user = childSnapshot.getValue(Items::class.java)
                    items.add(user!!)
                }
                 Log.i("sizewwwww",snapshot.childrenCount.toString())
                result.invoke(
                    UiState.Success(items)
                )
            }
            override fun onCancelled(error: DatabaseError) {
                result.invoke(
                    UiState.Failure(
                        error.message
                    )
                )
            }
        }

       FirebaseDatabase.getInstance().reference.child("AllItems").addValueEventListener(listener)
    }
}