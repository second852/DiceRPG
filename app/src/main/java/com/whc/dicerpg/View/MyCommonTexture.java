package com.whc.dicerpg.View;

import javax.microedition.khronos.opengles.GL10;


public class MyCommonTexture 
{
	TextureRectangular tr;
	
	public MyCommonTexture(TextureRectangular tr)
	{
		this.tr=tr;
	}
	
	public void drawself(GL10 gl, int textureId, float[] point, float z)
	{
		gl.glPushMatrix();
		gl.glTranslatef(point[0], point[1], 0);
		tr.drawSelf(gl, textureId, z);
		gl.glPopMatrix();
	}
}
