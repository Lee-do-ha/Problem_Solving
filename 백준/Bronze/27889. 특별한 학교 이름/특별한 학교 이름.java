import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("NLCS", "North London Collegiate School");
        hashMap.put("BHA", "Branksome Hall Asia");
        hashMap.put("KIS", "Korea International School");
        hashMap.put("SJA", "St. Johnsbury Academy");

        String input = br.readLine();

        System.out.println(hashMap.get(input));
    }

}