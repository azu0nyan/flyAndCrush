package com.company;


import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.image.BufferStrategy;
import java.util.List;

public class MainFrame extends JFrame {

    static Game game;
    static int physDx = 1000;
    static int physDy = 1000;
    static float physScale = 50;
    Graphics2D g;

    public static void main(String[] args) {
        game = Game.getInstance();
        game.init();
        new MainFrame();
    }

    public MainFrame(){
        setSize(1920, 1080);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createBufferStrategy(2);
        long t = System.currentTimeMillis();
        PlayerControl control = new PlayerControl(game.getPlayer());
        this.addMouseMotionListener(control);
        this.addKeyListener(control);
        while(true){
            long tempT = System.currentTimeMillis();
            long dt = tempT - t;
            t = tempT;
            game.update((float) (dt / 1000.0));
            draw();
        }
    }

    public void draw(){
        BufferStrategy bs = getBufferStrategy();
        g = (Graphics2D) bs.getDrawGraphics();

        clearScreen();

        List<Drawable> objectsToDraw = game.getObjectsToDraw();
        for(Drawable drawable : objectsToDraw){
            switch (drawable.getDrawingType()){
                case SHAPE:{
                    drawRectangle((DrawableBox)drawable, Color.BLACK, false);
                }
                case NONE:{
                    break;
                }

            }
        }

        bs.show();
    }



    public void drawRectangle(DrawableBox b, Color c, boolean fill){
        double x = physDx + b.getPosition().x  * physScale;
        double y = physDy - b.getPosition().y  * physScale;
        double width = b.getSizeX() * physScale;
        double height = b.getSizeY() * physScale;
        x -= width / 2.0;
        y -= height / 2.0;

        Rectangle r = new Rectangle((int)x, (int)y, (int)width, (int)height);
        Polygon p = rotateRectangle(r, b.getAngle());
        g.setColor(c);
        if(fill){
            g.fillPolygon(p);
        } else {
            g.drawPolygon(p);
        }
    }

    public Polygon rotateRectangle(Rectangle r, double angle){
        AffineTransform at = AffineTransform.getRotateInstance(-angle, r.getCenterX(), r.getCenterY());

        Polygon p = new Polygon();
        PathIterator i = r.getPathIterator(at);

        while (!i.isDone()) {
            double[] xy = new double[2];
            int segClose = i.currentSegment(xy);
            if(segClose != PathIterator.SEG_CLOSE ){
                p.addPoint((int) xy[0], (int) xy[1]);
            }
            i.next();
        }
        return p;
    }

    public void clearScreen(){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public static Vec2 toScreenCords(Vec2 physCords){


        return new Vec2(physDx + physCords.x * physScale, physDy - physCords.y * physScale);
    }

    public static Vec2 toPhysCords(Vec2 screenCords){
        /*
        dx + x * s = sc
        x = (sc - dx) /s
        dy - y * s = sc
        y = -(sc - dy) /s
         */
        return  new Vec2((screenCords.x - physDx) / physScale, -(screenCords.y - physDy) / physScale);
    }
}


