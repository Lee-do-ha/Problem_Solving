import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        String str = br.readLine();

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i = 0 ; i < str.length() ; i++){
            priorityQueue.add(Integer.parseInt(String.valueOf(str.charAt(i))));
        }

        str = "";

        while (!priorityQueue.isEmpty()){
            str += priorityQueue.poll();
        }

        System.out.println(str);
    }
}