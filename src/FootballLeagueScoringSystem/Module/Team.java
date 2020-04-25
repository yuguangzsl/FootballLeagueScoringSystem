package FootballLeagueScoringSystem.Module;

import java.sql.*;

/**
 * @param :teamScore,teamRank,teamName,winNum,loseNum,drawNum,goalNum,goalLostNum
 * @author:Long
 */
public class Team implements Comparable<Team> {
    private String teamName;//球队名字
    private int teamRank;//球队排名
    private int winNum;     //总赢局数
    private int loseNum;    //总输局数
    private int drawNum;    //总平局数
    private int goalNum;    //总进球数
    private int goalLostNum;//总失球数
    private int teamScore;//球队积分
    private String teamGroup;//球队所属组
    private Player[] players;//球员列表

    public Team(String teamName, int teamRank, int winNum, int loseNum, int drawNum, int goalNum, int goalLostNum, String teamGroup, int teamScore) {
        /**
         * 全参数传入，生成一个新的球队对象
         * */
        this.teamName = teamName;
        this.teamRank = teamRank;
        this.winNum = winNum;
        this.loseNum = loseNum;
        this.drawNum = drawNum;
        this.goalNum = goalNum;
        this.goalLostNum = goalLostNum;
        this.teamGroup = teamGroup;
        this.teamScore = teamScore;
        this.players = new Player[11];
    }

    public Team(String teamName) {
        /**@author:Long
         * 仅传入队名，从数据库中读取数据生成一个team对象
         * */
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed()) System.out.println("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();
            String sql = "select * from footballteam where teamName='" + teamName + "'";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                this.teamName = rs.getString("teamName");
                this.teamRank = rs.getInt("teamRank");
                this.winNum = rs.getInt("winNum");
                this.loseNum = rs.getInt("loseNum");
                this.drawNum = rs.getInt("drawNum");
                this.goalNum = rs.getInt("goalNum");
                this.goalLostNum = rs.getInt("goalLostNum");
                this.teamGroup = rs.getString("teamGroup");
                this.teamScore = rs.getInt("teamScore");
            }
            rs.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        /**@author:QUANHAO
         * 将这个对象的更新数据写入数据库
         * */
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed()) System.out.println("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();
            String sql = "update footballteam set teamname = '" + this.teamName + "' where teamName='" + teamName + "'";
            ResultSet rs = statement.executeQuery(sql);
            sql = "update footballteam set teamrank = '" + this.teamRank + "' where teamName='" + teamName + "'";
            statement.executeQuery(sql);
            sql = "update footballteam set winNum = '" + this.winNum + "' where teamName='" + teamName + "'";
            statement.executeQuery(sql);
            sql = "update footballteam set loseNum = '" + this.loseNum + "' where teamName='" + teamName + "'";
            statement.executeQuery(sql);
            sql = "update footballteam set drawNum = '" + this.drawNum + "' where teamName='" + teamName + "'";
            statement.executeQuery(sql);
            sql = "update footballteam set goalNum = '" + this.goalNum + "' where teamName='" + teamName + "'";
            statement.executeQuery(sql);
            sql = "update footballteam set goalLostNum = '" + this.goalLostNum + "' where teamName='" + teamName + "'";
            statement.executeQuery(sql);
            sql = "update footballteam set teamscore = '" + this.teamScore + "' where teamName='" + teamName + "'";
            statement.executeQuery(sql);
            sql = "update footballteam set teamgroup = '" + this.teamGroup + "' where teamName='" + teamName + "'";
            statement.executeQuery(sql);
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData() {
        /**
         * @author :QUANHAO
         * 将新生成的对象的数据写入数据库
         * */
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed()) System.out.println("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO footballteam values ('"
                    + this.teamName + "'," +
                    "'" + this.teamRank + "'," +
                    "'" + this.winNum + "'," +
                    "'" + this.loseNum + "'," +
                    "'" + this.drawNum + "'," +
                    "'" + this.goalNum + "'," +
                    "'" + this.goalLostNum + "'," +
                    "'" + this.teamScore + "'," +
                    "'" + this.teamGroup + "')";
            ResultSet rs = statement.executeQuery(sql);
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
            if (!conn.isClosed()) System.out.println("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();
            String sql = "select * from footballplayer where playerteamName ='" + this.teamName + "'";
            ResultSet rs = statement.executeQuery(sql);
            String playerName = null;
            int i = 0;
            while (rs.next()) {
                playerName = rs.getString("playerName");
                this.players[i] = new Player(this.teamName, playerName);
                System.out.println(players[i].toString());
                i++;
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return this.players;
    }

    public String getGameInfo() {
        /**
         * @author:quanhao
         * 获取这支队伍的比赛信息
         * */
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        String result = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed()) System.out.println("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();
            String sql = "select * from battledetail where teamone='" + teamName + "'" + "or teamtwo='" + this.teamName + "'";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result+=rs.getString("teamOne")+" ";
                result+=rs.getString("teamTwo")+" ";
                result+=rs.getString("battleSide")+" ";
                result+=rs.getString("battleTime")+" ";
                int status = rs.getInt("battleResult");
                switch (status){
                    case 1->result+=rs.getString("teamOne")+"获胜"+" ";
                    case 0->result+="平局"+" ";
                    case -1->result+=rs.getString("teamTwo")+"获胜"+" ";
                    case -2->result+="比赛未开始"+" ";
                }
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getWinNum() {
        return winNum;
    }

    public int getLoseNum() {
        return loseNum;
    }

    public int getDrawNum() {
        return drawNum;
    }

    public int getGoalNum() {
        return goalNum;
    }

    public int getGoalLostNum() {
        return goalLostNum;
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

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamRank(int teamRank) {
        this.teamRank = teamRank;
    }

    public void setWinNum(int winNum) {
        this.winNum = winNum;
    }

    public void setLoseNum(int loseNum) {
        this.loseNum = loseNum;
    }

    public void setDrawNum(int drawNum) {
        this.drawNum = drawNum;
    }

    public void setGoalNum(int goalNum) {
        this.goalNum = goalNum;
    }

    public void setGoalLostNum(int goalLostNum) {
        this.goalLostNum = goalLostNum;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamScore=" + teamScore +
                ", teamRank=" + teamRank +
                ", teamName='" + teamName + '\'' +
                ", winNum=" + winNum +
                ", loseNum=" + loseNum +
                ", drawNum=" + drawNum +
                ", goalNum=" + goalNum +
                ", goalLostNum=" + goalLostNum +
                ", teamGroup=" + teamGroup +
                '}';
    }

    @Override
    public int compareTo(Team o) {
        return o.getTeamScore() - this.getTeamScore();
    }
}
