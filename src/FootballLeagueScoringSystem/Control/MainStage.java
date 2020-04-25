package FootballLeagueScoringSystem.Control;

import FootballLeagueScoringSystem.Module.League;
import FootballLeagueScoringSystem.View.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage extends Application {
   public Stage mainStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        League league = new League();
        MainView mainView = new MainView(league,primaryStage);
        Scene scene = new Scene(mainView);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
