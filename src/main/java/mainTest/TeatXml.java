package mainTest;

import java.lang.reflect.Field;


public class TeatXml {
	/**
	 * 将model转化为XML
	 * @return
	 */
	public String toXMLString(User user){
		StringBuffer sb = new StringBuffer();
		
		Field[] fields = user.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				sb.append("<"+field.getName()+">"+(String)field.get(field.getName())+"</"+field.getName()+">");
			} catch (Exception e) {
				return null;
			} 
		}
		System.out.println(sb.toString());
		return "<GYJ>"+sb.toString()+"</GYJ>";
	}
	

	
	public static void main(String[] args) {
		User user = new User();
		user.setAge("xxxxxxxxxxx");
		user.setName("name");
		
		StringBuffer sb = new StringBuffer();
		
		Field[] fields = user.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				sb.append("<"+field.getName()+">"+(String)field.get(user)+"</"+field.getName()+">");
			} catch (Exception e) {
				
			} 
		}
		System.out.println(sb.toString());
    }
}

