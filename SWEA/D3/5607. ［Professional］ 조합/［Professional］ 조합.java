import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static final int P = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int test = 1 ; test <= T ; test++){
            st = new StringTokenizer(br.readLine());

            long N = Integer.parseInt(st.nextToken());
            long M = Integer.parseInt(st.nextToken());

            long result = Factorial(N) * pow(Factorial(N-M) * Factorial(M) % P, P - 2) % P;
            
            sb.append("#").append(test).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static long Factorial(long a){
        if(a == 1) return a;

        long k = 1;
        long result = 1;

        while(k <= a){
            result = (result * k) % P;
            k++;
        }

        return result;
    }

    private static long pow(long a, long b){
        if(b == 1) return a;
        if(b == 0) return 1;

        long ans = pow(a, b/2) % P;

        if(b%2 == 0){
            return ans * ans % P;
        }else{
            return (ans * ans % P) * a % P;
        }
    }
}
