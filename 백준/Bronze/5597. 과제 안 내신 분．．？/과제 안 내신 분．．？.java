import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>();
        for (int i = 1; i <= 30; i++) {
            hashSet.add(i);
        }

        for (int i = 0; i < 28; i++) {
            hashSet.remove(Integer.parseInt(br.readLine()));
        }


        for (int i : hashSet) {
            System.out.println(i);
        }

    }

}