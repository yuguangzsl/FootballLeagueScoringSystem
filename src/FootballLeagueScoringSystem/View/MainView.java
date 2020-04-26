package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Control.ViewTrans;
import FootballLeagueScoringSystem.Module.League;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import FootballLeagueScoringSystem.Module.Team;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * @author QuanHao
 * 这个是主界面
 */
public class MainView extends TabPane {
    private League theLeague;
    public Stage stage;
    private Button entryEven;//录入赛事信息
    private Button autoPlay;//自动进行比赛->测试功能

    public MainView(League theLeague, Stage stage) {
        this.theLeague = theLeague;
        this.stage = stage;
        generate();
        this.setMinSize(1200, 600);
        this.setMaxSize(1920, 1080);
    }

    public void generate() {
        /**
         * 标签换成按钮仅仅是为了让文本居中，设置背景填充是为了让按钮显示出标签的效果
         * setStyle("-fx-CSS代码")
         * */
        //设置标签属性
        String tabStyle = "-fx-pref-width:400px;-fx-pref-height:40px;-fx-text-align:center;-fx-font-size:18px;";
        String titleStyle = "-fx-pref-width:1200px;-fx-pref-height:60px;-fx-text-align:center;-fx-font-size:18px;";
        //球队排行榜
        Tab teamRank = new Tab();
        teamRank.setText("球队排名");
        teamRank.setStyle(tabStyle);
        teamRank.setClosable(false);//不允许关闭这个标签页面
        Accordion groups = new Accordion();
        //男子甲组
        TitledPane groupMale1 = new TitledPane();
        groupMale1.setText("男子甲组");
        groupMale1.setStyle(titleStyle);
        AnchorPane gm1AP0 = new AnchorPane();
        gm1AP0.setMinSize(1200, 435);
        ScrollPane gm1SP = new ScrollPane();
        gm1SP.setMinSize(1200, 435);
        gm1SP.setLayoutX(0);
        gm1SP.setLayoutY(36);
        AnchorPane gm1AP = new AnchorPane();
        gm1AP.setMinSize(1200, 435);
        gm1AP.setLayoutX(0);
        gm1AP.setLayoutY(0);
        //
        Team[] gm1Teams = theLeague.getTeams("男子甲组");
        RankData(gm1Teams, gm1AP);
        //
        gm1SP.setContent(gm1AP);
        TitleLabels(gm1AP0);
        gm1AP0.getChildren().add(gm1SP);
        groupMale1.setContent(gm1AP0);
        //男子乙组
        TitledPane groupMale2 = new TitledPane();
        groupMale2.setText("男子乙组");
        groupMale2.setStyle(titleStyle);
        AnchorPane gm2AP0 = new AnchorPane();
        gm2AP0.setMinSize(1200, 435);
        ScrollPane gm2SP = new ScrollPane();
        gm2SP.setMinSize(1200, 435);
        gm2SP.setLayoutX(0);
        gm2SP.setLayoutY(36);
        AnchorPane gm2AP = new AnchorPane();
        gm2AP.setMinSize(1200, 435);
        //
        Team[] gm2Teams = theLeague.getTeams("男子乙组");
        RankData(gm2Teams, gm2AP);
        //
        gm2SP.setContent(gm2AP);
        TitleLabels(gm2AP0);
        gm2AP0.getChildren().add(gm2SP);
        groupMale2.setContent(gm2AP0);
        //女子组
        TitledPane groupFemale = new TitledPane();
        groupFemale.setText("女子组");
        groupFemale.setStyle(titleStyle);
        AnchorPane gfAP0 = new AnchorPane();
        gfAP0.setMinSize(1200, 435);
        ScrollPane gfSP = new ScrollPane();
        gfSP.setMinSize(1200, 435);
        gfSP.setLayoutX(0);
        gfSP.setLayoutY(36);
        AnchorPane gfAP = new AnchorPane();
        gfAP.setMinSize(1200, 435);
        //
        Team[] gfTeams = theLeague.getTeams("女子组");
        RankData(gfTeams, gfAP);
        //
        gfSP.setContent(gfAP);
        TitleLabels(gfAP0);
        gfAP0.getChildren().add(gfSP);
        groupFemale.setContent(gfAP0);
        //成年组
        TitledPane groupAdult = new TitledPane();
        groupAdult.setText("成年组");
        groupAdult.setStyle(titleStyle);
        AnchorPane gaAP0 = new AnchorPane();
        gaAP0.setMinSize(1200, 435);
        ScrollPane gaSP = new ScrollPane();
        gaSP.setMinSize(1200, 400);
        gaSP.setLayoutX(0);
        gaSP.setLayoutY(36);
        AnchorPane gaAP = new AnchorPane();
        gaAP.setMinSize(1200, 400);
        //
        Team[] gaTeams = theLeague.getTeams("成年组");
        RankData(gaTeams, gaAP);
        //
        gaSP.setContent(gaAP);
        TitleLabels(gaAP0);
        gaAP0.getChildren().add(gaSP);
        groupAdult.setContent(gaAP0);
        //控件添加区
        groups.getPanes().addAll(groupMale1, groupMale2, groupFemale, groupAdult);
        teamRank.setContent(groups);
        //射手排行榜
        Tab playerRank = new Tab();
        playerRank.setText("射手排名");
        //今日赛程
        TodayBattleView todayBattleView = new TodayBattleView();
        Tab todayBattle = new Tab("今日赛程",todayBattleView.getScrollPane());
        //全部赛程
        AllBattleView allBattleView = new AllBattleView();
        Tab allBattle = new Tab("全部赛程",allBattleView.getScrollPane());


        //battlepane.s
        this.getTabs().addAll(teamRank,playerRank,todayBattle,allBattle);
        playerRank.setClosable(false);
        playerRank.setStyle(tabStyle);
        //赛程表
        Tab battle = new Tab();
        battle.setText("赛程表");
        battle.setClosable(false);
        battle.setStyle(tabStyle);
        //控件添加
        this.getTabs().addAll(teamRank, playerRank, battle);
    }

