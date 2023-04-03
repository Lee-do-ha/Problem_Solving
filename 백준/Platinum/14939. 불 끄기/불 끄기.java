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
        flag = false;
        result = Integer.MAX_VALUE;

        for(int i = 0 ; i < 10 ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < 10 ; j++){
                original[i][j] = str.charAt(j);
            }
        }

        Bitmask();

        if(flag){
            System.out.println(result);
        }else{
            System.out.println(-1);
        }
    }

    private static void Bitmask(){
        for(int i = 0 ; i < 1024 ; i++){
            Greedy(i);
        }
    }

    private static void Greedy(int bit){
        char[][] copy = new char[10][10];
        int clickNum = 0;

        for(int i = 0 ; i < 10 ; i++){
            copy[i] = original[i].clone();
        }

        for(int i = 0 ; i < 10 ; i++){
            if((bit & (1<<i)) > 0){
                Switch(0, i, copy);
                clickNum++;
            }
        }

        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                if(copy[i][j] == 'O'){
                    Switch(i+1,j , copy);
                    clickNum++;
                }
            }
        }

        for(int i = 0 ; i < 10 ; i++){
            if(copy[9][i] == 'O') return;
        }

        flag = true;
        result = Math.min(result, clickNum);
    }

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
