import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class people{
        String name;
        int day;
        int month;
        int year;

        public people(String name, int day, int month, int year) {
            this.name = name;
            this.day = day;
            this.month = month;
            this.year = year;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        people[] people = new people[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            people[i] = new people(name, day, month, year);
        }

        Arrays.sort(people, (o1, o2) -> {
            if (o1.year == o2.year) {
                if (o1.month == o2.month) {
                    if (o1.day == o2.day) {
                        return o1.name.compareTo(o2.name);
                    } else {
                        return o1.day - o2.day;
                    }
                } else {
                    return o1.month - o2.month;
                }
            } else {
                return o1.year - o2.year;
            }
        });

        System.out.println(people[N-1].name);
        System.out.println(people[0].name);
        
    }

}