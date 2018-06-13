package com.whc.dicerpg.Model;

import android.opengl.GLSurfaceView;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cube
{  
	private IntBuffer VertexBuffer;
	private FloatBuffer TextureBuffer;
	private int textureIDs[];
	GLSurfaceView mv;
	int vCount=0;
	float xAngle=0;
    float yAngle=0;
    float zAngle=0;

    boolean isRotafe;



	public Cube(GLSurfaceView mv)
    {  
    	this.mv=mv;

    	final int UNIT_SIZE=5000;

    	int vertice[]=new int[]
       {  
    
           -4*UNIT_SIZE,4*UNIT_SIZE,-4*UNIT_SIZE,
           -4*UNIT_SIZE,4*UNIT_SIZE,4*UNIT_SIZE,
           4*UNIT_SIZE,4*UNIT_SIZE,4*UNIT_SIZE, 
           
           
           -4*UNIT_SIZE,4*UNIT_SIZE,-4*UNIT_SIZE,
           4*UNIT_SIZE,4*UNIT_SIZE,4*UNIT_SIZE,                 
           4*UNIT_SIZE,4*UNIT_SIZE,-4*UNIT_SIZE,
           
           -4*UNIT_SIZE,4*UNIT_SIZE,4*UNIT_SIZE,
           -4*UNIT_SIZE,-4*UNIT_SIZE,4*UNIT_SIZE,
           4*UNIT_SIZE,-4*UNIT_SIZE,4*UNIT_SIZE,
           
           -4*UNIT_SIZE,4*UNIT_SIZE,4*UNIT_SIZE,
           4*UNIT_SIZE,-4*UNIT_SIZE,4*UNIT_SIZE, 
           4*UNIT_SIZE,4*UNIT_SIZE,4*UNIT_SIZE,
          
           4*UNIT_SIZE,4*UNIT_SIZE,4*UNIT_SIZE,  
           4*UNIT_SIZE,-4*UNIT_SIZE,4*UNIT_SIZE,
           4*UNIT_SIZE,-4*UNIT_SIZE,-4*UNIT_SIZE,
           
           4*UNIT_SIZE,4*UNIT_SIZE,4*UNIT_SIZE, 
           4*UNIT_SIZE,-4*UNIT_SIZE,-4*UNIT_SIZE,           
           4*UNIT_SIZE,4*UNIT_SIZE,-4*UNIT_SIZE, 
           
           4*UNIT_SIZE,4*UNIT_SIZE,-4*UNIT_SIZE,
           4*UNIT_SIZE,-4*UNIT_SIZE,-4*UNIT_SIZE,          
           -4*UNIT_SIZE,-4*UNIT_SIZE,-4*UNIT_SIZE,
           
           4*UNIT_SIZE,4*UNIT_SIZE,-4*UNIT_SIZE,
           -4*UNIT_SIZE,-4*UNIT_SIZE,-4*UNIT_SIZE,           
           -4*UNIT_SIZE,4*UNIT_SIZE,-4*UNIT_SIZE,
           
           -4*UNIT_SIZE,4*UNIT_SIZE,-4*UNIT_SIZE,          
           -4*UNIT_SIZE,-4*UNIT_SIZE,-4*UNIT_SIZE,
           -4*UNIT_SIZE,-4*UNIT_SIZE,4*UNIT_SIZE,
           
           -4*UNIT_SIZE,4*UNIT_SIZE,-4*UNIT_SIZE,
           -4*UNIT_SIZE,-4*UNIT_SIZE,4*UNIT_SIZE,          
           -4*UNIT_SIZE,4*UNIT_SIZE,4*UNIT_SIZE,
  
           -4*UNIT_SIZE,-4*UNIT_SIZE,4*UNIT_SIZE,           
           -4*UNIT_SIZE,-4*UNIT_SIZE,-4*UNIT_SIZE,          
            4*UNIT_SIZE,-4*UNIT_SIZE,-4*UNIT_SIZE,  
            
           -4*UNIT_SIZE,-4*UNIT_SIZE,4*UNIT_SIZE,
           4*UNIT_SIZE,-4*UNIT_SIZE,-4*UNIT_SIZE,
           4*UNIT_SIZE,-4*UNIT_SIZE,4*UNIT_SIZE
 
      };
    	vCount=36;
    	
    	ByteBuffer vbb= ByteBuffer.allocateDirect(vertice.length*4);
    	vbb.order(ByteOrder.nativeOrder());
    	VertexBuffer=vbb.asIntBuffer();
    	VertexBuffer.put(vertice);
    	VertexBuffer.position(0);
    	   	
    	
    	float Texturecood[]=new float[]
    	{
    		0,0,
    		0,1,
    		1,1,
    		0,0,
    		1,1,
    		1,0,
    		
    		0,0,
    		0,1,
    		1,1,
    		0,0,
    		1,1,
    		1,0,
    		
    		0,0,
    		0,1,
    		1,1,
    		0,0,
    		1,1,
    		1,0,
    		
    		0,0,
    		0,1,
    		1,1,
    		0,0,
    		1,1,
    		1,0,
    		
    		0,0,
    		0,1,
    		1,1,
    		0,0,
    		1,1,
    		1,0,
    		
    		0,0,
    		0,1,
    		1,1,
    		0,0,
    		1,1,
    		1,0
    	};
    	ByteBuffer cbb= ByteBuffer.allocateDirect(Texturecood.length*4);
    	cbb.order(ByteOrder.nativeOrder());
    	TextureBuffer=cbb.asFloatBuffer();
    	TextureBuffer.put(Texturecood);
    	TextureBuffer.position(0);
    }


	public void drawSelf(GL10 gl)
	{
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	
	
		gl.glRotatef(xAngle, 1, 0, 0);
		gl.glRotatef(zAngle, 0, 0, 1);
		gl.glRotatef(yAngle, 0, 1, 0);

		
		gl.glVertexPointer
		(
				3, 
				GL10.GL_FIXED,
				0,
				VertexBuffer
	   );
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, TextureBuffer);
		for(int i=0;i<6;i++)
		{
			gl.glBindTexture(GL10.GL_TEXTURE_2D,textureIDs[i]);
			gl.glDrawArrays
			(
					GL10.GL_TRIANGLES,
					i*6, 
					6
			);
		}
	}

	public int[] getTextureIDs() {
		return textureIDs;
	}

	public void setTextureIDs(int[] textureIDs) {
		this.textureIDs = textureIDs;
	}
}
