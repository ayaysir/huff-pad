package treePackage;

import java.util.Iterator;
import stackForTree.*;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements BinaryTreeInterface<T> {
	
	private BinaryNodeInterface<T> root;
	
	public BinaryTree()
	{
		root = null;
	}
	
	public BinaryTree(T rootData)
	{
		root = new BinaryNode<T>(rootData);
	}
	
	public BinaryTree(T rootData, BinaryTree<T> leftTree,
									BinaryTree<T> rightTree)
	{
		privateSetTree(rootData, leftTree, rightTree);
	}

	@Override
	public T getRootData() {
		T rootData = null;
		
		if (root != null)
			rootData = root.getData();
		
		return rootData;
	}

	@Override
	public int getHeight() {
		
		return root.getHeight();
	}

	@Override
	public int getNumberOfNodes() {

		return root.getNumberOfNodes();
	}

	@Override
	public boolean isEmpty() {

		return root == null;
	}

	@Override
	public void clear() {
		root = null;
	}
	
	protected void setRootData(T rootData)
	{
		root.setData(rootData);
	}
	
	protected void setRootNode(BinaryNodeInterface<T> rootNode)
	{
		root = rootNode;
	}
	
	public BinaryNodeInterface<T> getRootNode()
	{
		return root;
	}

	@Override
	public Iterator<T> getPreorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> getPostorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> getInorderIterator() {

		return new InorderIterator();
	}

	@Override
	public Iterator<T> getLevelOrderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTree(T rootData) {
		root = new BinaryNode<T>(rootData);

	}

	@Override
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
			BinaryTreeInterface<T> rightTree) {
		
		privateSetTree(rootData, (BinaryTree<T>) leftTree,
									(BinaryTree<T>) rightTree);
	}
	
	private void privateSetTree(T rootData, BinaryTree<T> leftTree,
											BinaryTree<T> rightTree)
	{
		root = new BinaryNode<T>(rootData);
		
		/*if ((leftTree != null) && !leftTree.isEmpty())
			root.setLeftChild(leftTree.root.copy());
		// from BinaryNode<T>
		
		if ((rightTree != null) && !rightTree.isEmpty())
		{
			if (rightTree != leftTree)
				root.setRightChild(rightTree.root);
			else
				root.setRightChild(rightTree.root.copy());
		}
		
		if ((leftTree != null) && (leftTree != this))
			leftTree.clear();
		if ((rightTree != null) && (rightTree != this))
			rightTree.clear();*/
		
		if ((leftTree != null) && !leftTree.isEmpty())
			root.setLeftChild(leftTree.root.copy());
		if ((rightTree != null) && !rightTree.isEmpty())
			root.setRightChild(rightTree.root.copy());
			
	}
	
	public void inorderTraverse()
	{
		inorderTraverse(root);
	}
	
	public void preorderTraverse()
	{
		
	}
	
	public void postorderTraverse()
	{
		
	}
	
	public void levelorderTraverse()
	{
		
	}
	
	private void inorderTraverse(BinaryNodeInterface<T> node)
	{
		if (node != null)
		{
			inorderTraverse(node.getLeftChild());
			System.out.println(node.getData());
			inorderTraverse(node.getRightChild());
		}
	}
	
	private class InorderIterator implements Iterator<T>
	{
		private StackInterface<BinaryNodeInterface<T>> nodeStack;
		private BinaryNodeInterface<T> currentNode;
		
		public InorderIterator()
		{
			nodeStack = new VectorStack<BinaryNodeInterface<T>>();
			currentNode = root;
		}
		
		public boolean hasNext()
		{
			return !nodeStack.isEmpty() || (currentNode != null); 
		}
		
		public T next()
		{
			BinaryNodeInterface<T> nextNode = null;
			
			while (currentNode != null)
			{
				nodeStack.push(currentNode);
				currentNode = currentNode.getLeftChild();
			}
			
			if (!nodeStack.isEmpty())
			{
				nextNode = nodeStack.pop();
				assert nextNode != null;
				
				currentNode = nextNode.getRightChild();
			}
			else
				throw new NoSuchElementException();
			
			return nextNode.getData();
		}
	}
	
	public void remove()
	{
		throw new UnsupportedOperationException();
	}

}
