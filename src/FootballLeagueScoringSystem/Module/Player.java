package FootballLeagueScoringSystem.Module;

public class Player implements Comparable<Player> {
    private String name;
    private String photo_address;
    private String teamName;
    private String foul;    //违规信息
    private int score;
    private int rank;

    public Player(String name, String teamName, int score){
        this.name=name;
        this.teamName = teamName;
        this.score=score;
    }

    public Player(String name, String teamName, int score, int rank){
        this.name=name;
        this.teamName = teamName;
        this.score=score;
        this.rank=rank;
    }

    public Player(String name, String teamName, String foul, int score){
        this.name=name;
        this.teamName = teamName;
        this.foul=foul;
        this.score=score;
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

    @Override
    public int compareTo(Player o) {
        return o.score-this.score;
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
