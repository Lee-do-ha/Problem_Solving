import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 무게가 낮은 추부터 올려서 무게 올리기
        Arrays.sort(arr);

        // 현재 잴 수 있는 최대 무게
        // 초기값은 0
        int sum = 0;

        for(int i = 0 ; i < N ; i++){
            // 현재까지 잴 수 있는 무게보다 작거나 1큰 무게가 와야 잴 수 있음
            // 따라서 현재의 무게에 1을 더한 값보다 커진다면 현재의 무게+1의 무게는 잴 수가 없음
            if(sum + 1 < arr[i]){
                break;
            }else{
                // 잴 수 있다면 최대 무게는 이번 추까지 사용한 무게로 갱신
                sum += arr[i];
            }
        }
        // break가 걸렸다면 그 당시의 최대 무게 + 1 출력
        // for문을 모두 통과했다면 모든 무게 사용한 무게 + 1 출력
        System.out.println(sum+1);
    }
}
