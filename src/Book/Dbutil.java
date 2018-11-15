package Book;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Dbutil {
	public static String url;
	public static String name;
	public static String password;
	public static String className;
	static{
		Properties p = new Properties();
		InputStream is = Dbutil.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			p.load(is);
			url = p.getProperty("url");
			name = p.getProperty("name");
			password = p.getProperty("password");
			className = p.getProperty("className");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
