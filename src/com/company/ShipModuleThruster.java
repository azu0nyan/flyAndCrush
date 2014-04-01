package com.company;

import org.jbox2d.common.Vec2;

/**
 * Created with IntelliJ IDEA.
 * User: ^azu-nyan^
 * Date: 01.04.14
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */
public class ShipModuleThruster extends ShipModule {

    float thrust;
    Vec2 direction;
    boolean on = true;
    float thrustingSpeedLimit = 20;

    ShipModuleThruster(Vec2 center, float sizeX, float sizeY, float mass, float thrust) {
        super(center, sizeX, sizeY, mass);
        this.thrust = thrust;
    }

    public void update(float dt){
        if(on && direction != null ){
            Vec2 temp = direction.clone();
            temp.normalize();
            temp = temp.mul(thrust * dt);
            if(getBody().getLinearVelocity().length() < 20 || getBody().getLinearVelocity().length() > getBody().getLinearVelocity().add(temp).length())
            getBody().applyLinearImpulse(temp, getBody().getLocalCenter());
        }
    }

    public float getThrust() {
        return thrust;
    }

    public void setThrust(float thrust) {
        this.thrust = thrust;
    }

    public Vec2 getDirection() {
        return direction;
    }

    public void setDirection(Vec2 direction) {
        this.direction = direction;
    }

    public void on(){
        on = true;
    }

    public void off(){
        on = false;
    }
}
