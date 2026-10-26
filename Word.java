public class Word {
    private String text;
    private int difficulty; 

    public Word(String text, int difficulty) {
        this.text = text;
        this.difficulty = difficulty;
    }

    public boolean isCorrect(String userInput) {
        if (userInput == null) {
            return false;
        }
        return this.text.trim().equalsIgnoreCase(userInput.trim());
    }

    public String getText() {
        return text;
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return text;
    }
}