package com.liam.topdown.objects;

import com.liam.topdown.framework.GameObject;
import com.liam.topdown.framework.ObjectHandler;
import com.liam.topdown.framework.ObjectId;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

public class Sword extends GameObject {

    int width, height, damage;
    double angle, end, speed;
    float x, y;
    ObjectHandler handler;
    Player player;
    Rectangle rect;
    Shape shape;
    AffineTransform transform = new AffineTransform();

    public Sword(float x, float y, int width, int height, double start, double end, double speed, int damage, Player player, ObjectHandler handler, ObjectId id) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        angle = start - Math.toDegrees(player.getFacing()) - 90;
        this.end = end;
        this.speed = speed;
        this.damage = damage;
        this.handler = handler;
        this.player = player;
        this.id = id;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x = player.getX();
        y = player.getY();

        if(angle < end - Math.toDegrees(player.getFacing()) - 90){
            angle += speed;
        }else {
            handler.removeObject(this);
        }

        transform = new AffineTransform();
        rect = new Rectangle((int)player.getX() - width/2,(int)player.getY(), width,height);
        transform.rotate(Math.toRadians(angle),  rect.getX() + rect.width/2, rect.getY());
        shape = transform.createTransformedShape(rect);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.MAGENTA);
        g2d.fill(shape);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
