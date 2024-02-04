import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class node{
        int score; char name;

        public node(int score, char name) {
            this.score = score;
            this.name = name;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int[] arr1 = new int[3];
        node[] arr2 = new node[3];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int score = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            arr1[i] = year % 100;
            arr2[i] = new node(score, name.charAt(0));
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2, Comparator.comparingInt(o -> -o.score));

        String str1 = ""; String str2 = "";

        for (int i = 0; i < 3; i++) {
            str1 += String.valueOf(arr1[i]);
            str2 += arr2[i].name;
        }

        System.out.println(str1);
        System.out.println(str2);

    }

}