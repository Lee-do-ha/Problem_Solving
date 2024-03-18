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
        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        long A = Math.min(X, Y);
        long B = Math.max(X, Y);
        long sum = (A + B) * ((B - A + 1) / 2);

        if((B - A) % 2 == 0) {
            sum += (A + B) / 2;
        }

        System.out.println(sum);

    }

}