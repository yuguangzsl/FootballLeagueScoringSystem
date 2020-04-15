package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Module.FootballPlayer;
import javafx.scene.layout.Pane;
/**
 * 球员射手视图模型
 * 仅显示单个射手
 * 显示姓名，所属球队，进球数，排名
 */
public class PlayerView extends Pane {
    private FootballPlayer player;
    PlayerView(FootballPlayer player){
        this.player = player;
    }
    public void mainPart(){

    }
}
