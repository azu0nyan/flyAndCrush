package com.company;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

/**
 * Created with IntelliJ IDEA.
 * User: azu
 * Date: 01.04.14
 * Time: 3:32
 * To change this template use File | Settings | File Templates.
 */
public class DrawablePhysicsGameObject extends PhysicsGameObject implements DrawableBox {

    private float sizeX;
    private float sizeY;

    DrawablePhysicsGameObject(Vec2 center, float sizeX, float sizeY, float density, float friction, BodyType type){
        super();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        setBody(Physics.getInstance().createBody(center, sizeX, sizeY, density, friction, type));
    }


    public float getAngle(){
        return getBody().getAngle();
    }

    public Vec2 getPosition(){
        return getBody().getPosition();
    }

    public float getSizeX() {
        return sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }


}
