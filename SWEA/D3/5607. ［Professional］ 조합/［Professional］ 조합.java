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

            // N Combination M = (N!) * ((N-M)! * M!)^-1
            // 페르마의 소정리로 인해 N! 뒷부분은 분모이므로 분자로 변환 시키면 ((N-M)! * M!)^P-2 와 같음
            long result = Factorial(N) * pow(Factorial(N-M) * Factorial(M) % P, P - 2) % P;

            sb.append("#").append(test).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    // 팩토리얼 구하기
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

    // 분할정복으로 거듭제곱값 구하기
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
