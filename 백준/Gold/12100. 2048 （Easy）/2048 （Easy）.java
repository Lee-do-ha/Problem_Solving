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
    static Deque<Node> deque;

    // 숫자 밀때 해당 숫자와 그 숫자가 이미 합쳐진 수인지 체크하기 위한 클래스
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
        // 어느 방향으로 밀지 저장할 배열
        numbers = new int[5];
        // 정답저장할 result
        result = 0;
        // 숫자 옮길때 사용할 Deque
        deque = new ArrayDeque<>();

        // 순열 구하기 시작
        permutation(0);

        System.out.println(result);
    }

    // 순열 구하기
    private static void permutation(int cnt){
        // 5가 되면 5개의 숫자가 완성됐기때문에 옮기기 시작
        if (cnt == 5){
            move(numbers);
            return;
        }

        for(int i = 0 ; i < 4 ; i++){
            numbers[cnt] = i;
            permutation(cnt+1);
        }
    }

    // 5번 밀기 메소드
    private static void move(int[] arr){
        // 주어진 map deepCopy
        int[][] copy = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            copy[i] = map[i].clone();
        }


        for(int i = 0 ; i < 5 ; i++){
            switch (arr[i]){
                // 왼쪽으로 밀기
                case 0:
                    for(int x = 0 ; x < N ; x++){
                        for(int y = 0 ; y < N ; y++){
                            // 값이 0인지 체크
                            if(copy[x][y] != 0){
                                int a = copy[x][y];
                                // 0이 아닐경우 Deque가 비었는지 체크
                                if(!deque.isEmpty()){
                                    // 맨 뒤에 들어온 값을 사용하지 않았고 값이 현재 값과 같다면 맨뒤의 값 빼고 합친 값 사용한 상태로 deque에 저장
                                    if(deque.peekLast().weight == a && deque.peekLast().used == false){
                                        deque.pollLast();
                                        a *= 2;
                                        deque.add(new Node(a, true));
                                    // 합칠수없다면 사용하지않은상태로 deque에 저장
                                    }else{
                                        deque.add(new Node(a, false));
                                    }
                                // 비었을시에 사용하지않을걸로 deque에 저장
                                }else{
                                    deque.add(new Node(a, false));
                                }
                                // copy맵 0으로 초기화
                                copy[x][y] = 0;
                            }
                        }
                        
                        // deque가 비었는지 체크
                        if(!deque.isEmpty()){
                            int a = 0;
                            // 맨 왼쪽부터 값 넣어주기
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
        }
        fineMax(copy);
    }

    // 최대값 찾기 메소드
    private static void fineMax(int[][] A){

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(result < A[i][j]) result = A[i][j];
            }
        }
    }
}
