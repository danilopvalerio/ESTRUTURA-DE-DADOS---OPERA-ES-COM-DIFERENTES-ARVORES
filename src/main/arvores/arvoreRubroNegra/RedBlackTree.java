package arvoreRubroNegra;

public class RedBlackTree {
    private Node root;
    private int rotationCount;
    int rotationLeft = 0;
    int rotationRight = 0;

    private static class Node {
        int key;
        Node left;
        Node right;
        Node parent;
        boolean isRed;

        public Node(int key) {
            this.key = key;
            isRed = true;
        }
    }

    public int getRotationCount() {
        return rotationCount;
    }

    public void insert(int key) {
        Node node = new Node(key);
        root = insert(root, node);
        fixRedBlackProperties(node);
    }

    private Node insert(Node root, Node node) {
        if (root == null) {
            return node;
        }

        if (node.key < root.key) {
            root.left = insert(root.left, node);
            root.left.parent = root;
        } else if (node.key > root.key) {
            root.right = insert(root.right, node);
            root.right.parent = root;
        }

        return root;
    }

    private void fixRedBlackProperties(Node node) {
        while (node != null && node != root && node.parent != null && node.parent.isRed) {
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;

                if (uncle != null && uncle.isRed) {
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                    rotationCount++;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                        rotationCount++;
                    }

                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    rotateRight(node.parent.parent);
                    rotationCount++;
                }
            } else {
                Node uncle = node.parent.parent.left;

                if (uncle != null && uncle.isRed) {
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                    rotationCount++;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                        rotationCount++;
                    }

                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    rotateLeft(node.parent.parent);
                    rotationCount++;
                }
            }
        }

        root.isRed = false;
    }

    private void rotateLeft(Node node) {
        rotationLeft++;
        Node rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.parent = node.parent;

        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(Node node) {
        rotationRight++;
        Node leftChild = node.left;
        node.left = leftChild.right;

        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.parent = node.parent;

        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.left) {
            node.parent.left = leftChild;
        } else {
            node.parent.right = leftChild;
        }

        leftChild.right = node;
        node.parent = leftChild;
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(Node node, int key) {
        if (node == null) {
            return false;
        }

        if (key < node.key) {
            return search(node.left, key);
        } else if (key > node.key) {
            return search(node.right, key);
        } else {
            return true;
        }
    }

    public int getRotacao_dir(){
        return rotationRight;
    }

    public int getRotacao_esq(){
        return rotationLeft;
    }
}
