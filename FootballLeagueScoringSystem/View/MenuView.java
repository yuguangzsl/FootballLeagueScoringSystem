package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Control.ViewTrans;
import FootballLeagueScoringSystem.Module.League;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author QuanHao
 * 显示菜单页面：添加队伍，更新赛事信息，更新赛程信息
 */
public class MenuView extends AnchorPane {
    public MenuView(Stage stage, League theLeague,MainView mainView) {
        this.setMinSize(1200, 500);
        this.setMaxSize(1920, 1080);
        generate(stage, theLeague,mainView);
    }

    private void generate(Stage stage, League theLeague,MainView mainView) {
        ViewTrans vt = new ViewTrans();
        Button registerTeam = new Button("新建参赛队伍");
        registerTeam.setLayoutX(0);
        registerTeam.setLayoutY(0);
        registerTeam.setPrefSize(140, 140);
        registerTeam.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vt.toAddDataView(theLeague, stage);
            }
        });

        Button updateBattle = new Button("更新赛事信息");
        updateBattle.setLayoutX(registerTeam.getLayoutX() + registerTeam.getPrefWidth());
        updateBattle.setLayoutY(registerTeam.getLayoutY());
        updateBattle.setPrefSize(140, 140);
        updateBattle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vt.toUpdateDataView(theLeague, stage);
            }
        });
        Button updateSchedule = new Button("更新赛程信息");
        updateSchedule.setLayoutX(updateBattle.getLayoutX()+updateBattle.getPrefWidth());
        updateSchedule.setLayoutY(updateSchedule.getLayoutY());
        updateSchedule.setPrefSize(140,140);
        updateSchedule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vt.toUpdateSchedule(theLeague,stage);
            }
        });
        this.getChildren().addAll(registerTeam, updateBattle,updateSchedule);

    }
}
