package com.example.projectalgorithm3;

public class Edge {

    private Building building1;
    private Building building2;
    private double distance;

    public Edge(Building building1, Building building2, double distance) {

        this.building1 = building1;
        this.building2 = building2;
        this.distance = distance;
    }

    public Building getSourcebuilding() {
        return building1;
    }

    public void setSourcebuilding(Building sourcebuilding) {
        this.building1 = sourcebuilding;
    }

    public Building getTargetbuilding() {
        return building2;
    }

    public void setTargetbuilding(Building targetbuilding) {
        this.building2 = targetbuilding;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Edge [sourcebuilding=" + building1 + ", targetbuilding=" + building2 + ", distance=" + distance + "]"+ "\n";
    }

}

