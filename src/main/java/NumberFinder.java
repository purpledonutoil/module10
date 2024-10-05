import java.io.FileInputStream;
import java.io.IOException;

public class NumberFinder {
    public void findNumbers(){
        try(FileInputStream fileInputStream = new FileInputStream("file.txt")) {
            byte[] array = new byte[fileInputStream.available()];
            fileInputStream.read(array);
            StringBuilder num = new StringBuilder();
            for (int i = 0; i < array.length; i++) {
                if (array[i]==40){
                    num.append("(");
                    i++;
                }
                boolean check = (array[i] > 47 && array[i] < 58);
                int r = 0;
                while(check){

                    if((r==3 || r==7) && array[i]==45) {
                        num.append((char) array[i]);
                        i++;
                        r++;
                    }
                    check = (array[i] > 47 && array[i] < 58);
                    num.append((char) array[i]);
                    r++;
                    //System.out.println(num);
                    if (r==12 && (array[i+1] < 47 || array[i] > 58)) {
                        System.out.println(num);
                        num = new StringBuilder();
                        break;
                    }
                    i++;
                    if (i==array.length) break;
                    if (array[i]==41 && array[i+1]==32){
                        num.append(") ");
                        i+=2;
                        r++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        NumberFinder n = new NumberFinder();
        n.findNumbers();
    }
}
