import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[100000];

        Queue<Integer> queue = new LinkedList<>();

        boolean flag = false;

        queue.add(N);
        visited[N] = true;

        int time = 0;

        while (!queue.isEmpty() && time <= M && !flag) {

            int size = queue.size();

            for(int i = 0 ; i < size ; i++){

                int cur = queue.poll();

                if (cur == K) {
                    flag = true;
                    break;
                }

                if (cur + 1 < 100000 && !visited[cur + 1]) {
                    queue.add(cur + 1);
                    visited[cur + 1] = true;
                }

                if (cur * 2 < 100000) {

                    int changeNum = cur * 2;

                    for(int k = 1 ; k < 6 ; k++){
                        if (changeNum < Math.pow(10, k)) {
                            changeNum -= Math.pow(10, k-1);
                            break;
                        }
                    }

                    if (changeNum >= 0 && changeNum < 100000 && !visited[changeNum]) {
                        queue.add(changeNum);
                        visited[changeNum] = true;
                    }

                }

            }
            if (flag) {
                break;
            }
            time++;
        }

        if (flag) {
            System.out.println(time);
        } else {
            System.out.println("ANG");
        }

    }

}