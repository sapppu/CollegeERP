#include<iostream>
using namespace std;
#define mx 10
class LQ{
   // int mx=10;
    int a[mx];
    int f,r;
public:
    LQ(){
         f=-1;
         r=-1;
    }
    void insert(int ele){
        if(r==mx-1){
            cout<<"Full";
            return;;
        }
        else{
            r++;
            a[r]=ele;
            if(f==-1){
                f=0;
            }
        }
    }
    void Delete(){
        if(f==-1){
            cout<<"Empty";
            return;
        }
        int n=a[f];
        cout<<"Deleted Element : "<<n;
        if(f==r){
            f=r=-1;
        }
        else{
            f++;
        }
    }
    void disp(){
        if(f==-1){
            cout<<"Empty";
            return;;
        }
        else{
            for(int i=f;i<=r;i++){
                cout<<a[i]<<" ";
            }
            cout<<"\n";
        }
    }
};

int main(){
    LQ lq;
    int ch,ele;
   // LQ lq;
    do{
        cout<<"Select the options : \n 1- Insert : \n 2-Delete : \n 3-Display : \n 4-Exit \n ";
        cin>>ch;
        switch(ch){
            case 1:
                cout<<"Enter Elements  : ";
                cin>>ele;
                lq.insert(ele);
                break;
            case 2:
                lq.Delete();
                break;
            case 3:
                lq.disp();
                break;
        }
    }while(ch!=4);
}