import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static char[][] map;
    static ArrayList<int[]> list;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        map = new char[8][8];
        list = new ArrayList<>();

        for(int i = 0 ; i < 8 ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < 8 ; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == '#'){
                    list.add(new int[] {i, j});
                }
            }
        }

        boolean arrived = false;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {7, 0});

        int time = 1;

        while(!queue.isEmpty()){

            int size = queue.size();

            for(int i = 0 ; i < size ; i++){

                int[] cur = queue.poll();
                
                if(cur[0] == 0 && cur[1] == 7){
                    arrived = true;
                    queue.clear();
                    break;
                }

                if(map[cur[0]][cur[1]] == '#') continue;

                for(int k = 0 ; k < 9 ; k++){
                    int changeX = cur[0] + dx[k];
                    int changeY = cur[1] + dy[k];

                    if(changeX >= 0 && changeX < 8 && changeY >= 0 && changeY < 8){
                        if(map[changeX][changeY] == '.'){
                            queue.add(new int[] {changeX, changeY});
                        }
                    }

                }

            }
            time++;
            if(!list.isEmpty()){
                down();
            }

        }
        if(arrived){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }

    private static void down(){

        for(int i = list.size()-1 ; i >= 0 ; i--){
            map[list.get(i)[0]][list.get(i)[1]] = '.';

            if(list.get(i)[0] + 1  < 8){
                map[list.get(i)[0] + 1][list.get(i)[1]] = '#';
                list.get(i)[0]++;
            }else{
                list.remove(i);
            }
        }

    }
}