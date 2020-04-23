package FootballLeagueScoringSystem.Module;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface GetInfo {
    /**
     * @author :long
     * 获取球员信息接口，输入球队名+球员名，返回查询到的球员Player类型
     */
    default Player getPlayerInfo(String teamName,String playerName){
        Player player = null;
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
            Date todaydate = new Date();    //今天的日期
            SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd");
            String date = ft2.format(todaydate);
            String sql = "select * from footballplayer where playerName='"+playerName+"' and playerTeam='"+teamName+"'";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String playerFoul = rs.getString("playerFoul");
                String playerPhoto = rs.getString("playerPhoto");
                int playerScore = rs.getInt("playerScore");
                int rank = rs.getInt("playerRank");
                player = new  Player(playerName,teamName,playerPhoto,playerFoul,playerScore,rank);
            }


            rs.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    /**
     * @author :long
     * 获取球队信息接口，提供球队名字，返回team类型
     */
    default Team getTeamInfo(String teamName){
        Team team = null;
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
            String sql = "select * from footballteam where teamName='"+teamName+"'";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int teamScore = rs.getInt("teamScore");
                int teamRank = rs.getInt("teamRank");
                team = new Team(teamName, teamScore, teamRank);
            }
            rs.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return team;
    }
}
