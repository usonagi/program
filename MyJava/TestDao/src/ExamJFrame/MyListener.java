package ExamJFrame;

import java.awt.event.*;
import javax.swing.*;

public interface MyListener extends ActionListener //实现ActionListener接口
{
	public void setJTextField(JTextField text);
	public void setJTextArea(JTextArea area);
}