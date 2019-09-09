package com.liam.topdown.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyInput extends KeyAdapter {

    private ObjectHandler handler;
    public static boolean[] activekeys = new boolean[256];

    public KeyInput(ObjectHandler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
            activekeys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e){
        activekeys[e.getKeyCode()] = false;
    }
}