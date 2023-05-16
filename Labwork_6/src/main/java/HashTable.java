import java.util.Random;
import java.util.Scanner;

public class HashTable  {
    private final int size;
    private final LinkedList[] table;
    private int hashTableGetComparisons = 0;


    public HashTable(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList ();
        }
    }

    private int hash(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length (); i++) {
            hash = (hash ^ key.charAt ( i )) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }
    public int getIndex(String key){
        int index = Math.abs ( hash ( key ) % size );
        return index;
    }
    public void put(String key, String value) {
        int index = Math.abs ( hash ( key ) % size );
        Node node = table[index].getNode ( key );
        if (node != null) {
            node.setValue ( value );
        } else {
            table[index].addNode ( key, value );
        }
        table[index].putComparisons++; // каунтер порівнянь для додавання
    }

    public String get(String key) {
        int index = Math.abs ( hash ( key ) % size );
        hashTableGetComparisons++;
        Node node = table[index].getNode ( key );
        if (node != null) {
            return node.getValue ();
        }
        table[index].getComparisons++; // каунтер порівнянь для пошуку
        return null;
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.print ( i + ": " );
            if (table[i].size () == 0) {
                System.out.println ( "Empty" );
            } else {
                Node currentNode = table[i].getHead ();
                while (currentNode != null) {
                    System.out.print ( currentNode.getKey () + "=" + currentNode.getValue () + " " );
                    currentNode = currentNode.getNext ();
                }
                System.out.println ();
            }
        }
    }

    public int getGetComparisons() {
        return hashTableGetComparisons;
    }

    public void resetComparisons(){
        hashTableGetComparisons = 0;
    }

    private static class LinkedList {
        private HashTable.Node head;
        private int putComparisons; // каунтер порівнянь для додавання
        private int getComparisons; // каунтер порівнянь для пошуку

        public LinkedList() {
            this.head = null;
            this.putComparisons = 0; // ініціалізація каунтерів
            this.getComparisons = 0;
        }

        public HashTable.Node getHead() {
            return head;
        }

        public int size() {
            int count = 0;
            HashTable.Node currentNode = head;
            while (currentNode != null) {
                count++;
                currentNode = currentNode.getNext ();
            }
            return count;
        }

        public void addNode(String key, String value) {
            if (head == null) {
                head = new HashTable.Node ( key, value );
            } else {
                HashTable.Node currentNode = head;
                while (currentNode.getNext () != null) {
                    currentNode = currentNode.getNext ();
                }
                currentNode.setNext ( new HashTable.Node ( key, value ) );
            }
        }

        public HashTable.Node getNode(String key) {
            HashTable.Node currentNode = head;
            while (currentNode != null) {
                getComparisons++; // збільшуємо каунтер порівнянь для пошуку
                if (currentNode.getKey ().equals ( key )) {
                    return currentNode;
                }
                currentNode = currentNode.getNext ();
            }
            return null;
        }

        public int getPutComparisons() {
            return putComparisons;
        }

        public int getGetComparisons() {
            return getComparisons;
        }

        public void resetComparisons(){
            getComparisons = 0;
            putComparisons = 0;
        }

    }private static class Node {
        private final String key;
        private String value;
        private Node next;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private static final int[] TEST_SIZES = {100, 1000, 5000, 10000, 20000};

    public static void main(String[] args) {
        Scanner scanner2 = new Scanner ( System.in );
        System.out.println ( "Enter your choice: " );
        String choiceSTR = scanner2.nextLine ();

        Scanner scanner = new Scanner ( System.in );
        Random random = new Random ();
        try {

            switch (choiceSTR) {
                case "Manual":
                    HashTable table = new HashTable ( 10 );
                    System.out.print ( "Enter the number of elements to ADD to the table: " );
                    int numElements = scanner.nextInt ();

                    for (int i = 0; i < numElements; i++) {
                        int key = random.nextInt ( 1000 );
                        String value = "value" + key;
                        long startTime = System.nanoTime ();
                        table.put ( Integer.toString ( key ), value );
                        long endTime = System.nanoTime ();
                        long duration = (endTime - startTime);
                        System.out.println ( "Added to table: Key = " + key + ", Value = " + value + ", Duration = " + duration + " ns" );
                    }

                    table.printTable();
                    System.out.println();

                    System.out.print("Enter key to find: ");
                    int key = scanner.nextInt();
                    long startTime = System.nanoTime();
                    String value = table.get(Integer.toString(key));
                    long endTime = System.nanoTime();
                    long duration = (endTime - startTime);
                    System.out.println("Searched for the key: " + key + ",duration of the searching is - " + duration + " ns");
                    if (value != null) {
                        System.out.println("Found: " + value);
                    } else {
                        System.out.println("key " + key + " with value " + value + " not found");
                    }

                    int searchedIndex = table.getIndex(Integer.toString(key));
                    LinkedList linkedList = table.table[searchedIndex];
                    System.out.println ( "Number of comparisons for adding to linked list: " + linkedList.getPutComparisons () );
                    System.out.println ( "Number of comparisons for searching in linked list : " + linkedList.getGetComparisons () );
                    System.out.println ( "Number of comparisons for searching in hash table: " + table.getGetComparisons () );

                    break;
                case "Random":

                    for (int size : TEST_SIZES) {
                        HashTable table2 = new HashTable ( size );

                        System.out.println("Testing with size: " + size);
                        for (int i = 0; i < size; i++) {
                            int key2 = random.nextInt(size);
                            String value2 = "value" + key2;
                            long startTime2 = System.nanoTime();
                            table2.put(Integer.toString(key2), value2);
                            long endTime2 = System.nanoTime();
                            long duration2 = (endTime2 - startTime2);
                            System.out.println("Added to table: Key = " + key2 + ", Value = " + value2 + ", Duration = " + duration2 + " ns");
                        }


                        table2.printTable();
                        System.out.println();

                        System.out.print("Enter key to find: ");
                        int key3 = scanner.nextInt();
                        long startTime3 = System.nanoTime();
                        String value3 = table2.get(Integer.toString(key3));
                        long endTime3 = System.nanoTime();
                        long duration3 = (endTime3 - startTime3);
                        System.out.println("Searched for the key: " + key3 + ",duration of the searching is - " + duration3 + " ns");
                        if (value3 != null) {
                            System.out.println("Found: " + value3);
                        } else {
                            System.out.println("key " + key3 + " with value " + value3 + " not found");
                        }

                        int searchedIndex2 = table2.getIndex(Integer.toString(key3));
                        LinkedList linkedList2 = table2.table[searchedIndex2];
                        System.out.println ( "Number of comparisons for adding to linked list: " + linkedList2.getPutComparisons () + ", the size is " + size);
                        System.out.println ( "Number of comparisons for searching in linked list : " + linkedList2.getGetComparisons ()  + ", the size is " + size);
                        System.out.println ( "Number of comparisons for searching in hash table: " + table2.getGetComparisons ()  + ", the size is " + size);

                        linkedList2.resetComparisons();
                        table2.resetComparisons();

                        System.out.println("Continue?");
                        scanner2.nextLine ();
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid сhoice: " + choiceSTR);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}