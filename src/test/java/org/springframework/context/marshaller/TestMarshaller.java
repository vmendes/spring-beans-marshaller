package org.springframework.context.marshaller;

import junit.framework.TestCase;

import org.springframework.context.marshaller.string.StringApplicationContextMarshaller;
import org.springframework.context.marshaller.string.StringResult;

public class TestMarshaller extends TestCase {

	public void testStringMarshal() throws Exception {
		
		StringResult result = new StringResult();
		ApplicationContextMarshaller<StringResult> marshaller = new StringApplicationContextMarshaller();
		
		marshaller.addBean(new TestClassUnit());
		marshaller.addBean(new TestBean());
		marshaller.marshal(result);
		
		System.out.println(result.getXml());
	}
	
	static class TestClassUnit {
		private int first = 3;
		private String second = "second";
		public int getFirst() {
			return first;
		}
		public void setFirst(int first) {
			this.first = first;
		}
		public String getSecond() {
			return second;
		}
		public void setSecond(String second) {
			this.second = second;
		}
	}
}
