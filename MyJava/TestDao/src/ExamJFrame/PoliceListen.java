package ExamJFrame;

import java.awt.event.*;
import javax.swing.*;

public class PoliceListen implements MyListener { //负责创建监视器的类 实现接口
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

	public void actionPerformed(ActionEvent e) // 实现ActionListener中的方法
	{
		String str = input.getText();
		show.append(str+"的长度:"+str.length()+"\n");
	}
	
}
