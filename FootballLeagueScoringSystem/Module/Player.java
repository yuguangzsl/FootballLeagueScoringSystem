package FootballLeagueScoringSystem.Module;

import java.sql.*;

/**
 * @param :name,photo_address,teamName,score,foul,rank
 * @author QuanHao
 * @author Long
 */
public class Player implements Comparable<Player> {
    private String name;//姓名
    private String teamName;//所属球队名
    private String foul;    //违规信息
    private int score;//进球数/积分数
    private int rank;//排名
    private String photo_address;//图片地址

    public Player(String name, String teamName, String foul, int score, int rank, String photo_address) {
        /**
         * 全参数传入，生成一个新的player对象
         * */
        this.name = name;
        this.teamName = teamName;
        this.foul = foul;
        this.score = score;
        this.rank = rank;
        this.photo_address = photo_address;
    }

    public Player(String teamName, String name) {
        /**@author:Long
         * 仅传入队名，球员名字，从数据库中读取数据生成一个player对象
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
            String sql = "select * from footballplayer where playerName='" + name + "' and playerteamName='" + teamName + "'";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                this.name = rs.getString("playerName");
                this.teamName = rs.getString("playerTeamName");
                this.foul = rs.getString("playerFoul");
                this.score = rs.getInt("playerScore");
                this.rank = rs.getInt("playerRank");
                this.photo_address = rs.getString("playerPhoto");
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        /**
         * @author :QUANHAO
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
            String sql = "update footballplayer set playername ='" +this.name
                    +"',playerphoto='" +this.photo_address
                    +"',playerteamName='" +this.teamName
                    +"',playerscore=" +this.score
                    +",playerfoul='" +this.foul
                    +"',playerrank=" +this.rank
                    +" where playerName='" + name + "' and playerTeam='" + teamName + "'";
            statement.executeUpdate(sql);
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
            Statement statement = conn.createStatement();
            String sql = "insert INTO footballplayer values ('"
                    + this.name + "',"
                    + "'" + this.photo_address + "',"
                    + "'" + this.teamName + "',"
                    + this.score + ","
                    + "'" + this.foul + "',"
                    + this.rank + ")";
            statement.executeUpdate(sql);
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String getGoalInfo() {
        /**
         * @author :QUANHAO
         * 从数据库中读取该球员的进球信息
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
            Statement statement = conn.createStatement();
            String sql = "select * from goaldetail where playerName = '" + this.name + "'";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result += rs.getString("playerName")+" ";
                result += rs.getString("teamA")+" ";
                result += rs.getString("teamB")+" ";
                result += rs.getString("time")+" ";
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public String getPhoto_address() {
        return photo_address;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getScore() {
        return score;
    }

    public int getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setFoul(String foul) {
        this.foul = foul;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setPhoto_address(String photo_address) {
        this.photo_address = photo_address;
    }

    @Override
    public int compareTo(Player o) {
        return o.score - this.score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", photo_address='" + photo_address + '\'' +
                ", teamName='" + teamName + '\'' +
                ", foul='" + foul + '\'' +
                ", score=" + score +
                ", rank=" + rank +
                '}';
    }
}
