package com.liam.topdown.objects;

import com.liam.topdown.framework.GameObject;
import com.liam.topdown.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Bullet extends GameObject {
    float velx, vely, x, y;
    int damage;
    public Bullet(float x, float y, int damage, float velx, float vely, ObjectId id) {
        super(x, y, id);
        this.velx = velx;
        this.vely = vely;
        this.damage = damage;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velx;
        y += vely;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(100, 100, 100));
        g.fillOval((int)x, (int)y, 5,5);
        for (int i = 0; i < 5; i++){
            g.setColor(new Color(100, 100, 100, 255 - (i*20)));
            g.fillOval((int)Math.floor((int)x - velx * (i/2)), (int)Math.floor((int)y - vely * (i/2)), 5, 5);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 5, 5);
    }
}
