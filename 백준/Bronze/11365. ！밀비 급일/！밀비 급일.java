import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        String str = br.readLine();

        while (!str.equals("END")) {

            for (int i = str.length() - 1; i >= 0; i--) {
                stringBuilder.append(str.toCharArray()[i]);
            }
            stringBuilder.append("\n");
            str = br.readLine();
        }

        System.out.println(stringBuilder);
    }

}