package org.springframework.context.marshaller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleBean {

	private boolean testBoolean;

	private byte testByte;

	private short testShort;

	private int testInt;

	private long testLong;

	private float testFloat;

	private double testDouble;

	private String testString;

	private Date testDate;

	private List<?> testList;

	private Set<?> testSet;

	private Map<?, ?> testMap;

	private Object[] testArray;

	private Object testObject;

	public boolean isTestBoolean() {
		return testBoolean;
	}

	public void setTestBoolean(boolean testBoolean) {
		this.testBoolean = testBoolean;
	}

	public byte getTestByte() {
		return testByte;
	}

	public void setTestByte(byte testByte) {
		this.testByte = testByte;
	}

	public short getTestShort() {
		return testShort;
	}

	public void setTestShort(short testShort) {
		this.testShort = testShort;
	}

	public int getTestInt() {
		return testInt;
	}

	public void setTestInt(int testInt) {
		this.testInt = testInt;
	}

	public long getTestLong() {
		return testLong;
	}

	public void setTestLong(long testLong) {
		this.testLong = testLong;
	}

	public float getTestFloat() {
		return testFloat;
	}

	public void setTestFloat(float testFloat) {
		this.testFloat = testFloat;
	}

	public double getTestDouble() {
		return testDouble;
	}

	public void setTestDouble(double testDouble) {
		this.testDouble = testDouble;
	}

	public String getTestString() {
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public List<?> getTestList() {
		return testList;
	}

	public void setTestList(List<?> testList) {
		this.testList = testList;
	}

	public Set<?> getTestSet() {
		return testSet;
	}

	public void setTestSet(Set<?> testSet) {
		this.testSet = testSet;
	}

	public Map<?, ?> getTestMap() {
		return testMap;
	}

	public void setTestMap(Map<?, ?> testMap) {
		this.testMap = testMap;
	}

	public Object[] getTestArray() {
		return testArray;
	}

	public void setTestArray(Object[] testArray) {
		this.testArray = testArray;
	}

	public Object getTestObject() {
		return testObject;
	}

	public void setTestObject(Object testObject) {
		this.testObject = testObject;
	}

}
