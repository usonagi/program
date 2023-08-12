package ExamJFrame;

/********** 组件及事件处理  main *************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.charset.MalformedInputException;

public class FrameExam {
 
	
	
	
	
	
	public static void main(String args[]) {
		
		/*	常用方法
		 *  JFrame(String s)创建标题为s的窗口
		 *  setBounds(int x,int y,int width,int height)设置窗口的初始位置(x,y)和大小
		 *  setLocation(int x,iny y)设置窗口的位置 初始位置为(0,0) 屏幕左上角为原点
		 *  setVisible(boolean b)设置窗口是否可见 默认不可见
		 *  setResizable(boolean b)设置窗口是否可调整大小
		 *  dispose()撤销当前窗口，并释放其所使用的资源
		 *  setExtendedState(int state)设置窗口的扩展状态 MAXIMIZED_HORIZ/VERT/BOTH 水平/垂直/双方 
		 *  setDefaultColseOperation(int operation)设置怎样处理 单击窗口的关闭图标
		 *  0	DO_NOTHING_ON_CLOSE 什么也不做
		 *  1	HIDE_ON_COLSE		隐藏当前窗口
		 *  2	DISPOSE_ON_CLOSE	隐藏窗口并释放占有的其他资源
		 *  3	EXIT_ON_CLOSE		结束窗口所在的应用程序
		 * */
		
		/*JFrame window1 = new JFrame("第一个窗口");  //创建窗口
		JFrame window2 = new JFrame("第二个窗口");
		Container con = window1.getContentPane();  //创建容器
		con.setBackground(Color.yellow);		   //设置窗口背景颜色
		window1.setBounds(60,100,300,200);		   //设置窗口位置和大小
		window2.setBounds(460,100,300,200);
		window1.setVisible(true);				   //设置是否可见
		window1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //设置关闭处理为隐藏窗口
		//window1.setDefaultCloseOperation(0);	   //设置关闭处理为0(什么也不做) 
		window2.setVisible(true);
		window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //设置关闭处理为退出程序  */	
		
		/* 菜单 菜单条 菜单项
		 * JComponent类的子类JMenuBar负责添加菜单条到窗口的顶端 (只能添加一个)
		 * JComponent类的子类JMenu负责创建菜单
		 * JComponent类的子类JMenuItem负责创建菜单项
		 * 可以把一个菜单看作一个菜单项添加到某个菜单中 这样的菜单称为子菜单
		 * 可以用图标类Icon声明一个图标 然后使用其子类ImageIcon创建一个图标
		 * */
		
		// 带菜单条的窗口
		//WindowMenu win = new WindowMenu("带菜单的窗口",220,230,500,490);
		
		/* 常用组件
		 * JTextField(文本框)允许用户在文本框输入单行文本
		 * JTrxtArea(文本区)允许用户在文本区输入多行文本
		 * JButton(按钮)允许用户单击按钮
		 * JLabel(标签)为用户提供信息	
		 * JCheckBox(复选框)为用户提供多项选择
		 * JRadioButton(单选按钮)为用户提供单项选择
		 * JComboBox(下拉列表)为用户提供单项选择
		 * JPasswordField(密码框)允许输入单行密码 回显字符默认是* 可以使用setEchoChar(char c)重新设置
		 * */
		
		// 带组件的窗口
		/*ComponentInWindow win = new ComponentInWindow();
		win.setBounds(100, 100, 500, 260);
		win.setTitle("常用组件");*/
		
		/* 常用容器  JComponent创建的组件也是容器
		 * 用来添加组件的称为中间容器 其必须被添加到底层容器才能发挥作用
		 * JPabel创建一个面板 向面板添加组件然后将面板添加到其他容器中 面板默认布局是FlowLayout
		 * JTabbedPane选项卡窗口 每个选项卡对应一个组件 各组件层叠放入JTabbedPane容器 选项卡默认在容器顶部从左至右
		 *   add(String text,Component c)将组件c添加到JTabbedPane指定选项卡的文本提示为text
		 * JScrollPane滚动窗口 只可以添加一个组件
		 * JSplitPane拆分窗口   有两个构造方法   参数a指定水平后垂直拆分 Component指定放置的组件
		 * JSplitPane(int a,Component b,Component c)
		 * JSplitPane(int a,boolean b,Component c,Component d) b决定拆分线移动时组件是否连续(true)变化
		 * JLayeredPane分层窗口 分为5层 add(Jcomponent com,int layer)添加组件com指定所在层layer
		 * */
		
		// 使用布局的窗口
		/*new ShowLayout();
		
		WindowBoxLayout box = new WindowBoxLayout();
		box.setBounds(200, 400, 310, 260);
		box.setTitle("嵌套盒式布局容器");*/
		
		// 事件处理 包括3个对象  
		/* 1、事件源对象：发生事件的组件。一种组件上可以发生哪些事件，已经由组件的设计决定好了。
		 * 2、事件对象：组件上如果发生了某个事件，系统会自动生 成一个相应的事件对象，其中封装了该事件的详细信息，
		 *    并将其交给事件源对象。
		 * 3、事件监听器对象：一个实现了相应的事件监听器接口的、并且接受了事件源的委托的对象。事件源将发生在自己
		 *    上面的事件委托给相应的事件监听器来处理。
		 * 注册监视器：事件源.addXXXListener
		 * 创建监视器：实现接口 class T implements XXXListener{}
		 * */
		
		// ActionEvent事件
		// 事件源:文本框、按钮、菜单项、密码框、单选框等	监视器：ActionListener
		// 注册监视器：事件源.addActionListener(ActionListener e)
		// 创建监视器的类：class T implements ActionListener{}
		/*WindowActionEvent action = new WindowActionEvent();
		PoliceListen p = new PoliceListen(); //创建监视器
		action.setMyListener(p);			 //窗口组合监视器
		action.setBounds(100, 100, 460, 350);
		action.setTitle("处理ActionEvent事件");*/
		
		// ItemEvent事件
		// 事件源:选择框、下拉列表等	监视器：ItemListener
		// 注册监视器：事件源.addItemListener(ItemListener I)
		// 创建监视器的类：class T implements ActionListener{}
		WindowOperation win = new WindowOperation();
		win.setBounds(100,100,390,360);
		win.setTitle("简单计算器");
	}
}
