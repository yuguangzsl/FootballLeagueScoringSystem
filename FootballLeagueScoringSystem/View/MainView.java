package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Module.League;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * @author QuanHao
 * 这个是主界面
 */
public class MainView extends TabPane {
    private League theLeague;
    public Stage stage;
    private Button entryEven;//录入赛事信息
    private Button autoPlay;//自动进行比赛->测试功能

    public MainView(League theLeague, Stage stage) throws IOException {
        this.theLeague = theLeague;
        this.stage = stage;
        generate();
        this.setMinSize(1200, 600);
        this.setMaxSize(1920, 1080);
    }

    public void generate() throws IOException {
        /**
         * 标签换成按钮仅仅是为了让文本居中，设置背景填充是为了让按钮显示出标签的效果
         * setStyle("-fx-CSS代码")
         * */
        //设置标签属性
        String tabStyle = "-fx-pref-width:300px;-fx-pref-height:40px;-fx-text-align:center;-fx-font-size:18px;";
        //球队排行榜
        TeamRankView teamRankView = new TeamRankView(stage, theLeague);
        Tab teamRank = new Tab("球队排名", teamRankView);
        teamRank.setStyle(tabStyle);
        teamRank.setClosable(false);//不允许关闭这个标签页面
        //射手排行榜
        PlayerRankView playerRankView = new PlayerRankView(stage, theLeague);
        Tab playerRank = new Tab("射手排名", playerRankView);
        playerRank.setStyle(tabStyle);
        //今日赛程
        TodayBattleView todayBattleView = new TodayBattleView();
        Tab todayBattle = new Tab("今日赛程", todayBattleView.getScrollPane());
        todayBattle.setStyle(tabStyle);
        todayBattle.setClosable(false);
        //全部赛程
        AllBattleView allBattleView = new AllBattleView();
        Tab allBattle = new Tab("全部赛程", allBattleView.getScrollPane());
        allBattle.setStyle(tabStyle);
        allBattle.setClosable(false);
        //
        this.getTabs().addAll(teamRank, playerRank,todayBattle,allBattle);
    }

}





