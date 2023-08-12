package javaExample;

/********* 实现接口  白云公司类 *************/

public class WhiteCloud implements Advertisement {
	//抽象类既可以重写接口中的方法 也可以直接拥有即不重写
    // 非抽象类实现接口必须重写接口中的所有方法
	// 去掉abstract 给出方法体 public不能省略
	public void showAD() {
		System.out.println("@@@@@@@@@@@@@@");
		System.out.println("飞机中的战斗机！！！");
		System.out.println("@@@@@@@@@@@@@@");
	}
	
	public String getCorpName() {
		return "白云有限公司";
	}
}
