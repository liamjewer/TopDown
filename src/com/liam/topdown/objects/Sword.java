package com.liam.topdown.objects;

import com.liam.topdown.framework.GameObject;
import com.liam.topdown.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Sword extends GameObject {

    int width, height, damage, speed;
    double angle, end;
    float x, y;

    public Sword(float x, float y, int width, int height, double start, double end, int speed, int damage, ObjectId id) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        angle = start;
        this.end = end;
        this.speed = speed;
        this.damage = damage;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if(angle < end){
            angle += speed;
        }else{

        }
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
