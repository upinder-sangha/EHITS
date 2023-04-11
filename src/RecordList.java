
public class RecordList implements Comparable<Integer> {
    public Node head;
    public int key;

    public RecordList(int key, String value){
        head = new Node(value,null);
        this.key = key;
    }

    public void addToStart(String value){
        Node newNode = new Node(value,head);
        head = newNode;
    }

    @Override
    public int compareTo(Integer newKey) {
        if(this.key> newKey)
            return 1;
        else if (this.key< newKey) {
            return -1;
        }
        return 0;
    }

    public String toString(){
        Node temp = head;
        StringBuffer sb = new StringBuffer(key+" (");

        while (temp != null) {
            sb.append(temp.value+", ");
            temp = temp.next;
        }

        String str = sb.toString();
        str = str.substring(0,(str.length()-2));
        str+=")";

        return str;
    }


    class Node{
        public String value;
        public Node next;

        public Node(String value,Node next){
            this.value = value;
            this.next = next;
        }
    }
}
