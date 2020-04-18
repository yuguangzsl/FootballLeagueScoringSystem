package FootballLeagueScoringSystem.Module;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @param : name,teamName,goalNUm，rank
 * @author QuanHao
 * 球员射手的抽象模型
 * 包含参数：姓名，所属球队，进球数，排名
 */
public class FootballPlayer {
    private String name;//球员姓名
    private String teamName;//所属球队名字
    private int goalNum;//进球数
    private int rank;//排名
    private String recordFileName;//记录的文件地址

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getGoalNum() {
        return goalNum;
    }


    public int getRank() {
        return rank;
    }

    FootballPlayer(String name, String teamName, int goalNum, int rank) {
        this.name = name;
        this.teamName = teamName;
        this.goalNum = goalNum;
        this.rank = rank;
        this.mkdir();
        writePlayerInfo(name, teamName, goalNum, rank);
    }

    FootballPlayer(String teamName, int i) {
        this.teamName = teamName;
        this.name = this.teamName + "_" + i;
        this.goalNum = 0;
        this.rank = -1;
        this.mkdir();
        writePlayerInfo(name,teamName,goalNum,rank);
    }

    public String getRecordFileDirectory() {
        /**
         * 返回记录这个球员的信息的所属文件地址
         * */
        return "./GameData/PlayerData/" + this.teamName + "_" + this.name;


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

    public void writePlayerInfo(String name, String teamName, int goalNum, int rank) {
        /**
         * 将球员信息写入到文件中
         * */
        try {
            File file = new File(this.getRecordFileDirectory() + "/" + this.teamName + "_" + this.name + "Info.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(this.getRecordFileDirectory() + "/" + this.teamName + "_" + this.name + "Info.txt", false);
            fileWriter.write(name + "\n" + teamName + "\n" + goalNum + "\n" + rank + "\n");
            System.out.println("Record write success!");
        } finally {
            return;
        }
    }

    public void readPlayerInfo() {
        /**
         * 从文件中读取球员信息
         * */
        try {
            FileReader fileReader = new FileReader(this.getRecordFileDirectory() + "/" + this.teamName + "_" + this.name + "Info.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            this.name = bufferedReader.readLine();
            this.teamName = bufferedReader.readLine();
            this.goalNum = Integer.valueOf(bufferedReader.readLine());
            this.rank = Integer.valueOf(bufferedReader.readLine());
        } finally {
            return;
        }
    }

    public void writeGoalInfo(String teamAName, String teamBName, int minute, int second) {
        /**
         * 把球员进球信息写入文件中记录
         * */
        try {
            File file = new File(this.getRecordFileDirectory() + "/" + this.teamName + "_" + this.name + "GoalInfo.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(this.getRecordFileDirectory() + "/" + this.teamName + "_" + this.name + "GoalInfo.txt", true);
            fileWriter.write(teamAName+"\n"+teamBName+"\n"+minute+"\n"+second+"\n");
            fileWriter.close();
            System.out.println("GoalRecord write success!");
        } finally {
            return;
        }
    }

    public String readGoalInfo() {
        /**
         * 从文件中读取球员进球信息
         * */
        String goalInfo = "";
        try {

            StringBuffer stringBuffer = new StringBuffer();
            FileReader fileReader = new FileReader(this.getRecordFileDirectory() + "/" + this.teamName + "_" + this.name + "GoalInfo.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuffer.append(line);
                stringBuffer.append(" ");
                line = bufferedReader.readLine();
            }
            goalInfo = stringBuffer.toString();
            return goalInfo;
        } finally {
            return goalInfo;
        }
    }

    @Override
    public String toString() {
        return "球员名字：" + this.name + "\n" +
                "所属球队：" + this.teamName + "\n" +
                "进球数：" + this.goalNum + "\n" +
                "当前排名：" + this.rank;
    }
}
