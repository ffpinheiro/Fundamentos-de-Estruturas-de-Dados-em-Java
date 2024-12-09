public class BinaryTree <T extends Comparable<T>> implements Tree<T>{
    private TreeNode<T> root;
    @Override
    public void add(T element) {
        TreeNode<T> newNode = new TreeNode<>(element);
        if (this.root == null){
            root = newNode;
        }else{
            TreeNode<T> auxNode = root;
            while(auxNode != null){
                T info = auxNode.getInfo();
                if(element.compareTo(info) == 0){
                    break;
                } else if (element.compareTo(info) > 0) {
                    if (auxNode.getRightNode() == null){
                        auxNode.setRightNode(newNode);
                        break;
                    }
                    auxNode = auxNode.getRightNode();
                }else{
                    if (auxNode.getLeftNode() == null){
                        auxNode.setLeftNode(newNode);
                        break;
                    }
                    auxNode = auxNode.getLeftNode();
                }
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return (this.root == null);
    }

    @Override
    public int getTreeHeight() {
        return evaluateTreeHeight(this.root,-1);
    }
    private int evaluateTreeHeight(TreeNode<T> node, int depthCounter){
        if(node == null){
            return depthCounter;
        }
        int leftDepth = evaluateTreeHeight(node.getLeftNode(),depthCounter+1);
        int rightDepth = evaluateTreeHeight(node.getRightNode(),depthCounter+1);

        return Math.max(leftDepth,rightDepth);
    }

    @Override
    public int nodeDepth(T element) {
        return evaluateNodeDepth(this.root,element,0);
    }
    private int evaluateNodeDepth(TreeNode<T> node, T element, int depthCounter){
        if (node == null){
            return -1;
        }
        int comparingResult = element.compareTo(node.getInfo());
        if(comparingResult == 0){
            return depthCounter;
        }else if(comparingResult > 0){
            return evaluateNodeDepth(node.getRightNode(),element,depthCounter+1);
        }else{
            return evaluateNodeDepth(node.getLeftNode(),element,depthCounter+1);
        }
    }

    @Override
    public TreeNode<T> search(T element) {
        return binarySearch(this.root, element);
    }
    private TreeNode<T> binarySearch(TreeNode<T> node, T element){
        if(node == null){
            return null;
        }
        T nodeInfo = node.getInfo();
        int comparingResult = element.compareTo(nodeInfo);
        if (comparingResult == 0){
            return node;
        }else if(comparingResult > 0){
            return binarySearch(node.getRightNode(),element);
        }else{
            return binarySearch(node.getLeftNode(),element);
        }
    }

    @Override
    public List<T> preOrder() {
        List<T> preOrderElements = new LinkedList<>();
        preOrderNavigator(this.root, preOrderElements);
        return null;
    }
    private void preOrderNavigator(TreeNode<T> rootNode, List<T> elements){
        if (rootNode == null){
            return;
        }else{
            elements.add(rootNode.getInfo());
            preOrderNavigator(rootNode.getLeftNode(),elements);
            preOrderNavigator(rootNode.getRightNode(),elements);
        }
    }

    @Override
    public List<T> inOrder() {
        List<T> inOrderElements = new LinkedList<>();
        inOrderNavigator(this.root, inOrderElements);
        return null;
    }
    private void inOrderNavigator(TreeNode<T> rootNode, List<T> elements){
        if (rootNode == null){
            return;
        }else{
            inOrderNavigator(rootNode.getLeftNode(),elements);
            elements.add(rootNode.getInfo());
            inOrderNavigator(rootNode.getRightNode(),elements);
        }
    }

    @Override
    public List<T> postOrder() {
        List<T> postOrderElements = new LinkedList<>();
        postOrderNavigator(this.root, postOrderElements);
        return null;
    }
    private void postOrderNavigator(TreeNode<T> rootNode, List<T> elements){
        if (rootNode == null){
            return;
        }else{
            postOrderNavigator(rootNode.getLeftNode(),elements);
            postOrderNavigator(rootNode.getRightNode(),elements);
            elements.add(rootNode.getInfo());
        }
    }
}
