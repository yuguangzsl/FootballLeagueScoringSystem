package FootballLeagueScoringSystem.Module;
/**
 * @author QuanHao
 * 球员射手的抽象模型
 * 包含参数：姓名，所属球队，进球数，排名
 * @param : name,teamName,goalNUm，rank
 * */
public class FootballPlayer {
    private String name;
    private String teamName;
    private int goalNum;
    private int rank;

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getGoalNum() {
        return goalNum;
    }

    public int getRank() {
        return rank;
    }
}
