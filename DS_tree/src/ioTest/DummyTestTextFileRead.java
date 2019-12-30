package ioTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import sortedList.SortedLinkedList;
import sortedList.SortedListInterface;
import treePackage.HuffmanTreeInterface;

public class DummyTestTextFileRead {

	public static void main(String[] args) {
		
			
			FileReader fr = null;
			FileWriter fw = null;
			
			BufferedReader br = null;
			BufferedWriter bw = null;
			
			Date d = null;
			
			SortedListInterface<HuffmanPair> huffmanList = new SortedLinkedList<HuffmanPair>();
			
			try{
				
				// "ReadFile.txt" 파일을 읽는 FileReader 객체 생성
				// BufferedReader 객체 생성
				fr = new FileReader("homura.asc");
				br = new BufferedReader(fr);
				
				// FileWriter로 파일 "CopyFile.txt"에 출력한다. 기존 파일에 덮어쓴다.
				// BufferedWriter 객체 생성
				fw = new FileWriter("CopyFile.txt", false);
				bw = new BufferedWriter(fw);
				
				String s = null;
				String es = "";
				d = new Date();
				
				// 파일 복사 시작 시간
				long start = d.getTime();
				
				// ReadFile.txt 에서 한줄씩 읽어서 BufferedRaeder에 저장한다.
				while( (s = br.readLine()) != null){
					
					// 읽은 데이터(한줄)을 BufferedWriter에 쓴다.
					// 한줄씩 읽으므로, newLine() 메소드로 줄바꿈을 해준다.
					bw.write(s);
					bw.newLine();
					System.out.println(s);
					es = es + s + '\r';
				}
	
				// 복사 완료된 시간을 얻는다.
				d = new Date();
				long end = d.getTime();
				
				//s = "paul new era encounter to enchant fire endless love";
				
				System.out.println("복사 시간 : " + (end-start));
				
				/*char[] charList = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
						'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
						't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};
				
				
				for (int i = 0; i < charList.length; i++)
				{
					int n = 0;
					for (int j = 0; j < es.length(); j++)
					{
						if ( es.charAt(j) != charList[i] )
							continue;
						n++;
					}
					
					System.out.println(charList[i] + "의 갯수: " + n );
					huffmanList.add(new HuffmanPair(charList[i], n));
				}*/

				
				


				
				HuffmanEncoder encoder = new HuffmanEncoder();
				
				huffmanList = encoder.makeHuffmanList(es);
				Object[] display = huffmanList.toArray();
				
				for (int i = 0; i < display.length; i++)
				{
					System.out.println(display[i]);
				}	
				
				HuffmanTreeInterface<HuffmanPair> outputTree = 
										encoder.generateHuffmanTree(huffmanList);
				String code = encoder.encoding(es, outputTree);
				System.out.println("Huffman: " + code);
				
				HuffmanDecoder decoder = new HuffmanDecoder();
				
				String sentence = decoder.decoding(code, outputTree);
				System.out.println(sentence);
				
				System.out.println("number of code: " + code.length());

				
				byte byteOut = Byte.parseByte("1111111", 2);
				System.out.println(byteOut);
				
				// The regex (?<=\G.......) matches an empty string that has 
				// the last match (\G) followed by 7 characters (.......) 
				// before it ((?<= ))
				String[] strArray = "1111010111100001000".split("(?<=\\G.......)");
				
				byte[] byteArray = new byte["11110101111001001000".length() + 1];
				
				for (int i = 0; i < strArray.length; i++)
				{
					byteArray[i] = Byte.parseByte(strArray[i], 2);
					System.out.println(byteArray[i] + " || " + strArray[i]);
					if ( (strArray.length - 1) == i)
					{
						int toAppendZeroLastElement = 2 - 1;
						byteArray[i+1] = (byte) toAppendZeroLastElement;
					}
				}
				System.out.println(byteArray[3]);
				
		
				
				
				
				
			}catch(Exception e){
				
				e.printStackTrace();
			
			}finally{
				
				// BufferedReader FileReader를 닫아준다.
				if(br != null) try{br.close();}catch(IOException e){}
				if(fr != null) try{fr.close();}catch(IOException e){}
				
				// BufferedWriter FileWriter를 닫아준다.
				if(bw != null) try{bw.close();}catch(IOException e){}
				if(fw != null) try{fw.close();}catch(IOException e){}
				
			}
		
	}

}
