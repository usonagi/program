package ExamJFrame;

import java.awt.*;
import javax.swing.*;

/****** ���񲼾� 
 * GridLayout ����������Ϊ�����г������е��������� ���λ��С����
 * ******/

public class PanelGridLayout extends JPanel{

	PanelGridLayout() {
		GridLayout grid = new GridLayout(12,12);
		setLayout(grid);  //���ò���
		Label label1[][] = new Label[12][12];
		for(int i=0; i<12; i++) {
			for(int j=0; j<12; j++) {
				label1[i][j] = new Label();
				if( (i+j) % 2 == 0)
					label1[i][j].setBackground(Color.black);
				else
					label1[i][j].setBackground(Color.white);
				add(label1[i][j]);
			}
		} //for
	}
}
