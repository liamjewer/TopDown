package com.liam.topdown.objects;
import com.liam.topdown.framework.GameObject;
import com.liam.topdown.framework.ObjectHandler;
import com.liam.topdown.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Enemy extends GameObject {

    GameObject player;
    private int width = 32, height = 32;
    ObjectHandler handler;
    private static final int maxVel = 3;
    private int[][] map = new int[40][40];

    public Enemy(float x, float y, GameObject player, ObjectHandler handler, ObjectId id) {
        super(x, y, id);
        this.player = player;
        this.handler = handler;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        //follow player
        float diffx = x - player.getX();
        float diffy = y - player.getY();

        velX = diffx / -40;
        velY = diffy / -40;

        //collision
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ObjectId.Wall) {
                if (getBoundsBottom().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - height;
                    velY = 0;
                }
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + tempObject.getBounds().height;
                    velY = 0;
                }

                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - width;
                    velX = 0;
                }
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + tempObject.getBounds().width;
                    velX = 0;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.blue);
        g2d.fill(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
    public Rectangle getBoundsBottom() {
        return new Rectangle((int) ((int)x+(width/2)-(width/4)), (int) ((int)y + height/2), (int)width/2, (int)height/2 + 1);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x+(width/2)-(width/4)), (int)y, (int)width/2, (int)height/2);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x + width - (width/4)), (int)y + 5, (int)width/4, (int)height - 10);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y + 5, (int)width/4, (int)height - 10);
    }
}
