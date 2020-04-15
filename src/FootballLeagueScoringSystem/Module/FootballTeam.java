package FootballLeagueScoringSystem.Module;

/**
 * @param :
 * @author QuanHao
 * 球队的抽象模型:
 * 包含参数：名次，胜场数，负场数，进球，失球，积分
 */
public class FootballTeam {
    private int rank;
    private int winNum;
    private int loseNum;
    private int goalNum;
    private int goalLostNum;
    private int integral;

    public int getRank() {
        return rank;
    }

    public int getWinNum() {
        return winNum;
    }

    public int getLoseNum() {
        return loseNum;
    }

    public int getGoalNum() {
        return goalNum;
    }

    public int getGoalLostNum() {
        return goalLostNum;
    }

    public int getIntegral() {
        return integral;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setWinNum(int winNum) {
        this.winNum = winNum;
    }

    public void setLoseNum(int loseNum) {
        this.loseNum = loseNum;
    }

    public void setGoalNum(int goalNum) {
        this.goalNum = goalNum;
    }

    public void setGoalLostNum(int goalLostNum) {
        this.goalLostNum = goalLostNum;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
