package com.whc.dicerpg.View;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;
import android.view.MotionEvent;

import com.whc.dicerpg.Model.FireAttack;
import com.whc.dicerpg.Model.Monster;
import com.whc.dicerpg.Model.MyBody;
import com.whc.dicerpg.Thread.PhysicsThread;
import com.whc.dicerpg.Util.Box2DUtil;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.ContactListener;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.ContactPoint;
import org.jbox2d.dynamics.contacts.ContactResult;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.whc.dicerpg.View.Constant.OTHER_LOCATION;
import static com.whc.dicerpg.View.Constant.TEXTUREID_OTHER;
import static com.whc.dicerpg.View.Constant.*;

public class FirstOneView extends GLSurfaceView {


    AABB worldAABB;
    public MainActivity activity;
    public SceneRenderer mRenderer;//場景渲染器
    public World world;
    public FireAttack FA;
    public float xst=BALL_X_MIN;//火球位置x
    public float yst=BALL_Y;//火球位置y
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

        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
           pt.addTask=true;
        }
        return true;
    }

    public class SceneRenderer implements GLSurfaceView.Renderer{
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
            myBj.drawself(gl, TEXTUREID_OTHER[0], From2DTo3DUtil.point3D(OTHER_LOCATION[0]), -10f);
            myRo.drawself(gl,TEXTUREID_OTHER[1],From2DTo3DUtil.point3D(OTHER_LOCATION[1]), -6f);
            //剛體
            int size_bl=bl.size();
            for(int i=0;i<size_bl;i++)
            {
                try
                {
                    bl.get(i).drawSelf(gl);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            //剛體
            for(int i=0;i<b2.size();i++)
            {
                try
                {

                    b2.get(i).drawSelf(gl);
                    if(b2.get(i) instanceof FireAttack)
                    {
                        FireAttack fa= (FireAttack) b2.get(i);
                        fa.isLive=false;
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
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
            trBj=new TextureRectangular(Constant.OTHER_SIZE[2][0],Constant.OTHER_SIZE[2][1]);
            trRo=new TextureRectangular(OTHER_SIZE[0][0],OTHER_SIZE[0][1]);
            trMo=new TextureRectangular(OTHER_SIZE[1][0],OTHER_SIZE[1][1]);
            trXq=new TextureRectangular(OTHER_SIZE[0][0],OTHER_SIZE[0][1]);

            loadGameData();

            initContactListener();

            pt=new PhysicsThread(FirstOneView.this);
            pt.start();

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

            Monster monster= Box2DUtil.createMonster(
                    OTHER_LOCATION[2][0],
                    OTHER_LOCATION[2][1],
                    OTHER_SIZE[1][0],
                    OTHER_SIZE[1][1],
                    true,
                    world,
                    mRenderer.trMo,
                    TEXTUREID_OTHER[2],
                    FirstOneView.this
            );
            bl.add(monster);
            mRenderer.myBj=new MyCommonTexture(mRenderer.trBj);
            mRenderer.myRo=new MyCommonTexture(mRenderer.trRo);
            mRenderer.myMo=new MyCommonTexture(mRenderer.trMo);
        }

        //加載碰撞監聽器
        public void initContactListener()
        {
            ContactListener cl=new ContactListener()
            {
                @Override
                public void add(ContactPoint arg0)
                {
                    Body body1=arg0.shape1.getBody();
                    Body body2=arg0.shape2.getBody();
                    float x=arg0.position.x*RATIO;
                    float y=arg0.position.y*RATIO;
                    if(FirstOneView.this.FA==null)
                    {
                        return;
                    }
                    for (MyBody b:bl)
                    {
                       if(b instanceof Monster)
                       {
                           Monster m= (Monster) b;
                           if(body1==m.body||body2==m.body)
                           {
                                 m.doAction(x,y);
                           }
                       }
                        if(b instanceof FireAttack)
                        {
                            FireAttack f= (FireAttack) b;
                            if(body1==f.body||body2==f.body)
                            {
                                f.doAction(x,y);
                            }
                        }
                    }


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
