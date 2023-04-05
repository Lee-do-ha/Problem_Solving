import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;

    // 좌표, 높이, 경사로가 설치되었는지 유무 체크
    static class Node{
        int index, height, used;

        public Node(int index, int height, int used) {
            this.index = index;
            this.height = height;
            this.used = used;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        int result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<Node> queue = new LinkedList<>();

        for(int i = 0 ; i < N ; i++){
            
            // 가로 BFS체크
            boolean lowCheck = false;
            queue.add(new Node(0, map[i][0], 0));

            while(!queue.isEmpty()){
                Node now = queue.poll();
                // 마지막 좌표까지 도달하면 true로 체크
                if(now.index == N-1){
                    lowCheck = true;
                    queue.clear();
                    break;
                }

                // 오른쪽 칸 높이 같을 때
                if(map[i][now.index+1] == map[i][now.index]){
                    queue.add(new Node(now.index+1, now.height, 0));
                }

                // 현재 기준으로 더 낮은쪽으로 경사로 체크
                if(now.index + K < N){
                    if(now.height-1 == map[i][now.index+K]){
                        // 모두 같은 높이인지 체크하기
                        boolean check = true;
                        // 하나라도 다르면 false
                        for(int k = now.index+1 ; k < now.index+K ; k++){
                            if(map[i][now.index+K] != map[i][k]){
                                check = false;
                                break;
                            }
                        }
                        // 모두 같다면 낮은쪽 좌표 경사로 설치된것으로 BFS진행
                        if(check){
                            queue.add(new Node(now.index+ K, now.height-1, 1));
                        }
                    }
                }

                // 위로 경사로 지을려면 현재 좌표에 경사로가 안지어져있어야함
                if(now.used != 1){
                    if(now.index + K < N){
                        if(now.height+1 == map[i][now.index+K]){
                            // 모두 같은 높이인지 체크하기
                            boolean check = true;
                            // 하나라도 다르면 false
                            for(int k = now.index+1 ; k < now.index+K ; k++){
                                if(map[i][now.index] != map[i][k]){
                                    check = false;
                                    break;
                                }
                            }
                            // 모두 같다면 높은쪽 좌표로 BFS진행
                            if(check){
                                queue.add(new Node(now.index+ K, now.height+1, 0));
                            }
                        }
                    }
                }
            }





            // 세로 -> 가로랑 같은 방식
            boolean curCheck = false;
            queue.add(new Node(0, map[0][i], 0));

            while(!queue.isEmpty()){
                Node now = queue.poll();
                if(now.index == N-1){
                    curCheck = true;
                    queue.clear();
                    break;
                }

                if(map[now.index+1][i] == map[now.index][i]){
                    queue.add(new Node(now.index+1, now.height, 0));
                }

                if(now.index + K < N){
                    if(now.height-1 == map[now.index+K][i]){
                        boolean check = true;
                        for(int k = now.index+1 ; k < now.index+K ; k++){
                            if(map[now.index+K][i] != map[k][i]){
                                check = false;
                                break;
                            }
                        }
                        if(check){
                            queue.add(new Node(now.index+ K, now.height-1, 1));
                        }
                    }
                }

                if(now.used != 1){
                    if(now.index + K < N){
                        if(now.height+1 == map[now.index+K][i]){
                            boolean check = true;
                            for(int k = now.index+1 ; k < now.index+K ; k++){
                                if(map[now.index][i] != map[k][i]){
                                    check = false;
                                    break;
                                }
                            }
                            if(check){
                                queue.add(new Node(now.index+ K, now.height+1, 0));
                            }
                        }
                    }
                }
            }



            if(lowCheck) {
                result++;
            }
            if(curCheck) {
                result++;
            }
        }

        System.out.println(result);
    }
}
