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

    float thrustingScale = 1;

    ShipModuleThruster(Ship ship, Vec2 center, float sizeX, float sizeY, float mass, float thrust, ShipModule owner) {
        super(ship, center, sizeX, sizeY, mass, owner);
        this.thrust = thrust;
    }

    public void update(float dt){
        if(on && direction != null ){
            Vec2 temp = direction.clone();
            temp.normalize();
            temp = temp.mul(thrustingScale * thrust * dt);
            if(getBody().getLinearVelocity().length() < thrustingSpeedLimit || getBody().getLinearVelocity().length() > getBody().getLinearVelocity().add(temp).length())
            getBody().applyLinearImpulse(temp, getBody().getLocalCenter());
        }
    }

    public void setThrustingScale(float thrustingScale) {
        this.thrustingScale = thrustingScale;
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
