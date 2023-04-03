import javax.swing.tree.TreeNode;

public class AVLTree {
    
    private TreeNode root;

    class TreeNode implements Comparable<Integer>{

        public RecordList list;
        public TreeNode left;
        public TreeNode right;
        public int height;

        public TreeNode(int key, String value, TreeNode left, TreeNode right){
            this.list = new RecordList(key, value);
            this.left = left;
            this.right = right;
            this.height = 1;
        }

        public void addToStart(String value){
            list.addToStart(value);
        }

        @Override
        public int compareTo(Integer newKey){
            return this.list.compareTo(newKey);
        }
    }
        

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

        public void insert(int key, String value) {
            root = insert(root, key, value);
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

        public void inorder() {
            inorder(root);
        }

        private void inorder(TreeNode node) {
            if (node == null) return;
            inorder(node.left);
            System.out.print(node.list.key + " ");

            inorder(node.right);
        }
    @Override
    public String toString() {
        return toString(root, "", "");
    }

    private String toString(Node node, String prefix, String childrenPrefix) {
        StringBuilder sb = new StringBuilder();
        if (node != null) {
            sb.append(prefix);
            sb.append(node.key);
            sb.append("\n");
            if (node.left != null && node.right != null) {
                sb.append(toString(node.left, childrenPrefix + "├── ", childrenPrefix + "│   "));
                sb.append(toString(node.right, childrenPrefix + "└── ", childrenPrefix + "    "));
            } else if (node.left != null) {
                sb.append(toString(node.left, childrenPrefix + "└── ", childrenPrefix + "    "));
            } else if (node.right != null) {
                sb.append(toString(node.right, childrenPrefix + "└── ", childrenPrefix + "    "));
            }
        }
        return sb.toString();
    }





    
}
