package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Control.ViewTrans;
import FootballLeagueScoringSystem.Module.League;
import com.browniebytes.javafx.control.DateTimePicker;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 添加赛程信息,新建赛程
 * */
public class UpdateSchedule extends Pane {
    public UpdateSchedule(League theLeague, Stage stage) {
        this.setPrefSize(1200, 600);
        this.setMaxSize(1200, 600);
        this.getChildren().addAll(titleBar(),mainParty(theLeague,stage),bottom(theLeague,stage));
    }

    private HBox titleBar() {
        HBox title = new HBox();
        title.setLayoutX(0);
        title.setLayoutY(0);
        title.setPrefSize(1200, 60);
        title.setMaxSize(1200, 60);
        String labelStyle = "-fx-pref-width:150;-fx-pref-height:50;";
        Label homeCourt = new Label("主场");
        homeCourt.setStyle(labelStyle);
        Label guessCourt = new Label("客场");
        guessCourt.setStyle(labelStyle);
        Label battleSide = new Label("比赛场地");
        battleSide.setStyle(labelStyle);
        Label time = new Label("比赛时间");
        time.setStyle(labelStyle);
        Label commit = new Label("提交");
        commit.setStyle(labelStyle);
        title.getChildren().addAll(homeCourt, guessCourt, battleSide, time,commit);
        return title;
    }

    private ScrollPane mainParty(League theLeague, Stage stage) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefSize(1200, 500);
        scrollPane.setMaxSize(1920, 1080);
        scrollPane.setLayoutX(0);scrollPane.setLayoutY(60);
        VBox vBox = new VBox();
        vBox.setPrefWidth(1200);
        Button addCell = new Button("+");
        addCell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vBox.getChildren().remove(addCell);
                vBox.getChildren().add(cell(theLeague,stage));
                vBox.getChildren().add(addCell);
            }
        });
        vBox.getChildren().addAll(cell(theLeague, stage), addCell);
        scrollPane.setContent(vBox);
        return scrollPane;
    }

    private HBox cell(League theLeague, Stage stage) {
        HBox cells = new HBox();
        cells.setPrefSize(1200, 60);
        cells.setMinSize(1200, 60);
        String style = "-fx-pref-width:150;-fx-Min-height:50;-fx-pref-height: 50; ";
        ComboBox<String> teamA = new ComboBox<>(FXCollections.observableArrayList(theLeague.getTeams()));
        teamA.setStyle(style);
        ComboBox<String> teamB = new ComboBox<>(FXCollections.observableArrayList(theLeague.getTeams()));
        teamB.setStyle(style);
        ComboBox<String> court = new ComboBox<>(FXCollections.observableArrayList(theLeague.getCourts()));
        court.setStyle(style);
        DateTimePicker datePicker = new DateTimePicker();
        datePicker.setStyle(style);
        Button commit = new Button("提交");
        commit.setStyle(style);
        commit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDateTime time = datePicker.dateTimeProperty().getValue();
                String formatTime = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String[] battleInfo = new String[]{teamA.getValue(),teamB.getValue(),formatTime,court.getValue()};
                if(theLeague.addBattle(battleInfo)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("新的赛程信息添加成功！");
                }commit.setText("信息已提交");
            }
        });
        cells.getChildren().addAll(teamA,teamB,court,datePicker,commit);
        return cells;
    }
    private HBox bottom(League theLeague,Stage stage){
        String style = "-fx-pref-width: 600;-fx-pref-height: 40;";
        HBox hBox = new HBox();
        hBox.setLayoutX(0);
        hBox.setLayoutY(560);
        hBox.setPrefSize(1200,40);
        Button back = new Button("返回");
        Button autoGenerate = new Button("自动生成赛程（测试功能）");
        back.setStyle(style);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ViewTrans vt = new ViewTrans();
                try {
                    vt.toMainView(theLeague,stage,"");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        autoGenerate.setStyle(style);
        autoGenerate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("功能开发中");
                alert.showAndWait();
            }
        });
        hBox.getChildren().addAll(back,autoGenerate);
        return hBox;
    }
}
