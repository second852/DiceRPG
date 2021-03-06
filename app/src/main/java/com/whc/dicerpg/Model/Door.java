package com.whc.dicerpg.Model;

import android.opengl.GLSurfaceView;

import com.whc.dicerpg.View.From2DTo3DUtil;
import com.whc.dicerpg.View.TextureRectangular;

import org.jbox2d.dynamics.Body;

import javax.microedition.khronos.opengles.GL10;

import static com.whc.dicerpg.View.Constant.RATE;

public class Door implements MyBody {

    public Body body;
    public float width;
    public float height;
    int textureid;
    TextureRectangular tr;
    GLSurfaceView gv;

    public Door(Body body, float width, float height, int textureid, TextureRectangular tr, GLSurfaceView gv) {
        this.body = body;
        this.width = width;
        this.height = height;
        this.textureid = textureid;
        this.tr = tr;
        this.gv = gv;
    }

    @Override
    public void drawSelf(GL10 gl) {
        float x=body.getPosition().x*RATE;
        float y=body.getPosition().y*RATE;
        float point[]={x,y};
        point= From2DTo3DUtil.point3D(point);
        gl.glPushMatrix();
        gl.glTranslatef(point[0], point[1], 0);
        tr.drawSelf(gl,textureid,-8f);
        gl.glPopMatrix();
    }

    @Override
    public void doAction(float x, float y) {

    }
}
