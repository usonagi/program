package ExamJFrame;

import java.awt.event.*;
import javax.swing.*;

public class PoliceListen implements MyListener { //���𴴽����������� ʵ�ֽӿ�
	JTextField input;
	JTextArea show;
	
	public void setJTextField(JTextField text)
	{
		input = text;
	}
	
	public void setJTextArea(JTextArea area)
	{
		show = area;
	}

	public void actionPerformed(ActionEvent e) // ʵ��ActionListener�еķ���
	{
		String str = input.getText();
		show.append(str+"�ĳ���:"+str.length()+"\n");
	}
	
}
