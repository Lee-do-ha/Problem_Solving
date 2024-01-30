import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        char pre = '$';
        int ans = 0;

        String str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == pre) {
                ans += 5;
            } else {
                ans += 10;
                pre = str.charAt(i);
            }
        }
        System.out.println(ans);
    }

}