    private void RankData(Team[] Teams, AnchorPane AP) {
        if (Teams[0] == null) {
            //数据库中没有查到对应数据
            Button NoData = new Button();
            NoData.setText("没有找到对应数据！");
            NoData.setLayoutX(0);
            NoData.setLayoutY(0);
            NoData.setMinSize(1200, 30);
            AP.getChildren().add(NoData);
        } else {
            for (int i = 0; Teams[i] != null; i++) {
                Button Rank = new Button();
                Rank.setText("" + Teams[i].getTeamRank());
                Rank.setLayoutX(0);
                Rank.setLayoutY(i * 30);
                Rank.setMinSize(150, 30);
                Button name = new Button();
                name.setText(Teams[i].getTeamName());
                name.setLayoutX(Rank.getLayoutX() + Rank.getMinWidth());
                name.setLayoutY(i * 30);
                name.setMinSize(150, 30);
                name.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ViewTrans vt = new ViewTrans();
                        vt.toTeamView(stage,name.getText());
                    }
                });
                Button WinNum = new Button();
                WinNum.setText("" + Teams[i].getWinNum());
                WinNum.setLayoutX(name.getLayoutX() + name.getMinWidth());
                WinNum.setLayoutY(i * 30);
                WinNum.setMinSize(150, 30);
                Button LoseNum = new Button();
                LoseNum.setText("" + Teams[i].getLoseNum());
                LoseNum.setLayoutX(WinNum.getLayoutX() + WinNum.getMinWidth());
                LoseNum.setLayoutY(i * 30);
                LoseNum.setMinSize(150, 30);
                Button DrawNum = new Button();
                DrawNum.setText("" + Teams[i].getDrawNum());
                DrawNum.setLayoutX(LoseNum.getLayoutX() + LoseNum.getMinWidth());
                DrawNum.setLayoutY(i * 30);
                DrawNum.setMinSize(150, 30);
                Button GoalNum = new Button();
                GoalNum.setText("" + Teams[i].getGoalNum());
                GoalNum.setLayoutX(DrawNum.getLayoutX() + DrawNum.getMinWidth());
                GoalNum.setLayoutY(i * 30);
                GoalNum.setMinSize(150, 30);
                Button GoalLostNum = new Button();
                GoalLostNum.setText("" + Teams[i].getGoalLostNum());
                GoalLostNum.setLayoutX(GoalNum.getLayoutX() + GoalNum.getMinWidth());
                GoalLostNum.setLayoutY(i * 30);
                GoalLostNum.setMinSize(150, 30);
                Button Score = new Button();
                Score.setText("" + Teams[i].getTeamScore());
                Score.setLayoutX(GoalLostNum.getLayoutX() + GoalLostNum.getMinWidth());
                Score.setLayoutY(i * 30);
                Score.setMinSize(150, 30);
                AP.getChildren().addAll(Rank, name, WinNum, LoseNum, DrawNum,
                        GoalNum, GoalLostNum, Score);
            }
        }
    }

    private void TitleLabels(AnchorPane Ap) {
        Button rank = new Button();
        rank.setText("当前名次");
        rank.setLayoutX(0);
        rank.setLayoutY(0);
        rank.setMinSize(150, 30);
        Button name = new Button();
        name.setText("队名");
        name.setLayoutX(rank.getLayoutX() + rank.getMinWidth());
        rank.setLayoutY(rank.getLayoutY());
        name.setMinSize(rank.getMinWidth(), rank.getMinHeight());
        Button winNum = new Button();
        winNum.setText("胜场数");
        winNum.setLayoutX(name.getLayoutX() + name.getMinWidth());
        winNum.setLayoutY(name.getLayoutY());
        winNum.setMinSize(rank.getMinWidth(), rank.getMinHeight());
        Button loseNum = new Button();
        loseNum.setText("负场数");
        loseNum.setLayoutX(winNum.getLayoutX() + winNum.getMinWidth());
        loseNum.setLayoutY(winNum.getLayoutY());
        loseNum.setMinSize(rank.getMinWidth(), rank.getMinHeight());
        Button drawNum = new Button();
        drawNum.setText("平局数");
        drawNum.setLayoutX(loseNum.getLayoutX() + loseNum.getMinWidth());
        drawNum.setLayoutY(loseNum.getLayoutY());
        drawNum.setMinSize(rank.getMinWidth(), rank.getMinHeight());
        Button goalNum = new Button();
        goalNum.setText("进球数");
        goalNum.setLayoutX(drawNum.getLayoutX() + drawNum.getMinWidth());
        goalNum.setLayoutY(drawNum.getLayoutY());
        goalNum.setMinSize(rank.getMinWidth(), rank.getMinHeight());
        Button goalLostNum = new Button();
        goalLostNum.setText("失球数");
        goalLostNum.setLayoutX(goalNum.getLayoutX() + goalNum.getMinWidth());
        goalLostNum.setLayoutY(goalNum.getLayoutY());
        goalLostNum.setMinSize(rank.getMinWidth(), rank.getMinHeight());
        Button score = new Button();
        score.setText("积分");
        score.setLayoutX(goalLostNum.getLayoutX() + goalLostNum.getMinWidth());
        score.setLayoutY(goalLostNum.getLayoutY());
        score.setMinSize(rank.getMinWidth(), rank.getMinHeight());
        Ap.getChildren().addAll(rank, name, winNum, loseNum, drawNum, goalNum, goalLostNum, score);
    }
}





