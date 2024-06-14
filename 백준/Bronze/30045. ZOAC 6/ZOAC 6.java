import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int ans = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            boolean flag = check(str);

            if (flag) ans++;
        }
        System.out.println(ans);
    }

    private static boolean check(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == '0') {
                if (i + 1 < length && str.charAt(i + 1) == '1') {
                    return true;
                }
            } else if (str.charAt(i) == 'O') {
                if (i + 1 < length && str.charAt(i + 1) == 'I') {
                    return true;
                }
            }
        }
        return false;
    }

}