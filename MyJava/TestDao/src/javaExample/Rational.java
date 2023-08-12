package javaExample;

public class Rational {
   //  ����������������
	int numberator = 1;   //����
	int denominator = 1;  //��ĸ
	
	void setNumberator(int a){  //���÷���
		int c = Convent(Math.abs(a),denominator);  //�������Լ��
		numberator = a / c;
		denominator /= c;
		
		if(numberator < 0 && denominator < 0) {
			numberator = -numberator;
			denominator = -denominator;
		}
	}
	
	void setDenominator(int d) {  //���÷�ĸ
		int c = Convent(numberator,Math.abs(d));  //�������Լ��
		numberator /= c;  
		denominator = d / c;
		
		if(numberator < 0 && denominator < 0) {
			numberator = -numberator;
			denominator = -denominator;
		}
	}
	
	int getNumberator() {
		return numberator;
	}
	int getDenominator() {
		return denominator;
	}
	
	int Convent(int a,int b) {  //�����Լ��
		if(a == 0) return 1;
		if(a < b) {
			int c = a;
			a = b;
			b = c;
		}
		int r = a % b;
		while(r != 0) {
			a = b;
			b = r;
			r = a % b;
		}
		return b;
	}
	
	Rational Add(Rational r) {  //�ӷ�����
		int n = r.getNumberator();  //�����������ķ���
		int d = r.getDenominator();  //�����������ķ�ĸ
		int newNumberator = numberator*d + denominator*n;  //������·���
		int newDenominator = denominator*d;    //������·�ĸ
		
		Rational result = new Rational();
		result.setNumberator(newNumberator);
		result.setDenominator(newDenominator);
		return result;
	}
	
	Rational Sub(Rational r) {  //��������
		int n = r.getNumberator();  //�����������ķ���
		int d = r.getDenominator();  //�����������ķ�ĸ
		int newNumberator = numberator*d - denominator*n;  //������·���
		int newDenominator = denominator*d;    //������·�ĸ
		
		Rational result = new Rational();
		result.setNumberator(newNumberator);
		result.setDenominator(newDenominator);
		return result;
	}
	
	Rational Mutil(Rational r) {  //�˷�����
		int n = r.getNumberator();  //�����������ķ���
		int d = r.getDenominator();  //�����������ķ�ĸ
		int newNumberator = numberator*n;  //������·���
		int newDenominator = denominator*d;    //������·�ĸ
		
		Rational result = new Rational();
		result.setNumberator(newNumberator);
		result.setDenominator(newDenominator);
		return result;
	}
	
	Rational Div(Rational r) {  //��������
		int n = r.getNumberator();  //�����������ķ���
		int d = r.getDenominator();  //�����������ķ�ĸ
		int newNumberator = numberator*d;  //������·���
		int newDenominator = denominator*n;    //������·�ĸ
		
		Rational result = new Rational();
		result.setNumberator(newNumberator);
		result.setDenominator(newDenominator);
		return result;
	}
	
}
