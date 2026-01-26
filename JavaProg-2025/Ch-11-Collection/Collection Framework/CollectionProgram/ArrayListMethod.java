import java.util.ArrayList;

class ArrayListMethod {
  public static void main(String[] args){
    // create ArrayList
    ArrayList<String> languages = new ArrayList<>();
    //add() method without the index parameter
    languages.add("Java");
    languages.add("C");
    languages.add("Python");
    System.out.println("ArrayList: " + languages);
	// add JavaScript at index 3 
	languages.add(3,"C++");
	// get the element from the arraylist 
	String str = languages.get(1);
	System.out.println("Element at index 1: " + str);

	// change the element of the array list 
	languages.set(2, "JavaScript");
	System.out.println("Modified ArrayList: " + languages);

	  }
}
