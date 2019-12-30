package ioTest;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import sortedList.SortedLinkedList;
import sortedList.SortedListInterface;
import treePackage.HuffmanTree;
import treePackage.HuffmanTreeInterface;

public class HuffmanEncoder implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int toAppendZeroLastElement = 0;
	private SortedListInterface<HuffmanPair> huffmanList;
	private SortedListInterface<HuffmanPair> copyList;
	private HuffmanTreeInterface<HuffmanPair> outputTree;
	private String code;
	byte[] byteArray;
	
	

	
	
	public HuffmanEncoder() 
	{
	
	}
	
	public void run(String s, String filePath) throws IOException
	{
		huffmanList = makeHuffmanList(s);
		outputTree = generateHuffmanTree(huffmanList);
		code = encoding(s, outputTree);
		System.out.println("* number of code: " + code.length());
		
		copyList = makeHuffmanList(s);
		
		codeToBits(code, filePath);

	}

	public SortedListInterface<HuffmanPair> makeHuffmanList(String s)
	{
		SortedListInterface<HuffmanPair> huffmanList = 
											new SortedLinkedList<HuffmanPair>();
		/**
		 * 2018�뀈 4�썡 12�씪 異붽�
		 * use 4~126 on ascii code
		 * 1, 2, 3 are use for huffman tree
		 */
		
		char[] charList = new char[122];
		for(int i = 0; i < charList.length; i++)
			charList[i] = (char)(i+4);		
		
		for (int i = 0; i < charList.length; i++)
		{
			int n = 0;
			for (int j = 0; j < s.length(); j++)
			{
				if ( s.charAt(j) == charList[i] )
					n++;
			}
			
			huffmanList.add(new HuffmanPair(charList[i], n));
		}
		
		return huffmanList;
	}
	
	public HuffmanTreeInterface<HuffmanPair> 
							generateHuffmanTree(SortedListInterface<HuffmanPair> list)
	{
		// remove count==0
		while (list.getEntry(1).getCount() == 0)
		{
			list.remove(1);
		}
		
		// first two element make the base leaf.
		HuffmanPair firstHuffPair = list.getEntry(1);
		HuffmanPair secondHuffPair = list.getEntry(2);
		
		//@System.out.println(firstHuffPair.getCount() + " " + secondHuffPair.getCount());
		
		HuffmanTreeInterface<HuffmanPair> tree = new HuffmanTree<HuffmanPair>();
		HuffmanTreeInterface<HuffmanPair> treeFirst = new HuffmanTree<HuffmanPair>( firstHuffPair );
		HuffmanTreeInterface<HuffmanPair> treeSecond = new HuffmanTree<HuffmanPair>( secondHuffPair );
		//@BinaryTreeInterface<HuffmanPair> newTree = new BinaryTree<HuffmanPair>();
		
		/**
		 * @author yoonbumtae
		 * '*' to ascii(1)
		 */
		
		char starChr = (char) 1;
		int countSum = firstHuffPair.getCount() + secondHuffPair.getCount();
		HuffmanPair newHuffPair = new HuffmanPair(starChr, countSum);
		
		tree.setTree(newHuffPair, treeFirst, treeSecond);
		
		list.remove(1);
		list.remove(1); // remove first and second elements from list.
		
		//@tree.inorderTraverse();
		//@display(list);

		//BinaryTreeInterface<HuffmanPair> newTree = new BinaryTree<HuffmanPair>();
		while ( !list.isEmpty() )
		{ 
			int rootCount = tree.getRootData().getCount();
			
			
			int firstHuffPairCount = list.getEntry(1).getCount();
		
			//System.out.println("rc: " + rootCount);
			//System.out.println("fc: " + firstHuffPairCount);
					
			//@System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
			//@tree.inorderTraverse();
			//@System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
					
			HuffmanTreeInterface<HuffmanPair> tempTree = new HuffmanTree<HuffmanPair>();
					
			if ( rootCount < firstHuffPairCount )
			{
				int sum = rootCount + firstHuffPairCount;
				HuffmanPair toAddHuffPair = new HuffmanPair(starChr, sum);
				//@BinaryTreeInterface<HuffmanPair> copyOfLeftTree = tree;
						
				tempTree.setTree(toAddHuffPair, 
						tree, new HuffmanTree<HuffmanPair>(
								new HuffmanPair(list.getEntry(1).getCharacter(), 
												list.getEntry(1).getCount() ) ) );
			}

			else if ( rootCount > firstHuffPairCount )
			{
				//assert rootCount >= firstHuffPairCount;
				int sum = rootCount + firstHuffPairCount;
				HuffmanPair toAddHuffPair = new HuffmanPair(starChr, sum);
				//@BinaryTreeInterface<HuffmanPair> copyOfRightTree = tree;
				tempTree.setTree(toAddHuffPair, 
				new HuffmanTree<HuffmanPair>(
					new HuffmanPair(list.getEntry(1).getCharacter(), 
						list.getEntry(1).getCount() ) ) , tree );
			}
			
			else if ( rootCount == firstHuffPairCount )
			{
				//firstHuffPairCount += 1;
				int sum = rootCount + firstHuffPairCount;
				HuffmanPair toAddHuffPair = new HuffmanPair(starChr, sum);
				//@BinaryTreeInterface<HuffmanPair> copyOfLeftTree = tree;
				
				tempTree.setTree(toAddHuffPair, 
						tree, new HuffmanTree<HuffmanPair>(
								new HuffmanPair(list.getEntry(1).getCharacter(), 
												list.getEntry(1).getCount() ) ) );
	
			}

			list.remove(1); // remove first element.

					
			tree = tempTree;
		} // end while
		
		System.out.println("=========FIANL TREE===========");
		tree.inorderTraverse();
		System.out.println("==============================");
		
		System.out.println("\n...Generating HuffmanTree Complete!!");
		return tree;
		
	}
	
	public String encoding(String s, HuffmanTreeInterface<HuffmanPair> givenHuffTree)
	{
		//@System.out.println(s);
		
		String outputCode = "";
		
		givenHuffTree.reset();

		outputCode = makeCode(s, 0, outputTree);
		
		//@System.out.println(outputCode);
		System.out.println("...Encoding Complete!!");
		return outputCode;
	}
	
	private String makeCode(String s, int first, HuffmanTreeInterface<HuffmanPair> givenHuffTree )
	{
		String outputCode = "";
		
		/**
		 * @author yoonbumtae
		 * '*' to ascii(1)
		 */
		
		char starChr = (char) 1;
		
		for (int i = 0; i < s.length(); i++)
		{
			//boolean found = false;
			
			char x = s.charAt(i);
			
					
			givenHuffTree.reset();
			
			while (!givenHuffTree.isLeaf())
			{
				//@System.out.println("givenChar: " + x + ": " 
				//@		+ givenHuffTree.getLeftChildData().getCharacter()
				//@		+" "+ givenHuffTree.getRightChildData().getCharacter());
				
				// assert leftChild is leaf.
				if (givenHuffTree.getLeftChildData().getCharacter() != starChr)
				{
					if (givenHuffTree.getLeftChildData().getCharacter() == x)
					{
						outputCode = outputCode + 0;
						givenHuffTree.advanceToZero();
					}
					else
					{
						outputCode = outputCode + 1;
						givenHuffTree.advanceToOne();
					}
						
				}
				else if (givenHuffTree.getRightChildData().getCharacter() != '*')
				{
					if (givenHuffTree.getRightChildData().getCharacter() == x)
					{
						outputCode = outputCode + 1;
						givenHuffTree.advanceToOne();
					}
					else
					{
						outputCode = outputCode + 0;
						givenHuffTree.advanceToZero();
					}
				}
			}
			
			
			
		}
		
		return outputCode;
	}
	

	
	public byte[] stringBinaryToByte( String code )
	{
		// The regex (?<=\G.......) matches an empty string that has 
		// the last match (\G) followed by 7 characters (.......) 
		// before it ((?<= ))
		String[] strArray = code.split("(?<=\\G.......)");
		
		
		byte[] byteArray;

		byteArray = new byte[(code.length() / 7) + 1];

			
		
		for (int i = 0; i < strArray.length; i++)
		{
			byteArray[i] = Byte.parseByte(strArray[i], 2);
			System.out.println(byteArray[i] + " || " + strArray[i]);
			if ( (strArray.length - 1) == i)
			{
				int num = 1;
				for (int j = 0; j < strArray[i].length(); j++)
				{
					if ( strArray[i].charAt(j) == '1')
						break;
					else
						num++;
				}
				System.out.println( "num: " + num);
				toAppendZeroLastElement = num - 1;
				System.out.println(toAppendZeroLastElement);
			}
		}
		
		return byteArray;
	}
	
	public void codeToBits(String strCode, String filePath)
	{
		
	    BinaryOut out = new BinaryOut(filePath);
        
        out.write(code.length());
        
        for (int i = 0; i < strCode.length(); i++)
        {
        	//System.out.println("charAt: " + i + " " + strCode.charAt(i));
        	if (strCode.charAt(i) == '1')
        		out.write(true);
        	else if (strCode.charAt(i) == '0')
        		out.write(false);
        }
        
        /**
         * @author yoonbumtae
         * '$' to 2
         * '@' to 3
         */
        out.write((char)2);
        
        
        for (int i = 1; i <= copyList.getLength(); i++)
        {
        	out.write(copyList.getEntry(i).getCharacter());
        	out.write((short) copyList.getEntry(i).getCount());
        }
        
        
        out.write((char)3);
        out.write(0);	// ??
        out.flush();
        System.out.println("...File Export Complete!!");
	}
	


}
