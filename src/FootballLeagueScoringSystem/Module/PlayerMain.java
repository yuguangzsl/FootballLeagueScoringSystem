package FootballLeagueScoringSystem.Module;

public class PlayerMain {
    public static void main(String...args){
        PlayerSql playerSql = new PlayerSql();
        playerSql.playerSort();
        playerSql.getPlayers();
    }
}
