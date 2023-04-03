public class Main {
    public static void main(String[] args) {
        AVLTree newTree = new AVLTree();
        newTree.insert(1,"a");
        newTree.insert(2,"b");
        newTree.insert(3,"c");
        newTree.insert(4,"d");
        newTree.insert(5,"e");
        newTree.insert(5,"f");
        newTree.insert(6,"g");
        newTree.insert(7,"h");
        newTree.insert(8,"i");
        newTree.insert(9,"j");
//        newTree.inorder();
        System.out.println(newTree);
    }
}