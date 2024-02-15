import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int start = Integer.parseInt(br.readLine());
        int end = Integer.parseInt(br.readLine());

        boolean flag = false;

        int sum = 0;
        int ans = 0;

        for (int i = start; i <= end; i++) {
            if (check(i)) {
                sum += i;
                if (!flag) {
                    flag = true;
                    ans = i;
                }
            }
        }

        if (flag) {
            System.out.println(sum);
            System.out.println(ans);
        } else {
            System.out.println(-1);
        }

    }

    private static boolean check(int k) {

        if (k == 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(k); i++) {
            if (k % i == 0) {
                return false;
            }
        }

        return true;
    }

}