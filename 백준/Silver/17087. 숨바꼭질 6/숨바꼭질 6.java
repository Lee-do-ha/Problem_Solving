import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());

		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
            // 가야하는 거리만큼만 저장
			arr[i] = Math.abs(Integer.parseInt(st.nextToken()) - s);
		}

        // 가장 짧은 거리보단 작아야하므로 정렬해서 비교
		Arrays.sort(arr);

        // 갈 수 있는 모든 거리 저장
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i * i <= arr[0]; i++) {
			if (arr[0] % i == 0) {
				list.add(i);
				list.add(arr[0] / i);
			}
		}

        // 갈 수 있는 모든 거리 정렬해서 비교
		Collections.sort(list);

        // 다음 좌표부터 순차적으로 비교
		for (int i = 1; i < n; i++) {
            // 가장 먼 이동거리부터 비교
			for (int k = list.size() - 1; k >= 0; k--) {
				// 이번 이동거리로 목표지점에 갈 수 없는 경우
                if (arr[i] % list.get(k) != 0) {
					list.remove(k);
				}
			}
		}

        // 이미 정렬해있었으므로 리스트 가장 맨 뒤 값이 정답
		System.out.println(list.get(list.size() - 1));

	}

}