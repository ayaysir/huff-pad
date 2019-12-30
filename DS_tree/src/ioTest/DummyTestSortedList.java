package ioTest;
import sortedList.SortedLinkedList;
import sortedList.SortedListInterface;

public class DummyTestSortedList {

	public static void main(String[] args) {
		
		SortedListInterface<HuffmanPair> list = new SortedLinkedList<HuffmanPair>();
		
		
		list.add(new HuffmanPair('A', 15));
		list.add(new HuffmanPair('B', 19));
		list.add(new HuffmanPair('C', 115));
		list.add(new HuffmanPair('D', 24));
		list.add(new HuffmanPair('E', 165));
		list.add(new HuffmanPair('F', 1));
		list.add(new HuffmanPair(' ', 14));
		
		Object[] display = list.toArray();
		

		for (int i = 0; i < display.length; i++)
		{
			System.out.println(display[i]);
		}
		
		byte[] byteArray = { 127, 1, 0, 1, 0, 1, 0, 1};
		for ( int i = 0; i < byteArray.length; i++ )
		{
			System.out.println( byteArray[i] );
		}
		System.out.println("=========================");
		BinaryIn in = new BinaryIn("test1010.txt");
		
		int length = in.readInt();
		System.out.println(length);
		
		for (int i = 1; i <= length; i++)
		{
			System.out.print(i+ ":" + in.readBoolean() + " ");
			if (i % 10 == 0)
				System.out.println();
		}
		System.out.println(in.readChar());
		
			boolean progress = true;
			while(progress)
			{
				char ch = in.readChar();
				int it = in.readInt();
				System.out.println(ch);
				System.out.println(it);

				if (ch == '@')
				{
					progress = false;
				}
			}
	
		if ('A' < 'B')
			System.out.println(true);
		else
			System.out.println(false);
	}

}
