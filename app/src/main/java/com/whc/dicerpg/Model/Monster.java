package com.whc.dicerpg.Model;

import com.whc.dicerpg.View.FirstOneView;
import com.whc.dicerpg.View.From2DTo3DUtil;
import com.whc.dicerpg.View.TextureRectangular;

import org.jbox2d.dynamics.Body;

import javax.microedition.khronos.opengles.GL10;

import static com.whc.dicerpg.View.Constant.RATE;

public class Monster extends MyBody {


    public Body body;
    float radius;
    int textureid;
    TextureRectangular tr;
    FirstOneView fo;

    public Monster(Body body, float radius, int textureid, TextureRectangular tr, FirstOneView fo) {
        this.body = body;
        this.radius = radius;
        this.textureid = textureid;
        this.tr = tr;
        this.fo = fo;
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
