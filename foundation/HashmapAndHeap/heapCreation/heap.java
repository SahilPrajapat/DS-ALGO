
import java.util.ArrayList;

public class heap {

    private ArrayList<Integer> arr;
    private int size = 0;
    private boolean isMax = true;

    private void intialize(boolean isMax) {
        this.arr = new ArrayList<>();
        this.size = 0;
        this.isMax = isMax;
    }

    public heap() {
        intialize(true);
    }

    public heap(int[]arr, boolean isMax){
        intialize(isMax);
        for(int ele : arr)
            this.arr.add(ele);
        
        for(int i = this.arr.size() - 1; i >= 0; i--){
            downheapify(i);
        }
        this.size = arr.length;
    }

    //General Functions

    public int peek() {        //O(1)
        return this.arr.get(0);
    }

    public int size() {       //O(1)
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }
    
    public void add(int val){   //0(logN)
        this.arr.add(val);
        this.size++;
        upheapify(this.size - 1);
    }

    public int remove() { //O(logN)
        int n = this.arr.size();
        int rv = this.arr.get(0);

        swap(0, n - 1);
        this.arr.remove(n - 1);
        this.size--;

        downheapify(0);
        return rv;
    }

    private int compareTo(int a, int b){
        if(isMax){
            return this.arr.get(a) - this.arr.get(b);
        }else {
            return this.arr.get(b) - this.arr.get(a);
        }
    }

    private void swap(int i, int j){   //O(1)
        int ith = arr.get(i);
        int jth = arr.get(j);
        arr.set(i, jth);
        arr.set(j, ith);
    }

    private void downheapify(int pi){   //O(logN)
        int mini = pi;

        int li = 2 * pi + 1;
        if(li < this.arr.size() && compareTo(li, mini) > 0){
            mini = li;
        }

        int ri = 2 * pi + 2;
        if(ri < this.arr.size() && compareTo(ri, mini) > 0){
            mini = ri;
        }

        if(mini != pi){
            swap(pi, mini);
            downheapify(mini);
        }
    }

    private void upheapify(int i){     //O(logN)
        if(i == 0){
            return;
        }else {
            int pi = (i - 1) / 2;
            if(compareTo(i, pi) > 0){
                swap(i, pi);
                upheapify(pi);
            }
        }
    }

}

    
