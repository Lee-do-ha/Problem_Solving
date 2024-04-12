import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int N = Integer.parseInt(br.readLine());

            BigInteger ans = new BigInteger("0");
            for (int k = 0; k < N; k++) {
                ans = ans.add(new BigInteger(br.readLine()));
            }
            
            if (ans.compareTo(new BigInteger("0")) == 0 ) {
                sb.append(0).append("\n");
            } else if (ans.compareTo(new BigInteger("0")) < 0) {
                sb.append("-").append("\n");
            } else if (ans.compareTo(new BigInteger("0")) > 0) {
                sb.append("+").append("\n");
            }
        }
        System.out.println(sb);
    }
}