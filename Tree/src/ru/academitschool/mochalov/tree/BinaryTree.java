package ru.academitschool.mochalov.tree;

import ru.academitschool.mochalov.tree_node.BinaryTreeNode;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinaryTree<T> {
    private BinaryTreeNode<T> root;
    private int size;
    private final Comparator<? super T> comparator;

    public BinaryTree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public BinaryTree() {
        this.comparator = null;
    }

    public int getSize() {
        return size;
    }

    public void add(T data) {
        if (size == 0) {
            root = new BinaryTreeNode<>(data);
            size++;

            return;
        }

        addNode(new BinaryTreeNode<>(data), root);
    }

    public boolean find(T data) {
        if (size == 0) {
            return false;
        }

        BinaryTreeNode<T> parent = findParent(data);

        if (parent == null) {
            return true;
        }

        return parent.getLeft() != null || parent.getRight() != null;
    }

    public boolean delete(T data) {
        if (size == 0) {
            return false;
        }

        BinaryTreeNode<T> parent = findParent(data);

        if (parent == null) {
            root = null;
            size = 0;

            return true;
        }

        if (compare(parent.getLeft().getData(), data) == 0) {
            deleteChild(parent, parent.getLeft());

            return true;
        }

        if (compare(parent.getRight().getData(), data) == 0) {
            deleteChild(parent, parent.getRight());

            return true;
        }

        return false;
    }

    private void deleteChild(BinaryTreeNode<T> parent, BinaryTreeNode<T> child) {
        if (child.getLeft() == null && child.getRight() == null) {
            parent.setLeft(null);
            size--;

            return;
        }

        if (child.getLeft() == null) {
            parent.setLeft(child.getRight());
            size--;

            return;
        }

        if (child.getRight() == null) {
            parent.setLeft(child.getLeft());
            size--;

            return;
        }

        BinaryTreeNode<T> minNodeParent = child;
        BinaryTreeNode<T> minNode = minNodeParent.getRight();

        while (minNode.getLeft() != null) {
            minNodeParent = minNode;
            minNode = minNode.getLeft();
        }

        minNodeParent.setLeft(minNode.getRight());
        minNode.setLeft(child.getLeft());
        minNode.setRight(child.getRight());

        parent.setRight(minNode);

        size--;
    }

    private void addNode(BinaryTreeNode<T> node, BinaryTreeNode<T> tempRoot) {
        BinaryTreeNode<T> currentNode = tempRoot;

        for (; ; ) {
            if (compare(node.getData(), currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(node);
                    size++;

                    break;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(node);
                    size++;

                    break;
                }
            }
        }
    }

    public void passNodesInWidth(Consumer<T> function) {
        if (size == 0) {
            return;
        }

        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode<T> node = queue.remove();
            function.accept(node.getData());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void passNodesInDepthRecursive(Consumer<T> function) {
        if (size == 0) {
            return;
        }

        visit(root, function);
    }

    private void visit(BinaryTreeNode<T> node, Consumer<T> func) {
        func.accept(node.getData());

        if (node.getLeft() != null) {
            visit(node.getLeft(), func);
        }

        if (node.getRight() != null) {
            visit(node.getRight(), func);
        }
    }

    public void passNodesInDepth(Consumer<T> function) {
        if (size == 0) {
            return;
        }

        Deque<BinaryTreeNode<T>> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            BinaryTreeNode<T> node = stack.remove();
            function.accept(node.getData());

            if (node.getRight() != null) {
                stack.add(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.add(node.getLeft());
            }
        }
    }

    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        //noinspection unchecked
        return ((Comparable<T>) data1).compareTo(data2);
    }

    private BinaryTreeNode<T> findParent(T data) {
        BinaryTreeNode<T> currentParent = null;
        BinaryTreeNode<T> currentNode = root;

        for (; ; ) {
            int compareResult = compare(data, currentNode.getData());

            if (compareResult == 0) {
                return currentParent;
            } else if (compareResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentParent = currentNode;
                    currentNode = currentNode.getLeft();
                } else {
                    return currentNode;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentParent = currentNode;
                    currentNode = currentNode.getRight();
                } else {
                    return currentNode;
                }
            }
        }
    }
}