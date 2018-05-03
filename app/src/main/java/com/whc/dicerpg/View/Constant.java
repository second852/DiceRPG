package com.whc.dicerpg.View;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.opengl.GLUtils;
import android.util.Log;

import com.whc.dicerpg.Model.FireAttack;
import com.whc.dicerpg.Util.PicLoadUtil;
import com.whc.dicerpg.Util.ScreenScaleResult;
import com.whc.dicerpg.Util.ScreenScaleUtil;

import javax.microedition.khronos.opengles.GL10;

public class Constant {


    public static final float RATE = 10;//屏幕到現實世界的比例 10px:1m;
    public static float SCREEN_WIDTH_STANDARD = 800;  //屏幕標準寬度
    public static float SCREEN_HEIGHT_STANDARD = 480;
    public static int SCREEN_WIDTH;  //屏幕寬度
    public static int SCREEN_HEIGHT; //屏幕高度
    public static float RATIO = SCREEN_WIDTH_STANDARD / SCREEN_HEIGHT_STANDARD;//屏幕寬高比


    public static boolean PHYSICS_THREAD_FLAG = true;//物理模擬線程工作標誌位
    public static final float TIME_STEP = 2f / 60.0f;//模擬的的頻率
    public static final int ITERA = 10;//迭代越大，模擬約精確，但性能越低
    public static final int SLEEPTIME = 10;//休眠時間

    //自適應屏幕工具類對象
    public static ScreenScaleResult ssr;
    public static boolean XSFLAG=false;//運行scaleCL()標誌位

    //劍士
    public static int SwordBoy_PIC[];        //劍士紋理ID數組
    public static Bitmap[] SwordBoy_PIC_B;   //劍士圖片數組
    //法師
    public static int MagicGril_PIC[];        //法師紋理ID數組
    public static Bitmap[] MagicGril_PIC_B;   //法師圖片數組
    //石頭
    public static int Stone_PIC[];       //石頭紋理ID數組
    public static Bitmap[] Stone_PIC_B;  //石頭圖片數組
    //刺
    public static int Prick_PIC[];       //刺紋理ID數組
    public static Bitmap[] Prick_PIC_B;  //刺圖片數組
    //門
    public static int Door_PIC[];       //門紋理ID數組
    public static Bitmap[] Door_PIC_B;  //門圖片數組
    //BackGroup
    public static int BackGroup_PIC[];       //背景紋理ID數組
    public static Bitmap[] BackGroup_PIC_B;  //背景圖片數組
    //Ghost
    public static int Ghost_PIC[];       //背景紋理ID數組
    public static Bitmap[] Ghost_PIC_B;  //背景圖片數組
    //Dragon
    public static int Dragon_PIC[];       //背景紋理ID數組
    public static Bitmap[] Dragon_PIC_B;  //背景圖片數組



    //根據自適應屏幕參數對觸摸按鈕範圍變換
    public static void scaleCL()
    {
        if(XSFLAG) return;
        ssr=ScreenScaleUtil.calScale(SCREEN_WIDTH, SCREEN_HEIGHT);
        XSFLAG=true;
    }



    //加載紋理ID
    public static void loadTextureId(GL10 gl) {
        //劍士Bitmap轉成紋理ID
        SwordBoy_PIC=new int[SwordBoy_PIC_ID().length];
        for (int i = 0; i < SwordBoy_PIC_ID().length; i++) {
            SwordBoy_PIC[i] = initTexture(gl, SwordBoy_PIC_B[i]);
        }
        //法師Bitmap轉成紋理ID
        MagicGril_PIC=new int[SwordBoy_PIC_ID().length];
        for (int i = 0; i < SwordBoy_PIC_ID().length; i++) {
            MagicGril_PIC[i] = initTexture(gl, MagicGril_PIC_B[i]);
        }
        //石頭Bitmap轉成紋理ID
        Stone_PIC=new int[Stone_PIC_ID().length];
        for (int i = 0; i < Stone_PIC_ID().length; i++) {
            Stone_PIC[i] = initTexture(gl, Stone_PIC_B[i]);
        }
        //刺Bitmap轉成紋理ID
        Prick_PIC=new int[Stone_PIC_ID().length];
        for (int i = 0; i < Stone_PIC_ID().length; i++) {
            Prick_PIC[i] = initTexture(gl, Prick_PIC_B[i]);
        }
        //門Bitmap轉成紋理ID
        Door_PIC=new int[Door_PIC_ID().length];
        for (int i = 0; i < Door_PIC_ID().length; i++) {
            Door_PIC[i] = initTexture(gl, Door_PIC_B[i]);
        }
        //背景Bitmap轉成紋理ID
        BackGroup_PIC=new int[BackGroup_PIC_ID().length];
        for (int i = 0; i < BackGroup_PIC_ID().length; i++) {
            BackGroup_PIC[i] = initTexture(gl, BackGroup_PIC_B[i]);
        }
        //Ghost Bitmap轉成紋理ID
        Ghost_PIC=new int[Ghost_PIC_ID().length];
        for (int i = 0; i < Ghost_PIC_ID().length; i++) {
            Ghost_PIC[i] = initTexture(gl, Ghost_PIC_B[i]);
        }
        //Dragon Bitmap轉成紋理ID
        Dragon_PIC=new int[Dragon_PIC_ID().length];
        for (int i = 0; i < Dragon_PIC_ID().length; i++) {
            Dragon_PIC[i] = initTexture(gl, Dragon_PIC_B[i]);
        }

    }


