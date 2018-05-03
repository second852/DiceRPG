package com.whc.dicerpg.Model;

import javax.microedition.khronos.opengles.GL10;

public interface MyBody
{
	  void drawSelf(GL10 gl);
	  void doAction(float x,float y);
}
