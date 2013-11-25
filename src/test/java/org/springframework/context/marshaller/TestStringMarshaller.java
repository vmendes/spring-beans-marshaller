package org.springframework.context.marshaller;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;

import junit.framework.TestCase;

import org.springframework.context.marshaller.string.StringApplicationContextMarshaller;
import org.springframework.context.marshaller.string.StringResult;

public class TestStringMarshaller extends TestCase {

	public void testEmptyBean() throws Exception {
		String expected = getString("testEmpty.xml");
		
		String actual = marshalBean(null);
		//assertEquals(expected.trim(), actual.trim());
		
		actual = marshalBean(new SimpleBean());
		//assertEquals(expected, actual);
	}

	public void testBean1() throws Exception {
		SimpleBean bean = new SimpleBean();
		bean.setTestArray(new Object[] { "array1" });
		bean.setTestBoolean(true);
		bean.setTestByte((byte) 25);
		bean.setTestDate(new Date());
		bean.setTestDouble(4.2D);
		bean.setTestFloat(6.9F);
		bean.setTestInt(47);
		bean.setTestList(Collections.singletonList("list1"));
		bean.setTestLong(688L);
		bean.setTestMap(Collections.singletonMap("Key1", "Value1"));
		bean.setTestObject(new SimpleBean());
		bean.setTestSet(Collections.singleton("set1"));
		bean.setTestShort((short) 77);
		bean.setTestString("String1");
		String actual = marshalBean(bean);
		String expected = getString("testString1.xml");
		
		System.out.println(actual);
		//assertEquals(expected, actual);

	}

	private String marshalBean(SimpleBean bean) throws Exception {

		StringResult result = new StringResult();
		ApplicationContextMarshaller<StringResult> marshaller = new StringApplicationContextMarshaller();

		marshaller.addBean(bean);
		marshaller.marshal(result);

		return result.getString();
	}

	private static String getString(String fileName) {
		InputStream inputStream = TestStringMarshaller.class
				.getResourceAsStream(fileName);

		try {
			BufferedInputStream bin = new BufferedInputStream(inputStream);

			byte[] contents = new byte[1024];

			int bytesRead = 0;
			String content = "";

			while ((bytesRead = bin.read(contents)) != -1) {
				content += new String(contents, 0, bytesRead);
			}
			bin.close();
			
			return content;
		} catch (Exception e) {
			System.out.println("File not found" + e);
			return null;
		} 
	}
}
