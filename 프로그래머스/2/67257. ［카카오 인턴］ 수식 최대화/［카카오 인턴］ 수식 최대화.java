import java.util.*;

class Solution {
    
    // 정답
    static long answer;
    // 사용한 숫자인지
    static boolean[] visited;
    // 우선순위 저장할 배열
    static int[] arr;
    // 문자 기호 저장할 배열
    static char[] exp;
    // 처음 원본 형태의 문자열
    static List<String> originalInput;
    
    public long solution(String expression) {
        StringBuilder sb = new StringBuilder();
        
        // 초기 정답값
        answer = 0;
        
        // 원본 형태의 문자열
        originalInput = new ArrayList<>();

        // 문자열을 문자들로 자르기
        char[] s = expression.toCharArray();

        // 문자열을 숫자들은 합쳐서 문자열 리스트에 저장하고 기호는 바로 리스트에 저장
        for (int i = 0 ; i < s.length ; i++) {
            if (s[i] == '-' || s[i] == '+' || s[i] == '*') {
                originalInput.add(sb.toString());
                originalInput.add(String.valueOf(s[i]));
                sb = new StringBuilder();
            }else{
                sb.append(s[i]);
            }
        }
        // 마지막 숫자 입력
        originalInput.add(sb.toString());

        visited = new boolean[3];
        arr = new int[3];

        exp = new char[3];
        exp[0] = '-';
        exp[1] = '+';
        exp[2] = '*';

        // 재귀함수
        round(0);
        
        return answer;
    }
    
    private static void round(int number){

        // 우선순위가 다 정해졌다면 계산하러 가기
        if (number == 3) {
            recursive();
            return;
        }

        // 순열 조합
        for (int i = 0; i < 3; i++) {
            if (!visited[i]) {
                arr[number] = i;
                visited[i] = true;
                round(number+1);
                visited[i] = false;
            }
        }

    }
    
    private static void recursive() {

        // 새로 사용할 배열
        List<String> curList = new ArrayList<>();

        // 리스트에 넣어서 똑같이 만들기
        for (String s : originalInput) {
            curList.add(s);
        }

        for (int i = 0; i < 3; i++) {
            // 이번 순위의 문자 기호
            String curType = String.valueOf(exp[arr[i]]);

            for (int k = 0 ; k < curList.size() ; k++) {

                // 해당 기호 만났을 경우
                if (curList.get(k).equals(curType)) {
                    long back = Long.parseLong(curList.remove(k+1));
                    curList.remove(k);
                    long front = Long.parseLong(curList.remove(k-1));

                    long cur = -1;

                    if (curType.equals("-")) {
                        cur = front - back;
                    } else if (curType.equals("+")) {
                        cur = front + back;
                    } else if (curType.equals("*")) {
                        cur = front * back;
                    }

                    // 기호 기준 -1, 0, 1 값들 없애고 -1자리에 계산한 값 넣기
                    curList.add(k-1, String.valueOf(cur));

                    // 사이즈가 1이라면 더이상 없으므로 종료
                    if (curList.size() == 1) {
                        answer = Math.max(answer, Math.abs(Long.parseLong(curList.get(0))));
                        return ;
                    }
                    
                    // 그래도 진행하는 경우 다음 기호를 바로 탐색하는 경우가 생기므로 
                    k -= 2;

                }

            }
        }
    }
}