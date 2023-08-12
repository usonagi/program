package ExamJFrame;

/********** ������¼�����  main *************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.charset.MalformedInputException;

public class FrameExam {
 
	
	
	
	
	
	public static void main(String args[]) {
		
		/*	���÷���
		 *  JFrame(String s)��������Ϊs�Ĵ���
		 *  setBounds(int x,int y,int width,int height)���ô��ڵĳ�ʼλ��(x,y)�ʹ�С
		 *  setLocation(int x,iny y)���ô��ڵ�λ�� ��ʼλ��Ϊ(0,0) ��Ļ���Ͻ�Ϊԭ��
		 *  setVisible(boolean b)���ô����Ƿ�ɼ� Ĭ�ϲ��ɼ�
		 *  setResizable(boolean b)���ô����Ƿ�ɵ�����С
		 *  dispose()������ǰ���ڣ����ͷ�����ʹ�õ���Դ
		 *  setExtendedState(int state)���ô��ڵ���չ״̬ MAXIMIZED_HORIZ/VERT/BOTH ˮƽ/��ֱ/˫�� 
		 *  setDefaultColseOperation(int operation)������������ �������ڵĹر�ͼ��
		 *  0	DO_NOTHING_ON_CLOSE ʲôҲ����
		 *  1	HIDE_ON_COLSE		���ص�ǰ����
		 *  2	DISPOSE_ON_CLOSE	���ش��ڲ��ͷ�ռ�е�������Դ
		 *  3	EXIT_ON_CLOSE		�����������ڵ�Ӧ�ó���
		 * */
		
		/*JFrame window1 = new JFrame("��һ������");  //��������
		JFrame window2 = new JFrame("�ڶ�������");
		Container con = window1.getContentPane();  //��������
		con.setBackground(Color.yellow);		   //���ô��ڱ�����ɫ
		window1.setBounds(60,100,300,200);		   //���ô���λ�úʹ�С
		window2.setBounds(460,100,300,200);
		window1.setVisible(true);				   //�����Ƿ�ɼ�
		window1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //���ùرմ���Ϊ���ش���
		//window1.setDefaultCloseOperation(0);	   //���ùرմ���Ϊ0(ʲôҲ����) 
		window2.setVisible(true);
		window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //���ùرմ���Ϊ�˳�����  */	
		
		/* �˵� �˵��� �˵���
		 * JComponent�������JMenuBar������Ӳ˵��������ڵĶ��� (ֻ�����һ��)
		 * JComponent�������JMenu���𴴽��˵�
		 * JComponent�������JMenuItem���𴴽��˵���
		 * ���԰�һ���˵�����һ���˵�����ӵ�ĳ���˵��� �����Ĳ˵���Ϊ�Ӳ˵�
		 * ������ͼ����Icon����һ��ͼ�� Ȼ��ʹ��������ImageIcon����һ��ͼ��
		 * */
		
		// ���˵����Ĵ���
		//WindowMenu win = new WindowMenu("���˵��Ĵ���",220,230,500,490);
		
		/* �������
		 * JTextField(�ı���)�����û����ı������뵥���ı�
		 * JTrxtArea(�ı���)�����û����ı�����������ı�
		 * JButton(��ť)�����û�������ť
		 * JLabel(��ǩ)Ϊ�û��ṩ��Ϣ	
		 * JCheckBox(��ѡ��)Ϊ�û��ṩ����ѡ��
		 * JRadioButton(��ѡ��ť)Ϊ�û��ṩ����ѡ��
		 * JComboBox(�����б�)Ϊ�û��ṩ����ѡ��
		 * JPasswordField(�����)�������뵥������ �����ַ�Ĭ����* ����ʹ��setEchoChar(char c)��������
		 * */
		
		// ������Ĵ���
		/*ComponentInWindow win = new ComponentInWindow();
		win.setBounds(100, 100, 500, 260);
		win.setTitle("�������");*/
		
		/* ��������  JComponent���������Ҳ������
		 * �����������ĳ�Ϊ�м����� ����뱻��ӵ��ײ��������ܷ�������
		 * JPabel����һ����� �����������Ȼ�������ӵ����������� ���Ĭ�ϲ�����FlowLayout
		 * JTabbedPaneѡ����� ÿ��ѡ���Ӧһ����� ������������JTabbedPane���� ѡ�Ĭ��������������������
		 *   add(String text,Component c)�����c��ӵ�JTabbedPaneָ��ѡ����ı���ʾΪtext
		 * JScrollPane�������� ֻ�������һ�����
		 * JSplitPane��ִ���   ���������췽��   ����aָ��ˮƽ��ֱ��� Componentָ�����õ����
		 * JSplitPane(int a,Component b,Component c)
		 * JSplitPane(int a,boolean b,Component c,Component d) b����������ƶ�ʱ����Ƿ�����(true)�仯
		 * JLayeredPane�ֲ㴰�� ��Ϊ5�� add(Jcomponent com,int layer)������comָ�����ڲ�layer
		 * */
		
		// ʹ�ò��ֵĴ���
		/*new ShowLayout();
		
		WindowBoxLayout box = new WindowBoxLayout();
		box.setBounds(200, 400, 310, 260);
		box.setTitle("Ƕ�׺�ʽ��������");*/
		
		// �¼����� ����3������  
		/* 1���¼�Դ���󣺷����¼��������һ������Ͽ��Է�����Щ�¼����Ѿ����������ƾ������ˡ�
		 * 2���¼�������������������ĳ���¼���ϵͳ���Զ��� ��һ����Ӧ���¼��������з�װ�˸��¼�����ϸ��Ϣ��
		 *    �����佻���¼�Դ����
		 * 3���¼�����������һ��ʵ������Ӧ���¼��������ӿڵġ����ҽ������¼�Դ��ί�еĶ����¼�Դ���������Լ�
		 *    ������¼�ί�и���Ӧ���¼�������������
		 * ע����������¼�Դ.addXXXListener
		 * ������������ʵ�ֽӿ� class T implements XXXListener{}
		 * */
		
		// ActionEvent�¼�
		// �¼�Դ:�ı��򡢰�ť���˵������򡢵�ѡ���	��������ActionListener
		// ע����������¼�Դ.addActionListener(ActionListener e)
		// �������������ࣺclass T implements ActionListener{}
		/*WindowActionEvent action = new WindowActionEvent();
		PoliceListen p = new PoliceListen(); //����������
		action.setMyListener(p);			 //������ϼ�����
		action.setBounds(100, 100, 460, 350);
		action.setTitle("����ActionEvent�¼�");*/
		
		// ItemEvent�¼�
		// �¼�Դ:ѡ��������б��	��������ItemListener
		// ע����������¼�Դ.addItemListener(ItemListener I)
		// �������������ࣺclass T implements ActionListener{}
		WindowOperation win = new WindowOperation();
		win.setBounds(100,100,390,360);
		win.setTitle("�򵥼�����");
	}
}
