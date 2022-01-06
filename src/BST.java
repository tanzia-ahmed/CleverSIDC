public class BST {
    public Node root;

    class Node {

        public Node left;
        public Node right;
        public long key;

        public Node(long key) {
            this.key = key;
            left = right = null;
        }
    }
    public BST(){
        root = null;
    }
    public void insert(long value) {

        if (root == null) {
            root = new Node(value);
        }

        else {
            Node n = root;
            while(n.left != null || n.right != null) {
                if (value < n.key) {
                    if(n.left == null) break;
                    else n = n.left;
                }else {
                    if(n.right == null) break;
                    else n = n.right;
                }
            }
            if (value < n.key) {
                n.left = new Node(value);
            }else {
                n.right = new Node(value);
            }
        }



    }

    public void delete(Node root, int value) {

        if (root == null){

        }

        else if (value < (int) root.key) {
            delete(root.left, value);
        } else if (value > (int) root.key) {
            delete(root.right, value);
        } else {

            root.key = inOrderSuccessor(root.right);
            delete(root.right, (int) root.key);
        }


    }

    public long inOrderSuccessor(Node root) {
        long minimum = root.key;
        while (root.left != null) {
            minimum = root.left.key;
            root = root.left;
        }
        return minimum;
    }

    public boolean exist(Node root, long value) {
        if (root == null)
            return false;

        else if (root.key == value)
            return true;

        else if (value <  root.key)
            return exist(root.left, value);

        else if (value > root.key)
            return exist(root.right, value);


        else return false;
    }

    public long findParent(Node root, long value, long parent) {


        if (root == null)
            return -1;

        else if (root.key == value)
            return parent;

        else if (value <  root.key)
            return findParent(root.left, value, root.key);

        else if (value > root.key)
            return findParent(root.right, value, root.key);


        else return -1;
    }

    public long findChild(Node root, long value) {


        if (root == null)
            return -1;

        else if (root.key == value){
            if(root.right != null) return root.right.key;
            else if(root.left != null) return root.left.key;
            else return -1;
        }

        else if (value <  root.key)
            return findChild(root.left, value);

        else if (value > root.key)
            return findChild(root.right, value);


        else return -1;
    }
    public void printInorderTraversal(Node root) {
        if (root != null) {
            printInorderTraversal(root.left);
            System.out.println(root.key + " ");
            printInorderTraversal(root.right);
        }
    }




}
