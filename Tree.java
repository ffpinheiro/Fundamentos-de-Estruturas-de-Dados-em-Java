public interface Tree <T extends Comparable<T>>{
    void add(T element);
    boolean isEmpty();
    int getTreeHeight();
    int nodeDepth(T element);
    TreeNode<T> search(T element);
    List<T> preOrder();
    List<T> inOrder();
    List<T> postOrder();
}
