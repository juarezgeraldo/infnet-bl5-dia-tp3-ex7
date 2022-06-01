package com.juarezjunior.festafimdeano.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    private SharedPreferences mSharedPreferences;
    public SecurityPreferences(Context mContext) {
        this.mSharedPreferences = mContext.getSharedPreferences("FestaFimAno", mContext.MODE_PRIVATE);
    }

    public void storeString(String key, String value){
        this.mSharedPreferences.edit().putString(key, value).apply();
    }
    public String getStoredString(String key){
        return this.mSharedPreferences.getString(key, "");
    }
}
