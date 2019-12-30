package ioTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HuffmanClient {
	
	private static FileReader fileReader = null;
	private static FileWriter fileWriter = null;
	
	private static BufferedReader bufferReader = null;
	private static BufferedWriter bufferedWriter = null;

	public static void main(String[] args) {
		
		userInterface();		
	}
	
	public static void userInterface()
	{
		System.out.println( "Choose one that you want: " );
		System.out.println( "- input number 1 ~ 3 only -" );
		System.out.println( "1. Encoding the given text file " );
		System.out.println( "2. Decoding the encoded file and view original text " );
		System.out.println( "3. Decoding the encoded file and restore to file" );
		
		Scanner scanner = new Scanner(System.in);
		int choose = scanner.nextInt();
		
		if ( choose == 1 )
		{
			System.out.println( "You choose menu 1. " );
			importTextFile();
		}
		else if ( choose == 2 )
		{
			System.out.println( "You choose menu 2. " );
			exportTextView();
		}
		else if ( choose == 3 )
		{
			System.out.println( "You choose menu 3. " );
			restoreTextFile();
		}
		else
		{
			System.out.println( "Wrong number. Please re-enter correct number. \n" );
			userInterface();
		}
		
		scanner.close();
	}
	
	private static void importTextFile()
	{

		Scanner scanner = new Scanner(System.in);
		
		System.out.println( "Input text file name (include extension): " );
		String givenFile = scanner.next();
		
		System.out.println( "\n=============" + givenFile + "===========" );
		
		
		try
		{
			fileReader = new FileReader( givenFile );
			bufferReader = new BufferedReader( fileReader );			
			
			String s = null;
			String exportStr = "";
			
			// from ReadFile.txt, read one line each. save to BufferedRaeder
			while( (s = bufferReader.readLine()) != null)
			{
				System.out.println(s);
				exportStr = exportStr + s + '\n'; // include 'carriage return'
			}
			
			//@System.out.println(exportStr);
			
			System.out.println("==================================\n");
			
			System.out.println( "Input export file name (include extension): ");
			
			scanner = new Scanner(System.in);
			String filePath = scanner.next();
			
			HuffmanEncoder encoder = new HuffmanEncoder();
			encoder.run(exportStr, filePath);
			
			scanner.close();

		} // end try
		catch(Exception e){
			
			e.printStackTrace();
		
		}finally{
			
			// close BufferedReader and FileReader.
			if(bufferReader != null) try{bufferReader.close();}catch(IOException e){}
			if(fileReader != null) try{fileReader.close();}catch(IOException e){}
			
			// close BufferedWriter and FileWriter.
			//@if(bw != null) try{bw.close();}catch(IOException e){}
			//@if(fw != null) try{fw.close();}catch(IOException e){}
			
		} // end finally
	} // end importTextFile
	
	private static void exportTextView()
	{
		try {
			Scanner scanner = new Scanner(System.in);
			
			System.out.println( "Input encoded file name (include extension): " );
			String givenFile = scanner.next();	
			
			HuffmanDecoder decoder = new HuffmanDecoder();
			String decodedText = decoder.run(givenFile);
			
			System.out.println( "\n=============" + givenFile + "===========" );
			System.out.println(decodedText);
			System.out.println("==================================\n");
			
			scanner.close();
			
		} catch(Exception e) {}
		
	}
	
	private static void restoreTextFile()
	{
		
		try{
			Scanner scanner = new Scanner(System.in);
			
			System.out.println( "Input encoded file name (include extension): " );
			String givenFile = scanner.next();	
			
			HuffmanDecoder decoder = new HuffmanDecoder();
			String decodedText = decoder.run(givenFile);
			
			System.out.println( "Input want to restored file name (include extension): " );
			String givenOutputFile = scanner.next();
			
			scanner.close();
			
			fileWriter = new FileWriter(givenOutputFile);
			bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write(decodedText);
			
			System.out.println( "... generate restore file Complete!!" );
			
			bufferedWriter.flush();
			bufferedWriter.close();
			fileWriter.close();
			
		} catch(IOException ioe){ioe.printStackTrace();}
		catch(Exception e) {e.printStackTrace();}

	
	}

}
