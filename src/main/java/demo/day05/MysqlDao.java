package main.java.demo.day05;

public class MysqlDao implements IDao{
	@Override
	public void getCon(){
		System.out.println("连接mysql数据库");
	}

}
