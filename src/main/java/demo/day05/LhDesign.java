package demo.day05;
/**
 * 设计模式
 * 
 */
public class LhDesign {
	
	private static LhDesign design;
	
	static Object obj=new Object();//锁
	
	private LhDesign(){
		
	}
	//懒汉模式
	public static LhDesign  getInstance(){
		if(design==null){
			synchronized (obj) {
				if(design==null){
					design=new LhDesign();
				}
			}
		}
		return design;
	}

	public static void main(String[] args) {
		LhDesign d1=LhDesign.getInstance();
		LhDesign d2=LhDesign.getInstance();
		if(d1==d2){
			System.out.println("同一个对象...");
		}
	}
}
