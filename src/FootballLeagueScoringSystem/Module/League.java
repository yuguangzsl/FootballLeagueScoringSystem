package FootballLeagueScoringSystem.Module;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @param : FootballPlayers,FootballTeams,Battles,...
 * @author QuanHao
 * 联赛模型，用于主视图，球队排名界面
 */
public class League {
    Player[] players;
    Team[] teams;
    Battle[] battles;
    public void League() {
        players = new Player[768];
        teams = new Team[64];
        battles = new Battle[8192];
    }

    public void playerSort(){
        /**
         * @param :
         * @author :long
         * 将player根据score进行排序，并写入playerrank
         */
        Connection conn ;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver) ;
            conn = DriverManager.getConnection(url,user,password);
            if(!conn.isClosed())System.out.println("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();
            String sql = "select * from footballplayer";
            ResultSet rs = statement.executeQuery(sql);
            String playerName = null;
            String playerPhoto = null;
            String teamName = null;
            String foul = null;
            int playerScore = 0;
            int i=0;
            while (rs.next()){
                playerName = rs.getString("playerName");
                teamName = rs.getString("playerteamName");
                this.players[i]=new Player(teamName,playerName);
                i++;
            }
            Arrays.sort(this.players);
            for(i=0;i<players.length;i++){
                sql = "update footballplayer set playerRank=" +(i+1)+ " where " + "playerName='"+players[i].getName()+"'";
                statement.executeUpdate(sql);
            }
            System.out.println("Player Sort Succeeded");
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Player[] getPlayers(){
        Connection conn ;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver) ;
            conn = DriverManager.getConnection(url,user,password);
            if(!conn.isClosed())System.out.println("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();
            String sql = "select * from footballplayer";
            ResultSet rs = statement.executeQuery(sql);
            String playerName = null;
            String teamName = null;
            int i=0;
            while (rs.next()){
                playerName = rs.getString("playerName");
                teamName = rs.getString("playerteamName");
                this.players[i]=new Player(teamName,playerName);
                i++;
            }
            for(;i<this.players.length;i++){
                this.players[i]=null;
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return this.players;
    }

    public void teamSort() {
        /**
         * @param :
         * @author : long
         *对teamscore进行排序，将排序好的teamrank写入数据库
         */
        Connection conn ;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver) ;
            conn = DriverManager.getConnection(url,user,password);
            if(!conn.isClosed())System.out.println("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();
            String sql = "select teamName,teamScore from footballteam";
            ResultSet rs = statement.executeQuery(sql);
            String teamName = null;
            int teamScore = 0;
            int i=0;
            while (rs.next()){
                teamName = rs.getString("teamName");
                this.teams[i]=new Team(teamName);
                i++;
            }
            Arrays.sort(this.teams);
            for(i=0;i<teams.length;i++){
                sql = "update footballteam set teamRank=" +(i+1)+ " where " + "teamName='"+teams[i].getTeamName()+"'";
                statement.executeUpdate(sql);
            }
            System.out.println("Team Sort Succeeded");
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public Team[] getTeams(){
        Connection conn ;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver) ;
            conn = DriverManager.getConnection(url,user,password);
            if(!conn.isClosed())System.out.println("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();
            String sql = "select * from footballteam";
            ResultSet rs = statement.executeQuery(sql);
            String teamName = null;
            int i =0;
            while (rs.next()){
                teamName = rs.getString("teamName");
                this.teams[i]=new Team(teamName);
                i++;
            }
            for(;i<this.teams.length;i++){
                this.teams[i] = null;
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return this.teams;
    }
    /**
     * @author: long
     * 查询当天赛程
     */
    public Battle[] getTodayBattle(){
        Connection conn ;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver) ;
            conn = DriverManager.getConnection(url,user,password);
            if(!conn.isClosed())System.out.println("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();
            java.util.Date todaydate = new java.util.Date();    //今天的日期
            SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd");
            String date = ft2.format(todaydate);
            String sql = "select * from battledetail where DATE_FORMAT(battleTime, '%Y-%m-%d') = DATE_FORMAT('"+date+"', '%Y-%m-%d')";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            Timestamp battleTime ;  //对战时间
            String teamA = null;
            String teamB = null;
            String battleSide = null;      //比赛场地
            String battleResult = null;    //比赛结果，1表示A胜，0表示平局，-1表示A负
            String battleScore = null;     //比赛比分
            int i=0;
            while (rs.next()){
                battleTime = rs.getTimestamp("battleTime");
                teamA = rs.getString("teamA");
                teamB = rs.getString("teamB");
                battleSide = rs.getString("battleSide");
                this.battles[i]=new Battle(teamA,teamB,battleTime,battleSide);
                System.out.println(battles[i].toString());
                i++;
            }

            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return battles;
    }
    /**
     *查询所有赛程
     */
    public Battle[] getAllBattles(){
        Connection conn ;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver) ;
            conn = DriverManager.getConnection(url,user,password);
            if(!conn.isClosed())System.out.println("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();
            java.util.Date todayDate = new Date();    //今天的日期
            SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd");
            String date = ft2.format(todayDate);
            String sql = "select * from battledetail ";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            Timestamp battleTime ;  //对战时间
            String teamA = null;
            String teamB = null;
            String battleSide = null;      //比赛场地
            String battleResult = null;    //比赛结果，1表示A胜，0表示平局，-1表示A负
            String battleScore = null;     //比赛比分
            int i=0;
            while (rs.next()){
                battleTime = rs.getTimestamp("battleTime");
                teamA = rs.getString("teamA");
                teamB = rs.getString("teamB");
                battleSide = rs.getString("battleSide");
                this.battles[i]=new Battle(teamA,teamB,battleTime,battleSide);
                System.out.println(battles[i].toString());
                i++;
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return battles;
    }
}
