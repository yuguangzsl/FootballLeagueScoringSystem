package FootballLeagueScoringSystem.Module;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

/**
 * @param :
 * @author QuanHao
 * 球队的抽象模型:
 * 包含参数：队名，名次，胜场数，负场数，进球，失球，积分
 */
public class FootballTeam {
    private String groupName;//球队所在组名
    private String teamName;//球队名字
    private int rank;//球队排名
    private int winNum;//球队胜场数
    private int loseNum;//球队负场数
    private int goalNum;//球队进球数
    private int goalLostNum;//球队失球数
    private int integral;//积分
    private FootballPlayer[] players = new FootballPlayer[11];//球员列表
    private String status;//球队的状态：晋级->可以继续比赛，淘汰->不能继续比赛

    private
    /**
     * 构造方法1，用于自定义球队
     * 参数：球队所在的小组名,队名,球员列表
     *
     * @param
     */
    FootballTeam(String groupName, String teamName, FootballPlayer[] players) {
        this.groupName = groupName;
        this.teamName = teamName;
        this.players = players;
        this.winNum = 0;
        this.loseNum = 0;
        this.goalNum = 0;
        this.goalLostNum = 0;
        this.integral = 0;
        this.mkdir();
        writeTeamBasicInto(groupName, teamName, winNum, loseNum, goalNum, goalLostNum, integral);
    }

    /**
     * 构造方法2，用于自动创建球队
     */
    public FootballTeam(String groupName, int i) {
        this.groupName = groupName;
        this.teamName = groupName + i;
        //自动生成对应的球员
        FootballPlayer[] players = new FootballPlayer[11];
        for (int j = 0; j < 11; j++) {
            players[j] = new FootballPlayer(this.teamName, j + 1);
        }
        this.players = players;
        this.winNum = 0;
        this.loseNum = 0;
        this.goalNum = 0;
        this.goalLostNum = 0;
        this.integral = 0;
        this.mkdir();
        writeTeamBasicInto(groupName, teamName, winNum, loseNum, goalNum, goalLostNum, integral);
    }

    public String getTeamName() {
        return teamName;
    }

    public int getRank() {
        return rank;
    }

    public int getWinNum() {
        return winNum;
    }

    public int getLoseNum() {
        return loseNum;
    }

    public int getGoalNum() {
        return goalNum;
    }

    public int getGoalLostNum() {
        return goalLostNum;
    }

    public int getIntegral() {
        return integral;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setWinNum(int winNum) {
        this.winNum = winNum;
    }

    public void setLoseNum(int loseNum) {
        this.loseNum = loseNum;
    }

    public void setGoalNum(int goalNum) {
        this.goalNum = goalNum;
    }

    public void setGoalLostNum(int goalLostNum) {
        this.goalLostNum = goalLostNum;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public FootballPlayer[] getPlayers() {
        return players;
    }

    public FootballPlayer getPlayer(int i) {
        return players[i];
    }

    public String getRecordFileDirectory() {
        /**
         * 返回记录这个球队的信息的所属文件地址
         * */
        return "./GameData/TeamData/" + this.teamName;
    }

    public void mkdir() {
        /**
         * 自动创建文件目录
         * */
        File directory = new File(this.getRecordFileDirectory());
        if (!directory.exists()) {
            System.out.println("创建目录中...\n");
            directory.mkdirs();
        } else return;
    }

    @Override
    public String toString() {
        return "球队{" +
                "所在组='" + groupName + '\'' +
                ", 队名='" + teamName + '\'' +
                ", 当前排名=" + rank +
                ", 胜场数=" + winNum +
                ", 负场数=" + loseNum +
                ", 进球数=" + goalNum +
                ", 失球数=" + goalLostNum +
                ", 积分=" + integral +
                ", 球员=" + Arrays.toString(players) +
                '}';
    }


    private void writeTeamBasicInto(String groupName, String teamName, int winNum,
                                    int loseNum, int goalNum, int goalLostNum, int integral) {
        /**
         * 写入球队的基本信息
         */
        try {
            File file = new File(this.getRecordFileDirectory() + "/" + this.teamName + "BasicInfo.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(this.getRecordFileDirectory() + "/" + this.teamName + "BasicInfo.txt", false);
            fileWriter.write(groupName + "\n"
                    + teamName + "\n"
                    + winNum + "\n"
                    + loseNum + "\n"
                    + goalNum + "\n"
                    + goalLostNum + "\n"
                    + integral + "\n");
            fileWriter.close();
            System.out.println("Basic Info write success!");
        } finally {
            return;
        }
    }

    private void readTeamBasicInto(String groupName, String teamName, int winNum,
                                   int loseNum, int goalNum, int goalLostNum, int integral) {
        /**
         * 从文件中读取球队基本信息
         * */
        try {
            FileReader fileReader = new FileReader(this.getRecordFileDirectory() + "/" + this.teamName + "BasicInfo.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            this.groupName = bufferedReader.readLine();
            this.teamName = bufferedReader.readLine();
            this.winNum = Integer.valueOf(bufferedReader.readLine());
            this.loseNum = Integer.valueOf(bufferedReader.readLine());
            this.goalNum = Integer.valueOf(bufferedReader.readLine());
            this.goalLostNum = Integer.valueOf(bufferedReader.readLine());
            this.integral = Integer.valueOf(bufferedReader.readLine());
        } finally {
            return;
        }
    }

    public void writeGameRecord(String nameA, String nameB, String status) {
        /**
         * 将球队的比赛信息写入文件
         * */
        try {
            File file = new File(this.getRecordFileDirectory() + "/" + this.teamName + "GameData.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(this.getRecordFileDirectory() + "/" + this.teamName + "GameData.txt", true);
            fileWriter.write(nameA + "\n"
                    + nameB + "\n"
                    + status + "\n");
            fileWriter.close();
            System.out.println("game Info write success!");
        } finally {
            return;
        }
    }

    public String readGameRecord() {
        /**
         * 从文件中读取球队的比赛信息
         * */
        String GameInfo = "";
        try {
            StringBuffer stringBuffer = new StringBuffer();
            FileReader fileReader = new FileReader(this.getRecordFileDirectory() + "/" + this.teamName + "GameData.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuffer.append(line);
                stringBuffer.append(" ");
                line = bufferedReader.readLine();
            }
            GameInfo = stringBuffer.toString();
            return GameInfo;
        } finally {
            return GameInfo;
        }
    }
}
