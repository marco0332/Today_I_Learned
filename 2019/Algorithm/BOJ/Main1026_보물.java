import java.io.*;
import java.util.*;

public class Main1026_보물 {
    public static void main(String[] args) throws IOException {
        Solver sv = new Solver();
        sv.solve();
    }

    public static class Solver {
        BufferedReader br;
        BufferedWriter bw;

        public Solver(){
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void solve() throws IOException {
            int N = Integer.parseInt(br.readLine());

            int[] A = new int[N];
            int[] B = new int[N];
            int S = 0;

            String[] line = br.readLine().split(" ");
            for(int index=0; index<N; index++){
                A[index] = Integer.parseInt(line[index]);
            }
            line = br.readLine().split(" ");
            for(int index=0; index<N; index++){
                B[index] = Integer.parseInt(line[index]);
            }

            select(A,0,N-1,N);
            select(B,0,N-1,N);
            for(int index=0; index<N; index++){
                S += A[index] * B[N-1-index];
            }

            System.out.println(S);
        }

        public int select(int[] arr, int left, int right, int N){
            int pivotIndex = 0;
            while(left<right){
                pivotIndex = pivot(arr, left, right);
                pivotIndex = partition(arr, left, right, pivotIndex, N);

                if(N == pivotIndex){
                    return N;
                } else if(N < pivotIndex){
                    right = pivotIndex - 1;
                } else {
                    left = pivotIndex + 1;
                }
            }
            return pivotIndex;
        }

        public int partition(int[] arr, int left, int right, int pivotIndex, int N){
            int pivotValue = arr[pivotIndex];
            swap(arr, pivotIndex, right); // move pivot to end
            int storeIndex = left;

            // move all elements smaller than pivot to the left of the pivot
            for(int index=left; index<right; index++){
                if(arr[index] < pivotValue){
                    swap(arr, storeIndex, index);
                    storeIndex++;
                }
            }

            // move all elements equal to the pivot right after
            // the smaller elements
            int storeIndexEq = storeIndex;
            for(int index=storeIndex; index<right; index++){
                if(arr[index] == pivotValue){
                    swap(arr, storeIndexEq, index);
                    storeIndexEq++;
                }
            }
            swap(arr, right, storeIndexEq); // move pivot to its final plcae

            // return location of pivot considering the desired location n
            if(N < storeIndex){
                return storeIndex; // N is in the group of smaller elements
            }
            if(N <= storeIndexEq){
                return N; // n is in the group equal to pivot
            }
            return storeIndexEq;
        }

        public int pivot(int[] arr, int left, int right){
            // for 5 or less elements just get median
            if(right - left < 5){
                return partition5(arr, left, right);
            }

            // otherwise move the medians of five-element subgroups to the first n/5 positions
            for(int index=left; index<=right; index+=5){
                //get the median position of the i'th five-element subgroup
                int subRight = index+4;

                if(subRight > right){
                    subRight = right;
                }
                int median5 = partition5(arr, index, subRight);
                swap(arr, median5, left+ ((index-left)/5) );
            }

            // compute the median of the n/5 medians-of-five
            int mid = (right - left) / 10 + left + 1;
            return select(arr, left, left+ ((right- left) / 5), mid);
        }

        public int partition5(int[] arr, int left, int right){
            int index = left + 1;
            while(index <= right){
                int j = index;

                while(j > left && arr[j-1] > arr[j]){
                    swap(arr, j-1, j);
                    j--;
                }
                index++;
            }
            return (left+right)/2;
        }

        public void swap(int[] arr, int left, int right){
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
        }
    }
}