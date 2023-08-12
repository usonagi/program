package ExamJFrame;

import java.awt.*;
import javax.swing.*;

/* 空布局
 * null 空布局可以准确地定位组件在容器中的位置和大小
 * 向空布局添加组件 容器p使用add(c)添加组件 然后c调用setBounds()设置位置和大小
 * */

public class PanelNullLayout extends JPanel{

	JButton button;
	JTextField text;
	PanelNullLayout() {
		setLayout(null);  //设置空布局
		button = new JButton("确定");
		text = new JTextField();
		add(text);  	//添加文本框
		add(button); 	//添加按钮
		text.setBounds(100, 30, 90, 30);
		button.setBounds(190, 30, 66, 30);
	}
}
