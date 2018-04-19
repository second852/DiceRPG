package com.whc.dicerpg.View;


public class From2DTo3DUtil 
{
	public static float SCREEN_WIDTH_STANDARD=800;  //屏幕標準寬度
	public static float SCREEN_HEIGHT_STANDARD=480;  //屏幕標準高度
	public static float RATIO=SCREEN_WIDTH_STANDARD/SCREEN_HEIGHT_STANDARD;//屏幕寬高比

	public static float[] Vertices(float width,float height)
	{
		float halfwidth=width/2;
		float halfheight=height/2;
		float vertices[]=new float[18];

		
		vertices[0]=(-halfwidth)/(SCREEN_WIDTH_STANDARD/2)*RATIO;
		vertices[1]=(halfheight)/(SCREEN_HEIGHT_STANDARD/2);
		vertices[2]=0;
		
		vertices[3]=(-halfwidth)/(SCREEN_WIDTH_STANDARD/2)*RATIO;
		vertices[4]=(-halfheight)/(SCREEN_HEIGHT_STANDARD/2);
		vertices[5]=0;
		
		vertices[6]=(halfwidth)/(SCREEN_WIDTH_STANDARD/2)*RATIO;
		vertices[7]=halfheight/(SCREEN_HEIGHT_STANDARD/2);
		vertices[8]=0;
		
		vertices[9]=(halfwidth)/(SCREEN_WIDTH_STANDARD/2)*RATIO;
		vertices[10]=halfheight/(SCREEN_HEIGHT_STANDARD/2);
		vertices[11]=0;
		
		vertices[12]=(-halfwidth)/(SCREEN_WIDTH_STANDARD/2)*RATIO;
		vertices[13]=(-halfheight)/(SCREEN_HEIGHT_STANDARD/2);
		vertices[14]=0;
		
		vertices[15]=(halfwidth)/(SCREEN_WIDTH_STANDARD/2)*RATIO;
		vertices[16]=(-halfheight)/(SCREEN_HEIGHT_STANDARD/2);
		vertices[17]=0;
		
		return vertices;
	}
	
	public static float[] point3D(float[] point)
	{
		float[] p=new float[2];
		p[0]=(point[0]/(SCREEN_WIDTH_STANDARD/2)-1)*RATIO;
		p[1]=1-point[1]/(SCREEN_HEIGHT_STANDARD/2);
		return p;
	}
}
