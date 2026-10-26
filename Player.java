public class Player {
    private String name;
    private int score;
    private int indexWord;

    public Player(String name) {
        this.name = name;
        score = 0;
        indexWord = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getIndexWord() {
        return indexWord;
    }

    public void addScore(int timeLeft){
        score = score + (timeLeft * 10);
    }

    public void nextWord(){
        indexWord++;
    }

}
