
public class stack {
    protected int[] arr = null;
    protected int capacity = 0;   // maximum element that array can hold in it
    protected int elementCount = 0;  // No of element present in stack
    protected int tos = -1;

    //Constructor=====================================================

    protected void intializeVariable(int capacity){
        this.capacity = capacity;
        this.arr = new int[this.capacity];
        this.elementCount = 0;
        this.tos = -1;
    }

    public stack() {
        intializeVariable(10);
    }

    public stack(int size){
        intializeVariable(size);
    }

    // basic function=============================================================

    public int size() {
        return this.elementCount;
    }

    public boolean isEmpty() {
        return this.elementCount == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < this.elementCount; i++){
            sb.append(this.arr[i]);
            if(i != elementCount - 1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    //Exceptions============================================================

    private void OverFlowException() throws Exception{
        if(this.capacity == this.elementCount){
            throw new Exception("StackIsFull");
        }
    }

    private void UnderFlowException() throws Exception{
        if(this.elementCount == 0){
            throw new Exception("StackIsEmpty");
        }
    }

    // stack function============================================================

    protected void push_(int data){
        this.arr[++this.tos] = data;
        this.elementCount++;
    }

    public void push(int data) throws Exception{
        OverFlowException();
        push_(data);
    }

    public int top() throws Exception{
        UnderFlowException();
        return this.arr[this.tos];
    }

    protected int pop_(){
        int rv = this.arr[this.tos];
        this.arr[this.tos--] = 0;
        this.elementCount--;
        return rv;
    }

    public int pop() throws Exception{
        UnderFlowException();
        return pop_();
    }
}
