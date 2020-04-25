package FootballLeagueScoringSystem.View;
import FootballLeagueScoringSystem.Module.League;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
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
    }

    public void generate() {
        /**
         * 标签换成按钮仅仅是为了让文本居中，设置背景填充是为了让按钮显示出标签的效果
         * */
        //球队排行榜
        Tab teamRank = new Tab();
        teamRank.setText("球队排名");
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
    }
}
