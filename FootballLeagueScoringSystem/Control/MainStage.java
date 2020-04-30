package FootballLeagueScoringSystem.Control;

import FootballLeagueScoringSystem.Module.League;
import FootballLeagueScoringSystem.View.LoginView;
import FootballLeagueScoringSystem.View.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage extends Application {
   public Stage mainStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginView loginView = new LoginView(primaryStage);
        Scene scene = new Scene(loginView);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
