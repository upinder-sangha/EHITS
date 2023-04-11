import javax.swing.*;

public class ElasticERL {

    private SortedCollection sortedCollection;

    public ElasticERL(int size){
        if(size<=1){
            sortedCollection = new SortedArray(size);
        }
        else {
            sortedCollection = new AVLTree();
        }
    }

//    public void setEINThreshold(int size){
//        if(size<=10000){
//            sortedCollection = new SortedArray(size);
//        }
//        else {
//            sortedCollection = new AVLTree();
//        }
//    }

    public void add(int key, String value){
        sortedCollection.add(key,value);
    }

    public int generate(){
        return sortedCollection.generate();
    }
    public int[] allKeys() {
        return sortedCollection.allKeys();
    }

    public void remove(int key) {

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
}

