package com.listener.uilistener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/*
 * GUI的小窗口监听器
 */
public class MyFrame extends JFrame{
	public static void main(String[] args) {
		//1、创建小窗口对象
		MyFrame myFrame = new MyFrame();
		//设置窗口大小和位置
		myFrame.setBounds(0, 0, 400, 150);
		//2、设置窗口显示
		myFrame.setVisible(true);
		//在事件源上绑定监听器
		myFrame.addWindowListener(new MyWindowListener());
	}
}

class MyWindowListener implements WindowListener{

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("窗口关闭...");
		System.exit(0);
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
