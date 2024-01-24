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

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());

            if(check(input)) ans++;

        }

        System.out.println(ans);

    }

    private static boolean check(int input) {
        if (input <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(input); i++) {
            if(input % i == 0) return false;
        }

        return true;

    }

}