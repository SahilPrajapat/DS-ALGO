
public class client{
    public static void main(String[] args) throws Exception {
        dynamicStack st =  new dynamicStack();
        for(int i = 1; i <= 5; i++){
            st.push(i * 10);
            
            System.out.println(st);
        }
    }
}
