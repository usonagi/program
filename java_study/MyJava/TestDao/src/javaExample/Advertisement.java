package javaExample;

/******** 设计接口  ************/

// 接口中只能有常量 不能有变量  不有非abstract方法
public interface Advertisement {
	//接口体中的所有常量的访问权限一定是public 而且是static常量
	final int i = 0; 			//可以省略public、static、final 修饰符
	//final 不能和 abstract 同时修饰方法和类  abstract不能修饰变量
	public static final double m = 10.0; 

	
	// 接口体中只有抽象方法 访问权限一定是public(允许省略public static修饰符)
	public void showAD();		//展示广告   等价于 void showAD();
	public String getCorpName();//显示公司名
}

