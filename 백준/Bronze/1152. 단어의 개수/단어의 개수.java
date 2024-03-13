import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        if (str.equals(null)) {
            System.out.println(0);
            return;
        }

        String[] arr = str.split(" ");

        int minus = 0;
        for (String s : arr) {
            if(s.equals("")) minus++;
        }
        System.out.println(arr.length - minus);
    }
}