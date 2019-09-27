package com.liam.topdown.framework;

import com.liam.topdown.objects.Bullet;
import com.liam.topdown.objects.Enemy;
import com.liam.topdown.objects.Player;
import com.liam.topdown.objects.Wall;
import com.liam.topdown.window.Camera;
import com.liam.topdown.window.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    private boolean running;
    private Thread thread;
    private static ObjectHandler handler;
    public static Camera cam;
    public static int width = 800, height = 608;

    private void init() {
        handler = new ObjectHandler();
        Player player = new Player(width/2 - 32/2,height/2 - 32/2, handler, ObjectId.Player);
        handler.addObject(player);
        cam = new Camera(0, 0);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput());
        this.addMouseMotionListener(new MouseInput());

        Wall wall = new Wall(-272, -368, 32, 1312, ObjectId.Wall);
        handler.addObject(wall);
        wall = new Wall(-240, -368, 1280, 32, ObjectId.Wall);
        handler.addObject(wall);
        wall = new Wall(-240, 912, 1280, 32, ObjectId.Wall);
        handler.addObject(wall);
        wall = new Wall(1008, -368, 32, 1312, ObjectId.Wall);
        handler.addObject(wall);

    }

    public void start() {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void tick() {
        handler.tick();
        cam.tick(handler.getObjectById(ObjectId.Player));
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        //things to render go from here
        //background
        g.setColor(new Color(0, 100, 0));
        g.fillRect(0,0, width, height);

        g2d.translate(cam.getX(), cam.getY());

        handler.render(g);
        g2d.translate(-cam.getX(), -cam.getY()); //end of cam
        //to here

        g.dispose();
        bs.show();
    }

    public static void main(String[] args){
        new Window(width, height, "Top Down Game", new Game());
    }
}
