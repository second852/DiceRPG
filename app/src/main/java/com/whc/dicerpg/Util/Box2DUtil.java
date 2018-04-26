package com.whc.dicerpg.Util;

import com.whc.dicerpg.Model.FireAttack;
import com.whc.dicerpg.Model.Monster;
import com.whc.dicerpg.View.Constant;
import com.whc.dicerpg.View.FirstOneView;
import com.whc.dicerpg.View.TextureRectangular;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import static com.whc.dicerpg.View.Constant.RATE;

public class Box2DUtil {


    //Fire Create
    public  static  FireAttack createFireAttack
            (
                    float x,//x坐標
                    float y,//y坐標
                    float radius,//半徑
                    boolean isStatic,//是否為靜止的
                    World world,//世界
                    TextureRectangular tr,
                    int textureid,
                    FirstOneView fo
            )
    {
        //創建圓描述對象
        CircleDef shape = new CircleDef();
//        //設置密度
        if(isStatic)
        {
            shape.density = 0;
        }
        else
        {
            shape.density = 2.0f;
        }
        //設置摩擦係數
        shape.friction = 0.1f;
        //設置能量損失率（反彈）
//        shape.restitution = 0.3f;
        //設置半徑
        shape.radius=radius/ RATE;
        //創建剛體描述對象
        BodyDef bodyDef = new BodyDef();
        //設置位置
        bodyDef.position.set(x/ RATE, y/ RATE);
        //在世界中創建剛體
        Body bodyTemp = world.createBody(bodyDef);
        //設為子彈類

        bodyTemp.setBullet(true);
        //指定剛體形狀
        bodyTemp.createShape(shape);
        bodyTemp.setMassFromShapes();

        return new FireAttack(bodyTemp,radius,textureid,tr,fo);
    }


    public static Monster createMonster
            (
                    float x,//x坐標
                    float y,//y坐標
                    float width,//寬
                    float height,//高
                    boolean isStatic,//是否為靜止的
                    World world,//世界
                    TextureRectangular tr,
                    int textureid,
                    FirstOneView fo
            )
    {
        //創建多邊形描述對象
        PolygonDef shape = new PolygonDef();
        //設置摩擦係數
        shape.friction = 0.1f;
        //設置密度
        if(isStatic)
        {
            shape.density = 0;
        }
        else
        {
            shape.density = 2.0f;
        }
//        //設置能量損失率（反彈）
        shape.restitution = 0.3f;
        shape.setAsBox(width/2/RATE, height/2/RATE);
        //創建剛體描述對象
        BodyDef bodyDef = new BodyDef();
        //設置位置
        bodyDef.position.set(x/RATE, y/RATE);
        //在世界中創建剛體
        Body bodyTemp= world.createBody(bodyDef);
        //指定剛體形狀
        bodyTemp.createShape(shape);
        bodyTemp.setMassFromShapes();
        return new Monster(bodyTemp,width,height,textureid,tr,fo);
    }
}
