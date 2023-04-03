
public class RecordList implements Comparable<RecordList> {
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
    public int compareTo(RecordList list2) {
        if(this.key> list2.key)
            return 1;
        else if (this.key< list2.key) {
            return -1;
        }
        return 0;
    }

    //    public void addToEnd(int value){
//        if(head == null)
//            addToStart(value);
//        else {
//            Node temp = head;
//            while (temp.next != null)
//                temp = temp.next;
//
//            Node newNode = new Node(value, null);
//            temp.next = newNode;
//        }
//    }

    public void print(){
        Node temp = head;
        if(head == null)
            System.out.println("Empty");
        else {
            while (temp != null) {
                System.out.print(temp.value+" ");
                temp = temp.next;
            }
        }
        System.out.println();
    }

//    public Node createNode(int value, Node next){
//        return new Node(value,next);
//    }


    class Node{
        public String value;
        public Node next;

        public Node(String value,Node next){
            this.value = value;
            this.next = next;
        }
    }
}
