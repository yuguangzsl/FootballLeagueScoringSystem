package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Module.League;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


/**
 * @author QuanHao
 * 这个是主界面/球队排名界面
 */
public class MainView extends Pane {
    private League theLeague;
    public Stage stage;
    private Button entryEven;//录入赛事信息
    private Button autoPlay;//自动进行比赛->测试功能
    private ScrollPane teamRank;//球队排名界面
    MainView(League theLeague,Stage stage) {
        this.theLeague = theLeague;
        this.stage = stage;
        generate();
    }

    public void generate() {
        /**
         * 标签换成按钮仅仅是为了让文本居中，设置背景填充是为了让按钮显示出标签的效果
         * */
        //顶部按钮
        TopButton topButton = new TopButton(this.stage);
        //中二，标题栏,标签：
        Button rank = new Button();//排名标签
        rank.setFont(new Font("Microsoft YaHei", 24));
        rank.setText("排名");
        rank.setLayoutX(topButton.getNewBaseX());
        rank.setLayoutY(topButton.getNewBaseY() + 10);
        rank.setMinSize(topButton.getButtonWidth() / 2 - 5, 0.75 * topButton.getButtonHeight());
        rank.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));
        //------
        Button teamName = new Button();//队名标签
        teamName.setFont(new Font("Microsoft YaHei", 24));
        teamName.setText("球队");
        teamName.setTextAlignment(TextAlignment.CENTER);
        teamName.setLayoutX(rank.getLayoutX() + rank.getMinWidth() + 10);
        teamName.setLayoutY(rank.getLayoutY());
        teamName.setMinSize(rank.getMinWidth(), rank.getMinHeight());
        teamName.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));
        //------
        Button winNum = new Button();//胜场数标签
        winNum.setFont(new Font("Microsoft YaHei", 24));
        winNum.setText("胜场数");
        winNum.setLayoutX(teamName.getLayoutX() + teamName.getMinWidth() + topButton.getSpace());
        winNum.setLayoutY(teamName.getLayoutY());
        winNum.setMinSize(teamName.getMinWidth(), teamName.getMinHeight());
        winNum.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));
        //------
        Button loseNum = new Button();//负场数标签
        loseNum.setFont(new Font("Microsoft YaHei", 24));
        loseNum.setText("负场数");
        loseNum.setLayoutX(winNum.getLayoutX() + winNum.getMinWidth() + 10);
        loseNum.setLayoutY(winNum.getLayoutY());
        loseNum.setMinSize(winNum.getMinWidth(), winNum.getMinHeight());
        loseNum.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));
        //------
        Button drawNum = new Button();//平局数标签
        drawNum.setFont(new Font("Microsoft YaHei", 24));
        drawNum.setText("平局数");
        drawNum.setLayoutX(loseNum.getLayoutX() + loseNum.getMinWidth() + topButton.getSpace());
        drawNum.setLayoutY(loseNum.getLayoutY());
        drawNum.setMinSize(loseNum.getMinWidth(), loseNum.getMinHeight());
        drawNum.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));
        //------
        Button goalNum = new Button();//进球数标签
        goalNum.setFont(new Font("Microsoft YaHei", 24));
        goalNum.setText("进球数");
        goalNum.setLayoutX(drawNum.getLayoutX() + drawNum.getMinWidth() + 10);
        goalNum.setLayoutY(drawNum.getLayoutY());
        goalNum.setMinSize(drawNum.getMinWidth(), drawNum.getMinHeight());
        goalNum.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));
        //------
        Button loseGoal = new Button();//失球数标签
        loseGoal.setFont(new Font("Microsoft YaHei", 24));
        loseGoal.setText("失球数");
        loseGoal.setLayoutX(goalNum.getLayoutX() + goalNum.getMinWidth() + topButton.getSpace());
        loseGoal.setLayoutY(goalNum.getLayoutY());
        loseGoal.setMinSize(goalNum.getMinWidth(), goalNum.getMinHeight());
        loseGoal.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));
        //------
        Button integral = new Button();//积分标签
        integral.setFont(new Font("Microsoft YaHei", 24));
        integral.setText("当前积分");
        integral.setLayoutX(loseGoal.getLayoutX() + loseGoal.getMinWidth() + 10);
        integral.setLayoutY(loseGoal.getLayoutY());
        integral.setMinSize(loseGoal.getMinWidth(), loseGoal.getMinHeight());
        integral.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));
        //中三滚动面板，查看球队排名
        this.teamRank.setLayoutX(topButton.getNewBaseX());
        this.teamRank.setLayoutY(rank.getLayoutY()+rank.getMinHeight()+10);
        this.teamRank.setMaxSize(4*topButton.getButtonWidth()+3*topButton.getSpace(),
                400);
        FlowPane rankCanvas = new FlowPane();

        //下左：按钮：录入赛事信息

        //下右：按钮：自动比赛

        //控件添加到面板
        this.getChildren().addAll(
                topButton.teamRank, topButton.playerRank,
                topButton.todaySchedule, topButton.schedule,
                rank, teamName, winNum, loseNum, drawNum,
                goalNum, loseGoal, integral);
    }
}
