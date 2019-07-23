import java.io.*;

public class Main10815_숫자카드 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] cards = new int[N];
        String[] line = br.readLine().split(" ");
        for(int index=0; index<N; index++){
            cards[index] = Integer.parseInt(line[index]);
        }

        preprocessing(cards, N);

        int M = Integer.parseInt(br.readLine());
        line = br.readLine().split(" ");
        for(int index=0; index<M; index++){
            int query = Integer.parseInt(line[index]);
            sb.append(findCard(cards, query, N) + " ");
        }
        System.out.println(sb.toString());
    }

    public static void preprocessing(int[] cards, int N){
        findMedian(cards, N);
        qsort(cards, 0, N-1);
    }

    public static void findMedian(int[] cards, int N){
        if(N >= 3){
            int first = 0;
            int second = N/3;
            int third = (N+N)/3;

            int ret = 0;
            if(first <= second){
                if(second <= third){
                    ret = second;
                } else if(first <= third){
                    ret = third;
                } else{
                    ret = first;
                }
            } else {
                if(first <= third){
                    ret = first;
                } else if(second <= third){
                    ret = third;
                } else {
                    ret = second;
                }
            }

            swap(cards, 0, ret);
        }
    }

    public static void swap(int[] arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void qsort(int[] arr, int start, int end){
        if(end <= start){
            return;
        }

        int pivot = arr[start];
        int left = start+1;
        int right = end;

        while(true) {
            while(arr[left] < pivot && left < right){
                left++;
            }
            while(arr[right] >= pivot && start < right){
                right--;
            }

            if(left < right){
                swap(arr, left, right);
            } else{
                swap(arr, start, right);
                break;
            }
        }

        qsort(arr, start, right-1);
        qsort(arr, right+1, end);
    }

    public static int findCard(int[] cards, int query, int N){
        int left = 0;
        int right = N-1;
        int mid = 0;

        while(left <= right){
            mid = (right - left)/2 + left;

            if(cards[mid] == query){
                return 1;
            } else if(cards[mid] < query){
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        return 0;
    }
}