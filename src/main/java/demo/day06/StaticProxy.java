package demo.day06;

/**
 * 静态代理
 *
 */
interface ISearchHose {
	
	public void searchhose();
}

class Master implements ISearchHose{

	@Override
	public void searchhose() {
        System.out.println("客户找房子...");		
	}
}

//代理类，代理客户做一些事情
class MyProxy implements ISearchHose{
    //被代理对象
	private ISearchHose target;

	public MyProxy(ISearchHose target) {
		this.target = target;
	}

	@Override
	public void searchhose() {
		System.out.println("我是中介，我去找房源...");
		target.searchhose();
		System.out.println("我是中介，我去退房...");
	}
}
//测试类
public class StaticProxy{
	
	public static void main(String[] args) {
		ISearchHose searchHose=new MyProxy(new Master());
		searchHose.searchhose();
	}
}

