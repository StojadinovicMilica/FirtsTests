package TAF.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {
    WebDriver driver;
    WebDriverWait wait;

    private static final Logger log = LogManager.getLogger(MainPage.class);

    @FindBy(id = "navBtnDesktop")
    WebElement hamburger;

    @FindBy(xpath = "/html[1]/body[1]/header[1]/div[2]/div[1]/div[1]/ul[1]/li[2]/a[1]")
    WebElement aboutUS;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 3);
    }

    public void navigationListMenu() throws InterruptedException {
        hamburger.click();
        Thread.sleep(2000);
//    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("navigation-desktop")));
        aboutUS.click();
    }

    public void badinUrlTest() {
        Assert.assertTrue(driver
                .getCurrentUrl()
                .contains("badinsoft.com"));
    }



}
