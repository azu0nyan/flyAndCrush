package com.company;

import org.jbox2d.common.Vec2;

/**
 * Created with IntelliJ IDEA.
 * User: ^azu-nyan^
 * Date: 01.04.14
 * Time: 5:11
 * To change this template use File | Settings | File Templates.
 */
public interface Drawable {
    public float getAngle();

    public Vec2 getPosition();

    default DrawingType getDrawingType(){
        return DrawingType.NONE;
    }
}

