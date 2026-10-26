import java.util.ArrayList;
import java.util.Collections;

public class WordManager {

    public static ArrayList<Word> getRandomGameWords() {
        ArrayList<Word> easyList = new ArrayList<>();
        ArrayList<Word> mediumList = new ArrayList<>();
        ArrayList<Word> hardList = new ArrayList<>();
        ArrayList<Word> veryHardList = new ArrayList<>();

        easyList.add(new Word("cat", 1));
        easyList.add(new Word("dog", 1));
        easyList.add(new Word("run", 1));
        easyList.add(new Word("sun", 1));
        easyList.add(new Word("sky", 1));
        easyList.add(new Word("box", 1));
        easyList.add(new Word("red", 1));

        mediumList.add(new Word("java", 2));
        mediumList.add(new Word("code", 2));
        mediumList.add(new Word("game", 2));
        mediumList.add(new Word("loop", 2));
        mediumList.add(new Word("main", 2));
        mediumList.add(new Word("data", 2));

        hardList.add(new Word("socket", 3));
        hardList.add(new Word("server", 3));
        hardList.add(new Word("client", 3));
        hardList.add(new Word("thread", 3));
        hardList.add(new Word("action", 3));
        hardList.add(new Word("method", 3));

        veryHardList.add(new Word("network", 4));
        veryHardList.add(new Word("running", 4));
        veryHardList.add(new Word("program", 4));
        veryHardList.add(new Word("display", 4));
        veryHardList.add(new Word("winner", 4));
        veryHardList.add(new Word("protocol", 4));

        Collections.shuffle(easyList);
        Collections.shuffle(mediumList);
        Collections.shuffle(hardList);
        Collections.shuffle(veryHardList);

        ArrayList<Word> selectedWords = new ArrayList<>();

        for (int i = 0; i < 5; i++) selectedWords.add(easyList.get(i));
        for (int i = 0; i < 5; i++) selectedWords.add(mediumList.get(i));
        for (int i = 0; i < 5; i++) selectedWords.add(hardList.get(i));
        for (int i = 0; i < 5; i++) selectedWords.add(veryHardList.get(i));

        return selectedWords; 
    }
}