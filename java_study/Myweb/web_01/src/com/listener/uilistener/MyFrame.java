package com.listener.uilistener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/*
 * GUI��С���ڼ�����
 */
public class MyFrame extends JFrame{
	public static void main(String[] args) {
		//1������С���ڶ���
		MyFrame myFrame = new MyFrame();
		//���ô��ڴ�С��λ��
		myFrame.setBounds(0, 0, 400, 150);
		//2�����ô�����ʾ
		myFrame.setVisible(true);
		//���¼�Դ�ϰ󶨼�����
		myFrame.addWindowListener(new MyWindowListener());
	}
}

class MyWindowListener implements WindowListener{

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("���ڹر�...");
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
