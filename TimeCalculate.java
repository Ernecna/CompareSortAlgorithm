// Mustafa Erdem Gülsoy -- 20050111055
// Erencan Yakut -- 20050111045
// Onurhan Emin Bartal -- 20050111052
// Furkan Yılmaz -- 20050111081
// Emre Geçitli -- 20050111031
import java.io.*;
import java.time.Duration;
import java.time.LocalTime;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        next = null;
    }
}

class SinglyLinkedList {
    Node head = null;
    Node tail = null; // Added tail

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return getHead() == null;
    }

    public void addFirst(int newData) {
        Node newNode = new Node(newData);
        if (isEmpty()) {
            setHead(newNode);
            tail = newNode; // Tail is the head element if there is only one element
        } else {
            newNode.next = head; // The next element of the new node will be the old head element
            head = newNode; // We update the head element as a new node
        }
    }

    public void insertAt(int newData, int index) {
        Node newNode = new Node(newData);
        if (index == 0) {
            addFirst(newData);  // If index is 0, use addFirst method
            return;
        }

        Node current = head;
        int count = 0;

        // Go to the previous node from the index
        while (current != null && count < index - 1) {
            current = current.next;
            count++;
        }

        // Issue a warning if the specified index is not a valid index (e.g. larger than the list size).
        if (current == null) {
            System.out.println("Index out of bounds");
            return;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            setHead(newNode);
            tail = newNode; // Tail is the head element if there are no other elements
        } else {
            tail.next = newNode; // The next element of the old tail will be the new node
            tail = newNode; // We update the tail as a new node
        }
    }
}

class DynamicArray {
    private int[] arrayData; // Our array for duplicating and other processes.
    private int size; // Current number of elements
    private int capacity; // Whole capacity

     //We create a DynamicArray class to create new dynamic arrays.
    public DynamicArray() {
        capacity = 1;  // Capacity for doubling part
        size = 0; // We start from size zero. (Initial size)
        arrayData = new int[capacity];
    }

    public int get(int index) {
        // The function that gets a element that in a certain index.We can get a element that we want because of the function.
        return arrayData[index];
    }

     // We created add method to add new elements to arrayData.
    public void add(int value) {
        if (size == capacity) { // If the array full,we create a new array .New array has doubled-size.(Duplicating)
            capacity *= 2;
            int[] newData = new int[capacity];
            System.arraycopy(arrayData, 0, newData, 0, size);//copying arrayData to newData.
            arrayData = newData;
        }
        arrayData[size++] = value; // if there are free spaces in the array,basic insertion.
    }

    // We can say that insertAt function looks like similar "add function".
    // It takes two parameters to insert the value the index.
    public void insertAt(int value, int index) {
        //When the array is full, we can think of the copying process as 2 parts. We are adding to a certain index,
        //We can say that the copying is divided into two parts: before and after the index.
        if (size == capacity) {
            capacity *= 2;
            int[] newData = new int[capacity];
            System.arraycopy(arrayData, 0, newData, 0, index);
            newData[index] = value;
            System.arraycopy(arrayData, index, newData, index + 1, size - index);
            arrayData = newData;
        } else {
            // If the array is not full,There is no need to duplicate.
            //Only inserting certain index.
            System.arraycopy(arrayData, index, arrayData, index + 1, size - index);
            arrayData[index] = value;
        }
        // incrementing the size after inserting.
        size++;
    }
}


public class TimeCalculate {

    /*
    public static void FillTheFile(String filename, int max, int min, int amount){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Random random = new Random();
            for (int i = 0; i < amount; i++) {
                int randomNum = random.nextInt(max - min + 1) + min; // Sınırları belirtiyoruz.
                writer.write(Integer.toString(randomNum));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("File Write Error: " + e.getMessage());
        }
    }
    */

    // 1A - Build an integer array that stores integers hold in 1Mint.txt file (The length of the array must be 1,000,000).
    // Our Function store the array by reading from file.

