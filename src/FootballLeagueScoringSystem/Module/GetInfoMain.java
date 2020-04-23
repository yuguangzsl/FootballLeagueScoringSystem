package FootballLeagueScoringSystem.Module;

public class GetInfoMain implements GetInfo {


    public static void main(String...args){
        GetInfoMain getInfoMain = new GetInfoMain();
        Player player = getInfoMain.getPlayerInfo("艾欧尼亚","马可波罗");
        System.out.println(player.toString());
        Team team = getInfoMain.getTeamInfo("艾欧尼亚");
        System.out.println(team);

    }
}
