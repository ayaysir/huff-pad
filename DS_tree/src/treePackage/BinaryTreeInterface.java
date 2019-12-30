package treePackage;

public interface BinaryTreeInterface<T> extends TreeInterface<T>,
												TreeIteratorInterface<T>,
												TraverserInterface<T>
{
	public void setTree(T rootData);
	
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
									BinaryTreeInterface<T> rightTree);
	
}
