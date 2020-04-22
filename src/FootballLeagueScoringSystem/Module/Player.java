package FootballLeagueScoringSystem.Module;

public class Player implements Comparable<Player> {
    private String name;
    private String photo_address;
    private String teamname;
    private String foul;    //违规信息
    private int score;
    private int rank;

    public Player(String name,String teamname,int score){
        this.name=name;
        this.teamname=teamname;
        this.score=score;
    }

    public Player(String name,String teamname,int score,int rank){
        this.name=name;
        this.teamname=teamname;
        this.score=score;
        this.rank=rank;
    }

    public Player(String name,String teamname,String foul,int score){
        this.name=name;
        this.teamname=teamname;
        this.foul=foul;
        this.score=score;
    }
    public String getName() {
        return name;
    }

    public String getPhoto_address() {
        return photo_address;
    }

    public String getTeamname() {
        return teamname;
    }

    public int getScore() {
        return score;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public int compareTo(Player o) {
        return o.score-this.score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", photo_address='" + photo_address + '\'' +
                ", teamname='" + teamname + '\'' +
                ", foul='" + foul + '\'' +
                ", score=" + score +
                ", rank=" + rank +
                '}';
    }
}
