package com.mikeconroy.adventofcode.day10;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Asteroid{

    private Point position;
    private Set<Double> angles = new HashSet<Double>();

    public Asteroid(int x, int y){
        position = new Point(x, y);
    }

    public void populateLineOfSightSet(List<Asteroid> asteroids){
        for(Asteroid otherAsteroid : asteroids){
            if(!otherAsteroid.getPosition().equals(this.position)){
                double angle = calculateAngle(this.position.x, 
                                    this.position.y, 
                                    otherAsteroid.getPosition().x, 
                                    otherAsteroid.getPosition().y);
                angles.add(angle);
            }
        }
    }

    private double calculateAngle(int x1, int y1, int x2, int y2){
        double angle = Math.toDegrees(Math.atan2(x2-x1, y2-y1));
        return angle;
    }

    public int getLineOfSightCount(){
        return angles.size();
    }

    public Point getPosition(){
        return this.position;
    }

}