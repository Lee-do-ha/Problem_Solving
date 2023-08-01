import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        parent = new int[N];
        for(int i = 0 ; i < N ; i++){
            parent[i] = i;
        }

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0){
                    if(i < j){
                        unionSet(i, j);
                    }else{
                        unionSet(j, i);
                    }
                }
            }
        }

        boolean flag = true;
        st = new StringTokenizer(br.readLine());

        int target = -1;

        for(int i = 0 ; i < M ; i++){
            if(i == 0){
                target = findSet(Integer.parseInt(st.nextToken())-1);
            }else{
                if(findSet(Integer.parseInt(st.nextToken())-1) != target){
                    flag = false;
                    break;
                }
            }
        }

        if(flag == true){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

//        System.out.println(Arrays.toString(parent));
    }

    static int findSet(int a){
        if(parent[a] == a) return a;

        return parent[a] = findSet(parent[a]);
    }

    static void unionSet(int a, int b){

        if(findSet(a) != findSet(b)){
            parent[findSet(b)] = findSet(a);
        }
    }
}