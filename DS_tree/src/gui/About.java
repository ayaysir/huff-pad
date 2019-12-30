package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class About extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String intro = "\n"
			+ "HuffPad는 Tree Interface를 기반으로 한\n"
			+ "Huffman Code 압축을 지원하는 메모장 프로그램입니다.\n"
			+ "평소에는 일반 메모장처럼 사용하다가 저장 시 확장자를 huff, huf, enc로 지정하면\n"
			+ "Huffman Code 방식으로 텍스트를 저장합니다. \n"
			+ "Huffman Code는 알고리즘 특성 상 사용 문자의 종류가 적으며, \n"
			+ "특정 문자가 지속적으로 반복되고\n"
			+ "파일의 크기가 충분히 큰(1KB) 텍스트에서 압축 효과가 발휘됩니다.\n";

	/**
	 * Create the dialog.
	 */
	public About() {
		setTitle("About HuffPad");
		setSize(563, 266);
		setLocationRelativeTo(getParent());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JTextArea textArea = new JTextArea(intro);
			textArea.setEditable(false);
			contentPanel.add(textArea, BorderLayout.CENTER);
		}
		{
			JLabel lblNewLabel = new JLabel("About HuffPad...");
			lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		try {			
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
