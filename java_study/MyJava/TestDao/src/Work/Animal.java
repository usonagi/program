package Work;

public abstract class Animal {  //������  ��abstract�಻����abstract����
	
	//����ʹ��new����abstract��Ķ���
	//�����������󣬸ö�����Գ�Ϊ������������ת�Ͷ���
	
	//  ����ʹ��final��abstractͬʱ����һ����򷽷�
	public abstract void Cry();  //���󷽷�  ֻ������������ʵ��  ������ʵ������������ʹ��static���Σ�
	public abstract String getAnimalName();
}

class Simulator {  //ģ����
	void playSound(Animal am){
		System.out.println("���ڲ���"+am.getAnimalName()+"�������");
		am.Cry();
	}
}

//һ����abstrct������abstract������࣬�������д���������е�abstract����

/*class Dog extends Animal{  //���е�
	public void Cry(){
		System.out.println("����...����!");
	}
	public String getAnimalName(){
		return "��";
	}
}*/
class Cat extends Animal {  
	String name;  //������
	Cat(String s){  //���幹�췽��
		name = s;
	}
	public void Cry(){    		//��дabstract����
		System.out.println("����...����!");
	}
	public String getAnimalName(){   //��дabstract����
		return  name; //"è";
	}
}