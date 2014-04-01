package com.company;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

/**
 * Created with IntelliJ IDEA.
 * User: azu
 * Date: 01.04.14
 * Time: 3:30
 * To change this template use File | Settings | File Templates.
 */
public class PhysicsGameObject extends GameObject implements Drawable{

    Body body;

    PhysicsGameObject(){
        Game.getInstance().addGameObject(this);
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public float getAngle(){
        return getBody().getAngle();
    }

    public Vec2 getPosition(){
        return getBody().getPosition();
    }

}
