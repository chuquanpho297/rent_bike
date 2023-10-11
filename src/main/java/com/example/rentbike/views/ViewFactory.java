package com.example.rentbike.views;

import com.example.rentbike.App;
import com.example.rentbike.controllers.test.IBaseScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private static ViewFactory viewFactoryIns;
    private final Stage stage;
    private FXMLLoader loader;

    private ViewFactory(Stage stage) {
        this.stage = stage;
    }

    public static ViewFactory createInstance(Stage stage) {
        if (viewFactoryIns == null && stage != null) {
            viewFactoryIns = new ViewFactory(stage);
        }
        return viewFactoryIns;
    }

    public static ViewFactory getInstance() {
        return viewFactoryIns;
    }

    public void switchScene(IBaseScreenController nextCtrl){
        System.out.println("switchScene: " + App.class.getResource(nextCtrl.getScreenPath()));
        FXMLLoader loader = new FXMLLoader(App.class.getResource(nextCtrl.getScreenPath()));
        loader.setController(nextCtrl);
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.setTitle(nextCtrl.getScreenName());
        stage.show();
    }

    public Node loadFXMLScreen(IBaseScreenController ctrl) {
        System.out.println("loadFXMLScreen: " + App.class.getResource(ctrl.getScreenPath()));
        FXMLLoader loader = new FXMLLoader(App.class.getResource(ctrl.getScreenPath()));
        loader.setController(ctrl);
        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void checkNull() throws Exception {
        if (viewFactoryIns == null) throw new Exception("ViewFactoryIns didn't created");
        if (viewFactoryIns.stage == null) throw new Exception("Stage is null");
    }

}
