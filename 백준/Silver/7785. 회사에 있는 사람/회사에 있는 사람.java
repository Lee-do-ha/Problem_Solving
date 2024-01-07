import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        HashSet<String> hashSet = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            String name = input.split(" ")[0];
            String type = input.split(" ")[1];

            if (type.equals("enter")) {
                hashSet.add(name);
            }

            if (type.equals("leave")) {
                hashSet.remove(name);
            }
        }

        List<String> list = new ArrayList<>();

        for (String n : hashSet) {
            list.add(n);
        }

        Collections.sort(list, Collections.reverseOrder());

        for (String name : list) {
            System.out.println(name);
        }

    }

}