package ru.daniilshat;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class pageTest {
    // Variables
    String firstName = "Ivan";
    String lastName = "Ivanov";
    String email = "test@email.com";
    String mobile = "8123456789";
    String month = "January";
    String year = "1999";
    String subject = "Maths";
    String address = "Russia, Moscow, Red Square, 1";

    @BeforeAll
    static void setUP() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl =("https://demoqa.com");
    }

    @Test
    void fillRegForm() {
        open("/automation-practice-form");

        // First name, last name, email, gender, mobile number
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText("Other")).click();
        $("#userNumber").setValue(mobile);

        // Date Of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $("[aria-label$='" + month + " 1st, " + year + "']").click();

        // Subject and Hobbies
        $("#subjectsInput").setValue(subject).pressEnter();
        $(byText("Reading")).click();

        // Download picture
        $("#uploadPicture").uploadFromClasspath("img/1.jpg");

        // Current address
        $("#currentAddress").setValue(address);

        // stateCity-wrapper
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        // Submit button
        $("#submit").click();

        // Some Checks
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(
                text(firstName + " " + lastName),
                text(email),
                text("Other"),
                text(mobile),
                text("01 " + month + "," + year),
                text(subject),
                text("Reading"),
                text("1.jpg"),
                text(address),
                text("NCR Delhi")
        );

        // close the window
        $("#closeLargeModal").click();
    }
}
