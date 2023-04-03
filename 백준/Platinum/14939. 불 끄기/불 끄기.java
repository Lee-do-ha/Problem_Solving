import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[][] original;
    static boolean flag;
    static int result;
    static int[] dx = {-1,1,0,0,0};
    static int[] dy = {0,0,-1,1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        original = new char[10][10];
        
        // 불을 다 끌 수 있는지 확인하기 위함
        flag = false;
        
        // 최솟값을 구해야 하므로 처음은 최대값으로 저장
        result = Integer.MAX_VALUE;

        // 원본 상태
        for(int i = 0 ; i < 10 ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < 10 ; j++){
                original[i][j] = str.charAt(j);
            }
        }

        // 비트마스크 수행
        Bitmask();

        if(flag){
            System.out.println(result);
        }else{
            System.out.println(-1);
        }
    }

    // 총 칸이 10개이므로 2^10까지 수행
    private static void Bitmask(){
        for(int i = 0 ; i < 1024 ; i++){
            Greedy(i);
        }
    }

    private static void Greedy(int bit){
        
        // 원본 상태 deepCopy
        char[][] copy = new char[10][10];
        int clickNum = 0;

        for(int i = 0 ; i < 10 ; i++){
            copy[i] = original[i].clone();
        }

        // 각 자리에 수가 1이라면 스위치 누르고 누른 횟수 증가
        for(int i = 0 ; i < 10 ; i++){
            if((bit & (1<<i)) > 0){
                Switch(0, i, copy);
                clickNum++;
            }
        }

        // 다음부터는 그리디로 불이 켜져있으면 그 바로 아래칸 스위치 누르기
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                if(copy[i][j] == 'O'){
                    Switch(i+1,j , copy);
                    clickNum++;
                }
            }
        }

        // 맨 아래칸이 다 꺼져있지 않다면 return
        for(int i = 0 ; i < 10 ; i++){
            if(copy[9][i] == 'O') return;
        }

        // 위 문장을 통과했다면 불이 모두 꺼진것이므로 flag를 true로 바꾸기 정답 최솟값으로 갱신
        flag = true;
        result = Math.min(result, clickNum);
    }

    // 스위치 불끄기
    private static void Switch(int a , int b, char[][] map){
        for(int i = 0 ; i < 5 ; i++){
            int changeX = a + dx[i];
            int changeY = b + dy[i];
            if(changeX >= 0 && changeX < 10 && changeY >= 0 && changeY < 10){
                if(map[changeX][changeY] == 'O'){
                    map[changeX][changeY] = '#';
                }else if(map[changeX][changeY] == '#'){
                    map[changeX][changeY] = 'O';
                }
            }
        }
    }
}
