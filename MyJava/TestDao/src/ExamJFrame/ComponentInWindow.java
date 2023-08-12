package ExamJFrame;

import java.awt.FlowLayout;
import javax.swing.*;

/*** 常用组件 ***/
public class ComponentInWindow extends JFrame {

	JCheckBox cbox1,cbox2; //复选框
	JRadioButton radio1,radio2; //单选框
	ButtonGroup group;
	JComboBox<String> comBox; //下拉列表

	public ComponentInWindow() {
		init();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	void init() {
		setLayout(new FlowLayout());  //设置布局  顺序布局
		comBox = new JComboBox<String>();  //复选框
		cbox1 = new JCheckBox("喜欢音乐");
		cbox2 = new JCheckBox("喜欢旅游");
		group = new ButtonGroup();    //单选框
		radio1 = new JRadioButton("男");
		radio2 = new JRadioButton("女");
		group.add(radio1);
		group.add(radio2);
		add(cbox1);
		add(cbox2);
		add(radio1);
		add(radio2);
		comBox.addItem("音乐天地"); // 下拉列表中的菜单项
		comBox.addItem("武术天地");
		add(comBox);	//下拉列表
		
	}
}
