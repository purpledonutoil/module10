import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;

public class FromFileToJson {
    private String[] json;

    private void setJsonSize(byte[] array){
        int arraySize = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i]==13) arraySize++;
        }
        json = new String[arraySize];
    }

    public void fromFileToJson(){

        try(FileInputStream fileInputStream = new FileInputStream("file.txt")) {
            byte[] array = new byte[fileInputStream.available()];
            fileInputStream.read(array);

            setJsonSize(array);

            int i = 0;
            while (array[i]!=10) {
                i++;
            }
            i++;

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String[] words = new String[2];
            String word = "";
            int size = 0;
            int wcount = 0;
            for (; i < array.length; i++) {
                if (array[i] == 32 || array[i] == 13){
                    words[wcount] = word;
                    wcount++;
                    word="";
                }
                if (array[i]!=13 && array[i]!=10 && array[i]!=32) word+= (char) array[i];
                if (array[i]==10 || i==array.length-1){
                    wcount = 0;
                    Person person = new Person(words[0], words[1]);
                    json[size] = gson.toJson(person);
                    size++;
                }
                jsonWrite();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void jsonWrite(){
        try (FileWriter writer = new FileWriter("user.json"))
        {
            writer.write(Arrays.toString(json));
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        FromFileToJson f = new FromFileToJson();
        f.fromFileToJson();
    }

}

class Person {
    private String name;
    private String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }
}