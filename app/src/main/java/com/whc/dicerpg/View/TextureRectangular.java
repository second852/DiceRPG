package com.whc.dicerpg.View;



import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class TextureRectangular 
{
	private FloatBuffer mVertexBuffer;//頂點坐標數據緩衝
    private FloatBuffer mTextureBuffer;//頂點紋理數據緩衝
    int vCount;
    
    public TextureRectangular(float width, float height)
    {
    	float vertices[]=From2DTo3DUtil.Vertices(width,height);
    	this.vCount=vertices.length/3;
    	
    	ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);//創建頂點數據緩衝
    	vbb.order(ByteOrder.nativeOrder());//設置字節順序
    	mVertexBuffer = vbb.asFloatBuffer();//轉換為int型緩衝
    	mVertexBuffer.put(vertices);//向緩衝區中放入頂點坐標數據
        mVertexBuffer.position(0);//設置緩衝區起始位置
        
        float textureCoors[]=
    	{
    		0,0,
    		0,1,
    		1,0,
    		1,0,
    		0,1,
    		1,1
    	};
        
        ByteBuffer tbb = ByteBuffer.allocateDirect(textureCoors.length*4);//創建頂點紋理數據緩衝
        tbb.order(ByteOrder.nativeOrder());//設置字節順序
        mTextureBuffer= tbb.asFloatBuffer();//轉換為Float型緩衝
        mTextureBuffer.put(textureCoors);//向緩衝區中放入頂點著色數據
        mTextureBuffer.position(0);//設置緩衝區起始位置
    }
    
    public void drawSelf(GL10 gl, int textureId, float z)
    { 		
    	gl.glTranslatef(0, 0, z);
    	
    	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//啟用頂點坐標數組
    	
    	gl.glVertexPointer
        (
        		3,				//每個頂點的坐標數量為3  xyz 
        		GL10.GL_FLOAT,	//頂點坐標值的類型為 GL_FIXED
        		0, 				//連續頂點坐標數據之間的間隔
        		mVertexBuffer	//頂點坐標數據
        );
    	
    	//開啟紋理
        gl.glEnable(GL10.GL_TEXTURE_2D);
        //允許使用紋理數組
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //為畫筆指定紋理uv坐標數據
        gl.glTexCoordPointer
        (
        		2, 					//每個頂點兩個紋理坐標數據 S、T
        		GL10.GL_FLOAT, 		//數據類型
        		0, 					//連續紋理坐標數據之間的間隔
        		mTextureBuffer		//紋理坐標數據
        );
        
        //為畫筆綁定指定名稱ID紋理		
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
        
        //繪製圖形
        gl.glDrawArrays
        (
        		GL10.GL_TRIANGLES,
        		0, 
        		vCount
        );
    }
}
