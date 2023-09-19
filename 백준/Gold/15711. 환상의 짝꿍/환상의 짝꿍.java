import java.io.*;
import java.util.*;

public class Main {
	static final int MAX = 2000000;
	static boolean[] data = new boolean[MAX+1];
	static List<Integer> primes = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		
		Arrays.fill(data, true);
		data[0] = data[1] = false;
		for (int i=2; i<MAX+1; i++) {
			if (data[i]) {
				primes.add(i);
				for (int j=i+i; j<MAX+1; j=j+i) {
					data[j] = false;
				}
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int tc=0; tc<t; tc++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			long sum = a + b;
			
			if (sum < 4) {
				bw.write("NO\n");
			} else if (sum % 2 == 0) {
				bw.write("YES\n");
			} else {
				if (is_prime(sum-2)) {
					bw.write("YES\n");
				} else {
					bw.write("NO\n");
				}
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static boolean is_prime(long value) {
		if (value <= MAX)
			return data[(int) value];
		
		for (int i=0; i<primes.size(); i++) {
			if (value % primes.get(i) == 0)
				return false;
		}
		
		return true;
	}
}