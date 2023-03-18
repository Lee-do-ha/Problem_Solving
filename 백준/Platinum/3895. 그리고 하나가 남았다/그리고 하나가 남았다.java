import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        while(true){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 0 && b == 0 && c == 0){
                return;
            }

            ArrayList<Integer> List = new ArrayList<>();

            for(int i = 1 ; i < a+1 ; i++){
                List.add(i);
            }

            c = c-1;

            while(List.size() != 1){
                List.remove(c);

                c = (c+b-1)%List.size();
            }
            System.out.println(List.get(0));
        }
    }
}