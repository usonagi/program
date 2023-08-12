package ExamJFrame;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import static javax.swing.JFrame.*;
import javax.swing.*;

public class WindowMenu extends JFrame{
	JMenuBar bar;			//菜单条
	JMenu menu,subMenu;		//菜单
	JMenuItem item1,item2;	//菜单项
	
	public WindowMenu(){}
	public WindowMenu(String s,int x,int y,int w,int h){
		init(s);
		setLocation(x,y);
		setSize(w,h);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	void init(String s) {
		setTitle(s);
		bar = new JMenuBar();
		menu = new JMenu("菜单");
		subMenu = new JMenu("软件项目");  // 子菜单 本身被当作菜单项添加到其他菜单中
		item1 = new JMenuItem("java话题",new ImageIcon("图片"));
		//item1 = new JMenuItem("java话题");
		item2 = new JMenuItem("动画话题");
		item1.setAccelerator(KeyStroke.getKeyStroke('A'));
		item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		menu.add(item1);
		menu.addSeparator();
		menu.add(item2);
		menu.add(subMenu);
		// 子菜单的内容
		subMenu.add(new JMenuItem("汽车销售系统",new ImageIcon("图片")));
		subMenu.add(new JMenuItem("农村信息系统"));
		bar.add(menu);   //将菜单添加到菜单条中
		setJMenuBar(bar); //将菜单条放置到窗口中
		
	}
}
