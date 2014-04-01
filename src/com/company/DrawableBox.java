package com.company;

import org.jbox2d.common.Vec2;

/**
 * Created with IntelliJ IDEA.
 * User: ^azu-nyan^
 * Date: 01.04.14
 * Time: 5:09
 * To change this template use File | Settings | File Templates.
 */
public interface DrawableBox extends Drawable{

    default DrawingType getDrawingType(){
        return DrawingType.SHAPE;
    }

    public float getSizeX();

    public float getSizeY();

}
