import javax.swing.tree.TreeNode;
import java.util.Random;

public class AVLTree implements SortedCollection{

    private TreeNode root;
    protected int currentNoOfElements;

    public AVLTree(){
        root = null;
        currentNoOfElements = 0;
    }


    public void add(int key, String value) {
        if (!insertIfEqualKeys(root, key, value)) {
            root = insert(root, key, value);
            currentNoOfElements++;
        }
    }

    @Override
    public int generate() {
        Random random = new Random();
        return 10000000 + random.nextInt(90000000);
    }

    @Override
    public int[] allKeys() {
        int[] arrayOfKeys = new int[currentNoOfElements];
        inOrder(root,arrayOfKeys,0);
        return arrayOfKeys;
    }

    private int inOrder(TreeNode node, int[] arrayOfKeys, int index){
        if (node != null) {
            index = inOrder(node.left, arrayOfKeys, index);
            arrayOfKeys[index++] = node.list.key;
            index = inOrder(node.right, arrayOfKeys, index);
        }
        return index;
    }

    @Override
    public void remove(int key) {
    root = deleteNode(root,key);
    currentNoOfElements --;
    }

    // Function to delete a node with a given key from an AVL tree
    public TreeNode deleteNode(TreeNode root, int key) {
        // Base case: empty tree
        if (root == null) {
            return root;
        }

        // If the key to be deleted is smaller than the root's key,
        // then it lies in the left subtree
        if (root.compareTo(key)>0) {
            root.left = deleteNode(root.left, key);
        }
        // If the key to be deleted is greater than the root's key,
        // then it lies in the right subtree
        else if (root.compareTo(key)<0) {
            root.right = deleteNode(root.right, key);
        }
        // If key is same as root's key, then this is the node
        // to be deleted
        else {
            // Case 1: node with only one child or no child
            if (root.left == null || root.right == null) {
                TreeNode temp;
                if (root.left != null) {
                    temp = root.left;
                } else {
                    temp = root.right;
                }
                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else { // One child case
                    root = temp; // Copy the contents of the non-empty child
                }
            } else { // Case 2: node with two children
                // Get the inorder successor (smallest in the right subtree)
                TreeNode temp = minValue(root.right);
                // Copy the inorder successor's data to this node
                root.list = temp.list;
                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.list.key);
            }
        }

        // If the tree had only one node then return
        if (root == null) {
            return root;
        }

        // Update the height of the current node
        updateHeight(root);

        // Get the balance factor of this node
        int balance = balanceFactor(root);

        if (balance > 1) {
            if (balanceFactor(root.left) < 0) {
                root.left = rotateLeft(root.left);
            }
            return rotateRight(root);
        } else if (balance < -1) {
            if (balanceFactor(root.right) > 0) {
                root.right = rotateRight(root.right);
            }
            return rotateLeft(root);
        }

        return root;
    }


    @Override
    public String getValues(int key) {
        return getValues(root,key);
    }
    private String getValues(TreeNode node, int key) {
        if (node == null)
            return null;
        else if (node.compareTo(key) == 0) {
            return node.list.toString();
        } else {
            if (node.compareTo(key) > 0) {
                return getValues(node.left, key);
            } else if (node.compareTo(key) < 0) {
                return getValues(node.right, key);
            }
        }
        return null;
    }

    @Override
    public int nextKey(int key) {
        TreeNode currentNode = getNode(root,key);
        if(currentNode!=null) {
            TreeNode inorderSuccessor = getInorderSuccessor(root, currentNode);
            if (inorderSuccessor != null)
                return inorderSuccessor.list.key;
        }
        return -1;
    }
    private TreeNode getNode(TreeNode node, int key){
        if(node == null)
            return null;
        else if (node.compareTo(key) == 0) {
            return node;
        } else {
            if (node.compareTo(key) > 0) {
                return getNode(node.left, key);
            } else if (node.compareTo(key) < 0) {
                return getNode(node.right, key);
            }
        }
        return null;
    }

    private TreeNode getInorderSuccessor(TreeNode root, TreeNode node){
        if(node.right!=null)
            return minValue(node.right);

        TreeNode successor = null;
        while (root != null)
        {
            if (node.compareTo(root.list.key) < 0)
            {
                successor = root;
                root = root.left;
            }
            else if (node.compareTo(root.list.key) > 0)
                root = root.right;
            else
                break;
        }
        return successor;
    }
    private TreeNode minValue(TreeNode node)
    {
        /* loop down to find the leftmost leaf */
        while (node.left != null)
        {
            node = node.left;
        }
        return node;
    }

    @Override
    public int prevKey(int key) {
        TreeNode currentNode = getNode(root,key);
        if(currentNode!=null) {
            TreeNode inorderPredecessor = getInorderPredecessor(root, currentNode);
            if (inorderPredecessor != null)
                return inorderPredecessor.list.key;
        }
        return -1;
    }
    private TreeNode getInorderPredecessor(TreeNode root, TreeNode node){
        if(node.left!=null)
            return maxValue(node.left);

        TreeNode predecessor = null;
        while (root != null)
        {
            if (node.compareTo(root.list.key) > 0)
            {
                predecessor = root;
                root = root.right;
            }
            else if (node.compareTo(root.list.key) < 0)
                root = root.left;
            else
                break;
        }
        return predecessor;
    }
    private TreeNode maxValue(TreeNode node)
    {
        /* loop down to find the rightmost leaf */
        while (node.right != null)
        {
            node = node.right;
        }
        return node;
    }

    @Override
    public int rangeKey(int key1, int key2) {
        int numOfKeys = getNumOfKeys(root,key1,key2,0);
        return numOfKeys;
    }

    private int getNumOfKeys(TreeNode node, int key1, int key2, int numOfKeys){
        if(node!=null) {
            if(node.compareTo(key1)>0 && node.compareTo(key2)<0)
                numOfKeys++;
            numOfKeys = getNumOfKeys(node.left, key1, key2, numOfKeys);
            numOfKeys = getNumOfKeys(node.right, key1, key2, numOfKeys);
        }
        return numOfKeys;
    }

