import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[10];
        int ans = 0;

        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            ans += arr[i];
        }

        int idx = 0;
        while (idx < 10) {
            if (ans - arr[idx] == arr[idx]) {
                System.out.println(arr[idx]);
                break;
            }
            idx++;
        }

    }

}