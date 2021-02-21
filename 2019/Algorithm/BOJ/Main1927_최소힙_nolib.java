import java.io.*;

public class Main1927 {
    public static void main(String[] args) throws IOException {
        Solver sv = new Solver();
        sv.solve();
    }

    public static class Solver {
        BufferedReader br;
        BufferedWriter bw;
        Heap heap;

        public Solver() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
            heap = new Heap();
        }

        public void solve() throws IOException{
            int numQuery = Integer.parseInt(br.readLine());
            for(int iter=0; iter<numQuery; iter++){
                int query = Integer.parseInt(br.readLine());

                if(query == 0){
                    int val = heap.pop();
                    bw.write(val+"\n");
                } 
                else {
                    heap.push(query);
                }
            }

            print(bw);
        }

        public void print(BufferedWriter bw) throws IOException {
            bw.flush();
            bw.close();
        }
    }

    public static class Heap {
        private int[] data;
        private int size;

        public Heap(){
            this.data = new int[100000];
            size = 0;
        }

        public void push(int value){
            size++;
            int index = size;
            data[index] = value;

            while(index > 1){
                int parentIndex = index/2;

                if(data[parentIndex] > data[index]){
                    int tmp = data[parentIndex];
                    data[parentIndex] = data[index];
                    data[index] = tmp;
                    index /= 2;
                } else{
                    break;
                }
            }
        }

        public int pop(){
            if(size == 0){
                return 0;
            }

            int ret = data[1];
            data[1] = data[size];
            data[size] = 0;
            size--;

            int index = 1;
            while(true){
                int befoIndex = index;
                int leftChild = index*2;
                int rightChild = index*2+1;
                if(rightChild <= size){
                    if(data[leftChild] <= data[rightChild] && data[leftChild] < data[index]){
                        index = leftChild;
                    } else if(data[leftChild] > data[rightChild] && data[rightChild] < data[index]){
                        index = rightChild;
                    }
                } else if(leftChild <= size){
                    if(data[leftChild] < data[index]){
                        index = leftChild;
                    }
                }

                if(befoIndex == index) break;
                else{
                    int tmp = data[befoIndex];
                    data[befoIndex] = data[index];
                    data[index] = tmp;
                }
            }

            return ret;
        }

        public int size(){
            return size;
        }
    }
}