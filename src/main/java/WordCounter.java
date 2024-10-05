import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class WordCounter {
    public void wordCounter(){
        try(FileInputStream fileInputStream = new FileInputStream("words.txt")){
            byte[] array = new byte[fileInputStream.available()];
            fileInputStream.read(array);

            HashMap<String, Integer> map = new HashMap<>();
            String word = "";
            for (int i = 0; i < array.length; i++) {
                if (array[i]!=13 && array[i]!=32 && array[i]!=10){
                    word += (char) array[i];
                }
                else if(array[i]!=10 && word.length()>0){
                    if (map.containsKey(word)){
                        map.put(word, map.get(word)+1);
                    }
                    else map.put(word, 1);
                    word = "";
                }
                if (i==array.length-1){
                    if (map.containsKey(word)){
                        map.put(word, map.get(word)+1);
                    }
                    else map.put(word, 1);
                }
            }
            for (String keys : map.keySet())
            {
                System.out.println(keys + " " + map.get(keys));
            }

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter();
        wordCounter.wordCounter();
    }
}