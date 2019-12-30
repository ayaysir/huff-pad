package treePackage;

public class HuffmanTree<T> extends BinaryTree<T> implements HuffmanTreeInterface<T> {
	
	private BinaryNodeInterface<T> currentNode;
	
	public HuffmanTree()
	{
		super();
		reset();
		//@System.out.println("Constructor: " + currentNode);
	}
	
	public HuffmanTree(T rootData)
	{
		super(rootData);
		reset();
		//@System.out.println("Constructor: " + currentNode);
	}
	
	public HuffmanTree(T rootData, BinaryTree<T> leftTree,
									BinaryTree<T> rightTree)
	{
		super(rootData, leftTree, rightTree);
		reset();
		//@System.out.println("Constructor: " + currentNode);
	}

	public T getCurrentData()
	{
		T result = null;
		result = currentNode.getData();
		return result;
	}
	
	public boolean isLeaf()
	{
		return currentNode.isLeaf();
	}
	
	public T getLeftChildData()
	{
		assert currentNode.hasLeftChild();
		T result = null;
		result = currentNode.getLeftChild().getData();
		return result;
	}
	
	public T getRightChildData()
	{
		assert currentNode.hasRightChild();
		T result = null;
		result = currentNode.getRightChild().getData();
		return result;
	}
	
	public HuffmanTreeInterface<T> advanceToZero()
	{
		assert currentNode.hasLeftChild() && !isLeaf();
		currentNode = currentNode.getLeftChild();
		return this;
	}
	
	public HuffmanTreeInterface<T> advanceToOne()
	{
		assert currentNode.hasRightChild() && !isLeaf();
		currentNode = currentNode.getRightChild();
		return this;
	}
	
	public void reset()
	{
		currentNode = super.getRootNode();
	}
	

}
