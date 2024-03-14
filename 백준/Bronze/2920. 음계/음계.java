import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        String as = "12345678";
        String de = "87654321";

        String str = "";
        for (int i = 0; i < 8; i++) {
            str += st.nextToken();
        }

        if (str.equals(as)) {
            System.out.println("ascending");
        } else if (str.equals(de)) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }

    }
}