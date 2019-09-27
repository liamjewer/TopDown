package com.liam.topdown.framework;

import java.awt.*;
import java.util.LinkedList;

public class ObjectHandler {
    public LinkedList<GameObject> object = new LinkedList<>();

    private GameObject tempObject;


    public ObjectHandler(){

    }

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);

            tempObject.tick(object);
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public GameObject getObjectById(ObjectId id){
        for(int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() == id) {
                return tempObject;
            }
        }
        return null;
    }
    private void clearLevel(){
        object.clear();
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
    public boolean checkObject(ObjectId id){
        return object.contains(getObjectById(id));
    }
}