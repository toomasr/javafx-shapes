package com.toomasr;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application implements ChangeListener<Number> {
  private final StackPane shapePane = new StackPane();

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Hello World!");

    Pane root = buildRootPane();

    primaryStage.setScene(new Scene(root, 400, 350));
    primaryStage.show();
  }

  private Pane buildRootPane() {
    GridPane pane = new GridPane();

    Label shapeSelection = new Label("Shape");
    shapeSelection.setPadding(new Insets(5, 5, 5, 5));

    final ComboBox<String> comboBox = new ComboBox<String>(FXCollections.observableArrayList("Circle"));
    comboBox.getSelectionModel().select(0);

    Label sizeSelection = new Label("Size");
    sizeSelection.setPadding(new Insets(5, 5, 5, 5));

    Slider slider = new Slider(5, 100, 0.5);
    slider.setShowTickMarks(true);
    slider.setShowTickLabels(true);
    slider.setMajorTickUnit(10);
    slider.setBlockIncrement(5);

    slider.valueProperty().addListener(this);

    GridPane.setConstraints(shapeSelection, 0, 0);
    GridPane.setConstraints(comboBox, 1, 0);
    GridPane.setConstraints(sizeSelection, 0, 1);
    GridPane.setConstraints(slider, 1, 1);

    shapePane.setMinWidth(300);
    shapePane.setPadding(new Insets(5, 5, 5, 5));
    GridPane.setColumnSpan(shapePane, 2);
    GridPane.setRowIndex(shapePane, 2);

    pane.getChildren().addAll(shapeSelection, comboBox);
    pane.getChildren().addAll(sizeSelection, slider);
    pane.getChildren().addAll(shapePane);

    return pane;
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
    drawCircle(newValue.intValue());
  }

  private void drawCircle(int newValue) {
    shapePane.getChildren().clear();

    Circle circle = new Circle(newValue / 2, newValue / 2, newValue);
    circle.setStroke(Color.BLACK);
    circle.setFill(Color.BLACK);
    shapePane.getChildren().add(circle);

    circle = new Circle(newValue / 2, newValue / 2, newValue - 3);
    circle.setStroke(Color.WHITE);
    circle.setFill(Color.WHITE);
    shapePane.getChildren().add(circle);
  }
}
