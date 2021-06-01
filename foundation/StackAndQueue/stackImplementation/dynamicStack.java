public class dynamicStack extends stack {
    
    public dynamicStack(){
        super();
    }

    public dynamicStack(int size){
        super(size);
    }

    public dynamicStack(int[] arr){
        super.intializeVariable(2 * arr.length);

        for(int ele : arr){
            super.push_(ele);
        }
    }

    @Override
    public void push(int data) throws Exception{
        if(super.capacity == super.elementCount){
            int[] temp = super.arr;
            super.intializeVariable(2 * super.capacity);
            for(int ele : temp)
            super.push_(ele);
        }

        super.push(data);
    }
}
