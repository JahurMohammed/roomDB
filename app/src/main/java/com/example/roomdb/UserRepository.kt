package com.example.roomdb

import android.content.Context
import android.os.AsyncTask
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserRepository(context: Context) {

    var db: UserDao = AppDatabase.getInstance(context)?.userDao()!!

    //Fetch All the Users
    fun getAllUsers(): String{
        return db.gelAllUsers()
    }

    // Insert new user
    fun insertUser(users: Users) {
        //insertAsyncTask(db).execute(users)
        insertDb(db,users)
    }

    // update user
    fun updateUser(users: Users) {
        db.updateUser(users)
    }

    // Delete user
    fun deleteUser(users: Users) {
        db.deleteUser(users)
    }

    fun insertDb(usersDao: UserDao,user:Users){
        GlobalScope.launch{
            usersDao.insertUser(user)
        }


    }
}