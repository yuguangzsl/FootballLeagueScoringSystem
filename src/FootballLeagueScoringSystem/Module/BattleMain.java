package FootballLeagueScoringSystem.Module;

public class BattleMain {
    public static  void main(String...args){
        BattleSql battleSql = new BattleSql();
        battleSql.getTodayBattle();
        battleSql.getAllBattles();
    }
}
