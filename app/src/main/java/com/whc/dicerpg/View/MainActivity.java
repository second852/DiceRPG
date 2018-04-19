package com.whc.dicerpg.View;

import android.app.Service;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    AudioManager audio;//遊戲中控制音量工具對象
    FirstOneView fo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //設置為全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        //設置為橫屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //遊戲過程中只允許調整多媒體音量，而不允許調整通話音量
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        audio=(AudioManager) getSystemService(Service.AUDIO_SERVICE);

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
        fo=new FirstOneView(this);//創建遊戲界面對象
        setContentView(fo);//跳轉到遊戲界面
    }
}
