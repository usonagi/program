package ExamJFrame;

import java.awt.event.*;
import javax.swing.*;

public interface MyListener extends ActionListener //ʵ��ActionListener�ӿ�
{
	public void setJTextField(JTextField text);
	public void setJTextArea(JTextArea area);
}