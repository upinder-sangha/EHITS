public class SortedArray {

    public RecordList[] sortedArray;
    private int maxCapacity;
    private int currentNoOfElements;

    public SortedArray(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        currentNoOfElements = 0;
        sortedArray = new RecordList[this.maxCapacity];
    }

    private RecordList[] expand() {
        RecordList[] newArray = new RecordList[maxCapacity * 2];
        for (int i = 0; i < currentNoOfElements; i++) {
            newArray[i] = sortedArray[i];
        }
        maxCapacity = newArray.length;
        return newArray;
    }

    public void insert(int key, String value) {
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
