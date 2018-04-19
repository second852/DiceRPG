package com.whc.dicerpg.Model;

import com.bn.mfttq.TextureRectangular;
import com.bn.util.From2DTo3DUtil;
import com.bn.view.GameView;

import org.jbox2d.dynamics.Body;

import javax.microedition.khronos.opengles.GL10;

import static com.bn.mfttq.Constant.RATE;

public class MyEdgeImg extends MyBody
{
	public Body body;
	public float width;
	public float height;
	int textureid;
	TextureRectangular tr;
	GameView gv;
	public int bmIndex=0;//當前圖片索引
		
	public MyEdgeImg(Body body, float width, float height, int textureid, TextureRectangular tr, GameView gv)
	{
		this.body=body;
		this.width=width;
		this.height=height;
		this.textureid=textureid;
		this.tr=tr;
		this.gv=gv;
	}
	
	public void drawSelf(GL10 gl)
	{ 		 
		float x=body.getPosition().x*RATE;
		float y=body.getPosition().y*RATE;
		float point[]={x,y};
		point=From2DTo3DUtil.point3D(point);
		
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