package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Control.ViewTrans;
import FootballLeagueScoringSystem.Module.League;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author QuanHao
 * 顶部的四个按钮，事件后期补上
 * */
public class TopButton {
    public Button teamRank;
    public Button playerRank;
    public Button todaySchedule;
    public Button schedule;

   public TopButton(Stage stage, League theLeague) {
        /**
         * 顶部的四个按钮
         * */
       ViewTrans vt = new ViewTrans();
        this.teamRank = new Button();//队伍积分榜
        teamRank.setFont(new Font("Microsoft YaHei", 20));
        teamRank.setText("球队积分榜");
        teamRank.setLayoutX(40);
        teamRank.setLayoutY(10);
        teamRank.setMinWidth(250);
        teamRank.setMinHeight(40);
        teamRank.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vt.toMainView(theLeague,stage,"球队排名");
            }
        });
        this.playerRank = new Button();//射手积分榜
        playerRank.setFont(new Font("Microsoft YaHei", 20));
        playerRank.setText("射手积分榜");
        playerRank.setLayoutX(330);
        playerRank.setLayoutY(10);
        playerRank.setMinWidth(250);
        playerRank.setMinHeight(40);
        playerRank.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vt.toMainView(theLeague,stage,"射手排名");

            }
        });
        this.todaySchedule = new Button();//今日赛程
        todaySchedule.setFont(new Font("Microsoft YaHei", 20));
        todaySchedule.setText("今日赛程");
        todaySchedule.setLayoutX(620);
        todaySchedule.setLayoutY(10);
        todaySchedule.setMinWidth(250);
        todaySchedule.setMinHeight(40);
        todaySchedule.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage secondStage = new Stage();
                TodayBattleView todayBattleView = new TodayBattleView(stage,theLeague);
                Scene secondScene = new Scene(todayBattleView.getScrollPane(), 270, 600);
                secondStage.setScene(secondScene);
                secondStage.show();
            }
        });
        this.schedule = new Button();//赛程表
        schedule.setFont(new Font("Microsoft YaHei", 20));
        schedule.setText("总赛程表");
        schedule.setLayoutX(910);
        schedule.setLayoutY(10);
        schedule.setMinWidth(250);
        schedule.setMinHeight(40);
        schedule.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                vt.toMainView(theLeague,stage,"全部赛程");
            }
        });
    }

    public Button getTeamRank() {
        return teamRank;
    }

    public Button getPlayerRank() {
        return playerRank;
    }

    public Button getTodaySchedule() {
        return todaySchedule;
    }

    public Button getSchedule() {
        return schedule;
    }
    public void setHandle(){

    }
    public double getNewBaseY(){
        return 50.0;
    }
    public double getNewBaseX(){
        return 40.0;
    }
    public double getButtonWidth(){
        return 250;
    }
    public double getButtonHeight(){
        return 40;
    }
    public double getSpace(){return 40;}
}
