package FootballLeagueScoringSystem.Module;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BattleSql {
    private Battle[] battles =new Battle[3];

    /**
     *
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
            Date todaydate = new Date();    //今天的日期
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
            Date todayDate = new Date();    //今天的日期
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
