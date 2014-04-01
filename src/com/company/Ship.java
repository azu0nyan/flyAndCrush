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

    Ship(Vec2 center){
        modules = new CopyOnWriteArrayList<>();
        ShipModuleEngine e = new ShipModuleEngine(center, 1, 1, 1, 10);
        modules.add(e);
        e.on();
    }
}
