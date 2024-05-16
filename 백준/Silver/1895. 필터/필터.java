import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int T = Integer.parseInt(br.readLine());
        int ans = 0;

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 2; j++) {
                list.clear();
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        list.add(map[i+x][j+y]);
                    }
                }
                Collections.sort(list);

                if(list.get(4) >= T) ans++;
            }
        }
        System.out.println(ans);
    }

}