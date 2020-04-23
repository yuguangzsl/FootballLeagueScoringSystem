package FootballLeagueScoringSystem.Control;

import FootballLeagueScoringSystem.Module.FootballPlayer;
import FootballLeagueScoringSystem.Module.FootballTeam;
import FootballLeagueScoringSystem.View.PlayerView;
import FootballLeagueScoringSystem.View.TeamView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewTrans {
    public void toPlayerView(Stage stage,String playerName){
        FootballPlayer player = new FootballPlayer("蛇","蛇队",3,17);
        if(stage.isShowing()){
            stage.close();
        }
        player.writeGoalInfo("蛇队","龙队",37,45);
        System.out.println(player.readGoalInfo());
        PlayerView playerView = new PlayerView(player,stage);
        Scene scene = new Scene(playerView);
        stage.setScene(scene);
        stage.show();
    }
    public void toTeamView(Stage stage,String teamName){
        if(stage.isShowing()){
            stage.close();
        }
        FootballTeam team = new FootballTeam("男子甲组",1);
        TeamView teamView = new TeamView(team,stage);
        Scene scene = new Scene(teamView);
        stage.setScene(scene);
        stage.show();
    }
}
