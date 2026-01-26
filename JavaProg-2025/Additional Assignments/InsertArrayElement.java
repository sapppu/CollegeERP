/* write a java program to insert and delete the elements from specific index */  


import java.util.*;  
class InsertArrayElement
{  
public static void main(String[] args)   
{  
  
Scanner sc=new Scanner(System.in);  
System.out.print("Enter the number of elements you want to store: ");  

int n = sc.nextInt();  
int[] array = new int[10];  

System.out.println("Enter the elements of the array: ");  
for(int i=0; i<n; i++)  
{  
array[i]=sc.nextInt();  
}  
System.out.println("Array elements are: ");  
for (int i=0; i<n; i++)   
{  
System.out.println(array[i]);  
}  
System.out.print("Enter the element to insert: ");
int elementToInsert = sc.nextInt();

System.out.print("Enter the index where you want to insert: ");
int indexToInsert = sc.nextInt();

n = insertAt(array, n, elementToInsert, indexToInsert);

 System.out.println("Array after insertion: ");
 for (int i = 0; i < n; i++) {
 System.out.print(array[i] + " ");
}
System.out.println();

System.out.print("Enter the index to delete the element from: ");
int indexToDelete = sc.nextInt();

n = deleteAt(array, n, indexToDelete);

System.out.println("Array after deletion: ");
for (int i = 0; i < n; i++) {
System.out.print(array[i] + " ");
}
System.out.println();
}  

public static int insertAt(int[] array, int size, int element, int index) {
if (index < 0 || index > size || size >= array.length) {
System.out.println("Invalid index or array is full.");
return size;

}
  for (int i = size; i > index; i--) {
  array[i] = array[i - 1];
}
 array[index] = element;
return size + 1;
}

public static int deleteAt(int[] array, int size, int index) {
if (index < 0 || index >= size) {
System.out.println("Invalid index.");
return size;
}

for (int i = index; i < size - 1; i++) {
array[i] = array[i + 1];
}

return size - 1;
}
}