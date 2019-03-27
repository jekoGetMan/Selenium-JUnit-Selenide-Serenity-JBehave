import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

    public class SignUpPage {

        private By emailField = xpath("//*[@id=\"register-email\"]");
        private By confirmEmailField = xpath("//*[@id=\"register-confirm-email\"]");
        private By passwordField = xpath("//*[@id=\"register-password\"]");
        private By nameField = xpath("//*[@id=\"register-displayname\"]");
        private By monthDropDown = By.cssSelector("#register-dob-month");
        String monthDropDownOption = "//select[@id='register-dob-month']//option[text()='%s']";
        private By dayField = By.cssSelector("#register-dob-day");
        private By Year = By.cssSelector("#register-dob-year");
        private By sexRadioButton = By.cssSelector("li#li-gender");
        private By shareCheckBox = By.cssSelector("#li-thirdparty > label");
        private By registerButton = xpath("//*[@id=\"register-button-email-submit\"]");
        private By errorLabel = xpath("//label[@class='has-error' and string-length(text())>0]");
        String errorByText = "//label[@class=\"has-error\" and text()=\"%s\"]"; // локатор ошибки в зависимости от текста ошибки

        public SignUpPage open() {
            Selenide.open("/");
            return this;
        }

        public SignUpPage typeEmail(String email) {
            $(emailField).setValue(email);
            return this;
        }

        public SignUpPage typeConfirmEmail(String email) {
            $(confirmEmailField).setValue(email);
            return this;
        }

        public SignUpPage typePassword(String password) {
            $(passwordField).setValue(password);
            return this;
        }

        public SignUpPage typeName(String something) {
            $(nameField).setValue(something);
            return this;
        }

        public SignUpPage setMonth(String month) {
            $(monthDropDown).selectOption(month);

            return this;
        }

        public SignUpPage typeDay(String day) {
            $(dayField).setValue(day);
            return this;
        }

        public SignUpPage typeYear(String year) {
            $(Year).setValue(year);
            return this;
        }

        public SignUpPage setSex(String value) {
            $(sexRadioButton).selectRadio(value);
            return this;
        }

        public SignUpPage setShare(boolean value) {
            $(shareCheckBox).setSelected(value);
            return this;
        }

        public SignUpPage clickSignUpButton() {
            $(registerButton).click();
            return this;
        }

        public ElementsCollection getErrors() {
            return $$(errorLabel);
        }

        public SelenideElement getErrorByNumber(int number) {
            return getErrors().get(number - 1);
        }

        public SelenideElement getError(String message) {
            return $(xpath(format(errorByText, message)));
        }
    }


