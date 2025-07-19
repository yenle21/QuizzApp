/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.lby.quizzapp;

import com.lby.exam.Exam;
import com.lby.exam.ExamStrategy;
import com.lby.exam.FixedExamStrategy;
import com.lby.exam.SpecificExamStratery;
import com.lby.pojo.Choice;
import com.lby.pojo.Question;
import com.lby.services.questions.BaseQuestionServices;
import com.lby.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ExamsController implements Initializable {
    @FXML private ComboBox<Exam> cbExams;
    @FXML private TextField txtNum;
    @FXML private ListView<Question> lvQuestions;
    private Map<Integer,Choice> answers =  new HashMap<>();
    
    private List<Question> questions;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbExams.setItems(FXCollections.observableArrayList(Exam.values()));
        
        this.txtNum.setVisible(false);
        this.cbExams.getSelectionModel().selectedItemProperty().addListener((e->{
            if(this.cbExams.getSelectionModel().getSelectedItem()==Exam.SPECIFIC)
                this.txtNum.setVisible(true);
            else 
                this.txtNum.setVisible(false);        
        }));
      
    }    
    public void handleStart(ActionEvent e ) throws SQLException{
        ExamStrategy exam = new FixedExamStrategy();
        if(this.cbExams.getSelectionModel().getSelectedItem()==Exam.SPECIFIC)
            exam = new SpecificExamStratery(Integer.parseInt(this.txtNum.getText()));
        this.questions = exam.getQuestions();
        this.lvQuestions.setItems(FXCollections.observableArrayList(this.questions));
        
        this.lvQuestions.setCellFactory(params -> new ListCell<Question>(){
            @Override
            protected void updateItem(Question q, boolean empty) {
                super.updateItem(q, empty);
                if(q==null ||empty == true)
                {
                    this.setGraphic(null);
                }
                else {
                    VBox v = new VBox(5);
                    v.setStyle("-fx-padding:5; -fx-border-color:brown; -fx=border-width:1");
                    Text t = new Text(q.getContent());
                    v.getChildren().add(t);
                    ToggleGroup g  = new ToggleGroup();
                    for (var c:q.getChoices()){
                        RadioButton r = new RadioButton(c.getContent());
                        r.setToggleGroup(g);
                        
                        //update UI
                        if(answers.get(q.getId())==c)
                            r.setSelected(true);
                            
                            
                        r.setOnAction(e->{
                            if(r.isSelected()==true)
                                answers.put(q.getId(), c);
                    });
                        
                        
                        v.getChildren().add(r);
                    }
                     this.setGraphic(v);
                }
               
            }
            
        });
    }
    public void handleFinish(ActionEvent e ){
        if(questions !=null && !questions.isEmpty())
        {
            int count = 0 ;
            for(var c :answers.values()){
                if(c.isCorrect()==true)
                    count++;
                
              
            }
              MyAlert.getInstance().showMsg(String.format("Bạ đã đúng: %d/%d",count,questions.size()));
        }
    }
    public void handleSave(ActionEvent e ){
        
    }
    
}
