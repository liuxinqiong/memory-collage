package cn.com.util;

import java.lang.reflect.Field;
import java.util.List;

public class ObjectToJson<T> {

	// һ���򵥵�beanת��json ->{"name":"abc","teacher":{},"age":20 }
	public String javaBeanToJson(T bean) throws IllegalArgumentException,
			IllegalAccessException {
		StringBuffer sbBuffer = new StringBuffer("{");
		// �����
		Class<?> classType = bean.getClass();
		// "����":"����ֵ"
		Field[] fields = classType.getDeclaredFields();
		// ɨ
		for (Field field : fields) {
			String attName = field.getName();
			// ����
			field.setAccessible(true);
			String attValue = field.get(bean).toString();
			sbBuffer.append("\"").append(attName).append("\"").append(":")
					.append("\"").append(attValue).append("\"").append(",");

		}
		sbBuffer.deleteCharAt(sbBuffer.toString().length() - 1);
		sbBuffer.append("}");
		return sbBuffer.toString();
	}

	public String ListToJson(List<T> list) throws IllegalArgumentException,
			IllegalAccessException {

		StringBuffer stringBuffer = new StringBuffer("{");
		Class<?> classType = list.get(0).getClass();
		String className = classType.getSimpleName().toLowerCase();
		stringBuffer.append("\"").append(className).append("s\"").append(":[");

		for (int i = 0; i < list.size(); i++) {
			stringBuffer.append(this.javaBeanToJson(list.get(i)));
			stringBuffer.append(",");
		}
		stringBuffer.deleteCharAt(stringBuffer.toString().length() - 1);
		stringBuffer.append("]}");
		return stringBuffer.toString();
	}
}
