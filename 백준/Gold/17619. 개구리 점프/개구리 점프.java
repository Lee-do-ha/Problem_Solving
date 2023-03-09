import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    static class tree implements Comparable<tree>{
        int start, end, height, index;
        public tree(int start, int end, int height,int  index) {
            this.start = start;
            this.end = end;
            this.height = height;
            this.index = index;
        }

        @Override
        public int compareTo(tree o) {
            if(this.start == o.start) return this.end - o.end;

            return this.start - o.start;
        }

        @Override
        public String toString() {
            return "tree{" +
                    "start=" + start +
                    ", end=" + end +
                    ", height=" + height +
                    ", index=" + index +
                    '}';
        }
    }

    static int[] parents;
    static tree[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        parents = new int[N];
        trees = new tree[N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            parents[i] = i;
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            trees[i] = new tree(a,b,c,i);
        }

        Arrays.sort(trees);

        for(int i = 0 ; i < N-1 ; i++){
            if(trees[i].end >= trees[i+1].start){
                union(trees[i].index, trees[i+1].index);
            }
        }

        for(int i = 0 ; i < Q ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(unionFind(a-1) == unionFind(b-1)){
                sb.append(1).append("\n");
            }else{
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int unionFind(int n){
        if(parents[n] == n) return n;

        return parents[n] = unionFind(parents[n]);
    }

    private static void union(int n , int m){
        if(unionFind(n) == unionFind(m)) return;

        parents[unionFind(m)] = unionFind(n);
    }
}