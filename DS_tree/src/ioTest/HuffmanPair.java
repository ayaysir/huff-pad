package ioTest;

public class HuffmanPair implements Comparable<HuffmanPair> {
	
	private char character;
	private int count;
	
	public HuffmanPair(char character, int count)
	{
		this.character = character;
		this.count = count;
	}
	
	public void setCharacter(char character)
	{
		this.character = character;
	}

	public void setCount(int count)
	{
		this.count = count;
	}
	
	public char getCharacter()
	{
		return this.character;
	}
	
	public int getCount()
	{
		return this.count;
	}
	
	public int compareTo(HuffmanPair givenPair)
	{
		int result;
		
		if ( getCount() > givenPair.getCount())
			result = 1;
		else if ( getCount() == givenPair.getCount() && getCharacter() > givenPair.getCharacter() )
			result = 1;
		else if ( getCount() == givenPair.getCount() && getCharacter() < givenPair.getCharacter() )
			result = -1;
		else
			result = -1;
		
		return result;
	}
	
	public String toString()
	{
		String a = "Character: " + getCharacter() + " || ";
		String b = "Count: " + getCount();
		
		return a + b;
	}

}
