class VowelThread {
    public static void main(String[] args) {
        PrintVowels one = new PrintVowels("Hello This is India!");
        one.start();
        PrintVowels two = new PrintVowels("THIS IS MITWPU!");
        two.start();

    }
}


class PrintVowels extends Thread{
    String str;
    public PrintVowels(String st1) {
        str = st1;
    }
    
    @Override
    public synchronized  void run(){
        System.out.println("Thread Vowels Started!");
        for(int i=0;i<str.length();i++){
            if(isVowels(str.charAt(i))){
                System.out.println("Vowels:"+str.charAt(i));
            }
        }
    }

    boolean  isVowels(char ch){
        if(Character.toLowerCase(ch) == 'a'|| Character.toLowerCase(ch) == 'e' || Character.toLowerCase(ch)== 'i' || Character.toLowerCase(ch) == 'o'|| Character.toLowerCase(ch) =='u'){
            return  true;
        }
        else{
            return false;
        }
    }
}