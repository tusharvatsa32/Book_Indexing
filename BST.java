import java.util.Stack;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Tushar Vatsa
 * andrewId : tvatsa
 * @param <T>
 */
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    /**
     * Instance variable for the root node.
     */
    private Node<T> root;

    /**
     * Instance variable for the comparator object.
     */
    private Comparator<T> comparator;

    /**
     * Comparator when we pass nothing.
     */
    public BST() {
        this(null);
    }

    /**
     * Comparator when we pass the comparator.
     * @param comp
     */
    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }

    /**
     * Method to get the comparator.
     * @return comparator.
     */
    public Comparator<T> comparator() {
        return comparator;
    }

    /**
     * Method to get the root.
     * @return the data contained in the root.
     */
    public T getRoot() {
        if (root == null) {
            return null;
        }
        return root.data;
    }

    /**
     * Helper method to get the height.
     * @param node
     * @return height
     */
    private int helperGetHeight(Node<T> node) {
        /*
         * base case : If node = null, we return 0.
         */
        if (node == null) {
            return 0;
        }
        /*
         * recursive case : the height will be maximum from the left and the right.
         */
        return 1 + Math.max(helperGetHeight(node.left), helperGetHeight(node.right));
    }

    /**
     * Method to get the height.
     * @return height
     */
    public int getHeight() {
        int height = helperGetHeight(root);
        if (height == 0) {
            return 0;
        }
        return height - 1;
    }

    /**
     * Helper method to get the total number of nodes.
     * @param node
     * @return the number of nodes
     */
    private int helperGetNumberOfNodes(Node<T> node) {
        /*
         * base case : if node is null, return 0.
         */
        if (node == null) {
            return 0;
        }
        /*
         * base case : if node is a leaf node, return 1.
         */
        if (node.left == null && node.right == null) {
            return 1;
        }
        /*
         * recursive case : count the nodes on the left and the right.
         */
        return 1 + helperGetNumberOfNodes(node.left) + helperGetNumberOfNodes(node.right);
    }

    /**
     * Method to get the total number of nodes.
     * @return the number of nodes
     */
    public int getNumberOfNodes() {
        int nodes = helperGetNumberOfNodes(root);
        return nodes;
    }

    /**
     * Helper method to search for the data.
     * @param toSearch : the value to search
     * @param flag : determines whether the comparator is null or not
     * @param node : the node with which the value is compared
     * @return null or the object depending on the match
     */
    private T helperSearch(T toSearch, boolean flag, Node<T> node) {
        /*
         * base case : if node is null, the search method failed then we return null.
         */
        if (node == null) {
            return null;
        }
        /*
         * base case : if comparator object is not null and object is found.
         */
        if (flag && node != null && comparator.compare(toSearch, node.data) == 0) {
            return node.data;
        }
        /*
         * base case : if comparator object is null and object is found.
         */
        if (!flag && node != null && node.data.compareTo(toSearch) == 0) {
            return node.data;
        }
        /*
         * recursive case : search on the left.
         */
        T left = helperSearch(toSearch, flag, node.left);
        /*
         * recursive case : search on the right.
         */
        T right = helperSearch(toSearch, flag, node.right);
        /*
         * These make sure that we are returning the right value.
         */
        if (left == null && right == null) {
            return null;
        } else {
            if (left != null) {
                return left;
            } else {
                return right;
            }
        }
    }

    /**
     * Method to search the object.
     * @param toSearch Object value to search
     * @return null or the object contained in the BST
     */
    @Override
    public T search(T toSearch) {
        boolean flag = false;
        if (comparator != null) {
            flag = true;
        }
        T output = helperSearch(toSearch, flag, root);
        if (output == null) {
            return null;
        }
        return output;
    }

    /**
     * Helper method to insert the data in BST.
     * @param toInsert : the value to insert
     * @param node : the node with which the value is compared
     * @param parent : the parent of the current node so that we can add the toInsert to parent
     * @param flag : determines whether the comparator is present or not
     * @param isLeft : whether the node is the left child of the parent
     */
    private void helperInsert(T toInsert, Node<T> node, Node<T> parent, boolean flag, boolean isLeft) {
        /*
         * base case : if parent is null and node is null, that means the BST is empty.
         */
        if (parent == null && node == null) {
            root = new Node<T>(toInsert);
            return;
        }
        /*
         * base case : if node is null.
         */
        if (node == null) {
            /*
             * base case : If node is the left child of the parent, attach the node to the left of parent.
             * Otherwise, attach the node to the right of parent.
             */
            if (isLeft) {
                parent.left = new Node<T>(toInsert);
                return;
            } else {
                parent.right = new Node<T>(toInsert);
                return;
            }
        }
        /*
         * recursive case : if toInsert is bigger than the current node's data go right.
         * If toInsert is smaller than the current node's data go left.
         * If the values are same, do not do anything.
         * Flag : Determines whether there is a comparator or not.
         */
        if (flag) {
            if (comparator.compare(toInsert, node.data) > 0) {
                helperInsert(toInsert, node.right, node, true, false);
            } else if (comparator.compare(toInsert, node.data) < 0) {
                helperInsert(toInsert, node.left, node, true, true);
            } else {
                return;
            }
        } else {
            if (node.data.compareTo(toInsert) > 0) {
                helperInsert(toInsert, node.left, node, false, true);
            } else if (node.data.compareTo(toInsert) < 0) {
                helperInsert(toInsert, node.right, node, false, false);
            } else {
                return;
            }
        }
    }

    /**
     * Method to insert the data in the BST.
     * @param toInsert a value (object) to insert into the tree.
     */
    @Override
    public void insert(T toInsert) {
        boolean flag = false;
        if (comparator != null) {
            flag = true;
        }
        helperInsert(toInsert, root, null, flag, false);
    }

    /**
     * Iterator method.
     * @return the object from the iterator class.
     */
    @Override
    public Iterator<T> iterator() {
        return new BstIterator();
    }

    /**
     * The class which implements the iterator.
     */
    private class BstIterator implements Iterator<T> {
        /**
         * Instance variable for the index.
         */
        private int index;

        /**
         * Instance variable for the stack to carry out the iterative traversal of the BST.
         */
        private Stack<Node<T>> stack = new Stack<Node<T>>();

        /**
         * Constructor for the class.
         */
        BstIterator() {
            Node<T> curr = root;
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
        }

        /**
         * Method which checks if there is a next value.
         * @return true or false depending on whether the arraylist has values.
         */
        @Override
        public boolean hasNext() {
            return stack.size() > 0;
        }

        /**
         * Method which returns the next value in the arraylist.
         * @return result : the next word
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> temp = stack.peek().right;
            T item = stack.pop().data;
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            return item;
        }
    }

    /**
     * Node class.
     * @param <T>
     */
    private static class Node<T> {
        /**
         * Instance variable for the date.
         */
        private T data;

        /**
         * Instance variable for the left Node.
         */
        private Node<T> left;

        /**
         * Instance variable for the right Node.
         */
        private Node<T> right;

        /**
         * Constructor for the class.
         * @param d
         */
        Node(T d) {
            this(d, null, null);
        }

        /**
         * Constructor for the class with data, left and right.
         * @param d
         * @param l
         * @param r
         */
        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }
    }

}
