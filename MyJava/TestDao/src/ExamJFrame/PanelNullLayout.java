package ExamJFrame;

import java.awt.*;
import javax.swing.*;

/* �ղ���
 * null �ղ��ֿ���׼ȷ�ض�λ����������е�λ�úʹ�С
 * ��ղ��������� ����pʹ��add(c)������ Ȼ��c����setBounds()����λ�úʹ�С
 * */

public class PanelNullLayout extends JPanel{

	JButton button;
	JTextField text;
	PanelNullLayout() {
		setLayout(null);  //���ÿղ���
		button = new JButton("ȷ��");
		text = new JTextField();
		add(text);  	//����ı���
		add(button); 	//��Ӱ�ť
		text.setBounds(100, 30, 90, 30);
		button.setBounds(190, 30, 66, 30);
	}
}
