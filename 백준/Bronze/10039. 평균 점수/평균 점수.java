import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        int sum = 0;

        for (int i = 0; i < 5; i++) {
            sum += Math.max(Integer.parseInt(br.readLine()), 40);
        }

        System.out.println(sum/5);

    }

}