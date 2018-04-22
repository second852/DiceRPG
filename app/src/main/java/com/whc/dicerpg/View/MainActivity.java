package com.whc.dicerpg.View;


import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {
    AudioManager audio;
    FirstOneView fo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //設置為全屏
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        //設置為橫屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //獲取屏幕尺寸
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        if(dm.widthPixels>dm.heightPixels)
        {
            Constant.SCREEN_WIDTH=dm.widthPixels;
            Constant.SCREEN_HEIGHT=dm.heightPixels;
        }
        else
        {
            Constant.SCREEN_WIDTH=dm.heightPixels;
            Constant.SCREEN_HEIGHT=dm.widthPixels;
        }
        Constant.scaleCL();//自適應屏幕方法
        fo=new FirstOneView(this);//創建遊戲界面對象
        setContentView(fo);//跳轉到遊戲界面
    }
}
