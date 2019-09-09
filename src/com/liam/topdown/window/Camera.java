package com.liam.topdown.window;

import com.liam.topdown.framework.Game;
import com.liam.topdown.framework.GameObject;

public class Camera {
    private float x, y;
    public static boolean isMoving;

    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void tick(GameObject player) {
        x = -player.getX() + Game.width / 2;
        y = -player.getY() + Game.height / 2;
    }
}
