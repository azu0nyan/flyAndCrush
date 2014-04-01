package com.company;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.joints.*;

/**
 * Created with IntelliJ IDEA.
 * User: azu
 * Date: 01.04.14
 * Time: 1:20
 * To change this template use File | Settings | File Templates.
 */
public class Physics {

    World b2World;

    private static Physics p = new Physics();

    public static Physics getInstance() {
        return p;
    }

    public void init() {
        b2World = createWorld(new Vec2(0, -10f));
    }

    public void step(float timeS){
        b2World.step(timeS, 8, 3);
    }

    public World createWorld(Vec2 gravity) {
        return b2World = new World(gravity);
    }



    public Body createBody(Vec2 center, float sizeX, float sizeY, float density, float friction, BodyType type) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(center);
        bodyDef.type = type;
        bodyDef.angularDamping = 0.1f;

        Body b = b2World.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sizeX / 2.0f, sizeY / 2.0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = 0;
        b.createFixture(fixtureDef);

        return b;
    }

    public  void destroyBody(Body b){
        b2World.destroyBody(b);
    }


    public Joint createWeldJoint(Body a, Body b){
        WeldJointDef weldJointDef = new WeldJointDef();
        weldJointDef.initialize(a,b, new Vec2(0f, 0f));
        return b2World.createJoint(weldJointDef);
    }

    public Joint createRevoluteJoint(Body a, Body b){
        RevoluteJointDef revoluteJointDef = new RevoluteJointDef();
        revoluteJointDef.initialize(a, b, a.getLocalCenter());
        return b2World.createJoint(revoluteJointDef);
    }

    public Joint createRopeJoint(Body a, Body b, float length) {
        RopeJointDef ropeJointDef = new RopeJointDef();
        ropeJointDef.bodyA = a;
        ropeJointDef.bodyB = b;
        ropeJointDef.maxLength = length;

        return b2World.createJoint(ropeJointDef);
    }
    public Joint createDiscanceJoint(Body a, Body b){
        DistanceJointDef distanceJointDef = new DistanceJointDef();
        distanceJointDef.initialize(a, b, new Vec2(0, 0), new Vec2(0,0));
        return b2World.createJoint(distanceJointDef);
    }
}
