package com.company;

import org.jbox2d.common.Vec2;

/**
 * Created with IntelliJ IDEA.
 * User: ^azu-nyan^
 * Date: 01.04.14
 * Time: 12:41
 * To change this template use File | Settings | File Templates.
 */
public class ShipModuleWeapon extends ShipModule {
    ShipModuleWeapon(Ship ship, Vec2 center, float sizeX, float sizeY, float mass, ShipModule shipModule) {
        super(ship, center, sizeX, sizeY, mass, shipModule);
    }

    public void createJoins(){
        if(getOwner() != null){
            Physics.getInstance().createRopeJoint(getOwner().getBody(), getBody(), 6);
        }
    }
}
