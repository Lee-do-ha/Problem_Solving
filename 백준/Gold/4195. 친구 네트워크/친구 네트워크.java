import java.io.*;
import java.util.*;

public class Main {

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        parents = new int[200001];
        int[] family = new int[200001];

        for(int i = 0 ; i < T ; i++){
            int N = Integer.parseInt(br.readLine());

            for(int k = 1 ; k < 200001 ; k++){
                parents[k] = k;
                family[k] = 1;
            }

            HashMap<String, Integer> hashMap = new HashMap<>();

            int number = 1;

            for(int k = 0 ; k < N ; k++){
                st = new StringTokenizer(br.readLine());

                String str1 = st.nextToken();
                String str2 = st.nextToken();

                if(!hashMap.containsKey(str1)){
                    hashMap.put(str1, number);
                    number++;
                }

                if(!hashMap.containsKey(str2)){
                    hashMap.put(str2, number);
                    number++;
                }

                if(findSet(hashMap.get(str1)) == findSet(hashMap.get(str2))){
                    sb.append(family[findSet(hashMap.get(str1))]).append("\n");
                }else{
                    family[findSet(hashMap.get(str1))] += family[findSet(hashMap.get(str2))];
                    family[findSet(hashMap.get(str2))] = 0;
                    unionSet(hashMap.get(str1), hashMap.get(str2));
                    sb.append(family[findSet(hashMap.get(str1))]).append("\n");
                }

            }

        }

        System.out.println(sb);

    }

    private static int findSet(int a){

        if(parents[a] == a){
            return a;
        }

        return parents[a] = findSet(parents[a]);
    }

    private static void unionSet(int a, int b){
           
        parents[findSet(b)] = findSet(a);
        
    }
}