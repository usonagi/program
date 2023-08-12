package ExamJFrame;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import static javax.swing.JFrame.*;
import javax.swing.*;

public class WindowMenu extends JFrame{
	JMenuBar bar;			//�˵���
	JMenu menu,subMenu;		//�˵�
	JMenuItem item1,item2;	//�˵���
	
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
		menu = new JMenu("�˵�");
		subMenu = new JMenu("�����Ŀ");  // �Ӳ˵� ���������˵�����ӵ������˵���
		item1 = new JMenuItem("java����",new ImageIcon("ͼƬ"));
		//item1 = new JMenuItem("java����");
		item2 = new JMenuItem("��������");
		item1.setAccelerator(KeyStroke.getKeyStroke('A'));
		item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		menu.add(item1);
		menu.addSeparator();
		menu.add(item2);
		menu.add(subMenu);
		// �Ӳ˵�������
		subMenu.add(new JMenuItem("��������ϵͳ",new ImageIcon("ͼƬ")));
		subMenu.add(new JMenuItem("ũ����Ϣϵͳ"));
		bar.add(menu);   //���˵���ӵ��˵�����
		setJMenuBar(bar); //���˵������õ�������
		
	}
}
