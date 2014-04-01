package com.company;

import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created with IntelliJ IDEA.
 * User: ^azu-nyan^
 * Date: 01.04.14
 * Time: 12:06
 * To change this template use File | Settings | File Templates.
 */
public class PlayerControl implements MouseMotionListener, KeyListener {

    Ship ship;
    boolean on = true;
    int maxLength = 500;
    int minLength = 20;

    PlayerControl(Ship ship) {
        this.ship = ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (on) {
            Vec2 screenShipPos = MainFrame.toScreenCords(ship.getCenter());
            Vec2 thrustDirection = new Vec2(e.getX() - screenShipPos.x, e.getY() - screenShipPos.y);
            thrustDirection.y = -thrustDirection.y;
            if (thrustDirection.length() > minLength) {
                ship.getControl().on();
                ship.getControl().setDirection(thrustDirection);
                ship.getControl().setThrustingScale(Math.min(maxLength-minLength, thrustDirection.length()) / (maxLength - minLength) );
            } else {
                ship.getControl().off();
            }
           // ship.mainModule.getBody().setTransform(MainFrame.toPhysCords(new Vec2(e.getX(), e.getY())), 0);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
