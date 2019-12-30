package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import ioTest.HuffmanDecoder;
import ioTest.HuffmanEncoder;

public class TextMan {
	
	private String s = "";

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}
	
	public String asciiReader(String filePath) {
		try(FileInputStream fis = new FileInputStream(filePath);
				BufferedReader br = new BufferedReader(new InputStreamReader((fis), "EUC-KR"));){
			s = "";
			String line;
			while( (line = br.readLine()) != null )
				s += (line+"\r\n");			
			
		} catch(Exception e) {}		
		
		// 마지막 개행은 제거하여 출력
		return s.substring(0, s.length()-1);
	}
	
	public String huffReader(String filePath) throws Exception {
		HuffmanDecoder decoder = new HuffmanDecoder();
		return decoder.run(filePath);		
		
	
		
	}
	
	/**
	 * 정상적으로 종료하면 boolean 반환
	 * @param text
	 * @param filePath
	 * @return
	 */
			
	public boolean asciiWriter(String text, String filePath) {
		try
		{
			BufferedWriter fw = new BufferedWriter(new FileWriter(filePath));
			// 파일라이터의 true: 이어쓰기, false: 이어쓰지 않음
			System.out.println(text);
			fw.write(text);
			fw.flush();
			fw.close();
			return true;
			
		}catch(Exception e) {System.err.println(e);	return false;} 
			}
	
	public boolean huffWriter(String text, String filePath) {		
		
		try	{			
			HuffmanEncoder encoder = new HuffmanEncoder();
			encoder.run(text, filePath);
	
			return true;
			
		}catch(Exception e) {return false;} 
		
		
	}
	
	
}
