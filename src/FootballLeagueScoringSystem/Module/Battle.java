package FootballLeagueScoringSystem.Module;

import java.sql.Timestamp;

public class Battle implements Comparable<Battle>{
    private Timestamp battletime ;  //对战时间
    private String teamA;
    private String teamB;
    private String battleside;      //比赛场地
    private String battleresult;    //比赛结果，1表示A胜，0表示平局，-1表示A负
    private String battlescore;     //比赛比分

    /**
     * @author :long
     * 比赛还没开始之前的构造函数
     */
    public Battle(String teamA,String teamB,Timestamp battletime,String battleside){
        this.teamA=teamA;
        this.teamB=teamB;
        this.battletime=battletime;
        this.battleside=battleside;
    }

    /**
     * @author :long
     * 比赛结束后的构造函数
     */
    public Battle(String teamA,String teamB,Timestamp battletime,String battleside,String battleresult,String battlescore){
        this.teamA=teamA;
        this.teamB=teamB;
        this.battletime=battletime;
        this.battleside=battleside;
        this.battleresult=battleresult;
        this.battlescore=battlescore;
    }

    public Timestamp getBattletime() {
        return battletime;
    }

    public String getTeamA() {
        return teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public String getBattleside() {
        return battleside;
    }

    public String getBattleresult() {
        return battleresult;
    }

    public String getBattlescore() {
        return battlescore;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "battletime=" + battletime +
                ", teamA='" + teamA + '\'' +
                ", teamB='" + teamB + '\'' +
                ", battleside='" + battleside + '\'' +
                ", battleresult='" + battleresult + '\'' +
                ", battlescore='" + battlescore + '\'' +
                '}';
    }

    @Override
    public int compareTo(Battle o) {
        return o.battletime.compareTo(this.battletime);
    }
}
