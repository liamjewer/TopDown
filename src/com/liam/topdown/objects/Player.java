package com.liam.topdown.objects;

import com.liam.topdown.framework.*;
import com.liam.topdown.window.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

public class Player extends GameObject implements ActionListener {

    public static float width = 32;
    private final ObjectHandler handler;
    public float height = 32;
    private static final int maxVel = 5;

    private double facing;
    Rectangle rect = new Rectangle(0,0,0,0);
    Shape shape = rect;
    AffineTransform transform;
    double o, a;

    Timer cooldown = new Timer(1000, this);
    int elapsed = 3;
    public static boolean CanSwing = true;

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
        double halfW = Game.width/2;
        double halfH = Game.height/2;
        a = MouseInput.x - halfW;
        o = -(MouseInput.y - halfH);

        if(a == 0) a = 1;

        facing = Math.atan(o/a);
        if(MouseInput.x < halfW)
            facing += Math.toRadians(180);
        else if(MouseInput.y > halfH)
            facing += Math.toRadians(360);

        transform = new AffineTransform();
        rect = new Rectangle((int)(x-width/2), (int)(y-height/2), (int)width, (int)height);
        transform.rotate(-facing,  rect.getX() + rect.width/2, rect.getY() + rect.getHeight()/2);
        shape = transform.createTransformedShape(rect);

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

        //attack
        if(elapsed >= 1) {
            if (KeyInput.activekeys[32] && !handler.checkObject(ObjectId.Sword) && CanSwing) {
                Sword sword = new Sword(x, y, 20, 50, -90, 90, 25, 0, this, handler, ObjectId.Sword);
                handler.addObject(sword);
                CanSwing = false;
                elapsed = 0;
                cooldown.start();
            } else if (KeyInput.activekeys[90]) {
                handler.addObject(new Bullet(x, y, 10, facing, handler, ObjectId.Bullet));
                elapsed = 0;
                cooldown.start();
            }else{
                cooldown.stop();
            }
        }

        //collision
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectId.Wall){
                if(getBounds().intersects(tempObject.getBounds())){
                    y = tempObject.getY() - height/2 - 1;
                    velY = 0;
                }
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = tempObject.getY() + tempObject.getBounds().height + height/2;
                    velY = 0;
                }

                if(getBoundsRight().intersects(tempObject.getBounds())){
                    x = tempObject.getX() - width/2;
                    velX = 0;
                }
                if(getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + tempObject.getBounds().width + width/2;
                    velX = 0;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.black);
        g2d.fill(shape);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)(x - width/4), (int)(y), (int)width/2, (int)height/2 + 1);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x-(width/4)), (int)(y - height/2), (int)width/2, (int)height/2);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x + width/2 - (width/4)), (int)(y - (height/2) + 5), (int)width/4, (int)height - 10);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)(x - width/2), (int)(y - (height/2) + 5), (int)width/4, (int)height - 10);
    }

    public float getWidth() {
        return width;
    }
    public float getHeight(){
        return height;
    }

    public double getFacing() {
        return facing;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        elapsed++;
    }
}
