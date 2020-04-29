package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Control.ViewTrans;
import FootballLeagueScoringSystem.Module.League;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class InitialView extends Pane {
    public InitialView(Stage stage){
        this.setMinSize(1200,600);
        this.setMaxSize(1920,1080);
        Label label = new Label("程序准备中......");
        label.setFont(new Font("Microsoft YaHei",36));
        ViewTrans vt = new ViewTrans();
        League league = new League();
        vt.toMainView(league,stage);
        this.getChildren().add(label);
    }
}
