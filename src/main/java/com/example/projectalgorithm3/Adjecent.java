package com.example.projectalgorithm3;

public class Adjecent{
    private Building building;
    private double distance;

    public Adjecent(Building building, double distance) {
        this.setBuilding(building);
        this.distance = distance;

    }
    public Adjecent( double distance) {
        this.distance = distance;

    }

    public Adjecent() {
        // TODO Auto-generated constructor stub
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public String toString() {
        return "Adjecent [Building=" + building + ", distance=" + distance + "]";
    }



}