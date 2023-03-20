import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    // 세 용액의 합이 int를 벗어날수도 있으므로 long으로 선언
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

        // 현재 가지고 있다고 가정하는 세 용액의 합 -> 최소를 찾기 위해
        max = 3000000000L;
        result = new long[3];

        // 배열 오름차순 정렬
        Arrays.sort(arr);

        // i번째 index의 용액은 포함하는 경우
        for(int i = 0 ; i < N ; i++){
            int start = 0;;
            int end = N-1;

            // 하나는 작은 값, 하나는 큰 값인데 작은 값이 큰 값보다 커지면 break
            while(start < end){
                long sum = arr[i];

                // i번째는 이미 선택했으므로 continue
                if(start == i){
                    start++;
                    continue;
                }
                
                // i번째는 이미 선택했으므로 continue
                if(end == i){
                    end--;
                    continue;
                }

                sum += arr[start] + arr[end];

                // sum의 절대값이 max의 절대값보다 큰지 비교
                if(Math.abs(sum) < Math.abs(max)){
                    // 크다면 정답 배열에 값 넣고 max값 갱신
                    result[0] = arr[i];
                    result[1] = arr[start];
                    result[2] = arr[end];

                    max = sum;
                }

                // sum이 0 이면 더이상 찾을 필요가 없으므로 배열 정렬하고 출력하고 return
                if (sum == 0) {
                    Arrays.sort(result);
                    for(long k: result) System.out.print(k + " ");
                    return;
                // sum이 양수라면 end--
                }else if(sum > 0){
                    end--;
                // sum이 음수라면 start++
                }else {
                    start++;
                }
            }
        }

        // 배열 정렬하고 
        Arrays.sort(result);

        for(long i : result) System.out.print(i + " ");
    }
}
