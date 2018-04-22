package com.whc.dicerpg.View;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.opengl.GLUtils;
import com.whc.dicerpg.Util.PicLoadUtil;
import com.whc.dicerpg.Util.ScreenScaleResult;
import com.whc.dicerpg.Util.ScreenScaleUtil;

import javax.microedition.khronos.opengles.GL10;

public class Constant {
    //遊戲界面其他紋理ID數組
    public static int TEXTUREID_OTHER[];
    //其他圖面數組
    public static Bitmap[] OTHER_ARRAY;
    public static float SCREEN_WIDTH_STANDARD=800;  //屏幕標準寬度
    public static float SCREEN_HEIGHT_STANDARD=480;
    public static int SCREEN_WIDTH;  //屏幕寬度
    public static int SCREEN_HEIGHT; //屏幕高度
    public static float RATIO=SCREEN_WIDTH_STANDARD/SCREEN_HEIGHT_STANDARD;//屏幕寬高比
    public static boolean XSFLAG=false;//運行scaleCL()標誌位
    //自適應屏幕工具類對象
    public static ScreenScaleResult ssr;

    //加載紋理ID
    public static void loadTextureId(GL10 gl) {
        TEXTUREID_OTHER = new int[OTHER_ARRAY.length];
        for (int i = 0; i < OTHER_ID.length; i++) {
            TEXTUREID_OTHER[i] = initTexture(gl, OTHER_ARRAY[i]);
        }
    }

    public static void scaleCL()
    {
        if(XSFLAG) return;
        ssr= ScreenScaleUtil.calScale(SCREEN_WIDTH, SCREEN_HEIGHT);
        XSFLAG=true;
    }

    //其他貼圖尺寸
    public static float[][] OTHER_SIZE=
            {
                    {64,64},		//0小球
                    {64,64},		//1分數牌
                    {800,480},		//2背景
            };

    //加載遊戲界面圖片的方法
    public static void loadPic(Resources res) {

        OTHER_ARRAY = new Bitmap[OTHER_ID.length];
        for (int i = 0; i < OTHER_ID.length; i++) {
            OTHER_ARRAY[i] = PicLoadUtil.loadBM(res, OTHER_ID[i]);
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


    //遊戲界面其他圖片ID
    public static String[] OTHER_ID =
            {
                   "ground.jpeg",                 //0背景
                    "man.png",                    //1主角
                    "monster.jpeg"                 //2怪物
            };

    //其他物體位置
    public static float[][] OTHER_LOCATION =
            {
                    {400, 240},   //0背景
                    {100,400},    //man
                    {700,400}
            };
}
