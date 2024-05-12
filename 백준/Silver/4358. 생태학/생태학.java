import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        String str = "";
        HashMap<String, Integer> hashMap = new HashMap<>();
        int size = 0;

        while ((str = br.readLine()) != null && !str.isEmpty()) {

            hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);
            size++;

        }

        ArrayList<String> list = new ArrayList<>(hashMap.keySet());
        Collections.sort(list);

        for (String name : list) {
            System.out.println(name + " " + String.format("%.4f", ((double)hashMap.get(name) * 100 / (double) size)));
        }
    }

}