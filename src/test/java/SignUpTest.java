import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SignUpTest {
    WebDriver driver;
    SignUpPage page;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Yevhen_Pavlenko\\IdeaProjects\\tst3\\geckodriver-v0.24.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://www.spotify.com/us/signup/?forward_url=https%3A%2F%2Fwww.spotify.com%2Fus%2Faccount%2Foverview%2F%3F_ga%3D2.68218707.314742187.1552594654-368280494.1552594654");
    }

    @Test
    public void typeInvalidYear() {
        page = new SignUpPage(driver);
        page.setMonth("5")
                .typeDay("20")
                .typeYear("85")
                .setShare(true);
        Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
        Assert.assertFalse(page.isErrorVisible("When were you born?"));
    }

    @Test

    @After
    public void tearDown() {
        driver.quit();
    }
}
