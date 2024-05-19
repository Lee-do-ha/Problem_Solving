import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parents = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            sb.append("Scenario " + (t+1) + ":").append("\n");
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }

            int M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                unionSet(a, b);
            }

            int K = Integer.parseInt(br.readLine());
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (findSet(a) == findSet(b)) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int findSet(int a) {
        if(parents[a] == a) return a;

        return parents[a] = findSet(parents[a]);
    }

    private static void unionSet(int a, int b) {
        int pA = findSet(a);
        int pB = findSet(b);

        if (pA != pB) {
            parents[pB] = pA;
        }

    }

}