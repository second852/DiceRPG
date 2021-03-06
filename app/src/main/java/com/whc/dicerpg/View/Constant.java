package com.whc.dicerpg.View;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
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

    public static boolean StoneMove = true;
    public static boolean GhostMove = true;
    public static boolean DragonMove = true;

    public static boolean PHYSICS_THREAD_FLAG = true;//物理模擬線程工作標誌位
    public static final float TIME_STEP = 2f / 60.0f;//模擬的的頻率
    public static final int ITERA = 10;//迭代越大，模擬約精確，但性能越低
    public static final int SLEEPTIME = 10;//休眠時間

    //自適應屏幕工具類對象
    public static ScreenScaleResult ssr;
    public static boolean XSFLAG = false;//運行scaleCL()標誌位

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
    //Treasure
    public static int Treasure_PIC[];       //背景紋理ID數組
    public static Bitmap[] Treasure_PIC_B;  //背景圖片數組
    //元素
    public static int Element_PIC[];       //背景紋理ID數組
    public static Bitmap[] Element_PIC_B;  //背景圖片數組
    //Yellw Number
    public static int NumYl_PIC[];       //背景紋理ID數組
    public static Bitmap[] NumYl_PIC_B;  //背景圖片數組
    //Red Number
    public static int NumRd_PIC[];       //背景紋理ID數組
    public static Bitmap[] NumRd_PIC_B;  //背景圖片數組
    //Black Number
    public static int NumBk_PIC[];       //背景紋理ID數組
    public static Bitmap[] NumBk_PIC_B;  //背景圖片數組
    //HeroFace
    public static int HeroFace_PIC[];       //背景紋理ID數組
    public static Bitmap[] HeroFace_PIC_B;  //背景圖片數組
    //Blood
    public static int Blood_PIC[];       //背景紋理ID數組
    public static Bitmap[] Blood_PIC_B;  //背景圖片數組


    public static int currStage = 0;//當前關卡 0-第一關

    //根據自適應屏幕參數對觸摸按鈕範圍變換
    public static void scaleCL() {
        if (XSFLAG) return;
        ssr = ScreenScaleUtil.calScale(SCREEN_WIDTH, SCREEN_HEIGHT);
        XSFLAG = true;
    }

    //加載紋理ID
    public static void loadTextureId(GL10 gl) {
        //劍士Bitmap轉成紋理ID
        SwordBoy_PIC = new int[SwordBoy_PIC_ID().length];
        for (int i = 0; i < SwordBoy_PIC_ID().length; i++) {
            SwordBoy_PIC[i] = initTexture(gl, SwordBoy_PIC_B[i]);
        }
        //法師Bitmap轉成紋理ID
        MagicGril_PIC = new int[SwordBoy_PIC_ID().length];
        for (int i = 0; i < SwordBoy_PIC_ID().length; i++) {
            MagicGril_PIC[i] = initTexture(gl, MagicGril_PIC_B[i]);
        }
        //石頭Bitmap轉成紋理ID
        Stone_PIC = new int[Stone_PIC_ID().length];
        for (int i = 0; i < Stone_PIC_ID().length; i++) {
            Stone_PIC[i] = initTexture(gl, Stone_PIC_B[i]);
        }
        //刺Bitmap轉成紋理ID
        Prick_PIC = new int[Prick_PIC_ID().length];
        for (int i = 0; i < Prick_PIC_ID().length; i++) {
            Prick_PIC[i] = initTexture(gl, Prick_PIC_B[i]);
        }
        //門Bitmap轉成紋理ID
        Door_PIC = new int[Door_PIC_ID().length];
        for (int i = 0; i < Door_PIC_ID().length; i++) {
            Door_PIC[i] = initTexture(gl, Door_PIC_B[i]);
        }
        //背景Bitmap轉成紋理ID
        BackGroup_PIC = new int[BackGroup_PIC_ID().length];
        for (int i = 0; i < BackGroup_PIC_ID().length; i++) {
            BackGroup_PIC[i] = initTexture(gl, BackGroup_PIC_B[i]);
        }
        //Ghost Bitmap轉成紋理ID
        Ghost_PIC = new int[Ghost_PIC_ID().length];
        for (int i = 0; i < Ghost_PIC_ID().length; i++) {
            Ghost_PIC[i] = initTexture(gl, Ghost_PIC_B[i]);
        }
        //Dragon Bitmap轉成紋理ID
        Dragon_PIC = new int[Dragon_PIC_ID().length];
        for (int i = 0; i < Dragon_PIC_ID().length; i++) {
            Dragon_PIC[i] = initTexture(gl, Dragon_PIC_B[i]);
        }
        //Treasure Bitmap轉成紋理ID
        Treasure_PIC = new int[Treasure_PIC_ID().length];
        for (int i = 0; i < Treasure_PIC_ID().length; i++) {
            Treasure_PIC[i] = initTexture(gl, Treasure_PIC_B[i]);
        }
        //Element
        Element_PIC = new int[Element_PIC_ID().length];
        for (int i = 0; i < Element_PIC_ID().length; i++) {
            Element_PIC[i] = initTexture(gl, Element_PIC_B[i]);
        }
        //Number Yellow
        NumYl_PIC = new int[NumYl_PIC_ID().length];
        for (int i = 0; i < NumYl_PIC_ID().length; i++) {
            NumYl_PIC[i] = initTexture(gl,NumYl_PIC_B[i]);
        }
        //Number Red
        NumRd_PIC = new int[NumRd_PIC_ID().length];
        for (int i = 0; i < NumRd_PIC_ID().length; i++) {
            NumRd_PIC[i] = initTexture(gl,NumRd_PIC_B[i]);
        }
        //Number Black
        NumBk_PIC = new int[NumBk_PIC_ID().length];
        for (int i = 0; i < NumBk_PIC_ID().length; i++) {
            NumBk_PIC[i] = initTexture(gl,NumBk_PIC_B[i]);
        }
        //HeroFace 0:boy
        HeroFace_PIC = new int[HeroFace_PIC_ID().length];
        for (int i = 0; i < HeroFace_PIC_ID().length; i++) {
            HeroFace_PIC[i] = initTexture(gl,HeroFace_PIC_B[i]);
        }
        //Blood 0:boy
       Blood_PIC = new int[Blood_PIC_ID().length];
        for (int i = 0; i < Blood_PIC_ID().length; i++) {
            Blood_PIC[i] = initTexture(gl,Blood_PIC_B[i]);
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
        MagicGril_PIC_B = new Bitmap[MagicGirl_PIC_ID().length];
        for (int i = 0; i < MagicGirl_PIC_ID().length; i++) {
            MagicGril_PIC_B[i] = PicLoadUtil.loadBM(res, MagicGirl_PIC_ID()[i]);
        }
        //石頭圖片轉Bitmap
        Stone_PIC_B = new Bitmap[Stone_PIC_ID().length];
        for (int i = 0; i < Stone_PIC_ID().length; i++) {
            Stone_PIC_B[i] = PicLoadUtil.loadBM(res, Stone_PIC_ID()[i]);
        }
        //刺圖片轉Bitmap
        Prick_PIC_B = new Bitmap[Prick_PIC_ID().length];
        Bitmap c = PicLoadUtil.loadBM(res, Prick_PIC_ID()[4]);
        for (int i = 0; i < Prick_PIC_ID().length; i++) {
            Bitmap s = PicLoadUtil.loadBM(res, Prick_PIC_ID()[i]);
            Prick_PIC_B[i] = combineImages(c, s);
        }

        //門圖片轉Bitmap
        Door_PIC_B = new Bitmap[Door_PIC_ID().length];
        for (int i = 0; i < Door_PIC_ID().length; i++) {
            Door_PIC_B[i] = PicLoadUtil.loadBM(res, Door_PIC_ID()[i]);
        }
        //背景圖片轉Bitmap
        BackGroup_PIC_B = new Bitmap[BackGroup_PIC_ID().length];
        for (int i = 0; i < BackGroup_PIC_ID().length; i++) {
            BackGroup_PIC_B[i] = PicLoadUtil.loadBM(res, BackGroup_PIC_ID()[i]);
        }
        //Ghost圖片轉Bitmap
        Ghost_PIC_B = new Bitmap[Ghost_PIC_ID().length];
        for (int i = 0; i < Ghost_PIC_ID().length; i++) {
            Ghost_PIC_B[i] = PicLoadUtil.loadBM(res, Ghost_PIC_ID()[i]);
        }
        //Dragon圖片轉Bitmap
        Dragon_PIC_B = new Bitmap[Dragon_PIC_ID().length];
        for (int i = 0; i < Dragon_PIC_ID().length; i++) {
            Dragon_PIC_B[i] = PicLoadUtil.loadBM(res, Dragon_PIC_ID()[i]);
        }
        //Treasure圖片轉Bitmap
        Treasure_PIC_B = new Bitmap[Treasure_PIC_ID().length];
        for (int i = 0; i < Treasure_PIC_ID().length; i++) {
            Treasure_PIC_B[i] = PicLoadUtil.loadBM(res, Treasure_PIC_ID()[i]);
        }
        //Element圖片轉Bitmap
        Element_PIC_B = new Bitmap[Element_PIC_ID().length];
        Bitmap EB = PicLoadUtil.loadBM(res, Element_PIC_ID()[0]);
        int left, top;
        for (int i = 0; i < Element_PIC_ID().length; i++) {
            switch (i) {
                case 1:
                    left = 5;
                    top = 5;
                    break;
                case 2:
                    left = 9;
                    top = 5;
                    break;
                default:
                    left =5;
                    top = 5;
                    break;
            }
            Bitmap s = PicLoadUtil.loadBM(res, Element_PIC_ID()[i]);
            Element_PIC_B[i] = combineElement(EB, s, left, top);
        }
        //Number Yellow圖片轉Bitmap
        NumYl_PIC_B = new Bitmap[NumYl_PIC_ID().length];
        for (int i = 0; i <NumYl_PIC_ID().length; i++) {
            NumYl_PIC_B[i] = PicLoadUtil.loadBM(res, NumYl_PIC_ID()[i]);
        }
        //Number Red圖片轉Bitmap
        NumRd_PIC_B = new Bitmap[NumRd_PIC_ID().length];
        for (int i = 0; i <NumRd_PIC_ID().length; i++) {
            NumRd_PIC_B[i] = PicLoadUtil.loadBM(res, NumRd_PIC_ID()[i]);
        }
        //Number Black圖片轉Bitmap
        NumBk_PIC_B = new Bitmap[NumBk_PIC_ID().length];
        for (int i = 0; i <NumBk_PIC_ID().length; i++) {
            NumBk_PIC_B[i] = PicLoadUtil.loadBM(res, NumBk_PIC_ID()[i]);
        }
        //HeroFace圖片轉Bitmap
        HeroFace_PIC_B = new Bitmap[HeroFace_PIC_ID().length];
        for (int i = 0; i <HeroFace_PIC_ID().length; i++) {
            HeroFace_PIC_B[i] = PicLoadUtil.loadBM(res,HeroFace_PIC_ID()[i]);
        }
        //Blood圖片轉Bitmap
        Blood_PIC_B = new Bitmap[Blood_PIC_ID().length];
        for (int i = 0; i <Blood_PIC_ID().length; i++) {
            Blood_PIC_B[i] = PicLoadUtil.loadBM(res,Blood_PIC_ID()[i]);
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
        int length = 12;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "SwordBoy" + i + ".png";
        }
        return s;
    }

    //法師圖片ID
    public static String[] MagicGirl_PIC_ID() {
        int length = 12;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "MagicGirl" + i + ".png";
        }
        return s;
    }

    //石頭圖片ID
    public static String[] Stone_PIC_ID() {
        int length = 12;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "Stone" + i + ".png";
        }
        return s;
    }

    //刺圖片ID
    public static String[] Prick_PIC_ID() {
        int length = 5;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "Prick" + i + ".png";
        }
        return s;
    }

    //門的圖片ID
    public static String[] Door_PIC_ID() {
        int length = 4;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "Door" + i + ".png";
        }
        return s;
    }

    //背景的圖片ID
    public static String[] BackGroup_PIC_ID() {
        int length = 2;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "BackGroup" + i + ".png";
        }
        return s;
    }

    //Ghost 的圖片ID
    public static String[] Ghost_PIC_ID() {
        int length = 5;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "Ghost" + i + ".png";
        }
        return s;
    }

    //Dragon的圖片ID
    public static String[] Dragon_PIC_ID() {
        int length = 14;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "Dragon" + i + ".png";
        }
        return s;
    }

    //Dragon的圖片ID
    public static String[] Treasure_PIC_ID() {
        int length = 4;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "Treasure" + i + ".png";
        }
        return s;
    }

    //Element的圖片ID
    public static String[] Element_PIC_ID() {
        int length = 3;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "Element" + i + ".png";
        }
        return s;
    }

    //NumYl的圖片ID
    public static String[] NumYl_PIC_ID() {
        int length = 10;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "NumYl" + i + ".png";
        }
        return s;
    }

    //NumRd的圖片ID
    public static String[] NumRd_PIC_ID() {
        int length = 10;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "NumRd" + i + ".png";
        }
        return s;
    }

    //NumBk的圖片ID
    public static String[] NumBk_PIC_ID() {
        int length = 12;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "NumBk" + i + ".png";
        }
        return s;
    }

    //HereFace的圖片ID
    public static String[] HeroFace_PIC_ID() {
        int length = 1;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "HeroFace" + i + ".png";
        }
        return s;
    }

    //Blood的圖片ID
    public static String[] Blood_PIC_ID() {
        int length = 1;
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = "Blood" + i + ".jpg";
        }
        return s;
    }

    public static float[][] Object_Size = {
            {800, 480},//0 背景
            {32, 32},//1 邊界
            {64, 64},//2 門
            {48, 48},//3 元素,HeroFace
            {32, 32},//4 數字
            {160,20},//5 blood
            {16,13}//6 Black Number
    };


    public static float[][] Object_Location = {
            {400, 240},//0 背景


    };

    //貼圖物體的初始位置
    public static float[][][] LOCATION =
            {
                    {//第一關貼圖物體的初始位置
                            {48, 48}, {752, 400}//0,1上下左右方邊界
                            , {752, 352}, {48, 96}//2,3右門,左門
                            , {48, 304}, {48, 336}//4,5下障礙物
                            , {752, 112}//6中間障礙物
                            , {90, 365}, {704, 96}//7,8左右寶箱位置
                            , {140, 366}//9石頭
                            , {576, 96}, {480, 96}, {384, 96}, {288, 96}, {192, 96}//10,11,12,13,14刺
                            , {720, 272}//15 ghost
                            , {656, 96}//16 Dragon
                            , {90, 448}, {138, 448}//17 Element-Sun 18Number
                            , {250, 448}, {298, 448}//19 Element-Fire 20 Number
                            , {398,448}//21 Hero
                            , {520,448}//22 Blood
                            , {460,448},{480,448},{500,448},{520,448},{540,448},{560,448},{580,448}//23,24,25,26,27,28,29 Blood Number

                    }
            };


    public static boolean[][] IS_MOVE =
            {
                    {//第一關的是否靜止列表
                            true, true, true,                                                          //左右下方邊界
                            true, true, true, true, true, true, true, true, true, true,                      //八個擋板
                            true, true, true, true, true, true, true, true, true, true, true, true, true, true,//第一排釘子
                            true, true, true, true, true, true, true, true, true, true, true, true, true, true,//第二排釘子
                            true, true, true, true, true, true, true, true, true, true, true, true, true, true,//第三排釘子
                            true, true, true, true, true, true, true, true, true, true, true, true, true, true //第四排釘子
                    }
            };

    public static Bitmap combineImages(Bitmap c, Bitmap s) {
        int width = s.getWidth(), height = s.getHeight();
        Bitmap cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImage = new Canvas(cs);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAlpha(168);
        comboImage.drawBitmap(c, 0f, 0f, paint);
        paint.setAlpha(220);
        comboImage.drawBitmap(s, 1f, -1f, paint);
        return cs;
    }

    public static Bitmap combineElement(Bitmap back, Bitmap s, int left, int top) {
        int width = back.getWidth(), height = back.getHeight();
        Bitmap cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImage = new Canvas(cs);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAlpha(168);
        comboImage.drawBitmap(back, 0f, 0f, paint);
        paint.setAlpha(220);
        comboImage.drawBitmap(s, left, top, paint);
        return cs;
    }

    public static Bitmap DrawNumber(String Number, int Color) {
        int width = 50, height =50;
        Bitmap cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(cs);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1000);
        canvas.drawText(Number,0,0,paint);
        return cs;
    }


}
