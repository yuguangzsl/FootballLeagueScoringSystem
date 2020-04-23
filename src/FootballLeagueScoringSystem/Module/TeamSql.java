package FootballLeagueScoringSystem.Module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class TeamSql {
    private Team[] teams= new Team[3];//这里的3等全部数据录入后改成64

    /**
     * @param :
     * @author : long
     *对teamscore进行排序，将排序好的teamrank写入数据库
     */
    public void teamSort() {
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
                teamScore = rs.getInt("teamScore");
                this.teams[i]=new Team(teamName,teamScore);
                i++;
            }
            Arrays.sort(this.teams);
            for(i=0;i<teams.length;i++){
                sql = "update footballteam set teamRank=" +(i+1)+ " where " + "teamName=\""+teams[i].getTeamName()+"\"";
                statement.executeUpdate(sql);
                //System.out.println(teams[i].toString());
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
            int teamScore = 0;
            int teamRank = 0;
            int i =0;
            while (rs.next()){

                teamName = rs.getString("teamName");
                teamScore = rs.getInt("teamScore");
                teamRank = rs.getInt("teamRank");
                System.out.println(teamName+teamScore+teamRank);
                this.teams[i]=new Team(teamName,teamScore,teamRank);
                i++;

            }
            rs.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return this.teams;
    }
}
