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
        for(int i = 0 ; i < 3 ; i++) {
            int cur = Integer.parseInt(br.readLine());

            sum += (cur * (i+1));
        }

        if(sum >= 10) {
            System.out.println("happy");
        } else {
            System.out.println("sad");
        }
    }

}