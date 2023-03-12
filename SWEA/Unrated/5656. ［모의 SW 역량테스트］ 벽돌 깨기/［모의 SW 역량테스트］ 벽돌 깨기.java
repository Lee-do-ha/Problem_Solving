import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int[][] map, map2;
    static int W , H, N, result;
    static int[] numbers;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int test = 1 ; test <= T ; test++) {
            result  = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N =Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[H][W];

            for(int i = 0 ; i < H ; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < W ; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            numbers = new int[N];

            Combination(W, 0);

            sb.append("#" + test + " " + result).append("\n");
        }
        System.out.println(sb);
    }

    private static void Brick(int[] arr) {
        map2 = new int[H][W];

        for(int i = 0 ; i < H ; i++) {
            for(int j = 0 ; j < W ; j++) {
                map2[i][j] = map[i][j];
            }
        }

        for(int i = 0 ; i < arr.length ; i++) {
            visited = new boolean[H][W];

            int k = arr[i];
            int x = 0;
            for(int z = 0 ; z < H ; z++) {
                if(map2[z][k] != 0) {
                    x = z;
                    break;
                }
            }

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[] {x,k});
            visited[x][k] = true;

            while(!q.isEmpty()) {
                int[] a = q.poll();

                int size = map2[a[0]][a[1]];
                map2[a[0]][a[1]] = 0;

                for(int z = 1 ; z < size ; z++) {
                    if(a[0] + z >= 0 && a[0]+z < H) {
                        if(map2[a[0]+z][a[1]] != 0 && visited[a[0]+z][a[1]] == false) {
                            q.add(new int[] {a[0]+z, a[1]});
                            visited[a[0]+z][a[1]]= true;
                        }
                    }

                    if(a[0]-z >= 0 && a[0] - z < H) {
                        if(map2[a[0]-z][a[1]] != 0 && visited[a[0]-z][a[1]] == false) {
                            q.add(new int[] {a[0]-z, a[1]});
                            visited[a[0]-z][a[1]]= true;
                        }
                    }

                    if(a[1]+z >= 0 && a[1] + z < W) {
                        if(map2[a[0]][a[1]+z] != 0 && visited[a[0]][a[1]+z] == false) {
                            q.add(new int[] {a[0], a[1]+z});
                            visited[a[0]][a[1]+z]= true;
                        }
                    }

                    if(a[1]-z >= 0 && a[1] - z < W) {
                        if(map2[a[0]][a[1]-z] != 0 && visited[a[0]][a[1]-z] == false) {
                            q.add(new int[] {a[0], a[1]-z});
                            visited[a[0]][a[1]-z]= true;
                        }
                    }
                }
            }
            Move();
        }
        
        Count();
    }

    private static void Move() {
        Queue<Integer>[] queue = new LinkedList[W];

        for(int i = 0 ; i  < W ; i++) {
            queue[i] = new LinkedList<>();
        }

        for(int x = 0 ; x < W ; x++) {
            for(int y = H-1 ; y >= 0 ; y--) {
                if(map2[y][x] != 0) {
                    queue[x].add(map2[y][x]);
                    map2[y][x] = 0;
                }
            }
        }

        for(int x = 0 ; x < W ; x++) {
            int size = queue[x].size();
            for(int y = H-1 ; y > H-1-size ; y--) {
                map2[y][x] = queue[x].poll();
            }
        }
    }

    private static void Combination(int n, int cnt) {
        if(cnt == N) {
//			System.out.println(Arrays.toString(numbers));
            Brick(numbers);
            return;
        }

        for(int i = 0 ; i < W ; i++) {
            numbers[cnt] = i;
            Combination(n, cnt+1);
        }

    }

    private static void Count() {
        int sum = 0;
        for(int i = 0 ; i < H ; i++) {
            for(int j = 0 ; j < W ; j++) {
                if(map2[i][j] != 0) {
                    sum++;
                }
            }
        }
        result = Integer.min(result, sum);
    }

}