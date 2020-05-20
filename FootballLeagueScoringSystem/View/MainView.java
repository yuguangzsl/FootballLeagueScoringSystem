package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Module.League;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
        this.setPrefSize(1200, 600);
        this.setMaxSize(1200, 600);
    }

    public void generate() {
        /**
         * 标签换成按钮仅仅是为了让文本居中，设置背景填充是为了让按钮显示出标签的效果
         * setStyle("-fx-CSS代码")
         * */
        //设置标签属性
        String tabStyle = "-fx-pref-width:240;-fx-pref-height:40;-fx-text-align:center;-fx-font-size:18px;";
        //球队排行榜
        Tab teamRank = new Tab("球队排名");
        Thread teamRankThread = new Thread(() -> {
            long t1 = System.currentTimeMillis();
            TeamRankView teamRankView = new TeamRankView(stage, theLeague);
            teamRank.setContent(teamRankView);
            teamRank.setStyle(tabStyle);

            teamRank.setClosable(false);//不允许关闭这个标签页面
            long t2 = System.currentTimeMillis();
            System.out.println("TeamRankView load time :" + (t2 - t1));
        });

        //射手排行榜
        Tab playerRank = new Tab("射手排名");
        Thread playerRankThread = new Thread(()->{
            long t3 = System.currentTimeMillis();
            PlayerRankView playerRankView = new PlayerRankView(stage, theLeague);
            playerRank.setContent(playerRankView);
            playerRank.setStyle(tabStyle);
            playerRank.setClosable(false);
            long t4 = System.currentTimeMillis();
            System.out.println("PlayerRankView load time :" + (t4 - t3));
        });

        //今日赛程
        Tab todayBattle = new Tab("今日赛程");
        Thread todayBattleThread = new Thread(()->{
            long t5 = System.currentTimeMillis();
            TodayBattleView todayBattleView = new TodayBattleView(stage, theLeague);
            todayBattle.setContent(todayBattleView);
            todayBattle.setStyle(tabStyle);
            todayBattle.setClosable(false);
            long t6 = System.currentTimeMillis();
            System.out.println("TodayBattleView load time :" + (t6 - t5));

        });
        //全部赛程
        Tab allBattle = new Tab("全部赛程");
        Thread allBattleThread = new Thread(()->{
            long t7 = System.currentTimeMillis();
            AllBattleView allBattleView = new AllBattleView(stage, theLeague);
            allBattle.setContent(allBattleView.getScrollPane());
            allBattle.setStyle(tabStyle);
            allBattle.setClosable(false);
            long t8 = System.currentTimeMillis();
            System.out.println("allBattleView load time :" + (t8 - t7));
        });

        //菜单功能界面
        long t9 = System.currentTimeMillis();
        MenuView menuView = new MenuView(stage, theLeague, this);
        Tab menu = new Tab("更多功能", menuView);
        menu.setStyle(tabStyle);
        menu.setClosable(false);
        long t10 = System.currentTimeMillis();
        System.out.println("menuView load time :" + (t10 - t9));
        //
        teamRankThread.start();
        playerRankThread.start();
        todayBattleThread.start();
        allBattleThread.start();
        try{
            teamRankThread.join();
            playerRankThread.join();
            todayBattleThread.join();
            allBattleThread.join();
        }catch (InterruptedException e){

        }this.getTabs().addAll(teamRank, playerRank, todayBattle, allBattle, menu);
    }

    public int selectTab(String tab) {
        if (tab.equals("球队排名")) {
            this.getSelectionModel().select(0);
            return 0;
        }
        if (tab.equals("射手排名")) {
            this.getSelectionModel().select(1);
            return 1;
        }
        if (tab.equals("今日赛程")) {
            this.getSelectionModel().select(2);
            return 2;
        }
        if (tab.equals("全部赛程")) {
            this.getSelectionModel().select(3);
            return 3;
        } else return 4;
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return this.stage;
    }
}





