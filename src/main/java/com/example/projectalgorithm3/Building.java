package com.example.projectalgorithm3;

import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Building {
    private double Xaxis ;
    private double Yaxis ;
    private String name;
    Circle circle  ;
    private ArrayList<Adjecent> adjacents = new ArrayList<>();;



    public Building(double x, double y, String name, Circle circle) {
        Xaxis = x;
        Yaxis = y;
        this.name = name;
        this.circle = circle;
    }

    public Building(double x, double y, String name) {
        Xaxis = x;
        Yaxis = y;
        this.name = name;
    }
    public Building(String name) {

        this.name = name;
    }


    public Building() {
        // TODO Auto-generated constructor stub
    }

    public double getXaxis() {
        return Xaxis;
    }

    public void setXaxis(double xaxis) {
        Xaxis = xaxis;
    }

    public double getYaxis() {
        return Yaxis;
    }

    public void setYaxis(double yaxis) {
        Yaxis = yaxis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public ArrayList<Adjecent> getAdjacents() {
        return adjacents;
    }

    public void setAdjacents(ArrayList<Adjecent> adjacents) {
        this.adjacents = adjacents;
    }

    @Override
    public String toString() {
        return "City [X =" + Xaxis + ", Y=" + Yaxis + ", name=" + name + ", circle=" + circle + ", adjacents="
                + adjacents + "]";
    }




}
