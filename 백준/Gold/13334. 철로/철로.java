import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class node{
        int start, end;

        public node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        ArrayList<node> list = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.add(new node(Integer.min(a, b), Integer.max(a, b)));
        }

        int length = Integer.parseInt(br.readLine());

        Collections.sort(list, Comparator.comparingInt(o -> o.end));

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        int count = 0;
        int ans = 0;

        for(node node : list){

            while (!priorityQueue.isEmpty() && priorityQueue.peek() < node.end - length){
                priorityQueue.poll();
                count--;
            }

            if(node.start >= node.end - length){
                count++;
                priorityQueue.add(node.start);
            }

            ans = Integer.max(ans, count);

        }
        System.out.println(ans);
    }
}
