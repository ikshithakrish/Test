package pages;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Test_Programatically {
@Test
  public void testngXmlSuite() {
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		List<XmlClass> classes = new ArrayList<XmlClass>();
		
		XmlSuite suite = new XmlSuite();
		XmlTest test = new XmlTest();
		XmlClass Login = new XmlClass("Login");
		XmlClass Registration = new XmlClass("Registration");
		
		suite.setName("programTestSuite");
		
		System.out.println("  ");
		classes.add(Login);
		classes.add(Registration);
		
		test.setXmlClasses(classes);		
		suites.add(suite);
		
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run();		
	}
}
