package com.example.hungrytest;

import cn.bmob.v3.listener.SaveListener;
import model.Comment;
import Fragments.FragmentUserSetting;
import android.R.anim;
import android.app.Activity;
import android.support.v4.app.*;
import android.os.Bundle;
import android.view.Window;


public class UserOpera extends FragmentActivity {
   
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        FragmentUserSetting mFragmentUserSetting=new FragmentUserSetting();
        this.getSupportFragmentManager().beginTransaction().add(android.R.id.content, mFragmentUserSetting).commit();
	

        
        
        
        
}}
