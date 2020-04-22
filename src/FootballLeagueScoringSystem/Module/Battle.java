package FootballLeagueScoringSystem.Module;

import java.sql.Timestamp;

public class Battle implements Comparable<Battle>{
    private Timestamp battleTime;  //对战时间
    private String teamA;
    private String teamB;
    private String battleSide;      //比赛场地
    private String battleResult;    //比赛结果，1表示A胜，0表示平局，-1表示A负
    private String battleScore;     //比赛比分

    /**
     * @author :long
     * 比赛还没开始之前的构造函数
     */
    public Battle(String teamA, String teamB, Timestamp battleTime, String battleSide){
        this.teamA=teamA;
        this.teamB=teamB;
        this.battleTime = battleTime;
        this.battleSide = battleSide;
    }

    /**
     * @author :long
     * 比赛结束后的构造函数
     */
    public Battle(String teamA, String teamB, Timestamp battleTime, String battleSide, String battleResult, String battleScore){
        this.teamA=teamA;
        this.teamB=teamB;
        this.battleTime = battleTime;
        this.battleSide = battleSide;
        this.battleResult = battleResult;
        this.battleScore = battleScore;
    }

    public Timestamp getBattleTime() {
        return battleTime;
    }

    public String getTeamA() {
        return teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public String getBattleSide() {
        return battleSide;
    }

    public String getBattleResult() {
        return battleResult;
    }

    public String getBattleScore() {
        return battleScore;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "battleTime=" + battleTime +
                ", teamA='" + teamA + '\'' +
                ", teamB='" + teamB + '\'' +
                ", battleSide='" + battleSide + '\'' +
                ", battleResult='" + battleResult + '\'' +
                ", battleScore='" + battleScore + '\'' +
                '}';
    }

    @Override
    public int compareTo(Battle o) {
        return o.battleTime.compareTo(this.battleTime);
    }
}
