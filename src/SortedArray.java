import java.util.Random;

public class SortedArray implements SortedCollection {

    public RecordList[] sortedArray;
    private int maxCapacity;
    protected int currentNoOfElements;

    public SortedArray(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        currentNoOfElements = 0;
        sortedArray = new RecordList[this.maxCapacity];
    }

    public void add(int key, String value) {
        if (currentNoOfElements == 0) {
            sortedArray[0] = new RecordList(key, value);
        } else {
            if (maxCapacity == currentNoOfElements) {
                this.sortedArray = expand();
            }

            int i = currentNoOfElements - 1;
            while ((i >= 0)) {
                if (sortedArray[i].compareTo(key) == 0) {
                    sortedArray[i].addToStart(value);
                    return;
                }
                i--;
            }

            i = currentNoOfElements - 1;
            while ((i >= 0) && (sortedArray[i].compareTo(key) > 0)) {
                sortedArray[i + 1] = sortedArray[i];
                i--;
            }

            sortedArray[i + 1] = new RecordList(key, value);
        }
        currentNoOfElements++;
    }


    @Override
    public int generate() {
        Random random = new Random();
        return 10000000 + random.nextInt(90000000);
    }

    @Override
    public int[] allKeys() {
        int[] arrayOfKeys = new int[currentNoOfElements];
        for (int i = 0; i < currentNoOfElements; i++) {
            arrayOfKeys[i] = sortedArray[i].key;
        }
        return arrayOfKeys;
    }

    @Override
    public void remove(int key) {
        int low = 0;
        int high = currentNoOfElements-1;
        int index = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid].compareTo(key)==0) {
                index = mid;
                break;
            } else if (sortedArray[mid].compareTo(key)<0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if(index>=0){
            for (int i = index; i < currentNoOfElements-1; i++) {
                sortedArray[i]=sortedArray[i+1];
            }
            sortedArray[currentNoOfElements-1] = null;
            currentNoOfElements--;
        }

    }

    @Override
    public String getValues(int key) {
        int low = 0;
        int high = currentNoOfElements-1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid].compareTo(key)==0) {
                return sortedArray[mid].toString();
            } else if (sortedArray[mid].compareTo(key)<0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null; // key not found
    }

    @Override
    public int nextKey(int key) {
        int low = 0;
        int high = currentNoOfElements-1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid].compareTo(key)==0 && mid+1<currentNoOfElements) {
                return sortedArray[mid+1].key;
            } else if (sortedArray[mid].compareTo(key)<0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    @Override
    public int prevKey(int key) {
        int low = 0;
        int high = currentNoOfElements-1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid].compareTo(key)==0 && mid-1>=0) {
                return sortedArray[mid-1].key;
            } else if (sortedArray[mid].compareTo(key)<0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    @Override
    public int rangeKey(int key1, int key2) {
        int numOfKeys=0;
        for (int i = 0; i < currentNoOfElements; i++) {
            if(sortedArray[i].compareTo(key2)>=0)
                break;
            if(sortedArray[i].compareTo(key1)>0)
                numOfKeys++;
        }
        return numOfKeys;
    }


    private RecordList[] expand() {
        RecordList[] newArray = new RecordList[maxCapacity * 2];
        for (int i = 0; i < currentNoOfElements; i++) {
            newArray[i] = sortedArray[i];
        }
        maxCapacity = newArray.length;
        return newArray;
    }


    @Override
    public int getCurrentNoOfElements() {
        return currentNoOfElements;
    }

    @Override
    public RecordList[] getElements(){
        RecordList[] elements = new RecordList[currentNoOfElements];
        for (int i = 0; i < currentNoOfElements; i++) {
            elements[i] = sortedArray[i];
        }
        return elements;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentNoOfElements; i++) {
            sb.append(sortedArray[i] + ", ");
        }
        String str = sb.toString();
        str = str.substring(0, (str.length() - 2));
        return str;
    }
}
