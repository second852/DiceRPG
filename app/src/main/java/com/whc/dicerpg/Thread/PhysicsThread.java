package com.whc.dicerpg.Thread;

import android.graphics.Bitmap;
import android.util.Log;

import com.whc.dicerpg.Model.FireAttack;
import com.whc.dicerpg.Util.Box2DUtil;
import com.whc.dicerpg.View.Constant;
import com.whc.dicerpg.View.FirstOneView;
import com.whc.dicerpg.View.Constant.*;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.ContactListener;
import org.jbox2d.dynamics.contacts.ContactPoint;
import org.jbox2d.dynamics.contacts.ContactResult;

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
           while (PHYSICS_THREAD_FLAG)
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
           }

    }



    private void doAddtask() {
        Bitmap[] bma=new Bitmap[1];
        bma[0]=PIC_ARRAY[1];
        fo.FA= Box2DUtil.createFireAttack(
                OTHER_LOCATION[1][0],
                OTHER_LOCATION[1][1],
                Constant.OTHER_SIZE[0][0]/2,
                false,
                fo.world,
                fo.mRenderer.trXq,
                TEXTUREID_PIC[0],
                fo
        );
        fo.FA.body.setLinearVelocity(new Vec2(5,0));
        fo.bl.add(fo.FA);
    }

}
