package FootballLeagueScoringSystem.Control;

import FootballLeagueScoringSystem.Module.League;
import FootballLeagueScoringSystem.Module.Player;
import FootballLeagueScoringSystem.Module.Team;
import FootballLeagueScoringSystem.View.MainView;
import FootballLeagueScoringSystem.View.PlayerView;
import FootballLeagueScoringSystem.View.AddDataView;
import FootballLeagueScoringSystem.View.TeamView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewTrans {
    public void toPlayerView(Stage stage,String teamName,String playerName,League theLeague){
        Player player = new Player(teamName,playerName);
        PlayerView playerView = new PlayerView(player,stage,theLeague);
        Scene scene = new Scene(playerView);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        stage.setScene(scene);
    }
    public void toTeamView(Stage stage,String teamName,League theLeague){
        Team team = new Team(teamName);
        TeamView teamView = new TeamView(team,stage,theLeague);
        Scene scene = new Scene(teamView);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        stage.setScene(scene);
    }
    public void toAddDataView(Stage stage,League theLeague){
        AddDataView registerTeamView = new AddDataView(stage,theLeague);
        Scene scene = new Scene(registerTeamView);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        stage.setScene(scene);
    }
    public void toMainView(League theLeague,Stage stage) throws IOException {
        MainView mainView = new MainView(theLeague,stage);
        Scene scene = new Scene(mainView);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        stage.setScene(scene);
    }
    public void toMainView(League theLeague,Stage stage,String tab) throws IOException {
        MainView mainView = new MainView(theLeague,stage);
        mainView.selectTab(tab);
        Scene scene = new Scene(mainView);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        stage.setScene(scene);
    }

}
