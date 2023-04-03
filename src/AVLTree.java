public class AVLTree {







    class TreeNode implements Comparable<TreeNode>{

        public RecordList list;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key, String value, TreeNode left, TreeNode right){
            this.list = new RecordList(key, value);
            this.left = left;
            this.right = right;
        }

        public void addToStart(String value){
            list.addToStart(value);
        }

        @Override
        public int compareTo(TreeNode treeNode2){
            return this.list.compareTo(treeNode2.list);
        }
    }
}
