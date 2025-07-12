/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.lby.quizzapp;

import com.lby.pojo.Category;
import com.lby.pojo.Choice;
import com.lby.pojo.Level;
import com.lby.pojo.Question;
import com.lby.services.CategoryService;
import com.lby.services.FlyweightFactory;
import com.lby.services.LevelService;
import com.lby.services.questions.BaseQuestionServices;
import com.lby.services.questions.CategoryQuestionsDecorator;
import com.lby.services.questions.KeyWordQuestionDecorator;
import com.lby.services.questions.LevelsQuestionDecorator;
import com.lby.services.questions.QuestionServices;
import com.lby.services.questions.UpdateQuestionServices;
import com.lby.utils.Configs;
import com.lby.utils.MyAlert;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionsController implements Initializable {

    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private ComboBox<Level> cbLevels;
    @FXML
    private VBox vboxChoice;
    @FXML
    private TextArea txtContent;
    @FXML
    private ToggleGroup toggleChoice;
    @FXML
    private TableView<Question> tbquestions;
    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox<Category> cbSearchCate;
    @FXML
    private ComboBox<Level> cbSearchLevels;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.cateService, "category")));
            this.cbSearchCate.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.cateService, "category")));
            this.cbSearchLevels.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.levelService, "level")));
            this.cbLevels.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.levelService, "level")));
            this.loadColumn();
            this.loadQuestion(Configs.questionServices.list());
            //   this.tbquestions.setItems(FXCollections.observableList(questionServices.getQuestions()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.txtSearch.textProperty().addListener((e) -> {
            try {
                BaseQuestionServices s = new KeyWordQuestionDecorator(Configs.questionServices, this.txtSearch.getText());
                this.loadQuestion(s.list());
            } catch (SQLException ex) {
                Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        this.cbSearchCate.getSelectionModel().selectedItemProperty().addListener((e) -> {
            try {
                BaseQuestionServices s = new CategoryQuestionsDecorator(Configs.questionServices,  this.cbSearchCate.getSelectionModel().getSelectedItem());
                this.loadQuestion(s.list());
            } catch (SQLException ex) {
                Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        this.cbSearchLevels.getSelectionModel().selectedItemProperty().addListener((e->{
             try {
                BaseQuestionServices s = new LevelsQuestionDecorator(Configs.questionServices,  this.cbSearchLevels.getSelectionModel().getSelectedItem());
                this.loadQuestion(s.list());
            } catch (SQLException ex) {
                Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }));
    }

    public void addChoice(ActionEvent event) {
        HBox h = new HBox();
        h.getStyleClass().add("Main");

        RadioButton r = new RadioButton();
        r.setToggleGroup(toggleChoice);

        TextField txt = new TextField();
        txt.getStyleClass().add("Input");

        h.getChildren().addAll(r, txt);

        this.vboxChoice.getChildren().add(h);
    }

    public void addQuestion(ActionEvent event) {
        try {
            Question.Builder b = new Question.Builder(this.txtContent.getText(),
                    this.cbCates.getSelectionModel().getSelectedItem(),
                    this.cbLevels.getSelectionModel().getSelectedItem());

            for (var c : vboxChoice.getChildren()) {
                HBox h = (HBox) c;
                Choice choice = new Choice(((TextField) h.getChildren().get(1)).getText(),
                        ((RadioButton) h.getChildren().get(0)).isSelected());
                b.addChoice(choice);
            }

            Configs.upServices.addQuestion(b.build());

            MyAlert.getInstance().showMsg("Thêm câu hỏi thành công!");
        } catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Thêm câu hỏi thất bại, lý do: " + ex.getMessage());
        } catch (Exception ex) {
            MyAlert.getInstance().showMsg("Dữ liệu có lỗi!");

        }
    }

    private void loadQuestion(List<Question> questions) {
        this.tbquestions.setItems(FXCollections.observableList(questions));

    }

    private void loadColumn() {
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(80);

        TableColumn colContent = new TableColumn("QUESTIONS");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(250);

        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(e -> {
            TableCell cell = new TableCell();
            Button btn = new Button("Delete");
            btn.setOnAction(event -> {
                Optional<ButtonType> t = MyAlert.getInstance().showMsg("If u delete question then all choice also delete. Are u sure???", Alert.AlertType.CONFIRMATION);
                if (t.isPresent() && t.get().equals(ButtonType.OK)) {

                    Question q = (Question) cell.getTableRow().getItem();
                    try {
                        if (Configs.upServices.deleteQuestions(q.getId()) == true) {
                            MyAlert.getInstance().showMsg("Delete Done");
                            this.tbquestions.getItems().remove(q);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
            });
            cell.setGraphic(btn);
            return cell;
        });
        this.tbquestions.getColumns().addAll(colId, colContent, colAction);
    }

}
