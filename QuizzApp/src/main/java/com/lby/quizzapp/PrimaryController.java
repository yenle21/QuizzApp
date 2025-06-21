package com.lby.quizzapp;

import com.lby.utils.MyAlert;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class PrimaryController {
    public void handleMyQuestion(ActionEvent event) throws IOException{
        Scene scene = new Scene( new FXMLLoader(App.class.getResource( "Questions.fxml")).load());
        Stage stage =  new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void handlePractice(ActionEvent event){
         MyAlert.getInstance().showAlert("Coming Soon...");
    }
    
}
