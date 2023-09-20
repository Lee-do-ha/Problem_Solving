import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        Long allPrice = Long.parseLong(br.readLine());

        Long N = Long.parseLong(br.readLine());

        Long price = 0L;

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            price += Long.parseLong(st.nextToken()) * Long.parseLong(st.nextToken());
        }
        
        if(allPrice.equals(price)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

    }
}