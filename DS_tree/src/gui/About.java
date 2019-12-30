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
			+ "HuffPad�� Tree Interface�� ������� ��\n"
			+ "Huffman Code ������ �����ϴ� �޸��� ���α׷��Դϴ�.\n"
			+ "��ҿ��� �Ϲ� �޸���ó�� ����ϴٰ� ���� �� Ȯ���ڸ� huff, huf, enc�� �����ϸ�\n"
			+ "Huffman Code ������� �ؽ�Ʈ�� �����մϴ�. \n"
			+ "Huffman Code�� �˰��� Ư�� �� ��� ������ ������ ������, \n"
			+ "Ư�� ���ڰ� ���������� �ݺ��ǰ�\n"
			+ "������ ũ�Ⱑ ����� ū(1KB) �ؽ�Ʈ���� ���� ȿ���� ���ֵ˴ϴ�.\n";

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
