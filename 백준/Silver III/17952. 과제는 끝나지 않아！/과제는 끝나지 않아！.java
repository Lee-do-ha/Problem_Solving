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

        Stack<int[]> stack = new Stack<>();

        int ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 0) {
                if (!stack.isEmpty()) {
                    int[] cur = stack.pop();

                    if (cur[1] == 1) {
                        ans += cur[0];
                    } else if (cur[1] > 1) {
                        stack.push(new int[]{cur[0], cur[1] - 1});
                    }
                }
            } else {
                int score = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                if (time == 1) {
                    ans += score;
                } else {
                    stack.push(new int[] {score, time-1});
                }

            }
        }
        System.out.println(ans);
    }

}