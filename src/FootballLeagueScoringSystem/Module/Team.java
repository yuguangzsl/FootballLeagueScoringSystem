package FootballLeagueScoringSystem.Module;

public class Team implements Comparable<Team> {
    private int teamscore;
    private int teamrank;
    private String teamname;

    public Team(String teamname, int teamscore){
        this.teamname=teamname;
        this.teamscore=teamscore;
        this.teamrank=0;
    }
    public Team(String teamname, int teamscore,int teamrank){
        this.teamname=teamname;
        this.teamscore=teamscore;
        this.teamrank=teamrank;
    }

    public int getTeamscore() {
        return teamscore;
    }

    public int getTeamrank() {
        return teamrank;
    }

    public String getTeamname() {
        return teamname;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamscore=" + teamscore +
                ", teamrank=" + teamrank +
                ", teamname='" + teamname + '\'' +
                '}';
    }

    @Override
    public int compareTo(Team o) {
        return o.teamscore-this.teamscore;
    }
}
