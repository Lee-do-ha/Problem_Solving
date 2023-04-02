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

        int[] arr = new int[N];
        int[] length = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        length[1] = arr[0];

        int nowLength = 1;

        for(int i = 1 ; i < N ; i++) {
            if(arr[i] > length[nowLength]) {
                length[nowLength+1] = arr[i];
                nowLength++;
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
                if(length[start-1] != arr[i]){
                    length[start] = arr[i];
                }
            }
//        	for(int k = 1 ; k < nowLength+1 ; k++) {
//        		System.out.print(length[k]+ " ");
//        	}
//        	System.out.println();
        }

        System.out.println(nowLength);
    }
}