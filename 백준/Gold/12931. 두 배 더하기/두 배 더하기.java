import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] lists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        
        while (!finished(arr)) {

            count++;

            if (odd(arr)) {
                for (int i = 0; i < arr.length; i++) {
                    arr[i] /= 2;
                }
                continue;
            }

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != 0 && arr[i] % 2 != 0) {
                    arr[i]--;
                    break;
                }
            }

        }
        System.out.println(count);
    }

    private static boolean finished(int[] arr){
        for (int k : arr) {
            if(k != 0) return false;
        }

        return true;
    }

    private static boolean odd(int[] arr) {
        for (int k : arr) {
            if (k % 2 != 0) {
                return false;
            }
        }

        return true;
    }

}