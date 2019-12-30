package ioTest;

import sortedList.SortedLinkedList;
import sortedList.SortedListInterface;
import treePackage.HuffmanTree;
import treePackage.HuffmanTreeInterface;

public class DummyTestGenerateTree {
	
	public static void main(String args[])
	{
		SortedListInterface<HuffmanPair> list = new SortedLinkedList<HuffmanPair>();
		
		list.add(new HuffmanPair('A', 12));
		list.add(new HuffmanPair('B', 3));
		list.add(new HuffmanPair('C', 1));
		list.add(new HuffmanPair('D', 9));
		list.add(new HuffmanPair('E', 15));
		
		display(list);
		
		HuffmanPair firstHuffPair = list.getEntry(1);
		HuffmanPair secondHuffPair = list.getEntry(2);
		
		//System.out.println(firstValue + " " + secondValue);
		
		HuffmanTreeInterface<HuffmanPair> tree = new HuffmanTree<HuffmanPair>();
		HuffmanTreeInterface<HuffmanPair> treeFirst = new HuffmanTree<HuffmanPair>( firstHuffPair );
		HuffmanTreeInterface<HuffmanPair> treeSecond = new HuffmanTree<HuffmanPair>( secondHuffPair );
		//BinaryTreeInterface<HuffmanPair> newTree = new BinaryTree<HuffmanPair>();
		
		int countSum = firstHuffPair.getCount() + secondHuffPair.getCount();
		
		HuffmanPair newHuffPair = new HuffmanPair('*', countSum);
		
		tree.setTree(newHuffPair, treeFirst, treeSecond);
		
		list.remove(1);
		list.remove(1);
		
		tree.inorderTraverse();
		display(list);
		

		//BinaryTreeInterface<HuffmanPair> newTree = new BinaryTree<HuffmanPair>();
		while ( !list.isEmpty() )
		{
			int rootCount = tree.getRootData().getCount();
			int firstHuffPairCount = list.getEntry(1).getCount();
			System.out.println("rc: " + rootCount);
			System.out.println("fc: " + firstHuffPairCount);
			
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
			tree.inorderTraverse();
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
			
			HuffmanTreeInterface<HuffmanPair> tempTree = new HuffmanTree<HuffmanPair>();
			
			if ( rootCount <= firstHuffPairCount )
			{
				int sum = rootCount + firstHuffPairCount;
				HuffmanPair toAddHuffPair = new HuffmanPair('*', sum);
				//BinaryTreeInterface<HuffmanPair> copyOfLeftTree = tree;
				
				tempTree.setTree(toAddHuffPair, 
						tree,
						new HuffmanTree<HuffmanPair>(new HuffmanPair(list.getEntry(1).getCharacter(), 
																	list.getEntry(1).getCount() ) ) );
			}
			else
			{
				assert rootCount > firstHuffPairCount;
				int sum = rootCount + firstHuffPairCount;
				HuffmanPair toAddHuffPair = new HuffmanPair('*', sum);
				//BinaryTreeInterface<HuffmanPair> copyOfRightTree = tree;
				tempTree.setTree(toAddHuffPair, 
						new HuffmanTree<HuffmanPair>(new HuffmanPair(list.getEntry(1).getCharacter(), 
																	list.getEntry(1).getCount() ) ) , 
						tree );
			}
			list.remove(1);
			display(list);
			System.out.println("====================");
			tempTree.inorderTraverse();
			System.out.println("====================");
			
			tree = tempTree;
		}
		
		System.out.println("=========FIANL TREE===========");
		tree.inorderTraverse();
		System.out.println("==============================");
		
		//System.out.println("====================");
		//tree.inorderTraverse();
		tree.reset();
		System.out.println(tree.getCurrentData());
		tree.advanceToZero();
		System.out.println(tree.getCurrentData());
		
		
		String huffmanCode = "111010111";
		System.out.println( huffmanCode.charAt(0));
		
		tree.reset();
		String decodeSentence = "";
		
		for (int i = 0; i < huffmanCode.length(); i++)
		{
			System.out.println("charAt: " + huffmanCode.charAt(i));
			char currentCharAt = huffmanCode.charAt(i);
			//System.out.println("pair: " + (huffmanCode.charAt(i) == 0));
			if( currentCharAt == '0' )
			{
				tree.advanceToZero();
				System.out.println("isLeaf?: " + tree.isLeaf());
				
				if (tree.isLeaf())
				{
					decodeSentence = decodeSentence + tree.getCurrentData().getCharacter();

					tree.reset();
				}

			}
			else if ( currentCharAt == '1')
			{
				tree.advanceToOne();
				
				if (tree.isLeaf())
				{
					decodeSentence = decodeSentence.concat(
						new StringBuilder().append(tree.getCurrentData().getCharacter()).toString()
						);

					tree.reset();
				}

			}
		}


			

		
		System.out.println("Huffman decode: " + decodeSentence  );
		
	}
	
	public static <T> void display(SortedListInterface<HuffmanPair> list)
	{
		Object[] display = list.toArray();
		

		for (int i = 0; i < display.length; i++)
		{
			System.out.println(display[i]);
		}
	}

}
