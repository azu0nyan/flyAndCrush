package com.company;

import org.jbox2d.common.Vec2;

/**
 * Created with IntelliJ IDEA.
 * User: ^azu-nyan^
 * Date: 01.04.14
 * Time: 10:30
 * To change this template use File | Settings | File Templates.
 */
public class ShipModuleEngine extends ShipModule {

    private float force;
    private boolean on;
    private boolean sinusoidalThrustingOn = false;
    private float sinFreq = 2;// sec/ 0 -> PI

    private float sinForceDelta = 2;

    private float time = 0;
    ShipModuleEngine(Ship ship, Vec2 center, float sizeX, float sizeY, float mass, float force, ShipModule owner) {
        super(ship, center, sizeX, sizeY, mass, owner);
        this.force = force;
    }

    public void on(){
        on = true;
    }

    public void off(){
        on = false;
    }

    public void update(float dt){
        time += dt;
        if(on){
            float tempForce = force;
            if(sinusoidalThrustingOn){
                float df = (float) (Math.sin(time * Math.PI * sinFreq) * sinForceDelta);
                tempForce += (df > 0)?df:1.1 *df;
            }
            getBody().applyLinearImpulse(new Vec2(0, dt * tempForce), getBody().getLocalCenter());
        }
    }

    public boolean isSinusoidalThrustingOn() {
        return sinusoidalThrustingOn;
    }

    public void setSinusoidalThrustingOn(boolean sinusoidalThrustingOn) {
        this.sinusoidalThrustingOn = sinusoidalThrustingOn;
    }

    public float getSinFreq() {
        return sinFreq;
    }

    public void setSinFreq(float sinFreq) {
        this.sinFreq = sinFreq;
    }

    public float getSinForceDelta() {
        return sinForceDelta;
    }

    public void setSinForceDelta(float sinForceDelta) {
        this.sinForceDelta = sinForceDelta;
    }

    public void setForce(float force) {
        this.force = force;
    }

    public float getForce() {
        return force;
    }
}
