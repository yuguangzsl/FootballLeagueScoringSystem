package FootballLeagueScoringSystem.Module;

public class Team implements Comparable<Team> {
    private int teamScore;
    private int teamRank;
    private String teamName;

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
                '}';
    }

    @Override
    public int compareTo(Team o) {
        return o.teamScore -this.teamScore;
    }
}
