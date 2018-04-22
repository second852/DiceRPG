package com.whc.dicerpg.Util;

enum ScreenOrien
{
	HP,  //表示橫屏的枚舉值
	SP   //表示豎屏的枚舉值
}

//縮放計算的結果
public class ScreenScaleResult
{
	public int lucX;//左上角X坐標
	public int lucY;//左上角y坐標
	public float ratio;//縮放比例
	public ScreenOrien so;//橫豎屏情況	
	
	public ScreenScaleResult(int lucX,int lucY,float ratio,ScreenOrien so)
	{
		this.lucX=lucX;
		this.lucY=lucY;
		this.ratio=ratio;
		this.so=so;
	}
	
	public String toString()
	{
		return "lucX="+lucX+", lucY="+lucY+", ratio="+ratio+", "+so;
	}
}