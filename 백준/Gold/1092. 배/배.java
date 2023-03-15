import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        ArrayList<Integer> Crane = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            Crane.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer> Box = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i++){
            Box.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(Crane, Collections.reverseOrder());
        Collections.sort(Box, Collections.reverseOrder());

        int time = 0;

        if(Crane.get(0) < Box.get(0)){
            System.out.println(-1);
            return;
        }

        while(!Box.isEmpty()){
            time++;

            int crane = 0;
            int box = 0;

            while(crane < N){
                if(box == Box.size()){
                    break;
                }else if(Crane.get(crane) >= Box.get(box)){
                    Box.remove(box);
                    crane++;
                }else{
                    box++;
                }
            }
        }
        System.out.println(time);
    }
}