package vaadinpoc.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ITEjb3Button {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:9191/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
    @Ignore
	public void test1() throws Exception {
		driver.get(baseUrl + "/vaadinui/ui/");
		driver.findElement(By.xpath("//div[@id='vaadinuiui-2021976997']/div/div[2]/div/div[2]/div/div/div/div/div/div/div/div[2]/div/div[5]/div/div/div")).click();
		driver.findElement(By.xpath("//div[@id='vaadinuiui-2021976997']/div/div[2]/div/div[2]/div/div/div/div/div/div/div/div/div/div/div/div/div/div/div[2]/div/div/span/span")).click();
		// Warning: verifyTextPresent may require manual changes
		try {
			assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*The EJB3 helloService says 'Halloj!!! Ett meddelande från EJB3-bönan: 'Klockan är[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

    @After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
