package FootballLeagueScoringSystem.Module;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @param : FootballPlayers,FootballTeams,Battles,...
 * @author QuanHao
 * 联赛模型，用于主视图，球队排名界面
 */
public class League {

    Player[] players;
    Team[] teams;
    Battle[] battles;
    Battle[] todayBattles;
    Battle[] oneDayBattles;
    private String userStatus;

    public League() {
        players = new Player[768];
        teams = new Team[64];
        battles = new Battle[8192];
        todayBattles = new Battle[50];
        getPlayers();
        getTeams();
        getAllBattles();
    }

    public void playerSort() {
        /**
         * @param :
         * @author :long
         * 将player根据score进行排序，并写入playerrank
         */
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "select * from footballplayer";
            ResultSet rs = statement.executeQuery(sql);
            String playerName = null;
            String teamName = null;
            int i = 0;
            while (rs.next()) {
                playerName = rs.getString("playerName");
                teamName = rs.getString("playerteamName");
                this.players[i] = new Player(teamName, playerName);
                i++;
            }
            Arrays.sort(this.players);
            for (i = 0; i < players.length; i++) {
                sql = "update footballplayer set playerRank=" + (i + 1) + " where " + "playerName='" + players[i].getName() + "'";
                statement.executeUpdate(sql);
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Player[] getPlayers() {
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "select * from footballplayer ORDER BY playerscore DESC";
            ResultSet rs = statement.executeQuery(sql);
            String playerName = null;
            String teamName = null;
            int i = 0;
            while (rs.next()) {
                playerName = rs.getString("playerName");
                teamName = rs.getString("playerteamName");
                this.players[i] = new Player(teamName, playerName);
                i++;
            }
            for (; i < this.players.length; i++) {
                this.players[i] = null;
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
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "select teamName,teamScore from footballteam";
            ResultSet rs = statement.executeQuery(sql);
            String teamName = null;
            int i = 0;
            while (rs.next()) {
                teamName = rs.getString("teamName");
                this.teams[i] = new Team(teamName);
                i++;
            }
            Arrays.sort(this.teams);
            for (i = 0; i < teams.length; i++) {
                sql = "update footballteam set teamRank=" + (i + 1) + " where " + "teamName='" + teams[i].getTeamName() + "'";
                statement.executeUpdate(sql);
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getTeams() {
        /**
         * 获取全部队伍的比赛信息
         * 在小组赛结束后进入淘汰时，一次获取所有队伍的比赛信息
         * 女子组（如果必要），用重载方法独立获取排名信息
         * */
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "select * from footballteam Order BY teamscore DESC ";
            ResultSet rs = statement.executeQuery(sql);
            String teamName = null;
            int i = 0;
            while (rs.next()) {
                teamName = rs.getString("teamName");
                this.teams[i] = new Team(teamName);
                i++;
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Team[] getTeams(String groupName) {
        /**
         * 获取特定组别的排名信息
         * */
        Team[] teams = new Team[16];
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);

            Statement statement = conn.createStatement();
            String sql = "select * from footballteam where teamgroup='" + groupName + "' Order BY teamscore DESC ";
            ResultSet rs = statement.executeQuery(sql);
            String teamName = null;
            int i = 0;
            while (rs.next()) {
                teamName = rs.getString("teamName");
                teams[i] = new Team(teamName);
                i++;
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    /**
     * @author: long
     * 查询当天赛程
     */
    public Battle[] getTodayBattle() {
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            java.util.Date todaydate = new java.util.Date();    //今天的日期
            SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd");
            String date = ft2.format(todaydate);
            String sql = "select * from battledetail where DATE_FORMAT(battleTime, '%Y-%m-%d') = DATE_FORMAT('" + date + "', '%Y-%m-%d')";
            ResultSet rs = statement.executeQuery(sql);
            Timestamp battleTime;  //对战时间
            String teamA = null;
            String teamB = null;
            String battleSide = null;      //比赛场地
            int battleResult = 0;    //比赛结果，1表示A胜，0表示平局，-1表示A负,-2表示未开始
            String battleScore = null;     //比赛比分
            int i = 0;
            while (rs.next()) {
                battleTime = rs.getTimestamp("battleTime");
                teamA = rs.getString("teamOne");
                teamB = rs.getString("teamTwo");
                battleSide = rs.getString("battleSide");
                battleResult = rs.getInt("battleResult");
                battleScore = rs.getString("battleScore");
                this.todayBattles[i] = new Battle(teamA, teamB, battleTime, battleSide, battleResult, battleScore);
                System.out.println(this.todayBattles[i].toString());
                i++;
            }

            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return todayBattles;
    }

    /**
     * @author :long
     * 查询某天赛程
     */
    public Battle[] getOneDayBattles(String date) {
        oneDayBattles = new Battle[50];
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "select * from battledetail where DATE_FORMAT(battleTime, '%Y-%m-%d') = DATE_FORMAT('" + date + "', '%Y-%m-%d')";
            ResultSet rs = statement.executeQuery(sql);
            Timestamp battleTime;  //对战时间
            String teamA = null;
            String teamB = null;
            String battleSide = null;      //比赛场地
            int battleResult = 0;    //比赛结果，1表示A胜，0表示平局，-1表示A负,-2表示未开始
            String battleScore = null;     //比赛比分
            int i = 0;
            while (rs.next()) {
                battleTime = rs.getTimestamp("battleTime");
                teamA = rs.getString("teamOne");
                teamB = rs.getString("teamTwo");
                battleSide = rs.getString("battleSide");
                battleResult = rs.getInt("battleResult");
                battleScore = rs.getString("battleScore");
                this.oneDayBattles[i] = new Battle(teamA, teamB, battleTime, battleSide, battleResult, battleScore);
                i++;
            }

            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return oneDayBattles;
    }

    /**
     * 查询所有赛程
     */
    public Battle[] getAllBattles() {
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "select * from battledetail ORDER BY battletime";
            ResultSet rs = statement.executeQuery(sql);
            Timestamp battleTime;  //对战时间
            String teamA = null;
            String teamB = null;
            String battleSide = null;      //比赛场地
            int battleResult = 0;    //比赛结果，1表示A胜，0表示平局，-1表示A负
            String battleScore = null;     //比赛比分
            int i = 0;
            while (rs.next()) {
                battleTime = rs.getTimestamp("battleTime");
                teamA = rs.getString("teamOne");
                teamB = rs.getString("teamTwo");
                battleSide = rs.getString("battleSide");
                battleResult = rs.getInt("battleResult");
                battleScore = rs.getString("battleScore");
                this.battles[i] = new Battle(teamA, teamB, battleTime, battleSide, battleResult, battleScore);
                i++;
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return battles;
    }

    public boolean checkTeam(String teamName) {
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        boolean status = false;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "select * from footballteam where teamname='" + teamName + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs != null) status = true;
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean addSystemUser(String name, String account, String passwd, String position) {
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String[] s = {"系统管理员", "其他管理员", "主裁判", "副裁判"};
            List<String> strList = new ArrayList<String>();
            strList = Arrays.asList(s);
            if (strList.contains(position)) {
                String sql = "Insert Into systemuser values ('" + name +
                        "','" + account +
                        "','" + passwd +
                        "','" + position +
                        "')";
                int status = statement.executeUpdate(sql);
                conn.close();
                return true;//新用户插入成功
            } else return false;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String checkUser(String account, String passwd) {
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT * from systemuser where Account='" + account +
                    "' and password='" + passwd +
                    "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs != null) {
                String position = rs.getString("position");
                return position;
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setUserStatus(String position) {
        if(position.equals("系统管理员")){
            this.userStatus = "root";
        }
        if(position.equals("其他管理员")){
            this.userStatus = "administrator";
        }
        if(position.equals("主裁判")){
            this.userStatus = "mainJudge";
        }
        if(position.equals("副裁判")){
            this.userStatus = "asJudge";
        }
        else
            this.userStatus = "visitor";
    }

}
