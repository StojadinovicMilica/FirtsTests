package TAF.pages;


import com.aventstack.extentreports.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

public class ResultPage {

    WebDriver driver;
    WebDriverWait wait;



    @FindBy(xpath = ("//*[contains(text(),'Let’s connect')]"))
    WebElement letsConectBtn;
    @FindBy(id = ("menu-main-menu-2"))
    WebElement footer;
    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,3);
    }

    public void scroll (){
       new Actions(driver).moveToElement(footer);
       letsConectBtn.click();
    }

}
