package com.example.projectalgorithm3;

public class Node {

    private Building sourcebuilding ;
    private Building Currentbuilding ;
    private double Distance ;
    boolean known  ;
    public Node(Building sourcebuilding, Building Currentbuilding, double distance, boolean known) {
        this.sourcebuilding = sourcebuilding;
        this.Currentbuilding = Currentbuilding;
        Distance = distance;
        this.known = known;
    }
    public Node() {

    }

    public Building getSourcebuilding() {
        return sourcebuilding;
    }

    public void setSourcebuilding(Building sourcebuilding) {
        this.sourcebuilding = sourcebuilding;
    }

    public Building getCurrentbuilding() {
        return Currentbuilding;
    }

    public void setCurrentbuilding(Building currentbuilding) {
        Currentbuilding = currentbuilding;
    }

    public double getDistance() {
        return Distance;
    }
    public void setDistance(double distance) {
        Distance = distance;
    }
    public boolean isKnown() {
        return known;
    }
    public void setKnown(boolean known) {
        this.known = known;
    }
    @Override
    public String toString() {
        return "Node [sourcebuilding=" + sourcebuilding + ", targetbuilding=" + Currentbuilding + ", Distance=" + Distance + ", known="
                + known + "]";
    }

}

