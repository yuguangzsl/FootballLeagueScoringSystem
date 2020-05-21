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
    public Player[] players;//球员列表

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
        getPlayers();
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
            Statement statement = conn.createStatement();
            String sql = "select * from footballteam where teamName='" + teamName + "'";
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
                this.players = new Player[11];
                getPlayers();
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
            Statement statement = conn.createStatement();
            String sql = "update footballteam set teamname = '" + this.teamName
                    + "',teamrank=" + this.teamRank
                    + ",winNum=" + this.winNum
                    + ",loseNum=" + this.loseNum
                    + ",drawNum=" + this.drawNum
                    + ",goalNum=" + this.goalNum
                    + ",goalLostNum=" + this.goalLostNum
                    + ",teamscore=" + this.teamScore
                    + ",teamgroup='" + this.teamGroup
                    + "',where teamName='" + teamName + "'";
            statement.executeUpdate(sql);
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertData() {
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
            Statement statement = conn.createStatement();
            statement.execute("SET FOREIGN_KEY_CHECKS=0");//关闭外键约束检查
            String sql = "INSERT INTO footballteam (teamname,teamrank,winNum,loseNum,drawNum,goalNum,goalLostNum,teamscore,teamgroup) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.teamName);
            ps.setInt(2, this.teamRank);
            ps.setInt(3, this.winNum);
            ps.setInt(4, this.loseNum);
            ps.setInt(5, this.drawNum);
            ps.setInt(6, this.goalNum);
            ps.setInt(7, this.goalLostNum);
            ps.setInt(8, this.teamScore);
            ps.setString(9, this.teamGroup);
            ps.executeUpdate();
            statement.execute("SET FOREIGN_KEY_CHECKS=1");
            conn.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Player[] getPlayers() {
        Player[] players = new Player[20];
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "select * from footballplayer where playerteamName ='" + this.teamName + "'";
            ResultSet rs = statement.executeQuery(sql);
            String playerName = null;
            int i = 0;
            while (rs.next()) {
                playerName = rs.getString("playerName");
                players[i] = new Player(this.teamName, playerName);
                i++;
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    public String[] getGameInfo() {
        /**
         * @author:quanhao
         * 获取这支队伍的比赛信息
         * */
        Connection conn;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/football?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        String[] result = new String[50];
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "select * from battledetail where teamone='" + teamName + "'" + "or teamtwo='" + this.teamName + "'";
            ResultSet rs = statement.executeQuery(sql);
            int i=0;
            while (rs.next()) {
                result[i] = "";
                if(rs.getString("teamOne")==teamName) {
                    result[i] += rs.getString("teamOne") + "VS";
                    result[i] += rs.getString("teamTwo") + "\t\t";

                }
                else {
                    result[i] += rs.getString("teamTwo") + "VS";
                    result[i] += rs.getString("teamOne") + "\t\t";
                }
                int status = rs.getInt("battleResult");
                switch (status) {
                    case 1:
                        result[i] += rs.getString("teamOne") + "获胜" + "\n地点：";
                        break;
                    case 0:
                        result[i] += "平局" + "\n地点：";
                        break;
                    case -1:
                        result[i] += rs.getString("teamTwo") + "获胜" + "\n地点：";
                        break;
                    case -2:
                        result[i] += "比赛未开始" + "\n地点：";
                        break;
                }
                result[i] += rs.getString("battleSide") + "\t时间：";
                result[i] += rs.getString("battleTime");
                i++;
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
