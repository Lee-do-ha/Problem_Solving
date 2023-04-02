import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 주어진 입력 받을 배열
        int[] arr = new int[N];
        
        // 증가하는 수열 저장할 배열
        int[] length = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 초기값 세팅
        length[1] = arr[0];

        // 초기길이 세팅
        int nowLength = 1;

        for(int i = 1 ; i < N ; i++) {
            // 증가하는 수열의 맨끝값보다 크다면 길이 증가시키고 값 넣어주기
            if(arr[i] > length[nowLength]) {
                length[nowLength+1] = arr[i];
                nowLength++;
            // 수열의 값보다 작다면 대체할 자리 찾기 -> 이분탐색
            }else{
                int start = 1;
                int end = nowLength;

                while(end > start){
                    int mid = (start+end) / 2;
                    if(length[mid] <= arr[i]){
                        start = mid+1;
                    }else{
                        end = mid;
                    }
                }
                // 증가하는 수열이므로 이전값과 같으면 안됨
                if(length[start-1] != arr[i]){
                    length[start] = arr[i];
                }
            }
        }
        System.out.println(nowLength);
    }
}
