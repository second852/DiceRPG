package com.whc.dicerpg.Model;

import android.opengl.GLSurfaceView;
import android.util.Log;

import com.whc.dicerpg.View.From2DTo3DUtil;
import com.whc.dicerpg.View.TextureRectangular;

import org.jbox2d.dynamics.Body;

import javax.microedition.khronos.opengles.GL10;

import static com.whc.dicerpg.View.Constant.RATE;

public class Stone implements MyBody{

    public Body body;
    float radius;
    int textureid;
    TextureRectangular tr;
    GLSurfaceView gv;

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
        float angle=body.getAngle()-5;
        gl.glPushMatrix();
        gl.glTranslatef(point[0], point[1], 0);
        gl.glRotatef(angle, 0, 0, 1);
        tr.drawSelf(gl,textureid,-3f);
        gl.glPopMatrix();
    }

    @Override
    public void doAction(float x, float y) {

    }
}
