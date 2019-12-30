package treePackage;

public class BinaryTreeTest {

	public static void main(String[] args) {
		

		BinaryTreeInterface<String> tree = new BinaryTree<String>( "ROOT" );
		BinaryTreeInterface<String> treeA = new BinaryTree<String>( "A" );
		BinaryTreeInterface<String> treeB = new BinaryTree<String>( "B" );
		
		tree.setTree("ROOT", treeA, treeB);
		
		tree.inorderTraverse();
		System.out.println(tree.getHeight());
		

	}

}
