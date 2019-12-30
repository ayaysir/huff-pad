package ioTest;

import sortedList.SortedLinkedList;
import sortedList.SortedListInterface;
import treePackage.HuffmanTreeInterface;

public class HuffmanDecoder {
	
	private String afterCode = "";
	private SortedListInterface<HuffmanPair> afterList;
	private HuffmanTreeInterface<HuffmanPair> afterTree;
	
	public String run(String fileName) throws Exception
	{
//		try {
			decodeFile(fileName);
			HuffmanEncoder encoder = new HuffmanEncoder();
			afterTree = encoder.generateHuffmanTree(afterList);
			String restoredText = decoding(afterCode, afterTree);
			return restoredText;
//		}catch(Exception e) {e.printStackTrace();	return "";}

	}
	
	private void decodeFile(String fileName) throws Exception
	{
//		try {
			BinaryIn in = new BinaryIn(fileName);

			int length = in.readInt();
			System.out.println("length: " + length);

			for (int i = 1; i <= length; i++)
			{

				if (in.readBoolean())
					afterCode = afterCode + "1";
				else
					afterCode = afterCode + "0";
			}
			System.out.println(in.readChar());

			afterList = new SortedLinkedList<HuffmanPair>();
			boolean progress = true;
			while(progress)
			{
				char ch = in.readChar();
				int it = (int) in.readShort();
				HuffmanPair tempHuffPair;
				if (ch != (char)1)
				{
					tempHuffPair = new HuffmanPair(ch, it);
					afterList.add(tempHuffPair);
				}

				if (ch == (char)3)
				{
					System.out.println(ch);
					progress = false;
				}

			} // end while
			// 예외 걸림 (2018 04 17)
//		} catch(Exception e) {System.out.println("예외"); e.printStackTrace();}


	}
	
	public String decoding(String huffmanCode, HuffmanTreeInterface<HuffmanPair> tree )
	{
		//@System.out.println( huffmanCode );
		//@System.out.println( huffmanCode.charAt(0));
		
		tree.reset();
		String decodeSentence = "";
		
		//boolean isLeaf = false;
		for (int i = 0; i < huffmanCode.length(); i++)
		{
			//@System.out.println("charAt: " + huffmanCode.charAt(i));
			char currentCharAt = huffmanCode.charAt(i);
			
			//System.out.println("pair: " + (huffmanCode.charAt(i) == 0));
			
			if( currentCharAt == '0' )
			{
				tree.advanceToZero();
				//@System.out.println("isLeaf?: " + tree.isLeaf());
				
				if (tree.isLeaf())
				{
					decodeSentence = decodeSentence + tree.getCurrentData().getCharacter();

					//isLeaf = tree.isLeaf();
					tree.reset();
				}

			}
			else if ( currentCharAt == '1')
			{
				tree.advanceToOne();
				
				if (tree.isLeaf())
				{
					decodeSentence = decodeSentence + tree.getCurrentData().getCharacter();

					//isLeaf = tree.isLeaf();
					tree.reset();
				}

			}
		}

		System.out.println( "Huffman decode complete!!" );
		
		return decodeSentence;
	}
	
	

	}