    //加載遊戲界面圖片的方法
    public static void loadPic(Resources res) {
        //劍士圖片轉Bitmap
        SwordBoy_PIC_B = new Bitmap[SwordBoy_PIC_ID().length];
        for (int i = 0; i < SwordBoy_PIC_ID().length; i++) {
            SwordBoy_PIC_B[i] = PicLoadUtil.loadBM(res, SwordBoy_PIC_ID()[i]);
        }

        //法師圖片轉Bitmap
        MagicGril_PIC_B=new Bitmap[MagicGirl_PIC_ID().length];
        for (int i = 0; i < MagicGirl_PIC_ID().length; i++) {
            MagicGril_PIC_B[i] = PicLoadUtil.loadBM(res, MagicGirl_PIC_ID()[i]);
        }
        //石頭圖片轉Bitmap
        Stone_PIC_B=new Bitmap[Stone_PIC_ID().length];
        for (int i = 0; i < Stone_PIC_ID().length; i++) {
            Stone_PIC_B[i] = PicLoadUtil.loadBM(res,Stone_PIC_ID()[i]);
        }
        //刺圖片轉Bitmap
        Prick_PIC_B=new Bitmap[Prick_PIC_ID().length];
        for (int i = 0; i < Prick_PIC_ID().length; i++) {
            Prick_PIC_B[i] = PicLoadUtil.loadBM(res, Prick_PIC_ID()[i]);
        }
        //門圖片轉Bitmap
        Door_PIC_B=new Bitmap[Door_PIC_ID().length];
        for (int i = 0; i < Door_PIC_ID().length; i++) {
            Door_PIC_B[i] = PicLoadUtil.loadBM(res, Door_PIC_ID()[i]);
        }
        //背景圖片轉Bitmap
        BackGroup_PIC_B=new Bitmap[BackGroup_PIC_ID().length];
        for (int i = 0; i < BackGroup_PIC_ID().length; i++) {
            BackGroup_PIC_B[i] = PicLoadUtil.loadBM(res, BackGroup_PIC_ID()[i]);
        }
        //Ghost圖片轉Bitmap
        Ghost_PIC_B=new Bitmap[Ghost_PIC_ID().length];
        for (int i = 0; i < Ghost_PIC_ID().length; i++) {
            Ghost_PIC_B[i] = PicLoadUtil.loadBM(res, Ghost_PIC_ID()[i]);
        }
        //Dragon圖片轉Bitmap
        Dragon_PIC_B=new Bitmap[Dragon_PIC_ID().length];
        for (int i = 0; i < Dragon_PIC_ID().length; i++) {
            Dragon_PIC_B[i] = PicLoadUtil.loadBM(res, Dragon_PIC_ID()[i]);
        }

    }

    //加載紋理ID
    public static int initTexture(GL10 gl, Bitmap bitmapTmp) {
        int textureId;
        //生成紋理ID
        int[] textures = new int[1];
        gl.glGenTextures
                (
                        1,          //產生的紋理id的數量
                        textures,   //紋理id的數組
                        0           //偏移量
                );
        textureId = textures[0];
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);//貼圖過多時，使用壓縮函數進行縮小
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);//如果貼圖小的話，那我們需要使用放大函數進行放大操作
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
        GLUtils.texImage2D              //指定紋理
                (
                        GL10.GL_TEXTURE_2D,   //紋理類型，在OpenGL ES中必須為GL10.GL_TEXTURE_2D
                        0,                      //紋理的層次，0表示基本圖像層，可以理解為直接貼圖
                        bitmapTmp,              //紋理圖像
                        0                      //紋理邊框尺寸
                );
        bitmapTmp.recycle();          //紋理加載成功後釋放圖片
        return textureId;              //返迴紋理ID
    }


    //劍士圖片ID
    public static String[] SwordBoy_PIC_ID() {
        int length=12;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "SwordBoy" + i + ".png";
        }
        return s;
    }
    //法師圖片ID
    public static String[] MagicGirl_PIC_ID() {
        int length=12;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "MagicGirl" + i + ".png";
        }
        return s;
    }
    //石頭圖片ID
    public static String[] Stone_PIC_ID() {
        int length=4;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "Stone" + i + ".png";
        }
        return s;
    }
    //刺圖片ID
    public static String[] Prick_PIC_ID() {
        int length=4;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "Prick" + i + ".png";
        }
        return s;
    }
    //門的圖片ID
    public static String[] Door_PIC_ID() {
        int length=4;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "Door" + i + ".png";
        }
        return s;
    }
    //背景的圖片ID
    public static String[] BackGroup_PIC_ID() {
        int length=2;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "BackGroup" + i + ".png";
        }
        return s;
    }
    //Ghost 的圖片ID
    public static String[] Ghost_PIC_ID() {
        int length=4;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "Ghost" + i + ".png";
        }
        return s;
    }

    //Dragon的圖片ID
    public static String[] Dragon_PIC_ID() {
        int length=12;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "Dragon" + i + ".png";
        }
        return s;
    }

    public static float[][] Object_Size={
            {800,420}//背景

    };


    public static float[][] Object_Location={
            {400,200}//背景

    };

}
