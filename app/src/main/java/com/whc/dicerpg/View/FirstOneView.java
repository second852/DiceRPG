package com.whc.dicerpg.View;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.view.MotionEvent;

import com.whc.dicerpg.Model.FireAttack;
import com.whc.dicerpg.Model.MyBody;
import com.whc.dicerpg.Thread.PhysicsThread;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.ContactListener;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.ContactPoint;
import org.jbox2d.dynamics.contacts.ContactResult;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import static com.whc.dicerpg.View.Constant.*;

public class FirstOneView extends GLSurfaceView {


    AABB worldAABB;
    public MainActivity activity;
    public SceneRenderer mRenderer;//場景渲染器
    public World world;
    public FireAttack FA;
    public ArrayList<MyBody> bl=new ArrayList<MyBody>();//剛體列表
    public ArrayList<MyBody> b2=new ArrayList<MyBody>();//剛體列表
    public PhysicsThread pt;//物理模擬線程


    public FirstOneView(Context context)
    {
        super(context);
        this.activity=(MainActivity) context;
        mRenderer = new SceneRenderer();	//創建場景渲染器
        setRenderer(mRenderer);				//設置渲染器
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//設置渲染模式為主動渲染
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        return true;
    }

    public class SceneRenderer implements GLSurfaceView.Renderer{
        //背景
        public MyCommonTexture myBj;
        public TextureRectangular trBj;

        public MyCommonTexture myRo;
        public TextureRectangular trRo;
        public MyCommonTexture myMo;
        public TextureRectangular trMo;
        public TextureRectangular trXq;


        @Override
        public void onDrawFrame(GL10 gl) {
            //清除顏色緩存
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            //設置當前矩陣為模式矩陣
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            //設置當前矩陣為單位矩陣
            gl.glLoadIdentity();
            //設置攝像機
            GLU.gluLookAt
                    (
                            gl,
                            0,   //人眼位置的X
                            0, 	 //人眼位置的Y
                            0,   //人眼位置的Z
                            0, 	 //人眼球看的點X
                            0,   //人眼球看的點Y
                            -1,  //人眼球看的點Z
                            0,
                            1,
                            0
                    );

            //背景
            myBj.drawself(gl, BackGroup_PIC[0], From2DTo3DUtil.point3D(Object_Location[0]), -10f);


        }


        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //關閉抗抖動
            gl.glDisable(GL10.GL_DITHER);
            //設置特定Hint項目的模式，這裡為設置為使用快速模式
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);
            //設置屏幕背景色黑色RGBA
            gl.glClearColor(0,0,0,0);
            //開啟混合
            gl.glEnable(GL10.GL_BLEND);
            //設置混合參數
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
            //打開背面剪裁
            gl.glEnable(GL10.GL_CULL_FACE);
            //設置著色模型為平滑著色
            gl.glShadeModel(GL10.GL_SMOOTH);//GL10.GL_SMOOTH  GL10.GL_FLAT
            //啟用深度測試
            gl.glEnable(GL10.GL_DEPTH_TEST);

            //加載Bitmap
            Constant.loadPic(FirstOneView.this.getResources());
            //初始化紋理
            Constant.loadTextureId(gl);
            trBj=new TextureRectangular(Object_Size[0][0],Object_Size[0][1]);
            loadGameData();
            initContactListener();

        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //設置視窗大小及位置
            gl.glViewport(ssr.lucX,ssr.lucY, (int)(SCREEN_WIDTH_STANDARD*ssr.ratio), (int)(SCREEN_HEIGHT_STANDARD*ssr.ratio));
            //設置當前矩陣為投影矩陣
            gl.glMatrixMode(GL10.GL_PROJECTION);
            //設置當前矩陣為單位矩陣
            gl.glLoadIdentity();
            //計算透視投影的比例
            float ratio=RATIO;
            //調用此方法計算產生透視投影矩陣
            gl.glOrthof(-ratio, ratio, -1, 1, 1, 20);
        }


        public void loadGameData()
        {
            worldAABB = new AABB();
            //上下界，以屏幕的左上方為 原點，如果創建的剛體到達屏幕的邊緣的話，會停止模擬
            worldAABB.lowerBound.set(-100.0f,-100.0f);
            worldAABB.upperBound.set(100.0f, 100.0f);//注意這裡使用的是現實世界的單位
            Vec2 gravity = new Vec2(0.0f,0.0f);//設置重力
            boolean doSleep = true;
            //創建世界
            world = new World(worldAABB,gravity, doSleep);
            mRenderer.myBj=new MyCommonTexture(mRenderer.trBj);
        }

        //加載碰撞監聽器
        public void initContactListener()
        {
            ContactListener cl=new ContactListener()
            {
                @Override
                public void add(ContactPoint arg0)
                {

                }

                @Override
                public void persist(ContactPoint arg0) {
                }

                @Override
                public void remove(ContactPoint arg0) {
                }

                @Override
                public void result(ContactResult arg0) {
                }
            };
            world.setContactListener(cl);
        }

    }


}
