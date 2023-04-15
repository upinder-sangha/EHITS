//COMP6481 – Winter 2023
//Programming and Problem Solving
//Assignment # 3 ------- Part 2
//Submitted by:
//Upinder Singh Sangha (40224932)
//Karandeep Singh (40197407)


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        int itemCount = 0;

//        Get the threshold at which the adt used will change.
        System.out.println("Enter the threshold: ");
        int threshold = sc.nextInt();
        ElasticERL elasticERL = new ElasticERL(threshold);

//        Generate key value pairs and add them to the elasticERL
        System.out.println("<Adding " + (threshold-1) + " elements to the elasticERL(less than threshold)>");
        for (int i = 0; i < threshold-1; i++) {
            int key = elasticERL.generate();
            String value = "item-"+itemCount++;
            elasticERL.add(key,value);
        }

        testAllFunctionality(elasticERL);


        System.out.println("Press any key to add more key value pairs");
        sc.next();
//        Add more key value pairs
        System.out.println("<Adding 10 more elements to the elasticERL>");
        for (int i = 0; i<10;i++){
            int key = elasticERL.generate();
            String value = "item-"+itemCount++;
            elasticERL.add(key,value);
        }

        testAllFunctionality(elasticERL);

//        Testing duplicate entries
        System.out.println("What if we have a duplicate key? (Try adding it)");
        int duplicateKey = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the new value: ");
        String newValue = sc.nextLine();
        elasticERL.add(duplicateKey,newValue);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Value of the new key is: ");
        System.out.println(elasticERL.getValues(duplicateKey));


//        Testing on benchmark File
        System.out.println("<Testing on benchmark files>");
        System.out.println("Enter the new threshold: ");
        int newThreshold = sc.nextInt();
        elasticERL.setEINThreshold(newThreshold);


        FileInputStream fis = null;
        try {
            fis = new FileInputStream("EHITS_test_file3.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner fsc = new Scanner(fis);


//        Testing for less number of entries
        System.out.println("Enter the number of elements you want to read from the file (less than threshold):");
        int size = sc.nextInt();

        for (int i = 0; i < size; i++) {
            int key = Integer.parseInt(fsc.nextLine());
            String value = "item-"+itemCount++;
            elasticERL.add(key,value);
        }
        System.out.println("Data is currently stored in: "+elasticERL.getTypeOfADT());
        System.out.println(elasticERL);


//        Testing for more number of entries
        System.out.println("Enter the number of elements you want to read from the file (more than threshold):");
        size = sc.nextInt();

        for (int i = 0; i < size; i++) {
            try {
                int key = Integer.parseInt(fsc.nextLine());
                String value = "item-"+itemCount++;
                elasticERL.add(key,value);
            }
            catch (Exception e){
                break;
            }
        }
        System.out.println("Data is currently stored in: "+elasticERL.getTypeOfADT());
        System.out.println(elasticERL);

    }


    public static void testAllFunctionality(ElasticERL elasticERL){

        System.out.println("Data is currently stored in: "+elasticERL.getTypeOfADT());
        System.out.println(elasticERL);

//        Testing allKeys() functionality (getting all the keys in sorted sequence)
        int[] keys = elasticERL.allKeys();
        System.out.print("All the keys in sorted sequence are: ");
        for (int i = 0; i < keys.length; i++) {
            System.out.print(keys[i]+", ");
        }
        System.out.println();

//        Testing the remove(key) functionality
        System.out.println("Enter the key to remove: ");
        int keyToRemove = sc.nextInt();
        elasticERL.remove(keyToRemove);
        System.out.println("Here is the updated list of keys: ");
        keys = elasticERL.allKeys();
        System.out.print("all the keys are= ");
        for (int i = 0; i < keys.length; i++) {
            System.out.print(keys[i]+", ");
        }
        System.out.println();

//        Testing the get value functionality
        System.out.println("Enter the key to get it's value: ");
        int keyToGetValue = sc.nextInt();
        System.out.println("value = "+ elasticERL.getValues(keyToGetValue));

//        Testing the nextKey() functionality
        System.out.println("Enter the key who's next key you want to find: ");
        int keyToNext = sc.nextInt();
        System.out.println("Next Key = "+elasticERL.nextKey(keyToNext));

//        Testing the prevKey() functionality
        System.out.println("Enter the key who's previous key you want to find: ");
        int keyToPrev = sc.nextInt();
        System.out.println("Prev Key = "+elasticERL.prevKey(keyToPrev));

//        Testing the rangeKey() functionality
        System.out.println("Enter key1 and key2 between which you want to find the range: ");
        int key1 = sc.nextInt();
        int key2 = sc.nextInt();
        System.out.println("Range = "+ elasticERL.rangeKey(key1,key2));

    }
}