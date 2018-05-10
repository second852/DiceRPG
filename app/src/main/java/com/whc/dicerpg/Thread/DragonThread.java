package com.whc.dicerpg.Thread;

import android.opengl.GLSurfaceView;
import com.whc.dicerpg.View.FirstOneView;

import static com.whc.dicerpg.View.Constant.DragonMove;

public class DragonThread extends Thread {
    GLSurfaceView gv;
    long SleeTime=1000;

    public DragonThread(GLSurfaceView gv)
    {
        this.gv=gv;
    }

    @Override
    public void run() {
        while (DragonMove)
        {
            if(gv instanceof FirstOneView)
            {
                FirstOneView fo= (FirstOneView) gv;
                FirstOneViewMethod(fo);
            }
            try {
                Thread.sleep(SleeTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void FirstOneViewMethod(FirstOneView fo)
    {
           if(fo.DragonAttack)
           {
               SleeTime=1000;
               fo.DragonAttack=false;
           }else{
               SleeTime=2000;
               fo.DragonAttack=true;
           }

    }


}
