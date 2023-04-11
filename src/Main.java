import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of ids: ");
        int size = sc.nextInt();

        int[] ids = new int[0];
        try {
            ids= readIdsFromFile("EHITS_test_file3.txt", size);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        ElasticERL elasticERL = new ElasticERL(size);
//        for (int i = 0; i < size; i++) {
//            elasticERL.add(ids[i], "abc");
//        }
        elasticERL.add(1,"a");
        elasticERL.add(2,"b");
        elasticERL.add(4,"d");
        elasticERL.add(3,"c");
        elasticERL.add(5,"e");
        elasticERL.add(5,"f");
        elasticERL.add(6,"g");
        elasticERL.add(7,"h");
        elasticERL.add(8,"i");
        elasticERL.add(9,"j");
//        elasticERL.inorder();
//        System.out.println(elasticERL);
        System.out.println(elasticERL.generate());
        System.out.println(elasticERL.getValues(elasticERL.generate()));
        int[] keys = elasticERL.allKeys();
//        for (int i = 0; i < 9; i++) {
//            System.out.println(keys[i]);
//        }
        System.out.println(keys[0]+","+keys[1]+","+keys[2]+"....");

        System.out.println(elasticERL.nextKey(3));
        System.out.println(elasticERL.prevKey(9));
        System.out.println(elasticERL.rangeKey(2,9));

    }

    public static int[] readIdsFromFile(String fileName, int size) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        Scanner sc = new Scanner(fis);
        int[] ids = new int[size];

        for (int i = 0; i < size; i++) {
            ids[i] = Integer.parseInt(sc.nextLine());
        }
        return ids;
    }
}