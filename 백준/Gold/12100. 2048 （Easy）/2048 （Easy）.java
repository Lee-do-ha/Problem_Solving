import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int[] numbers;
    static int result, N;
    static boolean[] visited;
    static Deque<Node> deque;

    static class Node{
        int weight;
        boolean used;

        public Node(int weight, boolean used) {
            this.weight = weight;
            this.used = used;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        numbers = new int[5];
        visited = new boolean[4];
        result = 0;
        deque = new ArrayDeque<>();

        permutation(0);

        System.out.println(result);
    }

    private static void permutation(int cnt){
        if (cnt == 5){
//            System.out.println(Arrays.toString(numbers));
            move(numbers);
            return;
        }

        for(int i = 0 ; i < 4 ; i++){
            if(visited[i]) continue;

            numbers[cnt] = i;
            permutation(cnt+1);
        }
    }

    private static void move(int[] arr){
        int[][] copy = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            copy[i] = map[i].clone();
        }

        int size = 0;

        for(int i = 0 ; i < 5 ; i++){
            switch (arr[i]){
                // 왼쪽으로 밀기
                case 0:
                    for(int x = 0 ; x < N ; x++){
                        for(int y = 0 ; y < N ; y++){
                            if(copy[x][y] != 0){

                                int a = copy[x][y];
                                if(!deque.isEmpty()){
                                    if(deque.peekLast().weight == a && deque.peekLast().used == false){
                                        deque.pollLast();
                                        a *= 2;
                                        deque.add(new Node(a, true));
                                    }else{
                                        deque.add(new Node(a, false));
                                    }
                                }else{
                                    deque.add(new Node(a, false));
                                }
                                copy[x][y] = 0;
                            }
                        }

                        if(!deque.isEmpty()){
                            int a = 0;
                            while(!deque.isEmpty()){
                                copy[x][a] = deque.pollFirst().weight;
                                a++;
                            }
                        }
                    }
                    break;
                // 오른쪽으로 밀기
                case 1:
                    for(int x = 0 ; x < N ; x++){
                        for(int y = N-1 ; y >= 0 ; y--){
                            if(copy[x][y] != 0){

                                int a = copy[x][y];
                                if(!deque.isEmpty()){
                                    if(deque.peekLast().weight == a && deque.peekLast().used == false){
                                        deque.pollLast();
                                        a *= 2;
                                        deque.add(new Node(a, true));
                                    }else{
                                        deque.add(new Node(a, false));
                                    }
                                }else{
                                    deque.add(new Node(a, false));
                                }
                                copy[x][y] = 0;
                            }
                        }
                        if(!deque.isEmpty()){
                            int a = N-1;
                            while(!deque.isEmpty()){
                                copy[x][a] = deque.pollFirst().weight;
                                a--;
                            }
                        }
                    }
                    break;
                // 위쪽으로 밀기
                case 2:
                    for(int x = 0 ; x < N ; x++){
                        for(int y = 0 ; y < N ; y++){
                            if(copy[y][x] != 0){

                                int a = copy[y][x];
                                if(!deque.isEmpty()){
                                    if(deque.peekLast().weight == a && deque.peekLast().used == false){
                                        deque.pollLast();
                                        a *= 2;
                                        deque.add(new Node(a, true));
                                    }else{
                                        deque.add(new Node(a, false));
                                    }
                                }else{
                                    deque.add(new Node(a, false));
                                }
                                copy[y][x] = 0;
                            }
                        }
                        if(!deque.isEmpty()){
                            int a = 0;
                            while(!deque.isEmpty()){
                                copy[a][x] = deque.pollFirst().weight;
                                a++;
                            }
                        }
                    }
                    break;
                // 아래로 밀기
                case 3:
                    for(int x = 0 ; x < N ; x++){
                        for(int y = N-1 ; y >= 0 ; y--){
                            if(copy[y][x] != 0){

                                int a = copy[y][x];
                                if(!deque.isEmpty()){
                                    if(deque.peekLast().weight == a && deque.peekLast().used == false){
                                        deque.pollLast();
                                        a *= 2;
                                        deque.add(new Node(a, true));
                                    }else{
                                        deque.add(new Node(a, false));
                                    }
                                }else{
                                    deque.add(new Node(a, false));
                                }
                                copy[y][x] = 0;
                            }
                        }
                        if(!deque.isEmpty()){
                            int a = N-1;
                            while(!deque.isEmpty()){
                                copy[a][x] = deque.pollFirst().weight;
                                a--;
                            }
                        }
                    }
                    break;
            }
//            System.out.println(i + "번째 회전");
//            for(int[] a : copy){
//                System.out.println(Arrays.toString(a));
//            }
        }
        fineMax(copy);
    }

    private static void fineMax(int[][] A){

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(result < A[i][j]) result = A[i][j];
            }
        }
    }
}