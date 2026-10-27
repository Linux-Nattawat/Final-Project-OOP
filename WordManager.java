import java.util.ArrayList;
import java.util.Collections;

public class WordManager {

    public static ArrayList<Word> getRandomGameWords() {
        ArrayList<Word> easyList = new ArrayList<>();
        ArrayList<Word> mediumList = new ArrayList<>();
        ArrayList<Word> hardList = new ArrayList<>();
        ArrayList<Word> veryHardList = new ArrayList<>();

        easyList.add(new Word("bug", 1));
        easyList.add(new Word("byte", 1));
        easyList.add(new Word("node", 1));
        easyList.add(new Word("path", 1));
        easyList.add(new Word("host", 1));
        easyList.add(new Word("json", 1));
        easyList.add(new Word("ping", 1));
        easyList.add(new Word("java", 1));
        easyList.add(new Word("code", 1));
        easyList.add(new Word("game", 1));
        easyList.add(new Word("loop", 1));
        easyList.add(new Word("main", 1));
        easyList.add(new Word("data", 1));
        easyList.add(new Word("port", 1));
        easyList.add(new Word("root", 1));
        easyList.add(new Word("null", 1));
        easyList.add(new Word("true", 1));
        easyList.add(new Word("void", 1));
        easyList.add(new Word("file", 1));
        easyList.add(new Word("user", 1));
        easyList.add(new Word("html", 1));
        easyList.add(new Word("func", 1));
        easyList.add(new Word("char", 1));
        easyList.add(new Word("push", 1));

        mediumList.add(new Word("socket", 2));
        mediumList.add(new Word("server", 2));
        mediumList.add(new Word("client", 2));
        mediumList.add(new Word("thread", 2));
        mediumList.add(new Word("action", 2));
        mediumList.add(new Word("method", 2));
        mediumList.add(new Word("script", 2));
        mediumList.add(new Word("commit", 2));
        mediumList.add(new Word("branch", 2));
        mediumList.add(new Word("docker", 2));
        mediumList.add(new Word("python", 2));
        mediumList.add(new Word("binary", 2));
        mediumList.add(new Word("domain", 2));
        mediumList.add(new Word("string", 2));
        mediumList.add(new Word("object", 2));
        mediumList.add(new Word("cookie", 2));
        mediumList.add(new Word("return", 2));
        mediumList.add(new Word("network", 2));
        mediumList.add(new Word("running", 2));
        mediumList.add(new Word("program", 2));
        mediumList.add(new Word("display", 2));
        mediumList.add(new Word("winner", 2));
        mediumList.add(new Word("protocol", 2));

        hardList.add(new Word("algorithm", 3));
        hardList.add(new Word("framework", 3));
        hardList.add(new Word("namespace", 3));
        hardList.add(new Word("repository", 3));
        hardList.add(new Word("parameter", 3));
        hardList.add(new Word("breakpoint", 3));
        hardList.add(new Word("properties", 3));
        hardList.add(new Word("inheritance ", 3));
        hardList.add(new Word("javascript", 3));
        hardList.add(new Word("dependency", 3));
        hardList.add(new Word("refactoring", 3));
        hardList.add(new Word("interface", 3));
        hardList.add(new Word("dictionary", 3));
        hardList.add(new Word("operating", 3));

        veryHardList.add(new Word("multithreading",4));
        veryHardList.add(new Word("cryptography",4));
        veryHardList.add(new Word("authentication",4));
        veryHardList.add(new Word("authorization",4));
        veryHardList.add(new Word("implementation",4));
        veryHardList.add(new Word("responsiveness",4));
        veryHardList.add(new Word("telemetry",4));
        veryHardList.add(new Word("microservices",4));
        veryHardList.add(new Word("infrastructure",4));
        veryHardList.add(new Word("containerization",4));
        veryHardList.add(new Word("internationalization",4));
        
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