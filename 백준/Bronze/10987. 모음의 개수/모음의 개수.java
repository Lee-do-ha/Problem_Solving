import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        int ans = 0;

        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);

            if(cur == 'a' || cur == 'e' || cur == 'i' || cur == 'o' || cur == 'u') ans++;
        }
        System.out.println(ans);
    }

}