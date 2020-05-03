package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Control.ViewTrans;
import FootballLeagueScoringSystem.Module.League;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author QuanHao
 * 显示菜单页面：添加队伍，更新赛事信息，
 */
public class MenuView extends AnchorPane {
    public MenuView(Stage stage, League theLeague) {
        this.setMinSize(1200,500);
        this.setMaxSize(1920,1080);
        generate(stage,theLeague);
    }

    private void generate(Stage stage,League theLeague) {
        Button registerTeam = new Button("新建参赛队伍");
        registerTeam.setLayoutX(0);
        registerTeam.setLayoutY(0);
        registerTeam.setMinSize(140, 140);
        Button updateBattle = new Button("更新赛事信息");
        registerTeam.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ViewTrans vt = new ViewTrans();
                vt.toAddDataView(stage,theLeague);
            }
        });
        updateBattle.setLayoutX(registerTeam.getLayoutX() + registerTeam.getMinWidth());
        updateBattle.setLayoutY(registerTeam.getLayoutY());
        updateBattle.setMinSize(140, 140);
        this.getChildren().addAll(registerTeam, updateBattle);
        updateBattle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ViewTrans vt = new ViewTrans();
                vt.toUpdateDataView(stage,theLeague);
            }
        });
    }
}