    // *******************************************  ARRAY OPERATIONS  ********************************************************************
    public static void readForArray(String filename, int[] array) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // buffer created to read the file.
            String line; // create line variable to read file line by line.
            int index = 0;
            while ((line = br.readLine()) != null && index < array.length) {
                array[index++] = Integer.parseInt(line.trim());
            } //reading process we check index and array length
        } catch (IOException e) {
            System.out.println("File Read Error: " + e.getMessage());
            // it catches the exception and prints
            // an error message indicating the nature of the error.
        }
    }

    // 1B -  Insert an integer into the first index.
    // The size of the array must be one million. To do this, we can delete the element at the last index and shift all other elements to the right.
    //Shifting operation.
    public static void insertFirstAndDeleteLast(int[] array, int newNum) {
        // We are shifting all the elements one by one, by deleting the last element."
        for (int i = array.length - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }
        // We add the new number to the first index
        array[0] = newNum;
    }

    // 1C - Insert an integer into the last index.

    /*
    // The size of the Array must be 1000000. To do this, we can delete the element in the first index and shift all other elements to the left.
    //Firstly,We think that have to shift operations so we write this function.After the Lecturer's explanation
    // this function has cancelled.
    public static void InsertLastAndDeleteFirst (int[] array, int newNum) {
        for (int i = 0; i < array.length -1; i++) {
            array[i] = array[i + 1];
        }
        array[999999] = newNum;
    }
    */


    // ************************************  LINKED LIST OPERATIONS  **********************************************************************

    // 2A - Build an integer list that stores integers hold in 1Mint.txt
    // Our Function store the singly-linked list by reading from file(only integer values from file).
    public static void readForLinkedList(String filename, SinglyLinkedList linkedList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // buffer created to read the file.
            String line; // create line variable to read file line by line.
            while ((line = br.readLine()) != null) {
                int value = Integer.parseInt(line.trim()); // We read the lines and convert them to integer values.
                linkedList.addLast(value);
            }
        } catch (IOException e) {
            System.out.println("File Read Error: " + e.getMessage());
        }
    }

    // 2D - Read the integer from the index 900,000.
    // This function read the element that is in specified index from LinkedList(LL).
    public static int readIndexFromLL(SinglyLinkedList linkedList, int index){
        Node current = linkedList.head;
        // count to keep track of the current position
        int count = 0;
        // Searching the specified index in linked list.
        while (count <= index){
            current = current.next;
            count++;
        }
        return current.data; // after we reach the index, function return data at index.
    }

    //********************************************************  DYNAMIC ARRAY OPERATIONS  *************************************************

    // Our Function store the dynamic array by reading from file(only integer values from file).
    public static void readForDynamicArray(String filename, DynamicArray dynamicArray){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // buffer created to read the file.
            String line; // create line variable to read file line by line.
            while ((line = br.readLine()) != null) {
                dynamicArray.add(Integer.parseInt(line.trim())); // We read the lines and convert them to integer values.
            }
        } catch (IOException e) {
            System.out.println("File Read Error: " + e.getMessage());
        }
    }


    public static void main(String[] args) {

        // First, let's fill the 1Mimt.txt file with random integer numbers between 0 and 1000000.
        //FillTheFile("1Mint.txt", 1000000, 0, 1000000);
        //max = 1000000;     // Upper limit of numbers to be randomly selected.
        //min = 0;           // Lower limit of numbers to be randomly selected.
        //amount = 1000000;  // The total amount of numbers to be randomly selected.


        System.out.println("\n**************************************************  1 MILLION  ************************************************************\n");


        // 1
        int[] array = new int[1000000]; // we defined array variable.

        // A - Build an integer array2 that stores integers hold in 1Mint.txt file (The length of the array must be 1,000,000).
        LocalTime start = LocalTime.now();
        readForArray("1Mint.txt", array);
        LocalTime finish = LocalTime.now();
        Duration difference = Duration.between(start, finish);
        System.out.println("1a) The integer array structure is built in " + difference.toMillis() + " milliseconds.");

        int[] newArray = array.clone();
        // B -  Insert an integer into the first index.
        start = LocalTime.now();
        insertFirstAndDeleteLast(newArray, 24566);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("1b) An integer is inserted into the beginning of the integer array in " + difference.toMillis() + " milliseconds.");
        newArray = array.clone();

        // C - Insert an integer into the last index.
        start = LocalTime.now();
        //InsertLastAndDeleteFirst(newArray, 475245);
        array[999999]=777;
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("1c) An integer is inserted into the end of the integer array in " + difference.toMillis() + " milliseconds.");
        newArray = array.clone();

        // D - Read the integer from the index 900,000.
        start = LocalTime.now();
        int the_value = newArray[900000];
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("1d) An integer, which is " + the_value + ", is read from index 900,000 of the integer array in " + difference.toMillis() + " milliseconds.");
        newArray = array.clone();

        // E - Read the integer from the index 9.
        start = LocalTime.now();
        the_value = newArray[9];
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("1e) An integer, which is " + the_value + ", is read from index 9 of the integer array in " + difference.toMillis() + " milliseconds.");



        System.out.println("-------------------------------------");



        // 2
        //  Using LinkedList data structure (Not a doubly-linked list):
        SinglyLinkedList linkedList = new SinglyLinkedList();

        // A Build an integer list that stores integers hold in 1Mint.txt
        start = LocalTime.now();
        readForLinkedList("1Mint.txt",linkedList);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("2a) The integer LinkedList structure is built in " + difference.toMillis() + " milliseconds.");

        // B Insert an integer into the head.
        start = LocalTime.now();
        linkedList.addFirst(618451);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("2b) An integer is inserted into the beginning of the  Integer LinkedList in " + difference.toMillis() + " milliseconds.");

        // C Insert an integer into the index 900,000.
        start = LocalTime.now();
        linkedList.insertAt(265416, 900000);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("2c) An integer is inserted into the 900.000's index of the  Integer LinkedList in " + difference.toMillis() + " milliseconds.");

        // D - Read the integer from the index 900,000.
        start = LocalTime.now();
        the_value = readIndexFromLL(linkedList, 900000);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("2d) An integer, which is " + the_value + ", is read from index 900,000 of the  Integer LinkedList in " + difference.toMillis() + " milliseconds.");

        // E - Read the integer from the index 9.
        start = LocalTime.now();
        the_value = readIndexFromLL(linkedList, 9);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("2e) An integer, which is " + the_value + ", is read from index 9 of the  Integer LinkedList in " + difference.toMillis() + " milliseconds.");


        System.out.println("-------------------------------------");


        // 3
        DynamicArray dynamicArray = new DynamicArray();

        // A Build an integer array2 that stores integers hold in 1Mint.txt
        start = LocalTime.now();
        readForDynamicArray("1Mint.txt", dynamicArray);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("3a) The Dynamic Array structure is built in " + difference.toMillis() + " milliseconds.");

        // B Insert an integer into the first index.
        start = LocalTime.now();
        dynamicArray.insertAt(618451, 0);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("3b) An integer is inserted into the beginning of the Dynamic Array in " + difference.toMillis() + " milliseconds.");

        // C Insert an integer into the last index.
        start = LocalTime.now();
        dynamicArray.add(265416);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("3c) An integer is added to the end of the Dynamic Array in " + difference.toMillis() + " milliseconds.");

        // D - Read the integer from the index 900,000.
        start = LocalTime.now();
        the_value = dynamicArray.get(900000);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("3d) An integer, which is " + the_value + ", is read from index 900,000 of the Dynamic Array in " + difference.toMillis() + " milliseconds.");

        // E - Read the integer from the index 9.
        start = LocalTime.now();
        the_value = dynamicArray.get(9);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("3e) An integer, which is " + the_value + ", is read from index 9 of the Dynamic Array in " + difference.toMillis() + " milliseconds.");




// 50 MILLION **************************************************************************************************************************************************
        System.out.println("\n**************************************************  50 MILLION  ************************************************************\n");


        // First, let's fill the 50Mimt.txt file with random integer numbers between 0 and 50000000.
        //FillTheFile("50Mint.txt", 50000000, 0, 50000000);
        //max = 50000000;     // Upper limit of numbers to be randomly selected.
        //min = 0;           // Lower limit of numbers to be randomly selected.
        //amount = 50000000;  // The total amount of numbers to be randomly selected.


        // 1
        int[] array2 = new int[50000000]; // We determined the length of the array.

        // A - Build an integer array that stores integers hold in 1Mint.txt file (The length of the array must be 1,000,000).
        LocalTime start2 = LocalTime.now();
        readForArray("50Mint.txt", array2);
        LocalTime finish2 = LocalTime.now();
        Duration difference2 = Duration.between(start2, finish2);
        System.out.println("1a) The integer array structure is built in " + difference2.toMillis() + " milliseconds.");

        int[] newArray2 = array2.clone();
        // B -  Insert an integer into the first index.
        start2 = LocalTime.now();
        insertFirstAndDeleteLast(newArray2, 24566);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("1b) An integer is inserted into the beginning of the integer array in " + difference2.toMillis() + " milliseconds.");
        newArray2 = array2.clone();

        // C - Insert an integer into the last index.
        start2 = LocalTime.now();
        //InsertLastAndDeleteFirst(newArray2, 475245);
        array2[49999999]=1923;
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("1c) An integer is inserted into the end of the integer array in " + difference2.toMillis() + " milliseconds.");
        newArray2 = array2.clone();

        // D - Read the integer from the index 900,000.
        start2 = LocalTime.now();
        int the_value2 = newArray2[900000];
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("1d) An integer, which is " + the_value2 + ", is read from index 900,000 of the integer array in " + difference2.toMillis() + " milliseconds.");
        newArray2 = array2.clone();

        // E - Read the integer from the index 9.
        start2 = LocalTime.now();
        the_value2 = newArray2[9];
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("1e) An integer, which is " + the_value2 + ", is read from index 9 of the integer array in " + difference2.toMillis() + " milliseconds.");



        System.out.println("-------------------------------------");



        // 2
        //  Using LinkedList data structure (Not a doubly-linked list):
        SinglyLinkedList linkedList2 = new SinglyLinkedList();

        // A Build an integer list that stores integers hold in 1Mint.txt
        start2 = LocalTime.now();
        readForLinkedList("50Mint.txt",linkedList2);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("2a) The Integer LinkedList structure is built in " + difference2.toMillis() + " milliseconds.");

        // B Insert an integer into the head.
        start2 = LocalTime.now();
        linkedList2.addFirst(618451);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("2b) An integer is inserted into the beginning of the   Integer LinkedList in " + difference2.toMillis() + " milliseconds.");

        // C Insert an integer into the index 900,000.
        start2 = LocalTime.now();
        linkedList2.insertAt(265416, 900000);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("2c) An integer is inserted into the 900.000's index of the  Integer LinkedList in " + difference2.toMillis() + " milliseconds.");

        // D - Read the integer from the index 900,000.
        start2 = LocalTime.now();
        the_value2 = readIndexFromLL(linkedList2, 900000);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("2d) An integer, which is " + the_value2 + ", is read from index 900,000 of the  Integer LinkedList in " + difference2.toMillis() + " milliseconds.");

        // E - Read the integer from the index 9.
        start2 = LocalTime.now();
        the_value2 = readIndexFromLL(linkedList2, 9);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("2e) An integer, which is " + the_value2 + ", is read from index 9 of the integer LinkedList in " + difference2.toMillis() + " milliseconds.");


        System.out.println("-------------------------------------");


        // 3
        DynamicArray dynamicArray2 = new DynamicArray();

        // A Build an integer array2 that stores integers hold in 1Mint.txt
        start2 = LocalTime.now();
        readForDynamicArray("50Mint.txt", dynamicArray2);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("3a) The Dynamic Array structure is built in " + difference2.toMillis() + " milliseconds.");

        // B Insert an integer into the first index.
        start2 = LocalTime.now();
        dynamicArray2.insertAt(618451, 0);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("3b) An integer is inserted into the beginning of the Dynamic Array in " + difference2.toMillis() + " milliseconds.");

        // C Insert an integer into the last index.
        start2 = LocalTime.now();
        dynamicArray2.add(265416);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("3c) An integer is added to the end of the Dynamic Array in " + difference2.toMillis() + " milliseconds.");

        // D - Read the integer from the index 900,000.
        start2 = LocalTime.now();
        the_value2 = dynamicArray2.get(900000);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("3d) An integer, which is " + the_value2 + ", is read from index 900,000 of the Dynamic Array in " + difference2.toMillis() + " milliseconds.");

        // E - Read the integer from the index 9.
        start2 = LocalTime.now();
        the_value2 = dynamicArray2.get(9);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("3e) An integer, which is " + the_value2 + ", is read from index 9 of the Dynamic Array in " + difference2.toMillis() + " milliseconds.");


    }
}
