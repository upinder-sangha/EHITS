import java.util.ArrayList;

public class ElasticERL {

    private SortedCollection sortedCollection;
    private ArrayList<Integer> idHistory = new ArrayList<>();

    private int threshold;


    public ElasticERL(int threshold){
        this.threshold = threshold;
        sortedCollection = new SortedArray(10);
    }

    public void setEINThreshold(int newThreshold){
        if(sortedCollection.getCurrentNoOfElements()>newThreshold && sortedCollection.getClass()==SortedArray.class){
            copyToAVL();
        }
        else if(sortedCollection.getCurrentNoOfElements()<newThreshold && sortedCollection.getClass()==AVLTree.class)
            copyToSortedArray();
        this.threshold = newThreshold;
    }

    public void add(int key, String value){
        sortedCollection.add(key,value);
        if(sortedCollection.getCurrentNoOfElements()== threshold)
            copyToAVL();
        idHistory.add(key);
    }

    private void copyToSortedArray(){
        System.out.println("ADT switched to Sorted Array");
        int size = sortedCollection.getCurrentNoOfElements();
        SortedCollection newSortedCollection = new SortedArray(size);
        RecordList[] previousElements = sortedCollection.getElements();
        for (RecordList element : previousElements) {
            int id = element.key;
            RecordList.Node temp = element.head;
            //traversing the record list for each element
            while (temp != null) {
                newSortedCollection.add(element.key, temp.value);
                temp = temp.next;
            }
        }
        sortedCollection = newSortedCollection;
    }

    private void copyToAVL(){
        System.out.println("ADT switched to AVL");
        SortedCollection newSortedCollection = new AVLTree();
        RecordList[] previousElements = sortedCollection.getElements();
        for (RecordList element : previousElements) {
//            System.out.println(element);
            int id = element.key;
            RecordList.Node temp = element.head;
            //traversing the record list for each element
            while (temp != null) {
                newSortedCollection.add(element.key, temp.value);
                temp = temp.next;
            }
        }
        sortedCollection = newSortedCollection;
    }


    public int generate(){
        return sortedCollection.generate();
    }
    public int[] allKeys() {
        return sortedCollection.allKeys();
    }

    public void remove(int key) {
        if(sortedCollection.getCurrentNoOfElements()== threshold +1)
            copyToSortedArray();
        sortedCollection.remove(key);
    }

    public String getValues(int key) {
        if(sortedCollection.getValues(key)!=null)
            return sortedCollection.getValues(key);
        return "No equipment with such key exists";
    }

    public int nextKey(int key) {
        return sortedCollection.nextKey(key);
    }

    public int prevKey(int key) {
        return sortedCollection.prevKey(key);
    }

    public int rangeKey(int key1, int key2) {
        return sortedCollection.rangeKey(key1,key2);
    }


    public String toString(){
        return sortedCollection.toString();
    }

    public String getTypeOfADT(){
        return String.valueOf(sortedCollection.getClass());
    }

    public void displayHistory(){
        for (int i = idHistory.size()-1; i >=0 ; i--) {
            System.out.print(idHistory.get(i)+", ");
        }
        System.out.println();
    }
}

