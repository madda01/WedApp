package com.example.dreamwedmadd;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dreamwedmadd.models.User;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String shared_pref_name= "Session";
    String session_key="session_user";

    public SessionManagement(Context context){
        sharedPreferences=context.getSharedPreferences(shared_pref_name,Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();
    }

    public void saveSession(User user){
        //save session when user log in
        String id = user.getEmail();
        editor.putString(session_key, id).commit();
    }

    public int getSession(){
        //return use whose session is saved
        return sharedPreferences.getInt(session_key,-1);
    }

    public void removeSession(){
        editor.putInt(session_key,-1).commit();
    }
}