//    private int inOrder(TreeNode node, int[] arrayOfKeys, int index){
//        if (node != null) {
//            index = inOrder(node.left, arrayOfKeys, index);
//            arrayOfKeys[index++] = node.list.key;
//            index = inOrder(node.right, arrayOfKeys, index);
//        }
//        return index;
//    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        return node.height;
    }

    private int balanceFactor(TreeNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    private void updateHeight(TreeNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private TreeNode rotateRight(TreeNode node) {
        TreeNode newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    private TreeNode rotateLeft(TreeNode node) {
        TreeNode newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    private boolean insertIfEqualKeys(TreeNode node, int key, String value) {
        if (node == null)
            return false;
        else if (node.compareTo(key) == 0) {
            node.addToStart(value);
            return true;
        } else {
            if (node.compareTo(key) > 0) {
                return insertIfEqualKeys(node.left, key, value);
            } else if (node.compareTo(key) < 0) {
                return insertIfEqualKeys(node.right, key, value);
            }
        }
        return false;
    }

    private TreeNode insert(TreeNode node, int key, String value) {
        if (node == null) {
            return new TreeNode(key, value, null, null);
        }

        if (node.compareTo(key) > 0) {
            node.left = insert(node.left, key, value);
        } else {
            node.right = insert(node.right, key, value);
        }

        updateHeight(node);                 // Updates the height of root of subtree after inserting new node

        int balance = balanceFactor(node);

        if (balance > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        } else if (balance < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    @Override
    public int getCurrentNoOfElements() {
        return currentNoOfElements;
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.list.key + " ");

        inorder(node.right);
    }

    public RecordList[] getElements(){
        RecordList[] elements = new RecordList[currentNoOfElements];
        inOrder(root,elements,0);
        return elements;
    }
    private int inOrder(TreeNode node, RecordList[] elements, int index){
        if (node != null) {
            index = inOrder(node.left, elements, index);
            elements[index++] = node.list;
            index = inOrder(node.right, elements, index);
        }
        return index;
    }

    @Override
    public String toString() {
        return toString(root, "", "");
    }

    private String toString(TreeNode node, String prefix, String childrenPrefix) {
        StringBuilder sb = new StringBuilder();
        if (node != null) {
            sb.append(prefix);
            sb.append(node.list);
            sb.append("\n");
            if (node.left != null && node.right != null) {
                sb.append(toString(node.right, childrenPrefix + "├── ", childrenPrefix + "│   "));
                sb.append(toString(node.left, childrenPrefix + "└── ", childrenPrefix + "    "));
            } else if (node.left != null) {
                sb.append(toString(node.left, childrenPrefix + "└── ", childrenPrefix + "    "));
            } else if (node.right != null) {
                sb.append(toString(node.right, childrenPrefix + "└── ", childrenPrefix + "    "));
            }
        }
        return sb.toString();
    }

    class TreeNode implements Comparable<Integer> {

        public RecordList list;
        public TreeNode left;
        public TreeNode right;
        public int height;

        public TreeNode(int key, String value, TreeNode left, TreeNode right) {
            this.list = new RecordList(key, value);
            this.left = left;
            this.right = right;
            this.height = 1;
        }

        private void addToStart(String value) {
            list.addToStart(value);
        }

        @Override
        public int compareTo(Integer newKey) {
            return this.list.compareTo(newKey);
        }
    }

}
