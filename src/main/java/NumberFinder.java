import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberFinder {

    public static String readFile() {
        StringBuilder res = new StringBuilder();
        try (FileReader reader = new FileReader("file.txt")) {
            int c;
            while ((c = reader.read()) != -1) {
                res.append((char) c);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return String.valueOf(res);
    }

    public void findNumbers() {
        String text = readFile();
        StringBuilder num = new StringBuilder();
        String array[] = text.split("\\r");
        Pattern pattern1 = Pattern.compile("[\\d?]?[\\d?]?[\\d?]-[\\d?]?[\\d?]?[\\d?]-[\\d?]?[\\d?]?[\\d?][\\d?]");
        Pattern pattern2 = Pattern.compile("\\([\\d?]?[\\d?]?[\\d?]\\) [\\d?]?[\\d?]?[\\d?]-[\\d?]?[\\d?]?[\\d?][\\d?]");
        for (int i = 0; i < array.length; i++) {
            Matcher matcher1 = pattern1.matcher(array[i]);
            Matcher matcher2 = pattern2.matcher(array[i]);
            if (matcher1.find()) System.out.println(matcher1.group());
            if (matcher2.find()) System.out.println(matcher2.group());
        }
    }
    public static void main(String[] args) {
        NumberFinder n = new NumberFinder();
        n.findNumbers();
    }
}
