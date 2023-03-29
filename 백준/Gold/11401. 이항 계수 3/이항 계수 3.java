import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    final static int p = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Integer.parseInt(st.nextToken());
        long k = Integer.parseInt(st.nextToken());

        System.out.println(factorial(n) * pow(factorial(n-k), p-2) % p * pow(factorial(k), p-2) % p );
    }

    // 팩토리얼 구하기
    private static long factorial(long a){
        long result = 1L;
        while(a > 1){
            result = (result*a)%p;
            a--;
        }
        return result;
    }

    // 모듈러 역원 구하기
    private static long pow(long a, long b){
        if(b == 1){
            return a%p;
        }

        long k = pow(a, b/2);

        if(b%2 == 1){
            return (k * k % p) * a % p;
        }else{
            return k * k % p;
        }
    }
}
