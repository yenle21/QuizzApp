/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lby.utils;

import com.lby.quizzapp.App;
import com.lby.utils.theme.Theme;
import com.lby.utils.theme.ThemeManager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class myStage {

    private static myStage instance;
    private final Stage stage;
    private static Scene scene;

    private myStage() {
        stage = new Stage();

    }

    public static myStage getInstance() {
        if (instance == null) {
            instance = new myStage();
        }
        return instance;
    }

    public void showStage(String fxml) throws IOException {
        if (!this.stage.isShowing()) {
            if (scene == null) {
                scene = new Scene(new FXMLLoader(App.class.getResource(fxml)).load());
            } else {
                scene.setRoot(new FXMLLoader(App.class.getResource(fxml)).load());
            }
            ThemeManager.applyTheme(scene);
            this.stage.setScene(scene);
            this.stage.show();
        }

    }
}
