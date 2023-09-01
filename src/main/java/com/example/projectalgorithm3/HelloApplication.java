package com.example.projectalgorithm3;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    static ArrayList<Building> buildings = new ArrayList<>();
    static HashMap<String, Node> table = new HashMap<>();
    private static ArrayList<Edge> edges = new ArrayList<>();
    private ObservableList<String> pathBuilding = FXCollections.observableArrayList();
    private ObservableList<String> buildingList = FXCollections.observableArrayList();
    private ArrayList<Line> lines = new ArrayList<>();

    Circle circle = new Circle(2);
    static int counter = 0;

    TextField Distancetextfield = new TextField();

    Group s = new Group();
    private ListView<String> pathListView = new ListView<>();
    Pane pane = new Pane();
    static int counterline = 0;

    @Override
    public void start(Stage stage) throws Exception {
        getbuilding();
        getDistances();
        addAdjacents();

        Pane p = new Pane();
        for (int i = 0; i < buildings.size(); i++) { // To place red dots on the map at the locations of buildings
            circle = new Circle(3);
            circle.setFill(Color.RED);
            circle.setCenterX(buildings.get(i).getXaxis());
            circle.setCenterY(buildings.get(i).getYaxis());
            buildings.get(i).setCircle(circle);

            p.getChildren().add(buildings.get(i).getCircle());

        }

        Pane mainpane = new Pane(); // In order to show the names of the buildings
        for (int i = 0; i < buildings.size(); i++) {
            buildingList.add(buildings.get(i).getName());
        }

        Label sourcelabel = new Label("source");



        ComboBox<String> SourcecomboBox = new ComboBox<>(); // It contains all buildings
        SourcecomboBox.setItems(buildingList);               // In order to determine the starting point of the path
        sourcelabel.setLayoutX(700);
        sourcelabel.setLayoutY(30);
        SourcecomboBox.setPrefWidth(200);
        SourcecomboBox.setLayoutX(820);
        SourcecomboBox.setLayoutY(30);
        Label Targetlabel = new Label("Target");     //   In order to specify the end point of the path
        ComboBox<String> TargetcomboBox = new ComboBox<>(); // It contains all buildings
        TargetcomboBox.setItems(buildingList);
        Targetlabel.setLayoutX(700);
        Targetlabel.setLayoutY(80);
        TargetcomboBox.setLayoutX(820);
        TargetcomboBox.setLayoutY(80);
        TargetcomboBox.setPrefWidth(200);
        Button calculate = new Button("Calculate");
        calculate.setLayoutX(820);
        calculate.setLayoutY(150);


        Label pathlabel = new Label("Path");
        TextField pathtextfield = new TextField();
        pathlabel.setLayoutX(700);
        pathlabel.setLayoutY(250);
        pathListView.setLayoutX(820);

        pathListView.setLayoutY(280);
        pathListView.setPrefWidth(200);
        pathListView.setPrefHeight(200);
        Label Distancelabel = new Label("Distance");

        Distancelabel.setLayoutX(700);
        Distancelabel.setLayoutY(500);
        Distancetextfield.setLayoutX(820);
        Distancetextfield.setLayoutY(520);
        Image image = new Image(new FileInputStream("map.png"));
        ImageView imageview = new ImageView(image);
        imageview.setFitWidth(600);
        imageview.setFitHeight(600);
        pane.getChildren().addAll(imageview);
        pane.getChildren().addAll(lines);

        s.getChildren().addAll(pane);
        Group ea = new Group();
        ea.getChildren().addAll(s, p);


        //To place points on the map
        // based on the X and Y coordinates contained in a file
        p.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (counter < 2) {
                    for (int i = 0; i < buildings.size(); i++) {
                        if (buildings.get(i).getXaxis() + 5 > event.getX() && buildings.get(i).getXaxis() - 5 < event.getX()
                                && buildings.get(i).getYaxis() + 5 > event.getY()
                                && buildings.get(i).getYaxis() - 5 < event.getY()) {
                            if (buildings.get(i).circle.getFill().equals(Color.RED)) {
                                counter++;
                                buildings.get(i).circle.setFill(Color.GREEN);
                                if (counter == 1) {
                                    SourcecomboBox.setValue(buildings.get(i).getName());

                                }
                                if (counter == 2) {
                                    TargetcomboBox.setValue(buildings.get(i).getName());

                                }

                            }
                            break;
                        }
                    }

                }

            }

        });

        ea.setOnMouseClicked(e -> {
            System.out.println("-" + e.getX() + "-" + e.getY());

        });

        calculate.setOnAction(e -> {
            counterline = 0;
            Distancetextfield.clear();
            pathListView.getItems().clear();
            for (int i = 0; i < lines.size(); i++) {
                pane.getChildren().remove(lines.get(i));
            }

            Alert s;
            if (SourcecomboBox.getValue() == null) {
                Alert c;
                c = new Alert(AlertType.INFORMATION);
                c.setContentText("you must select source building ");
                c.showAndWait();

            }

            if (!getBuilding(SourcecomboBox.getValue()).equals(getBuilding(TargetcomboBox.getValue()))) {
                getShortestPath(getBuilding(SourcecomboBox.getValue()), getBuilding(TargetcomboBox.getValue()));
                for (int i = 0; i < lines.size(); i++) {
                    lines.get(i).setStrokeWidth(2);
                    pane.getChildren().add(lines.get(i));
                    counterline++;

                }
            } else {
                s = new Alert(AlertType.INFORMATION);
                s.setContentText("you choose the same building ");
                s.showAndWait();

                System.out.println("erroe");
            }
            counter = 0;
            for (int i = 0; i < buildings.size(); i++) {
                if (buildings.get(i).circle.getFill() == Color.GREEN) {
                    buildings.get(i).circle.setFill(Color.RED);
                }
            }

        });

        mainpane.getChildren().addAll(calculate, sourcelabel, SourcecomboBox, Targetlabel, TargetcomboBox, pathlabel,
                pathListView, Distancelabel, Distancetextfield, ea);

        Scene scene = new Scene(mainpane, 1100, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void getShortestPath(Building sourcebuilding, Building targetbuilding) {

        graph(sourcebuilding, targetbuilding);

        for (int i = 0; i < lines.size(); i++) {
            s.getChildren().remove(lines.get(i));
        }
        lines.clear();

        pathBuilding.clear();

        pathListView.getItems().clear();

        Distancetextfield.setText("0.0");

        if (table.get(targetbuilding.getName()).getDistance() != Double.POSITIVE_INFINITY
                && table.get(targetbuilding.getName()).getDistance() != 0) {
            shortestPath(sourcebuilding, targetbuilding);
            Node t = table.get(targetbuilding.getName());
            Distancetextfield.setText("" + t.getDistance());

            pathListView.getItems().add(sourcebuilding.getName() + " (start) --->");

            for (int i = pathBuilding.size() - 1; i >= 0; i--) {
                if (i == 0) {
                    pathListView.getItems().add(pathBuilding.get(i) + " (end)");

                } else {
                    pathListView.getItems().add(pathBuilding.get(i) + " --->");

                }
            }
        } else {
            Alert e;
            e = new Alert(AlertType.INFORMATION);
            e.setContentText("building that you add does not have adjecnt ");
            e.showAndWait();
            System.out.println("error");

        }

    }

    private void shortestPath(Building sourcebuilding, Building targetbuilding) {

        pathBuilding.add(targetbuilding.getName());

        Node t = table.get(targetbuilding.getName());

        if (t.getSourcebuilding() == null) {

            return;

        }

        if (t.getSourcebuilding() == sourcebuilding) {

            if (sourcebuilding != targetbuilding) {
                lines.add(new Line(t.getSourcebuilding().getXaxis(), t.getSourcebuilding().getYaxis(), targetbuilding.getXaxis(),
                        targetbuilding.getYaxis()));
                t.getSourcebuilding().circle.setFill(Color.GREEN);
                targetbuilding.circle.setFill(Color.GREEN);
            }
            return;
        }

        lines.add(new Line(t.getSourcebuilding().getXaxis(), t.getSourcebuilding().getYaxis(), targetbuilding.getXaxis(),
                targetbuilding.getYaxis()));

        shortestPath(sourcebuilding, t.getSourcebuilding());
        t.getSourcebuilding().circle.setFill(Color.GREEN);
        targetbuilding.circle.setFill(Color.GREEN);

    }

    public static void graph(Building current, Building target) {
        for (Building i : buildings) {
            table.put(i.getName(), new Node(null, i, Double.POSITIVE_INFINITY, false));
        }
        Node node = table.get(current.getName());
        ComparTO compareTo = new ComparTO();
        PriorityQueue<Node> queue = new PriorityQueue<>(10, compareTo);
        node.setDistance(0.0);
        node.setKnown(true);
        queue.add(node);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            temp.setKnown(true);
            if (temp.getCurrentbuilding() == target) {
                break;
            }
            ArrayList<Adjecent> adjecnt = temp.getCurrentbuilding().getAdjacents();
            System.out.println(temp.getCurrentbuilding().getName());
            for (Adjecent A: adjecnt) {
                Node targetN = table.get(A.getBuilding().getName());

                if (targetN.isKnown()) {
                    continue;
                }
                if (queue.contains(targetN)) {
                    queue.remove(targetN);
                }
                double newDis = A.getDistance() + temp.getDistance();
                if (newDis < targetN.getDistance()) {
                    targetN.setSourcebuilding(temp.getCurrentbuilding());
                    targetN.setDistance(newDis);

                }

                queue.add(targetN);
            }

        }

    }

    private static void getbuilding() throws FileNotFoundException {
        File file = new File("Faculty.txt");
        Scanner input = new Scanner(file);
        if (file.exists()) {
            while (input.hasNextLine()) {
                String str = input.nextLine();
                if (str.isEmpty()) {
                    continue; // Skip empty lines
                }
                String[] buildingData = str.trim().split(",");
                buildings.add(new Building(Double.parseDouble(buildingData[1]), Double.parseDouble(buildingData[2]), buildingData[0],
                        new Circle()));
                System.out.println(buildings);

            }
            input.close();
        }
    }

    private static Building getBuilding(String buildingName) {

        for (int i = 0; i < buildings.size(); i++) {
            if (buildings.get(i).getName().equalsIgnoreCase(buildingName)) {
                return buildings.get(i);
            }
        }
        return null;
    }

    private static void getDistances() throws FileNotFoundException {
        File file = new File("data.txt");
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String[] spStr = input.nextLine().trim().split(",");
            edges.add(new Edge(getBuilding(spStr[0]), getBuilding(spStr[1]), Double.parseDouble(spStr[2])));
        }
        input.close();
    }

    private static void addAdjacents() {
        for (int i = 0; i < buildings.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                if (buildings.get(i).getName().equalsIgnoreCase(edges.get(j).getSourcebuilding().getName())) {
                    // City c = edges.get(j).getTargetCity();
                    Adjecent n = new Adjecent(edges.get(j).getTargetbuilding(), edges.get(j).getDistance());
                    buildings.get(i).getAdjacents().add(n);
                } else if (buildings.get(i).getName().equalsIgnoreCase(edges.get(j).getTargetbuilding().getName())) {
                    // City c = edges.get(j).getSourceCity();
                    Adjecent n = new Adjecent(edges.get(j).getSourcebuilding(), edges.get(j).getDistance());
                    buildings.get(i).getAdjacents().add(n);
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);

    }


}
