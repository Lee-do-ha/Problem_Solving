import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        long A = Long.parseLong(br.readLine());
        long B = Long.parseLong(br.readLine());
        long C = Long.parseLong(br.readLine());

        String result = String.valueOf(A * B * C);

        int[] arr = new int[10];

        for (int i = 0; i < result.length(); i++) {
            arr[Integer.parseInt(String.valueOf(result.charAt(i)))]++;
        }

        for (int i = 0; i < 10; i++) {
            sb.append(arr[i]).append("\n");
        }

        System.out.println(sb);

    }

}