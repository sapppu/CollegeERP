#include<iostream>
using namespace std;
#define mx 10
class CQ{
    int q[mx];
    int f,r;
    public:
        CQ(){
            r=-1;
            f=-1;
        }
        void insert(int ele){
            if((f==0 && r==mx-1))||(f=r+1){
                cout<<"Full";
            }else{
                if(r==mx-1){r=-1;}
                r++;
                q[r]=ele;
                if(f==-1){
                    f=0;
                }
            }
        }
}