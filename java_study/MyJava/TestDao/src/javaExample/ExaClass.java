package javaExample;

// һ����ֻ����һ��public��
public class ExaClass {

}

//Java��֧�ֶ��ؼ̳� ������ֻ����һ�����ࣨ���ࣩ

class Goods {
	public double weight;
	
	//���������д����˶���󣬸ö����ܷ����Լ���˽�б���������˽�з���
	private int number;   //���ܱ�����̳У����ǿ��Ա�����̳еķ�������
	
	//ͬ���еĶ�����Է����Ѻó�Ա��������������ͬ�����ܷ���
	public void oldSetWeight(double w) {
		weight = w;  	 //�Ѻó�Ա����/����  ����private��public��protected����
		System.out.println("double�͵�weight="+weight);
	}
	
	public double oldGetPrice() {
		double price = weight * 10;
		return price;
	} 
	
	// ����̳еķ��������ĳ�Ա����һ���Ǳ����� �̳� �� ���� �ĳ�Ա����
	public int getNum() {  //������Լ̳и÷���������δ�̳е�number
		number = 5;
		return number;
	}
	
}
// ���಻�̳и����˽�г�Ա����
class CheapGoods extends Goods {
	
	//protected ͬһ�����е���������Ч����ͬ��������Ҳ��Ч
	protected int weight;  //���������˸����weight������ͨ��super.��Ա�������ñ����صı���
	
	public void newSetWeight(int w) {
		weight = w;
		System.out.println("int�͵�weight="+weight);
	}
	
	public double newGetPrice() {
		double price = weight * 10;
		return price;
	}
	
	//���������֡������������������ͺ͸���ķ�����ȫ��ͬ  ��Ϊ��д����
	public int getNum() {
		int m = 10;
		return m + super.getNum();  //���ø����getNum�������ʸ����˽�б���
	}
}