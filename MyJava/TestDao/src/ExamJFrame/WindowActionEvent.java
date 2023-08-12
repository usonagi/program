package ExamJFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowActionEvent extends JFrame{
	JTextField input;
	JTextArea show;
	JButton button;
	MyListener listener;
	
	public WindowActionEvent() {
		init();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void init() 
	{
		setLayout(new FlowLayout());
		input = new JTextField(10);
		button = new JButton("确定");
		show = new JTextArea(9,30);
		add(input);
		add(button);
		add(show);
	}
	
	void setMyListener(MyListener listener)
	{
		this.listener = listener;
		listener.setJTextField(input);
		listener.setJTextArea(show);
		// 注册监视器
		input.addActionListener(listener);  //input是事件源 listener是监视器
		button.addActionListener(listener); //button是事件源 listener是监视器
	}

	
}
