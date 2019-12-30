package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

import exPackage.FontChooser;

public class HuffPadClient extends JFrame {
	
	static final long serialVersionUID = 33994122;

	private JPanel contentPane;
	private static HuffPadClient frame = new HuffPadClient();
	private TextMan tm = new TextMan();
	private String currentOpenedFilePath = "";
	private String refText = "";
	
	// 새 파일, 저장 관련
	private boolean isNewFile = true;
	private boolean isSaved = false;
	private boolean isDialogOptionCanceled = false;
	
	// 폰트 관련
	final FontChooser fc = new FontChooser(frame);
	
	// 찾기 관련
	private TextFind tf = null;
	
	// Undo 관련
	protected UndoManager undoManager = new UndoManager();
	
//	private final String EXTENSIONS = "([^\\s]+(\\.(?i)(huff|huf|enc))$)";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HuffPadClient() {
		
		setTitle("HuffPad");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New file");
		mntmNewMenuItem.setMnemonic(KeyEvent.VK_N);
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
		
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Open...");
		mntmNewMenuItem_1.setMnemonic(KeyEvent.VK_O);
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");	
		mntmNewMenuItem_2.setMnemonic(KeyEvent.VK_S);
		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Save as...");
		mntmNewMenuItem_3.setMnemonic(KeyEvent.VK_A);
		mnNewMenu.add(mntmNewMenuItem_3);
		
		// 구분 경계선 넣기
		mnNewMenu.addSeparator();
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Print...");
		
		mntmNewMenuItem_5.setMnemonic(KeyEvent.VK_P);
		mntmNewMenuItem_5.setAccelerator(KeyStroke.getKeyStroke('P', Event.CTRL_MASK));
		mnNewMenu.add(mntmNewMenuItem_5);
		
		mnNewMenu.addSeparator();
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Exit");
		mntmNewMenuItem_6.setMnemonic(KeyEvent.VK_X);
		mnNewMenu.add(mntmNewMenuItem_6);
		
		// edit 메뉴
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setMnemonic(KeyEvent.VK_E);
		menuBar.add(mnEdit);
		
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mntmUndo.setEnabled(false);
		mnEdit.add(mntmUndo);
		
		JMenuItem mntmRedo = new JMenuItem("Redo");
		mntmRedo.setEnabled(false);
		mnEdit.add(mntmRedo);
		
		mnEdit.addSeparator();
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mntmCut.setMnemonic(KeyEvent.VK_T);
		mnEdit.add(mntmCut);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Copy");
		mntmNewMenuItem_11.setMnemonic(KeyEvent.VK_C);
		
		mnEdit.add(mntmNewMenuItem_11);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Paste");
		mntmNewMenuItem_7.setMnemonic(KeyEvent.VK_P);
		
		mnEdit.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Delete");
		mntmNewMenuItem_8.setMnemonic(KeyEvent.VK_D);
		mnEdit.add(mntmNewMenuItem_8);
		
		mnEdit.addSeparator();
		
		JMenuItem mntmFind = new JMenuItem("Find...");
		mntmFind.setMnemonic(KeyEvent.VK_F);
		mntmFind.setAccelerator(KeyStroke.getKeyStroke('F', Event.CTRL_MASK));
		mnEdit.add(mntmFind);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Next Find");
		mntmNewMenuItem_9.setMnemonic(KeyEvent.VK_F);
		mntmNewMenuItem_9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		// f3 입력
		mnEdit.add(mntmNewMenuItem_9);
		
		JMenuItem mntmReplace = new JMenuItem("Replace...");
		mntmReplace.setMnemonic(KeyEvent.VK_R);
		
		mnEdit.add(mntmReplace);
		
		mnEdit.addSeparator();
		
		JMenuItem mntmSelectAll = new JMenuItem("Select All");
		mntmSelectAll.setMnemonic(KeyEvent.VK_A);
		
		mnEdit.add(mntmSelectAll);
		
		JMenuItem mntmInsertTimedate = new JMenuItem("Insert Time/Date");
		mntmInsertTimedate.setMnemonic(KeyEvent.VK_D);
		mntmInsertTimedate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mnEdit.add(mntmInsertTimedate);
		
		// form 메뉴
		
		JMenu mnNewMenu_1 = new JMenu("Form");
		mnNewMenu_1.setMnemonic(KeyEvent.VK_O);
		menuBar.add(mnNewMenu_1);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Auto line wrap");
		chckbxmntmNewCheckItem.setMnemonic(KeyEvent.VK_W);
	
		mnNewMenu_1.add(chckbxmntmNewCheckItem);
		
