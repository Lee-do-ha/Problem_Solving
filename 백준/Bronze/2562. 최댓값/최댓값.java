import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int max = 0;
        int ans = 0;

        for (int i = 1; i < 10; i++) {
            int cur = Integer.parseInt(br.readLine());

            if (cur > max) {
                max = cur;
                ans = i;
            }
        }

        System.out.println(max);
        System.out.println(ans);
    }
}