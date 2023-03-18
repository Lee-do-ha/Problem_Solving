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

            // 종료 할때 들어오는 값
            if(a == 0 && b == 0 && c == 0){
                return;
            }

            // 숫자들 저장할 리스트
            ArrayList<Integer> List = new ArrayList<>();

            for(int i = 1 ; i < a+1 ; i++){
                List.add(i);
            }

            // 리스트 인덱스는 0부터 시작이므로 -1해주고 시작
            c = c-1;

            // 리스트에 값이 하나 남을때까지
            while(List.size() != 1){
                // 리스트에서 해당 인덱스번호 삭제
                List.remove(c);

                // 다음 번호는 b-1칸 앞으로 가서 현재 리스트의 사이즈만큼 나눈 나머지의 값
                c = (c+b-1)%List.size();
            }
            // 리스트에 값이 하나 나오면 while문이 종료되기때문에 인덱스 0번값 출력
            System.out.println(List.get(0));
        }
    }
}
