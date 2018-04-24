package com.whc.dicerpg.Thread;

import android.graphics.Bitmap;

import com.whc.dicerpg.Model.FireAttack;
import com.whc.dicerpg.Util.Box2DUtil;
import com.whc.dicerpg.View.Constant;
import com.whc.dicerpg.View.FirstOneView;
import com.whc.dicerpg.View.Constant.*;

import org.jbox2d.common.Vec2;

import static com.whc.dicerpg.View.Constant.ITERA;
import static com.whc.dicerpg.View.Constant.PHYSICS_THREAD_FLAG;
import static com.whc.dicerpg.View.Constant.PIC_ARRAY;
import static com.whc.dicerpg.View.Constant.RATE;
import static com.whc.dicerpg.View.Constant.SLEEPTIME;
import static com.whc.dicerpg.View.Constant.TEXTUREID_PIC;
import static com.whc.dicerpg.View.Constant.TIME_STEP;
import static com.whc.dicerpg.View.Constant.*;
import static com.whc.dicerpg.Util.Box2DUtil.*;


public class PhysicsThread extends Thread {

    public boolean addTask=false;//是否添加火球
    public boolean isStatic=true;//判斷上一個小球是否靜止
    public boolean judgeStatic=false;//是否檢測小球靜止
    FirstOneView fo;

    public PhysicsThread(FirstOneView fo)
    {
       this.fo=fo;
    }

    @Override
    public void run()
    {
           if(PHYSICS_THREAD_FLAG)
           {

               fo.world.step(TIME_STEP,ITERA);
               try {
                   Thread.sleep(SLEEPTIME);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               if(addTask&&isStatic)
               {
                   doAddtask();
                   addTask=false;
                   isStatic=false;
                   judgeStatic=true;
               }

               //火焰消失條件
               if(judgeStatic&&fo.FA.body.getLinearVelocity().lengthSquared()<VELOCITY_THRESHOLD&&fo.FA.body.getPosition().y*RATE>443)//判斷小球是否靜止
               {
                   float x=fo.FA.body.getPosition().x*RATE;
                   float y=fo.FA.body.getPosition().y*RATE;
                   changeState(x,y);
                   detectionBlood();
                   isStatic=true;
                   judgeStatic=false;
               }


           }

    }

    private void changeState(float x, float y) {
        fo.world.destroyBody(fo.FA.body);
        fo.FA.isLive=false;
        Bitmap[] bma=new Bitmap[1];
        bma[0]=PIC_ARRAY[1];
        FireAttack sb=Box2DUtil.createFireAttack
                (
                        x,
                        y,
                        OTHER_SIZE[0][0]/2,
                        true,
                        fo.world,
                        fo.mRenderer.trXq,
                        TEXTUREID_PIC[2],
                        fo
                );
        gv.staticball.add(sb);

        for(int i=0;i<BALL_LINE.length;i++)
        {
            if(x>BALL_BOUNDARY[currStage][i][0]&&x<BALL_BOUNDARY[currStage][i][1])
            {
                BALL_LINE[i]=1;
            }
        }
        for(int i:BALL_LINE)
        {
            System.out.print(i+" ");
        }
        System.out.println();
    }

    private void detectionBlood() {
        
    }

    private void doAddtask() {
        Bitmap[] bma=new Bitmap[1];
        bma[0]=PIC_ARRAY[1];
        fo.FA= Box2DUtil.createFireAttack(
                fo.xst,
                fo.yst,
                Constant.OTHER_SIZE[0][0]/2,
                false,
                fo.world,
                fo.mRenderer.trXq,
                TEXTUREID_PIC[0],
                fo
        );
        fo.FA.body.setLinearVelocity(new Vec2(0,10));
        fo.bl.add(fo.FA);
    }


}
