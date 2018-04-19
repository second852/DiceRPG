package com.whc.dicerpg.View;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.opengl.GLUtils;

import com.whc.dicerpg.R;
import com.whc.dicerpg.Util.PicLoadUtil;

import javax.microedition.khronos.opengles.GL10;

public class Constant {
    //遊戲界面其他紋理ID數組
    public static int TEXTUREID_OTHER[];
    //其他圖面數組
    public static Bitmap[] OTHER_ARRAY;

    public static int SCREEN_WIDTH;  //屏幕寬度
    public static int SCREEN_HEIGHT; //屏幕高度

    //加載紋理ID
    public static void loadTextureId(GL10 gl) {
        TEXTUREID_OTHER = new int[OTHER_ARRAY.length];
        for (int i = 0; i < OTHER_ID.length; i++) {
            TEXTUREID_OTHER[i] = initTexture(gl, OTHER_ARRAY[i]);
        }
    }

    //其他貼圖尺寸
    public static float[][] OTHER_SIZE=
            {
                    {40,40},		//0小球
                    {112,56},		//1分數牌
                    {800,480},		//2背景
                    {800,44},		//3天花板
                    {64,64},		//4暫停/繼續
                    {320,120},		//5GameOver
                    {138,120},		//6女孩
                    {56,64},		//7錢袋
                    {128,44},		//8謎之盒
                    {20,40},		//9白色數字
                    {10,20},		//10黃色紫色數字
                    {320,120},		//11繼續否
                    {128,88},		//12滾動謎之盒
                    {68,20},		//13lucky
                    {100,140},		//14綠色數字
                    {800,512},		//15煙花
                    {16,16},		//16煙花炮彈
                    {64,64}			//17煙花小
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
                   "ground.jpeg"                 //0背景
            };

    //其他物體位置
    public static float[][] OTHER_LOCATION =
            {
                    {400, 240},                                        //0背景
                    {56, 112}, {56, 172}, {56, 232}, {56, 292}, {56, 352},    //1~5分數牌
                    {96, 442}, {720, 442},                                //6~7謎之盒
                    {400, 22},                                        //8天花板
                    {37, 37},                                        //9暫停/繼續
                    {763, 34},                                        //10錢袋
                    {719, 140},                                        //11女孩
                    {400, 240},                                        //12繼續否
                    {400, 240},                                        //13遊戲結束
                    {500, 22}, {510, 22}, {520, 22},                        //14~16時間
                    {200, 20}, {210, 20}, {220, 20},                        //17~19得分
                    {560, 22}, {590, 22}, {620, 22}, {650, 22}, {680, 22},    //20~24遊戲幣
                    {470, 250}, {490, 250}, {510, 250},                    //25~27得分
                    {300, 240}, {400, 240}, {500, 240},                    //28~30抽獎信息
                    {400, 240}                                        //31煙花
            };
}
