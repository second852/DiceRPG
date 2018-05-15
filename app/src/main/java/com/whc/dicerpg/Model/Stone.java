package com.whc.dicerpg.Model;

import android.opengl.GLSurfaceView;
import android.util.Log;

import com.whc.dicerpg.View.From2DTo3DUtil;
import com.whc.dicerpg.View.TextureRectangular;

import org.jbox2d.dynamics.Body;

import javax.microedition.khronos.opengles.GL10;

import static com.whc.dicerpg.View.Constant.RATE;
import static com.whc.dicerpg.View.Constant.Stone_PIC;
import static com.whc.dicerpg.View.Constant.TIME_STEP;

public class Stone implements MyBody{

    public Body body;
    float radius;

    public int getTextureid() {
        return textureid;
    }

    public void setTextureid(int textureid) {
        this.textureid = textureid;
    }

    int textureid;
    TextureRectangular tr;
    GLSurfaceView gv;
    private static final double DEGREES_TO_RADIANS = (double)(Math.PI/180);

    public Stone(Body body, float radius, int textureid, TextureRectangular tr, GLSurfaceView gv) {
        this.body = body;
        this.radius = radius;
        this.textureid = textureid;
        this.tr = tr;
        this.gv = gv;
    }

    @Override
    public void drawSelf(GL10 gl) {
        float x=body.getPosition().x*RATE;
        float y=body.getPosition().y*RATE;
        float point[]={x,y};
        point=From2DTo3DUtil.point3D(point);
        gl.glPushMatrix();
        gl.glTranslatef(point[0], point[1], 0);
        tr.drawSelf(gl,textureid,-8f);
        gl.glPopMatrix();
    }


    @Override
    public void doAction(float x, float y) {

    }


}
