package javaExample;

public class Rational {
   //  有理数的四则运算
	int numberator = 1;   //分子
	int denominator = 1;  //分母
	
	void setNumberator(int a){  //设置分子
		int c = Convent(Math.abs(a),denominator);  //计算最大公约数
		numberator = a / c;
		denominator /= c;
		
		if(numberator < 0 && denominator < 0) {
			numberator = -numberator;
			denominator = -denominator;
		}
	}
	
	void setDenominator(int d) {  //设置分母
		int c = Convent(numberator,Math.abs(d));  //计算最大公约数
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
	
	int Convent(int a,int b) {  //求最大公约数
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
	
	Rational Add(Rational r) {  //加法运算
		int n = r.getNumberator();  //返回有理数的分子
		int d = r.getDenominator();  //返回有理数的分母
		int newNumberator = numberator*d + denominator*n;  //计算出新分子
		int newDenominator = denominator*d;    //计算出新分母
		
		Rational result = new Rational();
		result.setNumberator(newNumberator);
		result.setDenominator(newDenominator);
		return result;
	}
	
	Rational Sub(Rational r) {  //减法运算
		int n = r.getNumberator();  //返回有理数的分子
		int d = r.getDenominator();  //返回有理数的分母
		int newNumberator = numberator*d - denominator*n;  //计算出新分子
		int newDenominator = denominator*d;    //计算出新分母
		
		Rational result = new Rational();
		result.setNumberator(newNumberator);
		result.setDenominator(newDenominator);
		return result;
	}
	
	Rational Mutil(Rational r) {  //乘法运算
		int n = r.getNumberator();  //返回有理数的分子
		int d = r.getDenominator();  //返回有理数的分母
		int newNumberator = numberator*n;  //计算出新分子
		int newDenominator = denominator*d;    //计算出新分母
		
		Rational result = new Rational();
		result.setNumberator(newNumberator);
		result.setDenominator(newDenominator);
		return result;
	}
	
	Rational Div(Rational r) {  //除法运算
		int n = r.getNumberator();  //返回有理数的分子
		int d = r.getDenominator();  //返回有理数的分母
		int newNumberator = numberator*d;  //计算出新分子
		int newDenominator = denominator*n;    //计算出新分母
		
		Rational result = new Rational();
		result.setNumberator(newNumberator);
		result.setDenominator(newDenominator);
		return result;
	}
	
}
