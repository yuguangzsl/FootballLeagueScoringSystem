package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Module.Battle;
import FootballLeagueScoringSystem.Module.League;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Timestamp;

public class TodayBattleView extends Pane {
    public Stage stage;
    private League battleSql;
    private ScrollPane scrollPane = new ScrollPane();

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    TodayBattleView(Stage stage, League theLeague) {
        this.battleSql = theLeague;
        generate();
    }

    public void generate() {
        /**
         * @author :Long
         * 生成所有赛程
         */

        Battle[] battles = battleSql.getTodayBattle();
        GridPane gridPane  = new GridPane();
        gridPane.setHgap(5);    //修改为battles.length/3
        gridPane.setVgap(3);
        gridPane.setPadding(new Insets(20,20,20,20));

        String [] colors = new String[]{"panel-success","panel-info","panel-warning"};      //panel顶部颜色选择

        if (battles[0] == null) {
            Label label = new Label("今日无赛程");
            label.setFont(new Font("LiShu", 30));
            FlowPane flowPane = new FlowPane();
            flowPane.getChildren().add(label);
            gridPane.add(flowPane,0,0);
        } else {
            for (int i = 0,j=0; battles[i] != null; i++,j/=3) {
                Timestamp battleTime = battles[i].getBattleTime();  //对战时间
                String teamA = battles[i].getTeamA();
                String teamB = battles[i].getTeamB();
                String battleSide = battles[i].getBattleSide();      //比赛场地
                int battleResult = battles[i].getBattleResult();    //比赛结果，1表示A胜，0表示平局，-1表示A负
                String battleScore = battles[i].getBattleScore();
                org.kordamp.bootstrapfx.scene.layout.Panel panel = new org.kordamp.bootstrapfx.scene.layout.Panel();
                panel.getStyleClass().addAll("panel-primary", colors[i%3], "panel-default");
                panel.setPadding(new Insets(20, 20, 20, 20));
                Label headLable = new Label(battleTime + "\t" + battleSide);
                Label bodyLable = new Label(teamA + "\t\t" + battleScore + "\t\t" + teamB);
                Label footLable = new Label();
                switch (battleResult) {
                    case -2:
                        footLable.setText("\t\t未开始");
                        break;
                    case -1:
                        footLable.setText("\t\t" + teamB + "获胜");
                        break;
                    case 0:
                        footLable.setText("\t\t平局");
                        break;
                    case 1:
                        footLable.setText("\t\t" + teamA + "获胜");
                        break;
                }
                panel.setHeading(headLable);
                panel.setBody(bodyLable);
                panel.setFooter(footLable);
                gridPane.add(panel, i % 3, j);
            }
        }
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);    //设置水平滚动条用不出现
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    //设置垂直滚动条需要时出现
        this.scrollPane.setContent(gridPane);
    }
}