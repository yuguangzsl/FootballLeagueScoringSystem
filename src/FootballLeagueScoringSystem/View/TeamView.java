package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Module.FootballTeam;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


/**
 * 球队视图模型
 * 仅显示单个球队
 * 显示队名，球队logo，进球数，失球数，胜场数，负场数，积分，排名
 */
public class TeamView extends Pane {
    private Canvas canvas;
    private ScrollPane players;//显示球员信息的滚动面板
    private ScrollPane schedule;//显示赛程的滚动面板
    private Button rank;//显示积分的按钮

    TeamView(FootballTeam thisTeam) {
        this.players = new ScrollPane();
        this.schedule = new ScrollPane();
        this.rank = new Button();
        generate(thisTeam);
        this.setMinSize(1200, 600);
        this.setMaxSize(1920, 1080);
    }

    public void generate(FootballTeam thisTeam) {
        TopButton topButton = new TopButton();//顶部的四个按钮
        //中左，标签：球队名
        Label teamName = new Label();
        teamName.setFont(new Font("Microsoft YaHei", 28));
        teamName.setText(thisTeam.getTeamName());
        teamName.setLayoutX(topButton.getNewBaseX());
        teamName.setLayoutY(topButton.getNewBaseY() + 10);
        teamName.setMinSize(360, 90);
        //中右，按钮：当前排名，当前球队积分
        this.rank = new Button();
        rank.setFont(new Font("Microsoft YaHei", 28));
        rank.setText("当前排名：" + thisTeam.getRank() + ";当前积分：" + thisTeam.getIntegral());
        rank.setLayoutX(topButton.getNewBaseX() + teamName.getMinWidth() + 80);
        rank.setLayoutY(topButton.getNewBaseY() + 10);
        rank.setMinSize(480, 90);
        //下左，标签：球队LOGO
        Label teamLOGO = new Label();
        Image image = new Image("file:D:\\FootballLeagueScoringSystem\\ModuleTest\\src\\player.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        teamLOGO.setGraphic(imageView);
        teamLOGO.setLayoutX(topButton.getNewBaseX());
        teamLOGO.setLayoutY(topButton.getNewBaseY() + teamName.getMinHeight() + 10);
        teamLOGO.setMinSize(260, 300);
        //下中，滚动面板：球员列表
        ScrollPane playersSP = new ScrollPane();
        FlowPane playerC = new FlowPane();
        for (int i = 0; i < thisTeam.getPlayers().length; i++) {
            Button player = new Button();
            player.setFont(new Font("Microsoft YaHei", 28));
            player.setText(thisTeam.getPlayers()[i].getName());
            player.setTextAlignment(TextAlignment.CENTER);
            player.setMinSize(180, 50);
            playerC.getChildren().add(player);
        }
        playersSP.setContent(playerC);
        playersSP.setLayoutX(topButton.getNewBaseX() + teamLOGO.getMinWidth()+ 100);
        playersSP.setLayoutY(topButton.getNewBaseY() + teamName.getMinHeight() + 10);
        playersSP.setMaxSize(180, 300);
        playersSP.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        //下右，滚动面板：赛事列表
        ScrollPane gameSP = new ScrollPane();
        FlowPane gameC = new FlowPane();
        String gameInfo = thisTeam.readGameRecord();
        String[] Infos = gameInfo.split(" ");
        for (int i = 2; i < Infos.length; i+=3) {
            Button game = new Button();
            game.setFont(new Font("Microsoft YaHei", 28));
            game.setText(Infos[i-2]+"VS"+Infos[i-1]+"\n"+Infos[i-2]+Infos[i]);
            game.setTextAlignment(TextAlignment.CENTER);
            game.setMinSize(200, 60);
            gameC.getChildren().add(game);
        }
        gameSP.setContent(gameC);
        gameSP.setLayoutX(playersSP.getLayoutX()+playersSP.getMinWidth()+200);
        gameSP.setLayoutY(playersSP.getLayoutY());
        gameSP.setMaxSize(200, 300);
        gameSP.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        //将所有控件添加到面板
        this.getChildren().addAll(
                topButton.teamRank, topButton.playerRank,
                topButton.todaySchedule, topButton.schedule,
                teamName, rank, teamLOGO,
                playersSP,gameSP);

    }
}
