package com.liam.topdown.objects;

import com.liam.topdown.framework.*;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {

    public static float width = 32;
    private final ObjectHandler handler;
    public float height = 32;
    private static final int maxVel = 5;

    public Player(float x, float y, ObjectHandler handler, ObjectId id) {
        super(x, y, id);
        this.id = id;
        this.handler = handler;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        //set position to change at respective velocities
        x += velX;
        y += velY;

        //move up
        if(KeyInput.activekeys[87]){
            if (velY - 0.1 >= maxVel * -1)
                if(velY > 0)
                    velY -= 0.3;
                else
                    velY -= 0.1;
            else
                velY = maxVel * -1;
        }else if(KeyInput.activekeys[83]){
            if (velY + 0.1 <= maxVel)
                if(velY < 0)
                    velY += 0.3;
                else
                    velY += 0.1;
            else
                velY = maxVel;
        }else{
            if(velY > 0)
                velY -= 0.3;
            else if(velY < 0)
                velY += 0.3;
            if (velY >= -0.3 && velY <= 0.3)
                velY = 0;
        }

        //move Left
        if(KeyInput.activekeys[65]){
            if (velX - 0.1 >= maxVel * -1)
                if(velX > 0)
                    velX -= 0.3;
                else
                    velX -= 0.1;
            else
                velX = maxVel * -1;
        }else if(KeyInput.activekeys[68]){
            if (velX + 0.1 <= maxVel)
                if(velX < 0)
                    velX += 0.3;
                else
                    velX += 0.1;
            else
                velX = maxVel;
        }else{
            if(velX > 0)
                velX -= 0.3;
            else if(velX < 0)
                velX += 0.3;
            if (velX >= -0.3 && velX <= 0.3)
                velX = 0;
        }

        //collision
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectId.Wall){
                if(getBounds().intersects(tempObject.getBounds())){
                    y = tempObject.getY() - height - 1;
                    velY = 0;
                }
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = tempObject.getY() + tempObject.getBounds().height;
                    velY = 0;
                }

                if(getBoundsRight().intersects(tempObject.getBounds())){
                    x = tempObject.getX() - width;
                    velX = 0;
                }
                if(getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + tempObject.getBounds().width;
                    velX = 0;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int) x,(int) y,(int) width,(int) height);
    }

    @Override
    public Rectangle getBounds() {
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
