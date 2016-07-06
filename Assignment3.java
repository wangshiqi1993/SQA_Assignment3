import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Assignment3 {

	static WebDriver driver = new HtmlUnitDriver();
	
	// Start at the home page for reddit for each test
	@Before
	public void setUp() throws Exception {
		driver.get("http://lit-bayou-7912.herokuapp.com/");
	}
	
//	Given a program which contains spaces in code area
//	When I press the “Tokenize” button
//	Then I see that it contains the word “:on_sp”
	@Test
	public void testU1S1(){
	WebElement code =driver.findElement(By.id("code_code"));
	code.sendKeys(" ");
	
	WebElement tokenizeButton = driver.findElement(By.name("commit"));
	tokenizeButton.click();
	WebElement result=driver.findElement(By.cssSelector("code"));
	String resultText = result.getText();
	assertTrue(resultText.contains(":on_sp"));
	}
//	Given a program which contains identifiers for functions in code area
//	When I press the “Tokenize” button
//	Then I see that it contains the word “:on_ident”	
	@Test
	public void testU1S2(){
	WebElement code =driver.findElement(By.id("code_code"));
	code.sendKeys("puts \"foo\"");
	
	WebElement tokenizeButton = driver.findElement(By.name("commit"));
	tokenizeButton.click();
	WebElement result=driver.findElement(By.cssSelector("code"));
	String resultText = result.getText();
	assertTrue(resultText.contains(":on_ident"));
	}
	
//	Given a program which contains variables in code area
//	When I press the “Tokenize” button
//	Then I see that it contains the word “:on_ident”
	@Test
	public void testU1S3(){
	WebElement code =driver.findElement(By.id("code_code"));
	code.sendKeys("a = 5");
	
	WebElement tokenizeButton = driver.findElement(By.name("commit"));
	tokenizeButton.click();
	WebElement result=driver.findElement(By.cssSelector("code"));
	String resultText = result.getText();
	assertTrue(resultText.contains(":on_ident"));
	}
	
//	Given a program which contains newlines in code area
//	When I press the “Tokenize” button
//	Then I see that it contains the word “:on_nl”
	@Test
	public void testU1S4(){
	WebElement code =driver.findElement(By.id("code_code"));
	code.sendKeys("a = 5\nb = 6");
	
	WebElement tokenizeButton = driver.findElement(By.name("commit"));
	tokenizeButton.click();
	WebElement result=driver.findElement(By.cssSelector("code"));
	String resultText = result.getText();
	assertTrue(resultText.contains(":on_nl"));
	}

//	Given a program which contains operators in code area
//	When I press the “Tokenize” button
//	Then I see that it contains the word “:on_op”
	@Test
	public void testU1S5(){
	WebElement code =driver.findElement(By.id("code_code"));
	code.sendKeys("a = 5\nb = 6\nc = a + (b * 4)");
	
	WebElement tokenizeButton = driver.findElement(By.name("commit"));
	tokenizeButton.click();
	WebElement result=driver.findElement(By.cssSelector("code"));
	String resultText = result.getText();
	assertTrue(resultText.contains(":on_op"));
	}
	
//	Given a program which contains an operator in code area 
//	When I press the “Parse” button
//	Then I see this operator
	@Test
	public void testU2S1(){
	WebElement code =driver.findElement(By.id("code_code"));
	code.sendKeys("a = 5\nb = 6\nc = (a + (b * 4) -3 )/2");
	
	WebElement parseButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
	parseButton.click();
	WebElement result=driver.findElement(By.cssSelector("code"));
	String resultText = result.getText();
	assertTrue(resultText.contains(":+"));
	assertTrue(resultText.contains(":*"));
	assertTrue(resultText.contains(":-"));
	assertTrue(resultText.contains(":/"));
	}
	
//	Given a program which contains an identifier for function in code area
//	When I press the “Parse” button
//	Then I should see this identifier for function
	@Test
	public void testU2S2(){
	WebElement code =driver.findElement(By.id("code_code"));
	code.sendKeys("puts \"foo\"");
	
	WebElement parseButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
	parseButton.click();
	WebElement result=driver.findElement(By.cssSelector("code"));
	String resultText = result.getText();
	assertTrue(resultText.contains("puts"));
	}
	
//	Given a program which contains newlines in code area (Whitespace tokens)
//	When I press the “Parse” button
//	Then I shouldn’t see this identifier for function
	@Test
	public void testU2S3(){
	WebElement code =driver.findElement(By.id("code_code"));
	code.sendKeys("puts \"foo\"");
	
	WebElement parseButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
	parseButton.click();
	WebElement result=driver.findElement(By.cssSelector("code"));
	String resultText = result.getText();
	assertFalse(resultText.contains("\"\n\""));
	}
	
//	Given a program which contains spaces in code area (Whitespace tokens)
//	When I press the “Parse” button
//	Then I shouldn’t see this identifier for function

	@Test
	public void testU2S4(){
	WebElement code =driver.findElement(By.id("code_code"));
	code.sendKeys("a = 5");
	
	WebElement parseButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
	parseButton.click();
	WebElement result=driver.findElement(By.cssSelector("code"));
	String resultText = result.getText();
	assertFalse(resultText.contains("\" \""));
	}
	
//	Given identifiers for functions in code area
//	When I press the “Compile” button
//	Then I see “putstring”
	@Test
	public void testU3S1(){
	WebElement code =driver.findElement(By.id("code_code"));
	code.sendKeys("puts \"foo\"");
	
	WebElement compileButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
	compileButton.click();
	WebElement result=driver.findElement(By.cssSelector("code"));
	String resultText = result.getText();
	assertTrue(resultText.contains("putstring"));
	}
	
//	Given a program which contains “+” in code area 
//	When I press the “Compile” button
//	Then I see “opt_plus”
	@Test
	public void testU3S2(){
	WebElement code =driver.findElement(By.id("code_code"));
	code.sendKeys("a = 5\nb = 6\nc = (a + (b * 4) -3 )/2");
	
	WebElement compileButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
	compileButton.click();
	WebElement result=driver.findElement(By.cssSelector("code"));
	String resultText = result.getText();
	assertTrue(resultText.contains("opt_plus"));
	assertTrue(resultText.contains("opt_minus"));
	assertTrue(resultText.contains("opt_div"));
	assertTrue(resultText.contains("opt_mult"));
	}
	
}
