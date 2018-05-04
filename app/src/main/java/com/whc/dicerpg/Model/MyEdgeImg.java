package com.whc.dicerpg.Model;



import android.opengl.GLSurfaceView;

import com.whc.dicerpg.View.From2DTo3DUtil;
import com.whc.dicerpg.View.TextureRectangular;

import org.jbox2d.dynamics.Body;
import javax.microedition.khronos.opengles.GL10;

import static com.whc.dicerpg.View.Constant.RATE;


public class MyEdgeImg implements MyBody
{
	public Body body;
	public float width;
	public float height;
	int textureid;
	TextureRectangular tr;
	GLSurfaceView gv;

	public MyEdgeImg(Body body, float width, float height, int textureid, TextureRectangular tr, GLSurfaceView gv) {
		this.body = body;
		this.width = width;
		this.height = height;
		this.textureid = textureid;
		this.tr = tr;
		this.gv = gv;
	}

	public void drawSelf(GL10 gl)
	{ 		 
		float x=body.getPosition().x*RATE;
		float y=body.getPosition().y*RATE;
		float point[]={x,y};
		point= From2DTo3DUtil.point3D(point);
		gl.glPushMatrix();
		gl.glTranslatef(point[0], point[1], 0);
		tr.drawSelf(gl,textureid,-8f);
		gl.glPopMatrix();
	}
	//碰撞後執行不同的動作
	public void doAction(float x,float y)
	{
		
	}
}