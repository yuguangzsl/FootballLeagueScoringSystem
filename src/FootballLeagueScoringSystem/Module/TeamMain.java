package FootballLeagueScoringSystem.Module;

public class TeamMain {
    public static void main(String...args){
        TeamSql teamsql = new TeamSql();
        teamsql.teamSort();
        teamsql.getTeams();
    }
}
