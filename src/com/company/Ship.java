package com.company;

import org.jbox2d.common.Vec2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ^azu-nyan^
 * Date: 01.04.14
 * Time: 6:36
 * To change this template use File | Settings | File Templates.
 */
public class Ship {
    List<ShipModule> modules;
    ShipModule mainModule;

    ShipModuleThruster control;

    Ship(Vec2 center){
        modules = new CopyOnWriteArrayList<>();
        ShipModuleEngine e = new ShipModuleEngine(center, 1, 1, 1, 20);
        Vec2 tCenter = center.clone();
        tCenter.x -= 0.75f;
        ShipModuleThruster t = new ShipModuleThruster(tCenter, 0.5f, 0.5f, 1, 50f);
        Physics.getInstance().createRevoluteJoint(e.getBody(), t.getBody());
        Vec2 wCenter = center.clone();
        wCenter.y -= 5;
        ShipModuleWeapon w = new ShipModuleWeapon(wCenter, 0.7f, 0.7f, 0.2f);
        Physics.getInstance().createRopeJoint(e.getBody(), w.getBody(), 6);
        modules.add(e);
        modules.add(t);
        modules.add(w);
        t.on();
        e.on();
        mainModule = e;
        control = t;
        t.setThrust(getTotalMass() * 10);
    }

    public float getTotalMass(){
        return modules.stream().map(e -> e.getBody().getMass()).reduce(0f, (x, y) -> x + y);
    }
    public ShipModuleThruster getControl() {
        return control;
    }

    public Vec2 getCenter(){
        return mainModule.getPosition();
    }
}
