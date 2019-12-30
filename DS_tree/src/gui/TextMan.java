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
		
		// ������ ������ �����Ͽ� ���
		return s.substring(0, s.length()-1);
	}
	
	public String huffReader(String filePath) throws Exception {
		HuffmanDecoder decoder = new HuffmanDecoder();
		return decoder.run(filePath);		
		
	
		
	}
	
	/**
	 * ���������� �����ϸ� boolean ��ȯ
	 * @param text
	 * @param filePath
	 * @return
	 */
			
	public boolean asciiWriter(String text, String filePath) {
		try
		{
			BufferedWriter fw = new BufferedWriter(new FileWriter(filePath));
			// ���϶������� true: �̾��, false: �̾�� ����
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
