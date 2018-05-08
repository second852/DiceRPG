package com.whc.dicerpg.View;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;
import android.view.MotionEvent;

import com.whc.dicerpg.Model.Door;
import com.whc.dicerpg.Model.FireAttack;
import com.whc.dicerpg.Model.MyBody;
import com.whc.dicerpg.Model.MyEdgeImg;
import com.whc.dicerpg.Model.Stone;
import com.whc.dicerpg.Model.Treasure;
import com.whc.dicerpg.Thread.PhysicsThread;
import com.whc.dicerpg.Thread.StoneTread;
import com.whc.dicerpg.Util.Box2DUtil;

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
    public ArrayList<MyBody> BackGroup = new ArrayList<MyBody>();//背景列表
    public ArrayList<MyBody> b2 = new ArrayList<MyBody>();//剛體列表
    public float StoneX=128;
    public float StoneY=368;
    public PhysicsThread pt;//物理模擬線程
    public StoneTread stoneTread;
    public boolean addStone=true;


    public FirstOneView(Context context) {
        super(context);
        this.activity = (MainActivity) context;
        mRenderer = new SceneRenderer();    //創建場景渲染器
        setRenderer(mRenderer);                //設置渲染器
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//設置渲染模式為主動渲染
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        return true;
    }

    public class SceneRenderer implements GLSurfaceView.Renderer {
        //背景
        public MyCommonTexture myBj;
        public TextureRectangular trBj;
        //設置邊界
        public TextureRectangular trXbj;
        //右左門
        public TextureRectangular trDoor;
        //寶相
        public TextureRectangular trTreasure;
        //石頭
        public TextureRectangular trStone;



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
                            0,     //人眼位置的Y
                            0,   //人眼位置的Z
                            0,     //人眼球看的點X
                            0,   //人眼球看的點Y
                            -1,  //人眼球看的點Z
                            0,
                            1,
                            0
                    );
            //背景
            myBj.drawself(gl, BackGroup_PIC[0], From2DTo3DUtil.point3D(Object_Location[0]), -10f);
            //設定障礙物
            for (int i = 0; i < BackGroup.size(); i++) {
                try {
                    BackGroup.get(i).drawSelf(gl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //關閉抗抖動
            gl.glDisable(GL10.GL_DITHER);
            //設置特定Hint項目的模式，這裡為設置為使用快速模式
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
            //設置屏幕背景色黑色RGBA
            gl.glClearColor(0, 0, 0, 0);
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
            trBj = new TextureRectangular(Object_Size[0][0], Object_Size[0][1]);
            trXbj = new TextureRectangular(Object_Size[1][0], Object_Size[1][1]);
            trDoor = new TextureRectangular(Object_Size[2][0], Object_Size[2][1]);
            trTreasure=new TextureRectangular(Object_Size[1][0], Object_Size[1][1]);
            trStone =new TextureRectangular(Object_Size[1][0], Object_Size[1][1]);
            loadGameData();
            initContactListener();
            initThread();
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //設置視窗大小及位置
            gl.glViewport(ssr.lucX, ssr.lucY, (int) (SCREEN_WIDTH_STANDARD * ssr.ratio), (int) (SCREEN_HEIGHT_STANDARD * ssr.ratio));
            //設置當前矩陣為投影矩陣
            gl.glMatrixMode(GL10.GL_PROJECTION);
            //設置當前矩陣為單位矩陣
            gl.glLoadIdentity();
            //計算透視投影的比例
            float ratio = RATIO;
            //調用此方法計算產生透視投影矩陣
            gl.glOrthof(-ratio, ratio, -1, 1, 1, 20);
        }


        public void loadGameData() {
            worldAABB = new AABB();
            //上下界，以屏幕的左上方為 原點，如果創建的剛體到達屏幕的邊緣的話，會停止模擬
            worldAABB.lowerBound.set(-100.0f, -100.0f);
            worldAABB.upperBound.set(100.0f, 100.0f);//注意這裡使用的是現實世界的單位
            Vec2 gravity = new Vec2(0.0f, 0.0f);//設置重力
            boolean doSleep = true;
            //創建世界
            world = new World(worldAABB, gravity, doSleep);
            //背景
            mRenderer.myBj = new MyCommonTexture(mRenderer.trBj);
            //邊界
            Edge();
            //障礙物
            Barrier();
            //門
            Door();
            //寶相
            Treasure();
        }

        //加載碰撞監聽器
        public void initContactListener() {
            ContactListener cl = new ContactListener() {
                @Override
                public void add(ContactPoint arg0) {

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



    private void initThread() {
          stoneTread=new StoneTread(this);
          stoneTread.start();
    }

    //設置寶箱
    public void Treasure()
    {
        Treasure treasure1=Box2DUtil.createTreasure(
                LOCATION[currStage][7][0],
                LOCATION[currStage][7][1],
                Object_Size[1][0],
                Object_Size[1][1],
                world,
                mRenderer.trTreasure,
                Treasure_PIC[0],
                FirstOneView.this
        );
        BackGroup.add(treasure1);
        Treasure treasure2=Box2DUtil.createTreasure(
                LOCATION[currStage][8][0],
                LOCATION[currStage][8][1],
                Object_Size[1][0],
                Object_Size[1][1],
                world,
                mRenderer.trTreasure,
                Treasure_PIC[0],
                FirstOneView.this
        );
        BackGroup.add(treasure2);
    }


    //設置門
    public void Door()
    {
        Door doorR = Box2DUtil.createDoor
                (
                        LOCATION[currStage][2][0],
                        LOCATION[currStage][2][1],
                        Object_Size[2][0],
                        Object_Size[2][1],
                        world,
                        mRenderer.trDoor,
                        Door_PIC[1],
                        FirstOneView.this
                );
        BackGroup.add(doorR);
        Door doorL = Box2DUtil.createDoor
                (
                        LOCATION[currStage][3][0],
                        LOCATION[currStage][3][1],
                        Object_Size[2][0],
                        Object_Size[2][1],
                        world,
                        mRenderer.trDoor,
                        Door_PIC[0],
                        FirstOneView.this
                );
        BackGroup.add(doorL);
    }

    //設置障礙物
    public void Barrier() {
        //下障礙物
        for (int i = 1; i < 20; i++) {
            MyEdgeImg myedgetemp = Box2DUtil.createMyEdgeImg
                    (
                            LOCATION[currStage][4][0] + 32 * i,
                            LOCATION[currStage][4][1],
                            Object_Size[1][0],
                            Object_Size[1][1],
                            world,
                            mRenderer.trXbj,
                            BackGroup_PIC[1],
                            FirstOneView.this
                    );
            BackGroup.add(myedgetemp);
        }
        for (int i = 3; i < 18; i = i + 4) {
            MyEdgeImg myedgetemp1 = Box2DUtil.createMyEdgeImg
                    (
                            LOCATION[currStage][5][0] + 32 * i,
                            LOCATION[currStage][5][1],
                            Object_Size[1][0],
                            Object_Size[1][1],
                            world,
                            mRenderer.trXbj,
                            BackGroup_PIC[1],
                            FirstOneView.this
                    );
            MyEdgeImg myedgetemp2 = Box2DUtil.createMyEdgeImg
                    (
                            LOCATION[currStage][5][0] + 32 * (i + 1),
                            LOCATION[currStage][5][1],
                            Object_Size[1][0],
                            Object_Size[1][1],
                            world,
                            mRenderer.trXbj,
                            BackGroup_PIC[1],
                            FirstOneView.this
                    );
            BackGroup.add(myedgetemp1);
            BackGroup.add(myedgetemp2);
        }
        //中間障礙物
        for (int j = 1; j <5; j++) {
            for (int i = 1; i <20; i++) {
                MyEdgeImg myedgetemp = Box2DUtil.createMyEdgeImg
                        (
                                LOCATION[currStage][6][0]-32 * (i),
                                LOCATION[currStage][6][1]+32*j,
                                Object_Size[1][0],
                                Object_Size[1][1],
                                world,
                                mRenderer.trXbj,
                                BackGroup_PIC[1],
                                FirstOneView.this
                        );
                BackGroup.add(myedgetemp);
            }
        }
    }


    //設置邊界
    public void Edge() {
        //上邊界
        for (int i = 0; i < 23; i++) {
            MyEdgeImg myedgetemp = Box2DUtil.createMyEdgeImg
                    (
                            LOCATION[currStage][0][0] + 32 * i,
                            LOCATION[currStage][0][1],
                            Object_Size[1][0],
                            Object_Size[1][1],
                            world,
                            mRenderer.trXbj,
                            BackGroup_PIC[1],
                            FirstOneView.this
                    );
            BackGroup.add(myedgetemp);
        }
        //左邊界
        for (int i = 3; i < 12; i++) {
            MyEdgeImg myedgetemp = Box2DUtil.createMyEdgeImg
                    (
                            LOCATION[currStage][0][0],
                            LOCATION[currStage][0][1] + 32 * i,
                            Object_Size[1][0],
                            Object_Size[1][1],
                            world,
                            mRenderer.trXbj,
                            BackGroup_PIC[1],
                            FirstOneView.this
                    );
            BackGroup.add(myedgetemp);
        }
        //下邊界
        for (int i = 1; i < 23; i++) {
            MyEdgeImg myedgetemp = Box2DUtil.createMyEdgeImg
                    (
                            LOCATION[currStage][0][0] + 32 * i,
                            LOCATION[currStage][1][1],
                            Object_Size[1][0],
                            Object_Size[1][1],
                            world,
                            mRenderer.trXbj,
                            BackGroup_PIC[1],
                            FirstOneView.this
                    );
            BackGroup.add(myedgetemp);
        }
        //右邊界
        for (int i = 1; i <9; i++) {
            MyEdgeImg myedgetemp = Box2DUtil.createMyEdgeImg
                    (
                            LOCATION[currStage][1][0],
                            LOCATION[currStage][0][1] + 32 * i,
                            Object_Size[1][0],
                            Object_Size[1][1],
                            world,
                            mRenderer.trXbj,
                            BackGroup_PIC[1],
                            FirstOneView.this
                    );
            BackGroup.add(myedgetemp);
        }
    }


}
