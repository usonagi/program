package ExamJFrame;

/******* 嵌套盒式布局容器 **********/

import javax.swing.*;

public class WindowBoxLayout extends JFrame{
	Box boxH;
	Box boxVone,boxVtow;
	
	public WindowBoxLayout() {
		setLayout(new java.awt.FlowLayout());
		init();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	void init() {
		boxH = Box.createHorizontalBox();
		boxVone = Box.createVerticalBox();
		boxVtow = Box.createVerticalBox();
		boxVone.add(new JLabel("姓名:"));
		boxVone.add(new JLabel("职业:"));
		boxVtow.add(new JTextField(10));
		boxVtow.add(new JTextField(10));
		boxH.add(boxVone);
		boxH.add(Box.createHorizontalStrut(10));
		boxH.add(boxVtow);
		add(boxH);
	}

}
