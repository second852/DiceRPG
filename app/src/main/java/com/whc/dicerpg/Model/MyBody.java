package com.whc.dicerpg.Model;

import javax.microedition.khronos.opengles.GL10;

public abstract class MyBody 
{
	public abstract void drawSelf(GL10 gl);
	public abstract void doAction(float x,float y);
}
