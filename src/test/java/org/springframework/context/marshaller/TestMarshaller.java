package org.springframework.context.marshaller;

import java.util.Collections;
import java.util.Date;

import junit.framework.TestCase;

import org.springframework.context.marshaller.string.StringApplicationContextMarshaller;
import org.springframework.context.marshaller.string.StringResult;

public class TestMarshaller extends TestCase {

	public void testEmptyBean() throws Exception {
		System.out.println(marshalBean(null));
		System.out.println(marshalBean(new TestBean()));
	}
	
	public void testBean1() throws Exception {
		TestBean bean = new TestBean();
		bean.setTestArray(new Object[]{"array1"});
		bean.setTestBoolean(true);
		bean.setTestByte((byte) 25);
		bean.setTestDate(new Date());
		bean.setTestDouble(4.2D);
		bean.setTestFloat(6.9F);
		bean.setTestInt(47);
		bean.setTestList(Collections.singletonList("list1"));
		bean.setTestLong(688L);
		bean.setTestMap(Collections.singletonMap("Key1", "Value1"));
		//bean.setTestObject(bean);
		bean.setTestSet(Collections.singleton("set1"));
		bean.setTestShort((short)77);
		bean.setTestString("String1");
		System.out.println(marshalBean(bean));
	}
	
	private String marshalBean(TestBean bean) throws Exception {
		
		StringResult result = new StringResult();
		ApplicationContextMarshaller<StringResult> marshaller = new StringApplicationContextMarshaller();
		
		marshaller.addBean(bean);
		marshaller.marshal(result);
		
		return result.getXml();
	}
	
}
