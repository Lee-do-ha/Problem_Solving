import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] selected;
    static HashSet<String> hashSet;
    static String[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        arr = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        hashSet = new HashSet<>();
        selected = new int[K];
        visited = new boolean[N];

        select(0, K);

        System.out.println(hashSet.size());

    }

    private static void select(int j, int k) {
        if (j == k) {
            String str = "";
            for (int x = 0; x < selected.length; x++) {
                str += arr[selected[x]];
            }
            hashSet.add(str);
            return;
        }

        for (int x = 0; x < arr.length; x++) {
            if (!visited[x]) {
                visited[x] = true;
                selected[j] = x;
                select(j + 1, k);
                visited[x] = false;
            }
        }

    }
}