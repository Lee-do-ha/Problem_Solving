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

        boolean haveSeven = haveSeven(str);
        boolean divideSeven = divideSeven(str);

        if (haveSeven && divideSeven) {
            System.out.println(3);
        } else if (haveSeven && !divideSeven) {
            System.out.println(2);
        } else if (!haveSeven && divideSeven) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static boolean divideSeven(String str) {
        if (Integer.parseInt(str) % 7 == 0) {
            return true;
        }
        return false;
    }

    private static boolean haveSeven(String str) {
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '7') return true;
        }
        return false;
    }

}