package com.company;

import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

/**
 * Created with IntelliJ IDEA.
 * User: ^azu-nyan^
 * Date: 01.04.14
 * Time: 6:37
 * To change this template use File | Settings | File Templates.
 */
public class ShipModule extends DrawablePhysicsGameObject {
    ShipModule(Vec2 center, float sizeX, float sizeY, float mass) {
        super(center, sizeX, sizeY, 1, 1, BodyType.DYNAMIC);
        MassData massData = new MassData();
        massData.mass = mass;
        getBody().setMassData(massData);
    }
}
