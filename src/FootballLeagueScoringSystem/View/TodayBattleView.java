package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystemTest.Module.Battle;
import FootballLeagueScoringSystemTest.Module.BattleSql;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Timestamp;

public class TodayBattleView extends Pane {
    public Stage stage;
    private BattleSql battleSql;
    private ScrollPane scrollPane = new ScrollPane();

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    TodayBattleView(){
        generate();
    }
    public void generate(){
        /**
         * @author :Long
         * 生成所有赛程
         */
        this.battleSql = new BattleSql();
        Battle[] battles = battleSql.getTodayBattle();
        FlowPane flowPane = new FlowPane();
        flowPane.setMaxWidth(270);
        if(battles[0]==null){
            Label label= new Label("今日无赛程");
            label.setFont(new Font("Arial", 30));
            flowPane.getChildren().add(label);
        }
        else {
            for (int i = 0; i < battles.length; i++) {
                Timestamp battleTime = battles[i].getBattleTime();  //对战时间
                String teamA = battles[i].getTeamA();
                String teamB = battles[i].getTeamB();
                String battleSide = battles[i].getBattleSide();      //比赛场地
                int battleResult = battles[i].getBattleResult();    //比赛结果，1表示A胜，0表示平局，-1表示A负
                String battleScore = battles[i].getBattleScore();
                Button topButton = new Button(battleTime.toString() + "\t\t" + battleSide);
                topButton.setMaxWidth(flowPane.getMaxWidth() - 3);
                topButton.setMinWidth(270);
                topButton.setMinHeight(30);
                topButton.setLayoutX(100);
                topButton.setLayoutY(0 + i * 120);
                topButton.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));

                Button leftButton = new Button(teamA);
                leftButton.setMaxWidth(topButton.getMaxWidth() / 3);
                leftButton.setMinWidth(topButton.getMaxWidth() / 3);
                leftButton.setMinHeight(60);
                leftButton.setLayoutX(topButton.getLayoutX());
                leftButton.setLayoutY(topButton.getMinHeight() + i * 120);
                leftButton.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

                Button centerButton = new Button(battleScore);
                centerButton.setMaxWidth(topButton.getMaxWidth() / 3);
                centerButton.setMinWidth(topButton.getMaxWidth() / 3);
                centerButton.setMinHeight(60);
                centerButton.setLayoutX(leftButton.getLayoutX() + leftButton.getMaxWidth());
                centerButton.setLayoutY(topButton.getMinHeight() + i * 120);
                centerButton.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));
                centerButton.setText("0:0");

                Button rightButton = new Button(teamB);
                rightButton.setMaxWidth(topButton.getMaxWidth() / 3);
                rightButton.setMinWidth(topButton.getMaxWidth() / 3);
                rightButton.setMinHeight(60);
                rightButton.setLayoutX(centerButton.getLayoutX() + centerButton.getMaxWidth());
                rightButton.setLayoutY(topButton.getMinHeight() + i * 120);
                rightButton.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));

                Button buttomButton = new Button();
                buttomButton.setMaxWidth(topButton.getMaxWidth());
                buttomButton.setMinWidth(topButton.getMaxWidth());
                buttomButton.setMinHeight(topButton.getMinHeight());
                buttomButton.setLayoutX(topButton.getLayoutX());
                buttomButton.setLayoutY(leftButton.getLayoutY() + leftButton.getMinHeight());
                buttomButton.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));
                if (battleResult == -2) buttomButton.setText("未开始");
                else if (battleResult == 1) buttomButton.setText(teamA + "胜利");
                else if (battleResult == 0) buttomButton.setText("平局");
                else buttomButton.setText(teamB + "胜利");
                flowPane.getChildren().addAll(topButton, leftButton, centerButton, rightButton, buttomButton);
            }
        }
            this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);    //设置水平滚动条用不出现
            this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    //设置垂直滚动条需要时出现
            this.scrollPane.setContent(flowPane);

    }
}
