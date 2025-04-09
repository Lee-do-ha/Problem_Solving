import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		int sum = 0;

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}

		Arrays.sort(arr);

		int a = (int) Math.round((double) N * 0.15);

		for (int i = 0; i < a; i++) {
			sum -= arr[i];
			sum -= arr[arr.length - 1 - i];
		}

		System.out.println(Math.round((double) sum / (double) (N - 2 * a)));

	}

}