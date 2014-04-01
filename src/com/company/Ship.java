package com.company;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

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
        ShipModuleEngine e = new ShipModuleEngine(this, center, 1, 1, 1, 20, null);
        Vec2 tCenter = center.clone();
        tCenter.x -= 0.75f;
        ShipModuleThruster t = new ShipModuleThruster(this, tCenter, 0.5f, 0.5f, 1, 50f, e);
        Vec2 wCenter = center.clone();
        wCenter.y -= 5;
        ShipModuleWeapon w = new ShipModuleWeapon(this, wCenter, 0.3f, 0.7f, 1f,e);
        t.off();
        e.off();
        mainModule = e;
        control = t;
        t.setThrust(getTotalMass() * 10);
        modules.stream().forEach(ShipModule::createJoins);
        //mainModule.getBody().setType(BodyType.KINEMATIC);
    }
    public void addModule(ShipModule module){
        if(!modules.contains(module)){
            modules.add(module);
        }
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
