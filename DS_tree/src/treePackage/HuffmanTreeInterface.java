package treePackage;

public interface HuffmanTreeInterface<T> extends BinaryTreeInterface<T> {
	
	public T getCurrentData();
	
	public boolean isLeaf();
	
	public T getLeftChildData();
	
	public T getRightChildData();
	
	public HuffmanTreeInterface<T> advanceToZero();
	
	public HuffmanTreeInterface<T> advanceToOne();
	
	public void reset();
	

	

}
