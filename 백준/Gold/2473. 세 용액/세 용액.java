import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long max;
    static long[] result, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        max = 3000000000L;
        result = new long[3];

        Arrays.sort(arr);

        for(int i = 0 ; i < N ; i++){
            int start = 0;;
            int end = N-1;

            while(start < end){
                long sum = arr[i];

                if(start == i){
                    start++;
                    continue;
                }

                if(end == i){
                    end--;
                    continue;
                }

                sum += arr[start] + arr[end];

                if(Math.abs(sum) < Math.abs(max)){
                    result[0] = arr[i];
                    result[1] = arr[start];
                    result[2] = arr[end];

                    max = sum;
                }

                if (sum == 0) {
                    Arrays.sort(result);
                    for(long k: result) System.out.print(k + " ");
                    return;
                }else if(sum > 0){
                    end--;
                }else {
                    start++;
                }
            }
        }

        Arrays.sort(result);

        for(long i : result) System.out.print(i + " ");
    }
}