package com.whc.dicerpg.Thread;


import android.opengl.GLSurfaceView;
import com.whc.dicerpg.View.FirstOneView;

import static com.whc.dicerpg.View.Constant.*;

public class GhostThread extends Thread{
    GLSurfaceView gv;
    long SleepTime=100;
    int rate=5;

    public GhostThread(GLSurfaceView gv)
    {
        this.gv=gv;
    }


    @Override
    public void run() {
        while (GhostMove) {
           if(gv instanceof FirstOneView)
           {
               FirstOneView fo= (FirstOneView) gv;
               FirstOneViewMethod(fo);
           }
            try {
                Thread.sleep(SleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void  FirstOneViewMethod(FirstOneView fo)
    {
       if(fo.GhostStyle==0)
       {
           fo.GhostStyle=1;
           SleepTime=200;
           return;
       }
       //到達左邊界
       if(fo.GhostX<=(LOCATION[currStage][0][0]+32))
       {
           if(fo.GhostStyle==1)
           {
               fo.GhostStyle=4;
           }
           if(fo.GhostStyle==2)
           {
               fo.GhostStyle=0;
           }
       }
        //到達右邊界
        if(fo.GhostX>=(LOCATION[currStage][1][0]-32))
        {
            if(fo.GhostStyle==1)
            {
                fo.GhostStyle=2;
            }
            if(fo.GhostStyle==4)
            {
                fo.GhostStyle=0;
            }
        }

        if(fo.GhostStyle==4)
        {
            SleepTime=50;
            fo.GhostX=fo.GhostX+rate;
        }
        if(fo.GhostStyle==2)
        {
            SleepTime=50;
            fo.GhostX=fo.GhostX-rate;
        }
    }

}

