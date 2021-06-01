#include<iostream> 
using namespace std;

int multiply(int a, int b){
    int c = a*b;
    return c;
}

int add(int a, int b)
{
    int c = a+b;
    return c;
}

int subtract(int a, int b){
    int c = a-b;
    return c;
}

int divide(int a, int b){
    int c = b/a;
    return c;
}

void printmessage(){
    cout<<"See you soon"<<endl;
}

  void printNTime(int n){
            for(int i = 1; i<=10; i++){
                cout<< to_string(n) + "X" + to_string(i) + "=" + to_string(n * i)<<endl;
            }
        } 

        void printTableTillM(int m){
            for(int i = 1; i<=m; i++){
                printNTime(i);
                cout<<endl;
            }
        }

int main(){
    // int a,b;
    // cin>>a>>b;
    // cout<<multiply(a,b)<<endl;
    // cout<<add(a,b)<<endl;
    // cout<<subtract(a,b)<<endl;
    // cout<<divide(a,b)<<endl;
    // printmessage();
    int n;
    cin>>n;
    // printNTime(n);
    printTableTillM(18);
}