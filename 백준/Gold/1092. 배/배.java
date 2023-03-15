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
        
        // 크레인이 실을 수 있는 무게
        ArrayList<Integer> Crane = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            Crane.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());

        // 실어야 하는 박스들의 무게
        ArrayList<Integer> Box = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i++){
            Box.add(Integer.parseInt(st.nextToken()));
        }

        // 두 리스트 모두 내림차순 정렬
        Collections.sort(Crane, Collections.reverseOrder());
        Collections.sort(Box, Collections.reverseOrder());

        // 걸리는 시간
        int time = 0;

        // 가장 큰 크레인이 가장 큰 박스 실을 수 없다면 -1 출력
        if(Crane.get(0) < Box.get(0)){
            System.out.println(-1);
            return;
        }

        // 박스가 빌때까지 반복
        while(!Box.isEmpty()){
            time++;

            int crane = 0;
            int box = 0;

            // 마지막 크레인까지 반복
            while(crane < N){
                // 마지막 박스까지 확인했다면 break하고 처음 while문으로 돌아가기
                if(box == Box.size()){
                    break;
                // 실을 수 있다면 그 박스 삭제하고 다음 크레인확인
                }else if(Crane.get(crane) >= Box.get(box)){
                    Box.remove(box);
                    crane++;
                // 실을 수 없다면 그 다음 박스 확인
                }else{
                    box++;
                }
            }
        }
        System.out.println(time);
    }
}
