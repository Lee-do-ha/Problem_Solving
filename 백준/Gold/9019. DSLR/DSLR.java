import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class DSLR{
        int number;
        String command;

        public DSLR(int number, String command) {
            this.number = number;
            this.command = command;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[10000];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Arrays.fill(visited, false);

            Queue<DSLR> queue = new LinkedList<>();

            queue.add(new DSLR(a, ""));

            visited[a] = true;

            String ans = null;

            while (!queue.isEmpty()) {
                DSLR cur = queue.poll();

                int dCur = D(cur.number);
                int sCur = S(cur.number);
                int lCur = L(cur.number);
                int rCur = R(cur.number);

                if (!visited[dCur]) {
                    if (dCur == b) {
                        ans = cur.command + "D";
                        break;
                    } else {
                        visited[dCur] = true;
                        queue.add(new DSLR(dCur, cur.command + "D"));
                    } 
                }

                if (!visited[sCur]) {
                    if (sCur == b) {
                        ans = cur.command + "S";
                        break;
                    } else {
                        visited[sCur] = true;
                        queue.add(new DSLR(sCur, cur.command + "S"));
                    }
                }

                if (!visited[lCur]) {
                    if (lCur == b) {
                        ans = cur.command + "L";
                        queue.clear();
                        break;
                    } else {
                        visited[lCur] = true;
                        queue.add(new DSLR(lCur, cur.command + "L"));
                    }
                }

                if (!visited[rCur]) {
                    if (rCur == b) {
                        ans = cur.command + "R";
                        break;
                    } else {
                        visited[rCur] = true;
                        queue.add(new DSLR(rCur, cur.command + "R"));
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int D(int n) {
        return (2 * n) % 10000;
    }
    private static int S(int n) {
        if (n == 0) {
            return 9999;
        } else {
            return n-1;
        }
    }
    private static int L(int n) {
        return  (n % 1000) * 10 + n / 1000;
    }
    private static int R(int n) {
        return (n % 10) * 1000 + n / 10;
    }
}