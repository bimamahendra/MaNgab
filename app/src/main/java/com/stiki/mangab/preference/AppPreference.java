package com.stiki.mangab.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.stiki.mangab.model.User;

public class AppPreference {
    static final String PREF = "PREF";
    static final String USER_PREF = "USER_PREF";

    public static void saveUser(Context context, User user){
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit().putString(USER_PREF, new Gson().toJson(user)).apply();
    }

    public static User getUser(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        if(pref.contains(USER_PREF)){
            Gson gson = new Gson();

            return gson.fromJson(pref.getString(USER_PREF, ""), User.class);
        }

        return null;
    }

    public static void removeUser(Context context){
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit().remove(USER_PREF).apply();
    }
}
