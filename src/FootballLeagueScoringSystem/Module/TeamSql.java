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
            String sql = "select teamname,teamscore from footballteam";
            ResultSet rs = statement.executeQuery(sql);

            String teamname = null;
            int teamscore = 0;
            int i=0;
            while (rs.next()){

                teamname = rs.getString("teamname");
                teamscore = rs.getInt("teamscore");
                this.teams[i]=new Team(teamname,teamscore);
                i++;
            }
            Arrays.sort(this.teams);
            for(i=0;i<teams.length;i++){
                sql = "update footballteam set teamrank=" +(i+1)+ " where " + "teamname=\""+teams[i].getTeamname()+"\"";
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

            String teamname = null;
            int teamscore = 0;
            int teamrank = 0;
            int i =0;
            while (rs.next()){

                teamname = rs.getString("teamname");
                teamscore = rs.getInt("teamscore");
                teamrank = rs.getInt("teamrank");
                System.out.println(teamname+teamscore+teamrank);
                this.teams[i]=new Team(teamname,teamscore,teamrank);
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
