package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Control.ViewTrans;
import FootballLeagueScoringSystem.Module.League;
import FootballLeagueScoringSystem.Module.Player;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PlayerRankView extends AnchorPane {
    PlayerRankView(Stage stage, League theLeague) {
        AnchorPane AP0 = new AnchorPane();
        TitleLabels(AP0);
        ScrollPane SP = new ScrollPane();
        AnchorPane AP = new AnchorPane();
        RankData(theLeague.getPlayers(),AP,stage);
        SP.setLayoutX(0);
        SP.setLayoutY(36);
        SP.setMinSize(1200,550);
        SP.setMaxSize(1200,700);
        SP.setContent(AP);
        AP0.getChildren().add(SP);
        this.getChildren().add(AP0);
    }

    private void RankData(Player[] players, AnchorPane AP, Stage stage) {
        if (players[0] == null) {
            //数据库中没有查到对应数据
            Button NoData = new Button();
            NoData.setText("没有找到对应数据！");
            NoData.setLayoutX(0);
            NoData.setLayoutY(0);
            NoData.setMinSize(1200, 30);
            AP.getChildren().add(NoData);
        } else {
            for (int i = 0; players[i] != null; i++) {
                Button Rank = new Button();
                Rank.setText("" + players[i].getRank());
                Rank.setLayoutX(0);
                Rank.setLayoutY(i * 30);
                Rank.setMinSize(240, 30);
                Button name = new Button();
                name.setText(players[i].getName());
                name.setLayoutX(Rank.getLayoutX() + Rank.getMinWidth());
                name.setLayoutY(i * 30);
                name.setMinSize(Rank.getMinWidth(), Rank.getMinHeight());
                name.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ViewTrans vt = new ViewTrans();
                        vt.toTeamView(stage, name.getText());
                    }
                });
                Button teamName = new Button();
                teamName.setText("" + players[i].getTeamName());
                teamName.setLayoutX(name.getLayoutX() + name.getMinWidth());
                teamName.setLayoutY(i * 30);
                teamName.setMinSize(Rank.getMinWidth(), Rank.getMinHeight());
                teamName.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ViewTrans vt = new ViewTrans();
                        vt.toTeamView(stage, teamName.getText());
                    }
                });
                Button GoalNum = new Button();
                GoalNum.setText("" + players[i].getScore());
                GoalNum.setLayoutX(teamName.getLayoutX() + teamName.getMinWidth());
                GoalNum.setLayoutY(i * 30);
                GoalNum.setMinSize(Rank.getMinWidth(), Rank.getMinHeight());
                Button Score = new Button();
                Score.setText("" + players[i].getScore());
                Score.setLayoutX(GoalNum.getLayoutX() + GoalNum.getMinWidth());
                Score.setLayoutY(i * 30);
                Score.setMinSize(Rank.getMinWidth(), Rank.getMinHeight());
                AP.getChildren().addAll(Rank, name, teamName,
                        GoalNum, Score);
            }
        }
    }

    private void TitleLabels(AnchorPane Ap) {
        Button rank = new Button();
        rank.setText("当前名次");
        rank.setLayoutX(0);
        rank.setLayoutY(0);
        rank.setMinSize(240, 30);
        Button name = new Button();
        name.setText("姓名");
        name.setLayoutX(rank.getLayoutX() + rank.getMinWidth());
        name.setLayoutY(rank.getLayoutY());
        name.setMinSize(rank.getMinWidth(), rank.getMinHeight());
        Button teamName = new Button();
        teamName.setText("球队");
        teamName.setLayoutX(name.getLayoutX() + name.getMinWidth());
        teamName.setLayoutY(name.getLayoutY());
        teamName.setMinSize(rank.getMinWidth(), rank.getMinHeight());
        Button goalNum = new Button();
        goalNum.setText("进球数");
        goalNum.setLayoutX(teamName.getLayoutX() + teamName.getMinWidth());
        goalNum.setLayoutY(teamName.getLayoutY());
        goalNum.setMinSize(rank.getMinWidth(), rank.getMinHeight());
        Button score = new Button();
        score.setText("积分");
        score.setLayoutX(goalNum.getLayoutX() + goalNum.getMinWidth());
        score.setLayoutY(goalNum.getLayoutY());
        score.setMinSize(rank.getMinWidth(), rank.getMinHeight());
        Ap.getChildren().addAll(rank, name, teamName, goalNum, score);
    }
}
