package com.whc.dicerpg.Thread;

import android.graphics.Bitmap;
import android.util.Log;

import com.whc.dicerpg.Model.FireAttack;
import com.whc.dicerpg.Model.MyBody;
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

               try
               {
                   fo.world.step(TIME_STEP,ITERA);//開始模擬
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               }

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
               if(fo.FA!=null)
               {
                   Log.d("physic",judgeStatic+" : "+fo.FA.body.getPosition().x+" : "+VELOCITY_THRESHOLD+" : "+fo.FA.body.getPosition().y*RATE);
               }

               //小球靜止判斷條件
               if(judgeStatic&&fo.FA.body.getLinearVelocity().x==-3&&fo.FA.body.getPosition().x<63)//判斷小球是否靜止
               {
                   fo.FA.body.getLinearVelocity().setZero();
                   Log.d("physic","stop");
                   float x=fo.FA.body.getPosition().x*RATE;
                   float y=fo.FA.body.getPosition().y*RATE;
                   changeState(x,y);
                   isStatic=true;
                   judgeStatic=false;
               }
               //擊中火球
               for(MyBody mb:fo.b2)
               {
                   if(mb instanceof FireAttack)
                   {

                       FireAttack fa= (FireAttack) mb;
                       if(!fa.isLive)
                       {
                           try {
                               Thread.sleep(20);
                           }catch (Exception e)
                           {
                               e.printStackTrace();
                           }
                           fo.world.destroyBody(fa.body);
                           fo.b2.clear();
                       }
                   }
               }
//
               for(int i=0;i<fo.bl.size();i++)
               {
                   if(fo.bl.get(i) instanceof FireAttack)
                   {
                      FireAttack fa= (FireAttack) fo.bl.get(i);
                      if(!fa.isLive)
                      {
                          fo.bl.remove(i);
                      }
                   }
               }
           }
    }

    private void changeState(float x, float y) {
        fo.FA.isLive=false;
        fo.world.destroyBody(fo.FA.body);
        FireAttack sb=Box2DUtil.createFireAttack
                (
                        x,
                        y,
                        OTHER_SIZE[0][0]/2,
                        true,
                        fo.world,
                        fo.mRenderer.trXq,
                        TEXTUREID_PIC[1],
                        fo
                );
        fo.b2.add(sb);
    }


    private void doAddtask() {
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
        fo.FA.body.setLinearVelocity(new Vec2(10,0));
        fo.bl.add(fo.FA);
    }

}
