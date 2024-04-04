import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[26];
        Arrays.fill(arr, -1);

        String str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);

            if (arr[cur - 'a'] == -1) {
                arr[cur - 'a'] = i;
            }
        }

        for (int i : arr) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }

}