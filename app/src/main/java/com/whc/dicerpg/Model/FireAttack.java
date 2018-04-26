package com.whc.dicerpg.Model;

import android.util.Log;

import com.whc.dicerpg.View.FirstOneView;
import com.whc.dicerpg.View.From2DTo3DUtil;
import com.whc.dicerpg.View.TextureRectangular;

import org.jbox2d.dynamics.Body;

import javax.microedition.khronos.opengles.GL10;

import static com.whc.dicerpg.View.Constant.OTHER_LOCATION;
import static com.whc.dicerpg.View.Constant.RATE;
import static com.whc.dicerpg.View.Constant.TEXTUREID_PIC;

public class FireAttack extends MyBody {

    public Body body;
    float radius;
    int textureid;
    TextureRectangular tr;
    FirstOneView fo;
    int bmIndex = 0;//當前圖片索引
    public boolean isLive = true;//是否存活
    public boolean isDeleted = false;//是否已經刪除


    public FireAttack(Body body, float radius, int textureid, TextureRectangular tr, FirstOneView fo) {
        this.body = body;
        this.radius = radius;
        this.textureid = textureid;
        this.tr = tr;
        this.fo = fo;
    }

    @Override
    public void drawSelf(GL10 gl) {

        float x = body.getPosition().x * RATE;
        float y = body.getPosition().y * RATE;

        float point[] = {x,y};
        point = From2DTo3DUtil.point3D(point);
//        float angle = -(float) (body.getAngle() * (180.0 / Math.PI));
        gl.glPushMatrix();
        gl.glTranslatef(point[0],point[1] , 0);
        gl.glRotatef(90, 0, 0, 1);

        tr.drawSelf(gl, textureid, -6f);
        gl.glPopMatrix();
    }

    @Override
    public void doAction(float x, float y) {

    }
}
