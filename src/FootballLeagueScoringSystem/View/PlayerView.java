package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Module.FootballPlayer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


/**
 * 球员射手视图模型
 * 仅显示单个射手
 * 显示姓名，所属球队，进球数，排名
 */
public class PlayerView extends Pane {
    private FootballPlayer player;
    private ScrollPane scrollPane;
    private Button teamName;
    private Button rank;

    PlayerView(FootballPlayer player) {
        this.scrollPane = new ScrollPane();
        this.player = player;
        Generate(player);
        this.setMinSize(1200, 600);
        this.setMaxSize(1920, 1080);
    }

    private void Generate(FootballPlayer player) {
        TopButton topButton = new TopButton();//顶部的四个按钮
        //左上一，队名按钮
        this.teamName = new Button();
        teamName.setFont(new Font("Microsoft YaHei", 28));
        teamName.setText(player.getTeamName());
        teamName.setLayoutX(topButton.getNewBaseX());
        teamName.setLayoutY(topButton.getNewBaseY() + 10);
        teamName.setMinSize(topButton.getButtonWidth() * 1.5 + 40, topButton.getButtonHeight() * 2);
        //右上一，排名按钮
        this.rank = new Button();
        rank.setFont(new Font("Microsoft YaHei", 28));
        rank.setText("当前排名：" + player.getRank());
        rank.setLayoutX(topButton.getNewBaseX() + topButton.getButtonWidth() * 1.5 + 185);
        rank.setLayoutY(topButton.getNewBaseY() + 10);
        rank.setMinSize(topButton.getButtonWidth() * 2.25, topButton.getButtonHeight() * 2);
        //左下一，头像标签
        Label playerLogo = new Label();
        Image image = new Image("file:D:\\FootballLeagueScoringSystem\\ModuleTest\\src\\player.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        playerLogo.setGraphic(imageView);
        playerLogo.setLayoutX(topButton.getNewBaseX());
        playerLogo.setLayoutY(topButton.getNewBaseY() + 10 + topButton.getButtonHeight() * 2 + 10);
        playerLogo.setMinSize(topButton.getButtonWidth() * 1.5 + 40, (topButton.getButtonHeight() * 2) * 3.5);
        //中一，球员名字标签
        Label playerName = new Label();
        playerName.setTextAlignment(TextAlignment.CENTER);
        playerName.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        playerName.setFont(new Font("Microsoft YaHei", 28));
        playerName.setText(player.getName());
        playerName.setLayoutX(topButton.getNewBaseX() + topButton.getButtonWidth() * 1.5 + 55);
        playerName.setLayoutY(topButton.getNewBaseY() + 10 + topButton.getButtonHeight() * 2 + 10);
        playerName.setMinSize(170, 55);
        //中二，进球数标签
        Label goalNum = new Label();
        goalNum.setTextAlignment(TextAlignment.CENTER);
        goalNum.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        goalNum.setFont(new Font("Microsoft YaHei", 28));
        goalNum.setText(String.valueOf(player.getGoalNum()));
        goalNum.setLayoutX(topButton.getNewBaseX() + topButton.getButtonWidth() * 1.5 + 55);
        goalNum.setLayoutY(topButton.getNewBaseY() + 10 + topButton.getButtonHeight() * 2 + 75);
        goalNum.setMinSize(170, 55);
        //右下，进球详细信息
        ScrollPane scrollPane = new ScrollPane();
        FlowPane container = new FlowPane();
        String goalInfo = player.readGoalInfo();
        String[] Infos = goalInfo.split(" ");
        for (int i = 3; i < Infos.length; i += 4) {
            Label info = new Label();
            info.setFont(new Font("Microsoft YaHei", 28));
            info.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
            info.setText(Infos[i - 3] + "VS" + Infos[i - 2] + "\n" + "in" + Infos[i - 1] + ":" + Infos[i]);
            info.setTextAlignment(TextAlignment.CENTER);
            info.setMinSize(500, 120);
            container.getChildren().add(info);
        }
        scrollPane.setContent(container);
        scrollPane.setLayoutX(topButton.getNewBaseX() + topButton.getButtonWidth() * 1.5 + 250);
        scrollPane.setLayoutY(topButton.getNewBaseY() + 10 + topButton.getButtonHeight() * 2 + 10);
        scrollPane.setMaxSize(500, 400);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.getChildren().addAll(
                topButton.teamRank, topButton.playerRank,
                topButton.todaySchedule, topButton.schedule,
                this.teamName, this.rank, playerLogo, playerName, goalNum, scrollPane);
    }
}
