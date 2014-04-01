package com.company;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: azu
 * Date: 01.04.14
 * Time: 4:53
 * To change this template use File | Settings | File Templates.
 */
public class Game {
    private static Game ourInstance = new Game();
    private Physics p;

    public List<GameObject> gameObjects;

    Ship player;

    public static Game getInstance() {
        return ourInstance;
    }


    public void init(){
        p = Physics.getInstance();
        p.init();

        gameObjects = new CopyOnWriteArrayList<>();
        new DrawablePhysicsGameObject(new Vec2(0, -10), 100, 20, 0, 0, BodyType.STATIC);
        new DrawablePhysicsGameObject(new Vec2(2, 2), 5, 5, 0, 0, BodyType.STATIC);
        player = new Ship(new Vec2(10, 10));

    }

    public Ship getPlayer() {
        return player;
    }

    public void addGameObject(PhysicsGameObject object){
        gameObjects.add(object);
    }
    public List<Drawable> getObjectsToDraw(){
        List<Drawable> res= new ArrayList<>();
        gameObjects.stream().filter(e -> Drawable.class.isAssignableFrom(e.getClass())).forEach(e -> res.add((Drawable) e));
        return res;
    }
    public void update(float v) {
        p.step(v);
        gameObjects.stream().forEach(e -> e.update(v));
    }
}
