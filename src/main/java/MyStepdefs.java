
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import java.util.*;

public class MyStepdefs extends Utilities {
    WebDriver driver = getDriver();
    Page page = new Page();
    Actions action = new Actions(driver);
    Impl impl=new Impl();

    @Given("I navigate to url {string}")
    public void iNavigateToUrl(String url) {
        driver.get(url);
          screenShot();
    }

    @Then("I validate page title of {string}")
    public void iValidatePageTitleOf(String expectedTitle) {
        sleep(3);
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @And("I mouseover to {string} tab")
    public void iMouseoverToTab(String tabName) {
        sleep(2);

        String xpath = String.format(page.tabs, tabName);
        System.out.println("xpath: " + xpath);
        action.moveToElement(driver.findElement(By.xpath(xpath))).perform();
        sleep(3);
    }


    @And("I choose {string}")
    public void iChoose(String dropDown) {
        sleep(4);
        String xpath = String.format(page.dropdown, dropDown);
        System.out.println("Drop list: " + xpath);
        driver.findElement(By.xpath(xpath)).click();
        sleep(3);
    }

    @And("I get all th e values from table")
    public void iGetAllTheValuesFromTable() {
        Set<String> set = new HashSet<>();
        List<WebElement> list = driver.findElements(By.xpath(page.carsList));
        for (WebElement each : list) {
            if (!each.getText().equals("")) {
                set.add(each.getText());
            }
        }
        System.out.println(set);
    }

    @And("I get all the values from the {string} the row")
    public void iGetAllTheValuesFromTheTheRow(String number) {
        System.out.println("===");
        String xpath = String.format(page.tableRowInfo, number);
        List<WebElement> list2 = driver.findElements(By.xpath(xpath));
        for (WebElement show : list2) {
            System.out.println(show.getText());
        }
    }

    @And("verify tabs")
    public void verifyTabs(List<String> tabs) {
        sleep(3);
        for (String list : tabs) {
            String xpath = String.format(page.tabs, list);
            WebElement web = highlight(driver.findElement(By.xpath(xpath)));
            System.out.println(web.isDisplayed());
        }
    }

    @And("I should verify driver {string}")
    public void iShouldVerifyDriver(String expected) {
        sleep(3);
        String actual = highlight(driver.findElement(By.xpath(page.userNameVerify))).getText();
        System.out.println(actual);
        System.out.println(expected);
        Assert.assertEquals(expected, actual);

    }

    @Given("I login as {string}")
    public void iLoginAs(String userType) {
        System.out.println("usertype: "+userType);
        String username = "";
        String password = "";
        if (userType.equalsIgnoreCase("Truck driver")) {
            username = configReader("driverUsername");
            password = configReader("driverPassword");
        } else if (userType.equalsIgnoreCase("sales manager")) {
            username = configReader("salesManagerUsername");
            password = configReader("salesManagerPassword");
        } else if (userType.equalsIgnoreCase("store manager")) {
            username = configReader("storeManagerUsername");
            password = configReader("storeManagerPassword");
        }

        System.out.println("username: " + username + "\npassword: " + password);
        Utilities.waitElementDisplay(page.userNameInput,5);
        highlight(driver.findElement(By.xpath(page.userNameInput))).sendKeys(username);
        //page.userNameInput.sendKeys(username);
        Utilities.waitElementDisplay(page.passwordInput,5);
        highlight(driver.findElement(By.xpath(page.passwordInput))).sendKeys(password);
        //page.passwordInput.sendKeys(password);
        screenShot();
        Utilities.waitElementDisplay(page.submitButton,5);
        highlight(driver.findElement(By.xpath(page.submitButton))).click();
        // page.submitButton.click();

    }

    @And("I {string} dropdown list")
    public void iDropdownList(String actionType,Map<String,String> map) {
        impl.verifyClickDropdown(actionType,map);
        sleep(3);
    }

    @Given("I click {string} button")
    public void i_click_button(String string) {
        impl.clickButton(string);
    }

    @Given("I set the start date as {string}")
    public void i_set_the_start_date_as(String string) {
        impl.selectDate(string);

    }

    @Then("I validate the end date is {string}")
    public void i_validate_the_end_date_is(String string) {
        impl.verifyDate(string);

    }

    @And("I change the start time to {int} hours from now")
    public void iChangeTheStartTimeToHoursFromNow(int hour) {

    }


    @Then("I verify that time changes {int} hours")
    public void iVerifyThatTimeChangesHours(int arg0) {
    }


}


