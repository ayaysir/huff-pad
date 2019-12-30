package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TextReplace extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	
	private JCheckBox chkCaseSensitive = new JCheckBox("��/�ҹ��� ����");
	
	// ��𼭺��� ã�ƾߵǳ�?
	private int toStart = 0;
	private JTextField toReplaceField;
	
	private boolean isFinished = false;

	/**
	 * Create the dialog.
	 */
	public TextReplace(JTextArea textArea) {
		
		setTitle("Replace");
		setResizable(false);
		setSize(388, 168);
		setLocationRelativeTo(textArea);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ã�� ����: ");
		lblNewLabel.setBounds(12, 13, 71, 15);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(72, 10, 270, 21);
		contentPanel.add(textField);
		
		textField.setColumns(10);
		{
			chkCaseSensitive.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					toStart = 0;	// ��ҹ��� �ɼ��� �߰��� �����Ұ�� ó������ �ٽ�
				}
			});
			
			chkCaseSensitive.setBounds(12, 73, 115, 23);
			contentPanel.add(chkCaseSensitive);
		}
		{
			JLabel label = new JLabel("\uBC14\uAFC0 \uB0B4\uC6A9: ");
			label.setBounds(12, 41, 71, 15);
			contentPanel.add(label);
		}
		{
			toReplaceField = new JTextField();
			toReplaceField.setColumns(10);
			toReplaceField.setBounds(72, 38, 270, 21);
			contentPanel.add(toReplaceField);
		}
		
		showWindow(textArea);
		
	}
	
	public void showWindow(JTextArea textArea) {		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton nextButton = new JButton("���� ã��");
				nextButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {		
						
						nextFind(textArea);
						
					}
				});
				nextButton.setActionCommand("NEXT");
				buttonPane.add(nextButton);
				getRootPane().setDefaultButton(nextButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				{
					JButton button = new JButton("�ٲٱ�");
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(toReplaceField.getText().equals(""))
								JOptionPane.showMessageDialog(null, "�ٲ� ������ ���� �Է����ּ���." , "Error", JOptionPane.ERROR_MESSAGE);
							else if(textField.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "ã�� ������ ���� �Է����ּ���." , "Error", JOptionPane.ERROR_MESSAGE);
							}
							
							int txtStart = textArea.getSelectionStart();
							int txtEnd = textArea.getSelectionEnd();
							if(txtStart != txtEnd)
								textArea.replaceRange(toReplaceField.getText(), txtStart, txtEnd);
							
							nextFind(textArea);
							
						}
					});
					button.setActionCommand("REPLACE");
					buttonPane.add(button);
				}
				{
					JButton button = new JButton("��� �ٲٱ�");
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							if(isFinished)	isFinished = false;
							
							while(!isFinished) {
								nextFind(textArea);
								int txtStart = textArea.getSelectionStart();
								int txtEnd = textArea.getSelectionEnd();
								textArea.replaceRange(toReplaceField.getText(), txtStart, txtEnd);
							}
							
						}
					});
					button.setActionCommand("REPLACEALL");
					buttonPane.add(button);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
			
		}
		
		setVisible(true);
	}
	
	public void nextFind(JTextArea textArea) {
		
		int start;
		
		if(chkCaseSensitive.isSelected()) {
			System.out.println(chkCaseSensitive.isSelected());
			start = textArea.getText().substring(toStart)
					.indexOf(textField.getText());		
			System.out.println(start);
		} else {
			start = textArea.getText().toUpperCase().substring(toStart)
				.indexOf(textField.getText().toUpperCase());		
		}
		int end = start + textField.getText().length();

		if(start != -1) {							
			textArea.setSelectionStart(toStart + start);
			textArea.setSelectionEnd(toStart + end);
			toStart = toStart + end;
		} else {
			JOptionPane.showMessageDialog(null, "�� �̻� ã��/�ٲ� ������ �����ϴ�." , "Error", JOptionPane.ERROR_MESSAGE);
			toStart = 0;	// ó������ �ٽ� ����
			isFinished = true;
		}
	}
}
