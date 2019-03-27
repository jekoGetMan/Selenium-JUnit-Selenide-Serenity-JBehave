import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;

public class SignUpTest {
    private SignUpPage page;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Yevhen_Pavlenko\\IdeaProjects\\tst3\\geckodriver-v0.24.0-win64\\geckodriver.exe");
        baseUrl = "https://www.spotify.com/us/signup/?forward_url=https%3A%2F%2Fwww.spotify.com%2Fus%2Faccount%2Foverview%2F%3F_ga%3D2.68218707.314742187.1552594654-368280494.1552594654";
        browser = "marionette";
//        WebDriverRunner.setWebDriver(new FirefoxDriver());
    }

    @Test
    public void typeInvalidYear() {
        page = new SignUpPage();
        page.open()
                .setMonth("December")
                .typeDay("20")
                .typeYear("85")
                .setShare(true);
        page.getError("Please enter a valid year.").shouldBe(visible);
        page.getError("When were you born?").shouldNotBe(visible);
    }

    @Test
    public void typeInvalidEmail() {
        page = new SignUpPage();
        page.open()
                .typeName("test@gmail.com")
                .typeConfirmEmail("wrong@gmail.com")
                .typeName("Testname")
                .clickSignUpButton();
        page.getError("Email address doesn't match.").shouldBe(visible);
    }

    @Test
    public void signUpWithEmptyPassword() {
        page = new SignUpPage();
            page.open()
                .typeEmail("test@gmail.com")
                .typeConfirmEmail("test@gmail.com")
                .typeName("Testname")
                .clickSignUpButton();
        page.getError("Please choose a password.").shouldBe(visible);
    }

    @Test
    public void typeInvalidValue() {
        page = new SignUpPage();
        page.open()
                .typeEmail("testmail")
                .typeConfirmEmail("wrong@test.com")
                .typePassword("qweqwe!123")
                .typeName("name")
                .setSex("Male")
                .setShare(false)
                .clickSignUpButton();
        page.getErrors().shouldHave(size(4));
        page.getErrorByNumber(3).shouldHave(text("Please enter your birth month."));

    }

}
