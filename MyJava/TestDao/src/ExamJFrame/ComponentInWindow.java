package ExamJFrame;

import java.awt.FlowLayout;
import javax.swing.*;

/*** ������� ***/
public class ComponentInWindow extends JFrame {

	JCheckBox cbox1,cbox2; //��ѡ��
	JRadioButton radio1,radio2; //��ѡ��
	ButtonGroup group;
	JComboBox<String> comBox; //�����б�

	public ComponentInWindow() {
		init();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	void init() {
		setLayout(new FlowLayout());  //���ò���  ˳�򲼾�
		comBox = new JComboBox<String>();  //��ѡ��
		cbox1 = new JCheckBox("ϲ������");
		cbox2 = new JCheckBox("ϲ������");
		group = new ButtonGroup();    //��ѡ��
		radio1 = new JRadioButton("��");
		radio2 = new JRadioButton("Ů");
		group.add(radio1);
		group.add(radio2);
		add(cbox1);
		add(cbox2);
		add(radio1);
		add(radio2);
		comBox.addItem("�������"); // �����б��еĲ˵���
		comBox.addItem("�������");
		add(comBox);	//�����б�
		
	}
}
