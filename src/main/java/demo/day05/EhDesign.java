package demo.day05;
/**
 * 饿汉模式
 * 
 */
public class EhDesign {
	
    private EhDesign(){
		
	}
	private static volatile EhDesign design=new EhDesign();
	//饿汉模式
	public static EhDesign getInstance(){
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
