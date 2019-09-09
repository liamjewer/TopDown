package com.liam.topdown.objects;

import com.liam.topdown.framework.GameObject;
import com.liam.topdown.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Wall extends GameObject {
    private final int width;
    private final int height;
    private Graphics2D g2d;

    public Wall(float x, float y, int width, int height, ObjectId id) {
        super(x, y, id);
        this.id = id;
        this.width = width;
        this.height = height;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
    }

    @Override
    public void render(Graphics g) {
        g2d = (Graphics2D)g;
        g.setColor(Color.BLACK);
        g2d.fill(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}
