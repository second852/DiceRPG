package com.whc.dicerpg.Thread;

import android.opengl.GLSurfaceView;

import com.whc.dicerpg.Model.Ghost;
import com.whc.dicerpg.Model.Stone;
import com.whc.dicerpg.Util.Box2DUtil;
import com.whc.dicerpg.View.FirstOneView;
import org.jbox2d.common.Vec2;

import static com.whc.dicerpg.View.Constant.*;

public class PhysicsTread extends Thread {

    GLSurfaceView gv;
    Stone stone;
    Ghost ghost;
    int id;
    int i;
    public PhysicsTread(GLSurfaceView gv)
    {
        this.gv=gv;
    }

    @Override
    public void run() {
        while(StoneMove)
        {
            if(gv instanceof FirstOneView)
            {
                FirstOneView fo= (FirstOneView) gv;
                FirstOneView(fo);
            }
            try
            {
                Thread.sleep(SLEEPTIME);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public  void  FirstOneView( FirstOneView fo)
    {
        //開始模擬
        try
        {
            fo.world.step(TIME_STEP, ITERA);//開始模擬
        }
        catch(Exception e)
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

        id=stone.getTextureid();
        if(id==Stone_PIC[0])
        {
            stone.setTextureid(Stone_PIC[1]);
        }else if(id==Stone_PIC[1])
        {
            stone.setTextureid(Stone_PIC[2]);
        }else {
            stone.setTextureid(Stone_PIC[0]);
        }

        if(stone.body.getLinearVelocity().x<=0)
        {
            for(int i=0;i<fo.BackGroup.size();i++)
            {
                if(fo.BackGroup.get(i) instanceof Stone)
                {
                    fo.BackGroup.remove(i);
                    break;
                }
            }
            fo.world.destroyBody(stone.body);
            fo.addStone=true;
        }
        //ghost
        if(fo.addGhost) {
            ghost = Box2DUtil.createGhost(
                    LOCATION[currStage][15][0],
                    LOCATION[currStage][15][1],
                    Object_Size[1][0],
                    Object_Size[1][1],
                    fo.world,
                    fo.mRenderer.trGhost,
                    Ghost_PIC[1],
                    fo
            );
            ghost.body.setLinearVelocity(new Vec2(-2, 0));
            fo.BackGroup.add(ghost);
            fo.addGhost = false;
        }
        if(ghost.body.getLinearVelocity().x==0)
        {
            ghost.setTextureid(Ghost_PIC[0]);
            ghost.body.allowSleeping(true);
        }
        if(ghost.body.isSleeping())
        {
            i++;
        }
//        if(i>3&&ghost.body.getPosition().x<200)
//        {
//            ghost.setTextureid(Ghost_PIC[2]);
//            ghost.body.setLinearVelocity(new Vec2(2,0));
//            i=0;
//        }
//        if(i>10&&ghost.body.getPosition().x>200)
//        {
//            ghost.setTextureid(Ghost_PIC[1]);
//            ghost.body.setLinearVelocity(new Vec2(-2,0));
//            i=0;
//        }

    }


}
