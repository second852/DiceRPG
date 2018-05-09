package com.whc.dicerpg.Thread;

import android.opengl.GLSurfaceView;

import com.whc.dicerpg.View.FirstOneView;

import static com.whc.dicerpg.View.Constant.*;

public class PrickThread extends Thread{


    public GLSurfaceView gv;
    public int i=0,j;
    public PrickThread( GLSurfaceView gv)
    {
        this.gv=gv;
    }

    @Override
    public void run() {
        while (StoneMove)
        {
            j=i%8;
            switch (j){
                case 4:
                    j=3;
                    break;
                case 5:
                    j=2;
                    break;
                case 6:
                    j=1;
                    break;
                case 7:
                    j=0;
                    break;
            }
            if(gv instanceof FirstOneView)
            {
                FirstOneView fo= (FirstOneView) gv;
                fo.PrickStyle=j;
            }
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            i++;
            if(i==8)
            {
                i=0;
            }
        }
    }


}
