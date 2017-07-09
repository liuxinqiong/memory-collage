package cn.com.global;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class ConfigUtil {
	private ConfigUtil() {
		
	}
	private static Properties pop;
	
	public static Properties getConfig() {
		pop = new Properties();
		try {
			pop.load(new FileReader(new File("config.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pop;
	}
}
