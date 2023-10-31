import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        String str = br.readLine();

        char[] arr = str.toCharArray();

        int time = 1;

        for (int i = 1 ; i < arr.length ; i++){
            if (arr[i] <= arr[i - 1]) {
                time++;
            }
        }

        System.out.println(time);

    }

}