package javaExample;

/******** ��ƽӿ�  ************/

// �ӿ���ֻ���г��� �����б���  ���з�abstract����
public interface Advertisement {
	//�ӿ����е����г����ķ���Ȩ��һ����public ������static����
	final int i = 0; 			//����ʡ��public��static��final ���η�
	//final ���ܺ� abstract ͬʱ���η�������  abstract�������α���
	public static final double m = 10.0; 

	
	// �ӿ�����ֻ�г��󷽷� ����Ȩ��һ����public(����ʡ��public static���η�)
	public void showAD();		//չʾ���   �ȼ��� void showAD();
	public String getCorpName();//��ʾ��˾��
}

