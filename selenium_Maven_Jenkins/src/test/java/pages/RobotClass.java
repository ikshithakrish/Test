package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.testng.annotations.Test;

import com.sun.glass.events.KeyEvent;

public class RobotClass {
  @Test
  public void robot_WindowsAction() throws AWTException, InterruptedException{  
	  Robot robotAction = new Robot();  // include try catch(AWTException / throws AWTException
	  robotAction.keyPress(KeyEvent.VK_WINDOWS);
	  Thread.sleep(2000); 
	  robotAction.keyPress(KeyEvent.VK_E);
	  Thread.sleep(2000); 
	  
	  robotAction.keyRelease(KeyEvent.VK_WINDOWS);
	  robotAction.keyRelease(KeyEvent.VK_E);
	  
	  
	  robotAction.keyPress(KeyEvent.VK_CONTROL);
	  Thread.sleep(2000); 
	  robotAction.keyPress(KeyEvent.VK_SPACE);
	  Thread.sleep(2000);
	  
	  robotAction.keyRelease(KeyEvent.VK_CONTROL);
	  Thread.sleep(2000); 
	  robotAction.keyRelease(KeyEvent.VK_SPACE);
	  Thread.sleep(2000);
	  
	  robotAction.keyPress(KeyEvent.VK_RIGHT);
	  Thread.sleep(2000);
	  robotAction.keyRelease(KeyEvent.VK_RIGHT);
	  Thread.sleep(2000); 
	  
	  robotAction.keyPress(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  robotAction.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  robotAction.mouseMove(630, 420);  // x and y axis
	  robotAction.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
	  robotAction.delay(1500);  // sleep / wait for 1.5 seconds
	  robotAction.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // press left click
	  Thread.sleep(2000);
	  
	  robotAction.mouseMove(630, 420);
	  robotAction.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
	  robotAction.delay(1500);  // sleep / wait for 1.5 seconds
	  robotAction.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // press left click
	  Thread.sleep(2000);
	  
	  robotAction.mousePress(InputEvent.BUTTON3_DOWN_MASK); // press Right click
	  robotAction.delay(1500);  // sleep / wait for 1.5 seconds
	  robotAction.mouseRelease(InputEvent.BUTTON3_DOWN_MASK); // press Right click
	  robotAction.delay(2000);
	  
	  robotAction.keyPress(KeyEvent.VK_DOWN);
	  robotAction.delay(2000);
	  robotAction.keyRelease(KeyEvent.VK_DOWN);
	  
	  robotAction.keyPress(KeyEvent.VK_ENTER);
	  robotAction.delay(2000);
	  robotAction.keyRelease(KeyEvent.VK_ENTER);
  }
}
