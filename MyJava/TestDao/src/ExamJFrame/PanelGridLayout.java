package ExamJFrame;

import java.awt.*;
import javax.swing.*;

/****** 网格布局 
 * GridLayout 把容器划分为若干行乘若干列的网格区域 组件位于小格中
 * ******/

public class PanelGridLayout extends JPanel{

	PanelGridLayout() {
		GridLayout grid = new GridLayout(12,12);
		setLayout(grid);  //设置布局
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
