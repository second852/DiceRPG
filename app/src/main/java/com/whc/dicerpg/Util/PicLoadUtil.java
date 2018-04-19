package com.whc.dicerpg.Util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class PicLoadUtil 
{	
	public static Bitmap loadBM(Resources res, String picname)
    {
    	Bitmap result=null;
    	try
    	{   		
    		InputStream in=res.getAssets().open(picname);
			int ch=0;
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    while((ch=in.read())!=-1)
		    {
		      	baos.write(ch);  
		    }      
		    byte[] buff=baos.toByteArray();
		    baos.close();
		    in.close();
    		result= BitmapFactory.decodeByteArray(buff, 0, buff.length);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}    	
    	return result;
    }
}
