# [Platinum V] 최솟값 찾기 - 11003 

[문제 링크](https://www.acmicpc.net/problem/11003) 

### 성능 요약

메모리: 611004 KB, 시간: 2372 ms

### 분류

자료 구조, 우선순위 큐, 덱

### 문제 설명

<p>N개의 수 A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>N</sub>과 L이 주어진다.</p>

<p>D<sub>i</sub> = A<sub>i-L+1</sub> ~ A<sub>i</sub> 중의 최솟값이라고 할 때, D에 저장된 수를 출력하는 프로그램을 작성하시오. 이때, i ≤ 0 인 A<sub>i</sub>는 무시하고 D를 구해야 한다.</p>

### 입력 

 <p>첫째 줄에 N과 L이 주어진다. (1 ≤ L ≤ N ≤ 5,000,000)</p>

<p>둘째 줄에는 N개의 수 A<sub>i</sub>가 주어진다. (-10<sup>9</sup> ≤ A<sub>i</sub> ≤ 10<sup>9</sup>)</p>

### 출력 

 <p>첫째 줄에 D<sub>i</sub>를 공백으로 구분하여 순서대로 출력한다.</p>

```java
// PriorityQueue 사용한 문제풀이 방식인데 PriorityQueue 정렬 과정에서 시간초과가 나는듯.. 자바 왜???????????????/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class mininum implements Comparable<mininum>{
        int num, index;

        public mininum(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public int compareTo(mininum o) {
            return this.num - o.num;
        }
    }

    static int min = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] answer = new int[N+1];
        boolean[] visited= new boolean[N+1];
        PriorityQueue<mininum> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i < N+1 ; i++){
            int a = Integer.parseInt(st.nextToken());
            pq.add(new mininum(a, i));
        }

        int num = 0;

        while(!pq.isEmpty()) {
            mininum temp = pq.poll();

            int start = temp.index;
            int end = temp.index + K - 1 < N+1 ? temp.index + K - 1 : N;

            for(int i = start ; i <= end ; i++){
                if(visited[i] == false){
                    answer[i] = temp.num;
                    visited[i] = true;
                    num++;
                }
            }

        }
        for(int k = 1 ; k < N+1 ; k++){
            sb.append(answer[k] + " ");
        }
        System.out.println(sb);
    }
}
```
