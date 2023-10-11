package com.example.rentbike;

import com.example.rentbike.controllers.test.home.HomeScreenController;
import com.example.rentbike.views.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws SQLException, IOException {
        ViewFactory viewFactory = ViewFactory.createInstance(stage);
        StateManager stateManager = StateManager.getInstance();
        viewFactory.switchScene(new HomeScreenController());
    }

    public static void main(String[] args) {
        launch();
    }

}


