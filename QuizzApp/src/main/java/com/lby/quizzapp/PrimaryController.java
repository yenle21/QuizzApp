package com.lby.quizzapp;

import com.lby.utils.MyAlert;
import com.lby.utils.myStage;
import com.lby.utils.theme.DefaultThemeFactory;
import com.lby.utils.theme.Theme;
import com.lby.utils.theme.ThemeManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class PrimaryController implements Initializable {

    @FXML
    private ComboBox<Theme> cbTheme;

    public void changeTheme(ActionEvent event) {
        this.cbTheme.getSelectionModel().getSelectedItem().updateTheme(this.cbTheme.getScene());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbTheme.setItems(FXCollections.observableArrayList(Theme.values()));
        
    }
    
    public void handleMyQuestion(ActionEvent event) throws IOException {
        myStage.getInstance().showStage("Questions.fxml");
        
    }

    public void handlePractice(ActionEvent event) throws IOException {
         myStage.getInstance().showStage("practice.fxml");
    }
    
}