		JMenuItem mntmSetFont = new JMenuItem("Set Font...");
		mntmSetFont.setMnemonic(KeyEvent.VK_F);
		mnNewMenu_1.add(mntmSetFont);
		
		// help 메뉴
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setMnemonic(KeyEvent.VK_H);
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About Huffpad...");
		mntmAbout.setMnemonic(KeyEvent.VK_A);
		
		mnHelp.add(mntmAbout);
		
		UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Color.red, 4));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel statusLabel = new JLabel("줄 수: " + textArea.getLineCount() 
		                      + "       " + "글자 수: " + textArea.getText().length()  );
		statusLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(statusLabel, BorderLayout.SOUTH);
		
		// event: 자동 줄 바꿈
		chckbxmntmNewCheckItem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {				
				textArea.setLineWrap(chckbxmntmNewCheckItem.getState());
				
			}
		});
		
		// event: 파일 열기
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog(frame, "Open file...", FileDialog.LOAD);
				fd.setVisible(true);
				
				// 파일 열기 전에도 경고창이 나와야 함
				
				if(fd.getDirectory() != null && fd.getFile() != null) {
					currentOpenedFilePath = fd.getDirectory() + fd.getFile();
					setTitle("HuffPad: " + currentOpenedFilePath);
				}
				
				String ext = currentOpenedFilePath.substring(
						currentOpenedFilePath.lastIndexOf(".") + 1, 
						currentOpenedFilePath.length());
				System.out.println("ext: " + ext);
				
				// huffman encoded 파일 열기
				try {
					if(ext.equals("huff") || ext.equals("huf") || ext.equals("enc") )
						textArea.setText(tm.huffReader(currentOpenedFilePath));
					else
						textArea.setText(tm.asciiReader(currentOpenedFilePath));
				} catch(Exception e) {
					System.out.println("일반 열기");
					textArea.setText(tm.asciiReader(currentOpenedFilePath));
				} 			
				
				isNewFile = false;
				refText = textArea.getText();	// 비교용 텍스트 저장
			}
		});
						
		// event(클래스): 새로 저장하기
		ActionListener saveAs = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveAs(textArea);
				
			}
		};
		
		// 파일 저장 다이얼로그 (저장, 저장 안함, 취소)
			
		
		// event: 다른 이름으로 저장
		mntmNewMenuItem_3.addActionListener(saveAs);
		
		// event: 새 파일 만들기
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * 그냥 바로 새 파일을 만든다.
				 * 1. 프로그램이 처음 시작되고 나서 단 한글자도 변경되지 않은 경우
				 * 2. 저장을 마치고 나서 단 한글자도 변경되지 않은 경우
				 * 3. 파일을 열고 나서 단 한글자도 변경되지 않은 경우	
				 */	
				
				if(isNewFile && textArea.getText().equals("")) {
					textArea.setText("");
					System.out.println("new");
				}
				
				else if(refText.equals(textArea.getText())) {
					textArea.setText("");
					System.out.println("new");
				}
				else {
					System.out.println("case");
					saveDialog(textArea);
					textArea.setText("");
				}
					
				/*
				 * 저장용 경고창을 띄우고 '예'를 선택해서 일련의 과정을 진행 후 
				 * 또는 '아니오'를 선택했을 경우 바로 새 파일
				 * 취소를 선택할 경우 아무것도 바뀌지 않아야 함
				 *
				 * 1. 프로그램이 처음 시작되고 나서 글자가 변경된 경우
				 * 2. 저장을 마치고 나서 글자가 변경된 경우
				 * 3. 파일을 열고 나서 글자가 변경된 경우
				 */
				
				setTitle("HuffPad");
				isNewFile = true;
			}

		});
		
		// event: 저장하기		
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * 1. 새 파일 중 아무것도 없는 파일, 새 파일의 내용이 변경됨: 새로 저장하기
				 * 2. 기존 편집 파일: 저장(그냥)
				 */
				
				if (isNewFile) {
					saveAs.actionPerformed(e);
				}
				else {
					String ext = currentOpenedFilePath.substring(
							currentOpenedFilePath.lastIndexOf(".") + 1, 
							currentOpenedFilePath.length());
					
					// =====저장 부분=====
					if(	ext.toLowerCase().equals("huff") || 
							ext.toLowerCase().equals("huf") || 
							ext.toLowerCase().equals("enc") ) {
						// 아스키코드만 포함했는지 판별하는 정규식
						if(textArea.getText().matches("^[\\u0000-\\u007F]*$")) {
							isSaved = tm.huffWriter(textArea.getText(), currentOpenedFilePath);
							System.out.println("11");
						}						
						else {
							JOptionPane.showMessageDialog(frame, "Huffman Tree를 사용한 압축 지원은 ASCII 코드만 지원합니다."+
						"\n일반 텍스트 형식으로 저장합니다.", "경고", JOptionPane.ERROR_MESSAGE);
							isSaved = tm.asciiWriter(textArea.getText(), currentOpenedFilePath);
							System.out.println(isSaved);
						}
							
					}					
					else
						isSaved = tm.asciiWriter(textArea.getText(), currentOpenedFilePath);	
					// ================	
				}

			}
		});	
		
		
		
		// event: 끝내기			
		ActionListener closeApp = new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				exitApp(textArea);
			}
		};
		
		mntmNewMenuItem_6.addActionListener(closeApp);
		
		// 종료 버튼도 동일하게
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {				
				exitApp(textArea);
			}
		});
		
		// event: 시간 삽입
		mntmInsertTimedate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append(new Date(System.currentTimeMillis()).toString());
			}
		});
		
		// event: 전체 선택
		mntmSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.selectAll();
			}
		});
		
		// event(텍스트): 상태 반영
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				statusLabel.setText("줄 수: " + textArea.getLineCount() 
                + "       " + "글자 수: " + textArea.getText().length()  );
			}
		});
		
		// event: 폰트 설정
		mntmSetFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.setVisible(true);
		        Font newFont = fc.getSelectedFont();
		        System.out.println("You chose " + newFont);
		        textArea.setFont(newFont);
		        fc.dispose();
			}
		});
		
		// event: 복사(마우스)
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				String copyString = textArea.getSelectedText();
				if(copyString != null)
				{
				     StringSelection contents = new StringSelection(copyString);
				     clipboard.setContents(contents, null);
				}
			}
		});
		
		// event: 잘라내기(마우스)
		mntmCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				String copyString = textArea.getSelectedText();
				if(copyString != null)
				{
				     StringSelection contents = new StringSelection(copyString);
				     clipboard.setContents(contents, null);
				}
				int txtStart = textArea.getSelectionStart();
				int txtEnd = textArea.getSelectionEnd();
				textArea.replaceRange("", txtStart, txtEnd);
			}
		});
		
		// event: 붙여넣기(마우스)
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable contents = clipboard.getContents(clipboard);

				if(contents != null)
				{

				     try {
				          String pasteString = (String)(contents.getTransferData(
				DataFlavor.stringFlavor));
				          textArea.insert(pasteString, textArea.getCaretPosition());
				     } catch (Exception ex) {}

				}
				
			}
		});
		
		// event: 선택 영역 삭제
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int txtStart = textArea.getSelectionStart();
				int txtEnd = textArea.getSelectionEnd();
				textArea.replaceRange("", txtStart, txtEnd);
			}
		});
		
		// event: about HuffPad
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new About();
				
			}
		});
		
		
		// event: 찾기
		mntmFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println(textArea.getSelectedText());
				if(textArea.getSelectedText() == null)
					tf = null;
				
				if(tf == null) {
					tf = new TextFind(textArea); 
				}
				else
					tf.showWindow(textArea);
			}
		});
		
		// event: 다음 찾기
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textArea.getSelectedText() == null)
					tf = null;
				
				if(tf == null) {
					tf = new TextFind(textArea);
				}
				else 
					tf.nextFind(textArea);
					
			}					

		});
		
		// event: 바꾸기
		mntmReplace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TextReplace(textArea);
			}
		});
		
		// event: undo
		textArea.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					public void undoableEditHappened(UndoableEditEvent e) {
						undoManager.addEdit(e.getEdit());
						mntmUndo.setEnabled(undoManager.canUndo());
						mntmRedo.setEnabled(undoManager.canRedo());
					}
				});
		
		
		mntmUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					undoManager.undo();
					mntmUndo.setEnabled(undoManager.canUndo());
					mntmRedo.setEnabled(undoManager.canRedo());
				} catch (CannotRedoException cre) {
					cre.printStackTrace();
				}
			}
		});
		
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke('Z', Event.CTRL_MASK));

		
		
		mntmRedo.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        try {
		          undoManager.redo();
		        } catch (CannotRedoException cre) {
		          cre.printStackTrace();
		        }
		        mntmUndo.setEnabled(undoManager.canUndo());
				mntmRedo.setEnabled(undoManager.canRedo());
		      }
		    });
		
		mntmRedo.setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));
		
		
		mntmReplace.setAccelerator(KeyStroke.getKeyStroke('L', Event.CTRL_MASK));
		
		// event: print
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean isPrinted = textArea.print();
					if(isPrinted)
						JOptionPane.showMessageDialog(frame, "인쇄 성공", "Print", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(frame, "인쇄 실패", "Print", JOptionPane.ERROR_MESSAGE);
				} catch(Exception e) {}
			}
		});


	}	// end constructor
	
	// ==== 메소드 부분 ====
	  
	
	public void exitApp(JTextArea textArea) {
		/*
		 * 1. 새 파일의 내용이 아무것도 없는 경우, 열린 파일이 전혀 변경되지 않은 경우, 
		 *              열린 파일을 저장한 상태에서 1도 변경하지 않은 경우: 그대로 끝내기
		 * 2. 열린 파일이 저장되지 않은 경우 종료하려 할 떄: 저장 여부 문의창 띄우기
		 * 3. 
		 */
		
		if(!isSaved && !refText.equals(textArea.getText())) {
			saveDialog(textArea);						
			if(isDialogOptionCanceled == true) {
				isDialogOptionCanceled = false;
				return;
			}
			System.exit(0);
		} else {
			System.exit(0);
		}

	}
	
	public void saveDialog(JTextArea textArea) {
		int select = JOptionPane.showConfirmDialog(frame, "변경 내용을 저장하시겠습니까?", "HuffPad", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if(select == JOptionPane.YES_OPTION) {
//			mntmNewMenuItem_3.addActionListener(saveAs);
			if(isNewFile) {
				saveAs(textArea);
			} else {
				String ext = currentOpenedFilePath.substring(
						currentOpenedFilePath.lastIndexOf(".") + 1, 
						currentOpenedFilePath.length());
				
				// =====저장 부분=====
				if(	ext.toLowerCase().equals("huff") 
						|| ext.toLowerCase().equals("huf") 
						|| ext.toLowerCase().equals("enc") ) {
					// 아스키코드만 포함했는지 판별하는 정규식
					if(textArea.getText().matches("^[\\u0000-\\u007F]*$")) {
						isSaved = tm.huffWriter(textArea.getText(), currentOpenedFilePath);
						System.out.println("11");
					}						
					else {
						JOptionPane.showMessageDialog(frame, "Huffman Tree를 사용한 압축 지원은 ASCII 코드만 지원합니다."+
					"\n일반 텍스트 형식으로 저장합니다.", "경고", JOptionPane.ERROR_MESSAGE);
						isSaved = tm.asciiWriter(textArea.getText(), currentOpenedFilePath);
						System.out.println(isSaved);
					}
						
				}					
				else
					isSaved = tm.asciiWriter(textArea.getText(), currentOpenedFilePath);	
				// ================
			}
			
		}
		else if(select == JOptionPane.NO_OPTION) {
			System.out.println("아니오 선택");
			return;
		}
		else if(select == JOptionPane.CANCEL_OPTION) {
			System.out.println("취소 선택");
			isDialogOptionCanceled = true;
		}
	}
	
	public void saveAs(JTextArea textArea) {
		FileDialog fd = new FileDialog(frame, "Save file as...", FileDialog.SAVE);
		fd.setVisible(true);
		if(fd.getFile() == null) {
			isDialogOptionCanceled = true;
			return;
		}
		String dfName = fd.getDirectory() + fd.getFile();
		System.out.println(dfName);
		refText = textArea.getText();	// 비교용 텍스트 저장
		
		String ext = currentOpenedFilePath.substring(
				currentOpenedFilePath.lastIndexOf(".") + 1, 
				currentOpenedFilePath.length());
		
		String defExt = dfName.substring(
				dfName.lastIndexOf(".") + 1, 
				dfName.length());
		
		System.out.println(ext + " " + defExt);
		
		// =====저장 부분=====
		if(	defExt.toLowerCase().equals("huff") || 
				defExt.toLowerCase().equals("huf") || 
				defExt.toLowerCase().equals("enc") ) {
			// 아스키코드만 포함했는지 판별하는 정규식
			if(textArea.getText().matches("^[\\u0000-\\u007F]*$")) {
				isSaved = tm.huffWriter(textArea.getText(), dfName);
				System.out.println("11");
			}						
			else {
				JOptionPane.showMessageDialog(frame, "Huffman Tree를 사용한 압축 지원은 ASCII 코드만 지원합니다."+
			"\n일반 텍스트 형식으로 저장합니다.", "경고", JOptionPane.ERROR_MESSAGE);
				isSaved = tm.asciiWriter(textArea.getText(), dfName);
				System.out.println(isSaved);
			}
				
		}					
		else
			isSaved = tm.asciiWriter(textArea.getText(), dfName);	
		// ================
		
		
		isNewFile = false;
		System.out.println("dfName: " + dfName);
		setTitle("HuffPad: " + dfName);
	}

}
