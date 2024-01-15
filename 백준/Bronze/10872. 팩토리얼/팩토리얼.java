import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        System.out.println(factorial(N));

    }

    private static int factorial(int n) {
        int ans = 1;

        for (int i = n; i > 1; i--) {
            ans *= i;
        }

        return ans;
    }

}