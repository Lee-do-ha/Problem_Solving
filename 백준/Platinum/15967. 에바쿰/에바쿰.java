import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static long[] seg, lazy, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q1 = Integer.parseInt(st.nextToken());
        int Q2 = Integer.parseInt(st.nextToken());

        seg = new long[N * 4];
        lazy = new long[N * 4];
        arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init(1, 0, N - 1);

        for (int i = 0; i < (Q1 + Q2); i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());

                long ans = getSum(1, 0, N - 1, Integer.min(left - 1, right - 1), Integer.max(left - 1, right - 1));

                sb.append(ans).append("\n");
            } else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                int change = Integer.parseInt(st.nextToken());

                addSum(1, 0, N - 1, Integer.min(left - 1, right - 1), Integer.max(left - 1, right - 1), change);
            }

        }
        System.out.println(sb);

    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            seg[node] = arr[start];
            return ;
        }
        int middle = (start + end) / 2;
        init(node * 2, start, middle);
        init(node * 2 + 1, middle + 1, end);

        seg[node] = seg[node * 2] + seg[node * 2 + 1];
    }

    private static void setLazy(int node, int start, int end) {
        if (lazy[node] == 0) return;

        if (start != end) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }

        seg[node] += (end - start + 1) * lazy[node];
        lazy[node] = 0;
    }

    private static long getSum(int node, int start, int end, int left, int right) {
        setLazy(node, start, end);

        if(start > right || end < left) return 0;

        if (start >= left && end <= right) {
            return seg[node];
        }

        int middle = (start + end) / 2;

        long leftSum = getSum(node * 2, start, middle, left, right);
        long rightSum = getSum(node * 2 + 1, middle + 1, end, left, right);

        return leftSum + rightSum;
    }

    private static void addSum(int node, int start, int end, int left, int right, int change) {
        setLazy(node, start, end);

        if(start > right || end < left) return;

        if (start >= left && end <= right) {
            lazy[node] += change;
            setLazy(node, start, end);
            return;
        }

        int middle = (start + end) / 2;

        addSum(node * 2, start, middle, left, right, change);
        addSum(node * 2 + 1, middle + 1, end, left, right, change);

        seg[node] = seg[node * 2] + seg[node * 2 + 1];
    }

}