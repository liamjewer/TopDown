package com.liam.topdown.objects;

import com.liam.topdown.framework.GameObject;
import com.liam.topdown.framework.ObjectHandler;
import com.liam.topdown.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Bullet extends GameObject {
    float velx, vely, x, y;
    int damage;
    ObjectHandler handler;

    public Bullet(float x, float y, int damage, double facing, ObjectHandler handler, ObjectId id) {
        super(x, y, id);
        this.velx = (float) Math.cos(-facing) * 10;
        this.vely = (float) Math.sin(-facing) * 10;
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.handler = handler;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velx;
        y += vely;

        for(int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if(temp.getId() == ObjectId.Wall && getBounds().intersects(temp.getBounds())){
                handler.removeObject(this);
            }
        }
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
