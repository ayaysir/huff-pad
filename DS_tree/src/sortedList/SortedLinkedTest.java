package sortedList;

public class SortedLinkedTest {

	public static void main(String[] args) {
		SortedListInterface<Double> list = new SortedLinkedList<Double>();
		
		for (int i = 1; i < 100; i++)
		{
			list.add( (Math.random() * 100) + 1 );
		}
		
		Object[] display = list.toArray();
		

		for (int i = 0; i < display.length; i++)
		{
			System.out.println(display[i]);
		}

	}

}
