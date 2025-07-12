/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.lby.quizzapp;

import com.lby.pojo.Category;
import com.lby.pojo.Level;
import com.lby.pojo.Question;
import com.lby.services.FlyweightFactory;
import com.lby.services.questions.BaseQuestionServices;
import com.lby.services.questions.CategoryQuestionsDecorator;
import com.lby.services.questions.LevelsQuestionDecorator;
import com.lby.services.questions.LimitQuestionDecorator;
import com.lby.services.questions.QuestionServices;
import com.lby.utils.Configs;
import com.lby.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PracticeController implements Initializable {

    @FXML
    private Text txtContent;
    @FXML
    private Text txtResult;
    @FXML
    private VBox vboxChoices;
    @FXML
    private TextField txtNum;
    @FXML
    private ComboBox<Category> cbSearchCate;
    @FXML
    private ComboBox<com.lby.pojo.Level> cbSearchLevels;
    private List<Question> questions;
    private int currentQuestion = 0;
    private static final QuestionServices questionServices = new QuestionServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbSearchCate.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.cateService, "category")));
            this.cbSearchLevels.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.levelService, "level")));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void handleStart(ActionEvent event) {
        try {
            BaseQuestionServices s = Configs.questionServices;
            Category c = this.cbSearchCate.getSelectionModel().getSelectedItem();
            if (c != null) {
                s = new CategoryQuestionsDecorator(s, c);
            }
            Level lv = this.cbSearchLevels.getSelectionModel().getSelectedItem();
            if (lv != null) {
                s = new LevelsQuestionDecorator(s, lv);
            }

            s = new LimitQuestionDecorator(s, Integer.parseInt(this.txtNum.getText()));
            this.questions = s.list();
            if (this.questions.size()==0)
            {
                MyAlert.getInstance().showMsg("Unaviable Question!!!!");
            }
            this.loadQuestion();
        } catch (SQLException ex) {
          
        }
    }

    public void handleNext(ActionEvent event) {
        if (this.currentQuestion < this.questions.size() - 1) {
            this.currentQuestion++;
            this.loadQuestion();
        } else {
            MyAlert.getInstance().showMsg("Finished");
        }

    }

    public void handleCheck(ActionEvent event) {
        Question q = this.questions.get(this.currentQuestion);
        this.txtResult.getStyleClass().clear();

        for (int i = 0; i < q.getChoices().size(); i++) {
            RadioButton r = (RadioButton) this.vboxChoices.getChildren().get(i);
            if (r.isSelected()) {
                this.txtResult.setText("Congratulations!!!");
                this.txtResult.getStyleClass().add("Correct");
            } else {
                this.txtResult.setText("Ohh nooo!!!");
                this.txtResult.getStyleClass().add("Wrong");
            }
        }
    }

    private void loadQuestion() {
        Question q = this.questions.get(this.currentQuestion);
        this.txtContent.setText(q.getContent());
        ToggleGroup g = new ToggleGroup();
        vboxChoices.getChildren().clear();

        for (var c : q.getChoices()) {
            RadioButton r = new RadioButton(c.getContent());
            r.setToggleGroup(g);
            vboxChoices.getChildren().add(r);
        }
    }

}
