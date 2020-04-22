package FootballLeagueScoringSystem.Module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class PlayerSql {
    private Player[] players = new Player[5];
    /**
     * @param :
     * @author :long
     * 将player根据score进行排序，并写入playerrank
     */
    public void playerSort(){
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

            String playername = null;
            String playerphoto = null;
            String teamname = null;
            String foul = null;
            int playerscore = 0;
            int i=0;
            while (rs.next()){
                playername = rs.getString("playername");
                teamname = rs.getString("playerteam");
                playerscore = rs.getInt("playerscore");
                this.players[i]=new Player(playername,teamname,playerscore);
                i++;
            }
            Arrays.sort(this.players);
            for(i=0;i<players.length;i++){
                sql = "update footballplayer set playerrank=" +(i+1)+ " where " + "playername=\""+players[i].getName()+"\"";
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

            String playername = null;
            String playerphoto = null;
            String teamname = null;
            String foul = null;
            int playerscore = 0;
            int rank = 0;
            int i=0;
            while (rs.next()){
                playername = rs.getString("playername");
                teamname = rs.getString("playerteam");
                playerscore = rs.getInt("playerscore");
                rank = rs.getInt("playerrank");
                this.players[i]=new Player(playername,teamname,playerscore,rank);
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

}
