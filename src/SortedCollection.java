public interface SortedCollection {
    int getCurrentNoOfElements();
    int generate();
    int[] allKeys();
    void add(int key, String value);
    void remove(int key);
    String getValues(int key);
    int nextKey(int key);
    int prevKey(int key);
    int rangeKey(int key1, int key2);

    RecordList[] getElements();
}
