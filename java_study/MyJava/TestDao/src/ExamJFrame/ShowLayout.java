package ExamJFrame;

import java.awt.*;
import javax.swing.*;

/* ��ʾ���ڲ���
 * BorderLayout ����������Ϊ ���������� 5������ ���ֻ�����5�����
 * */

public class ShowLayout extends JFrame{
	PanelGridLayout panelGrid;  //���񲼾ֵ����
	PanelNullLayout panelNull;  //�ղ��ֵ����
	JTabbedPane p;				//ѡ�����
	ShowLayout() {
		panelGrid = new PanelGridLayout();
		panelNull = new PanelNullLayout();
		p = new JTabbedPane();
		p.add("���񲼾ֵ����",panelGrid);
		p.add("�ղ��ֵ����",panelNull);
		add(p,BorderLayout.CENTER);
		add(new JButton("������BorderLayout����"),BorderLayout.NORTH);
		add(new JButton("��"),BorderLayout.SOUTH);
		add(new JButton("��"),BorderLayout.WEST);
		add(new JButton("��"),BorderLayout.EAST);
		setBounds(10,10,570,390);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		validate();
	}

}
