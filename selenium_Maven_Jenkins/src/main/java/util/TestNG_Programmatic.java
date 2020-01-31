package util;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestNG_Programmatic {

	public static void testngXmlSuite() {
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		List<XmlClass> classes = new ArrayList<XmlClass>();
		//List<Class> listenerClasses = new ArrayList<Class>();
		XmlSuite suite = new XmlSuite();
		suite.setName("programTestSuite");
		
		XmlTest test = new XmlTest();
		test.setName("programTest");
		
		XmlClass Login = new XmlClass("pages.Login");
		classes.add(Login);
		XmlClass Registration = new XmlClass("pages.Registration");
		classes.add(Registration);
		
		//listenerClasses.add(ListenerTest.class);
		test.setXmlClasses(classes);
		suites.add(suite);
		
		
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run();		
	}
	
	public static void testNG_CodeBase(){
		List<XmlSuite> suiteList = new ArrayList<XmlSuite>();
		List<XmlClass> classList = new ArrayList<XmlClass>();
		
		XmlSuite suiteName = new XmlSuite();
		suiteName.setName("sampleSuite");
		
		XmlTest testName = new XmlTest(suiteName);
		testName.setName("testName");
		
		XmlClass className1 = new XmlClass("pages.Login");
		classList.add(className1);
		
		XmlClass className2 = new XmlClass("pages.Login");
		classList.add(className2);
		
		testName.setXmlClasses(classList);
		
		suiteList.add(suiteName);
		TestNG testRun = new TestNG();
		testRun.setXmlSuites(suiteList);
		testRun.run();
	}
	
	public static void main(String args[]) {
		 testngXmlSuite();
		//testNG_CodeBase();
	}
}
