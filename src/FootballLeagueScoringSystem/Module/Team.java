package FootballLeagueScoringSystem.Module;

public class Team implements Comparable<Team> {
    private int teamScore;
    private int teamRank;
    private String teamName;
    private int winNum;     //总赢局数
    private int loseNum;    //总输局数
    private int drawNum;    //总平局数
    private int goalNum;    //总进球数
    private int goalLostNum;//总失球数

    public Team(String teamName, int teamScore){
        this.teamName = teamName;
        this.teamScore = teamScore;
        this.teamRank =0;
    }
    public Team(String teamName, int teamScore, int teamRank){
        this.teamName = teamName;
        this.teamScore = teamScore;
        this.teamRank = teamRank;
    }

    public Team(String teamName, int teamScore, int teamRank, int winNum, int loseNum,int drawNum, int goalNum, int goalLostNum){
        this.teamName = teamName;
        this.teamScore = teamScore;
        this.teamRank = teamRank;
        this.winNum = winNum;
        this.loseNum = loseNum;
        this.drawNum = drawNum;
        this.goalNum = goalNum;
        this.goalLostNum =goalLostNum;
    }

    public int getWinNum() {
        return winNum;
    }

    public int getLoseNum() {
        return loseNum;
    }

    public int getDrawNum() {
        return drawNum;
    }

    public int getGoalNum() {
        return goalNum;
    }

    public int getGoalLostNum() {
        return goalLostNum;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public int getTeamRank() {
        return teamRank;
    }

    public String getTeamName() {
        return teamName;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamScore=" + teamScore +
                ", teamRank=" + teamRank +
                ", teamName='" + teamName + '\'' +
                ", winNum=" + winNum +
                ", loseNum=" + loseNum +
                ", drawNum=" + drawNum +
                ", goalNum=" + goalNum +
                ", goalLostNum=" + goalLostNum +
                '}';
    }

    @Override
    public int compareTo(Team o) {
        return o.getTeamScore() -this.getTeamScore();
    }
}
