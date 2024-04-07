import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[K][2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i][0] = x;
            arr[i][1] = y;

        }


        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int x = arr[i][0];
                int y = arr[j][1];

                int count = 0;
                for (int k = 0; k < arr.length; k++) {
                    if(arr[k][0] >= x && arr[k][0] <= x + L && arr[k][1] >= y && arr[k][1] <= y + L) count++;
                }
//                System.out.println("x = " + x + "  y = " + y + " count = " + count);
                max = Math.max(max, count);
            }
        }

//        System.out.println(max);
        System.out.println(K - max);

    }
}