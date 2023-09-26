import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int price = Integer.parseInt(br.readLine());

        if(a + b >= price * 2){
            stringBuilder.append(a + b - (price * 2));
        }else{
            stringBuilder.append(a + b);
        }

        System.out.println(stringBuilder);
    }
}