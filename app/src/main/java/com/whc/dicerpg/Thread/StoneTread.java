package com.whc.dicerpg.Thread;

import android.opengl.GLSurfaceView;

import com.whc.dicerpg.Model.MyBody;
import com.whc.dicerpg.Model.Stone;
import com.whc.dicerpg.Util.Box2DUtil;
import com.whc.dicerpg.View.FirstOneView;

import org.jbox2d.common.Vec2;

import static com.whc.dicerpg.View.Constant.*;

public class StoneTread extends Thread {

    GLSurfaceView gv;
    Stone stone;
    public StoneTread(GLSurfaceView gv)
    {
        this.gv=gv;
    }

    @Override
    public void run() {
        FirstOneView fo= (FirstOneView) gv;
        while(StoneMove)
        {
            try
            {
                fo.world.step(TIME_STEP, ITERA);//開始模擬
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                Thread.sleep(SLEEPTIME);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if(fo.addStone)
            {
                stone= Box2DUtil.createStone(
                        LOCATION[currStage][9][0],
                        LOCATION[currStage][9][1],
                        Object_Size[1][0]/2,
                        fo.world,
                        fo.mRenderer.trStone,
                        Stone_PIC[0],
                        fo
                );
                stone.body.setLinearVelocity(new Vec2(3,0));
                fo.BackGroup.add(stone);
                fo.addStone=false;
            }
            if(stone.body.getPosition().x>LOCATION[currStage][2][0])
            {
                for(MyBody m:fo.BackGroup)
                {
                    int i=0;
                    if(m instanceof Stone)
                    {
                        fo.BackGroup.remove(i);
                        break;
                    }
                    i++;
                }
                fo.addStone=true;
            }
        }
    }
}
