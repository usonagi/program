package javaExample;

/********* 定义 广告牌类  ************/

public class ADBoard {

	public void show(Advertisement ad) {
		System.out.println(ad.getCorpName()+"的广告词如下: ");
		ad.showAD();       //接口回调    ？？？
	}
}
