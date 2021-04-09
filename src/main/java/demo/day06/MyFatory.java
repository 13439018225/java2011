package demo.day06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 工厂模式
 *
 */
public class MyFatory {
	
	public static void main(String[] args) {
		//ICar car=getCar("Benz");
		ICar car=getReflectCar("E:\\rcieWorkSpace\\java2011\\src\\main\\java\\demo\\day06\\bean.properties");
		System.out.println(car);
	}

	public static ICar getCar(String type){
		ICar car=null;
		if("bmw".equals(type)){
			car=new Bmw();
		}else if("Benz".equals(type)){
			car=new Benz();
		}
		return car;
	}
	
	/**
	 * 通过反射实现工厂模式
	 * @param configpath
	 * @return
	 */
	public static ICar getReflectCar(String configpath){
		//加载配置文件
		Properties pro=new Properties();
		ICar car=null;
		try {
			pro.load(new FileInputStream(configpath));
			//读取属性 demo.day06.Bmw
			String classname=pro.getProperty("classname");
			//使用反射创建读写
			Class<?> clazz=Class.forName(classname);
			car=(ICar)clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return car;
	}
	
}

interface ICar{
	
}

class Bmw implements ICar{
	
}
class Benz implements ICar{
	
}
