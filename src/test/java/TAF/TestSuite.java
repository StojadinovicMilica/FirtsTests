package TAF;

import TAF.base.TestBase;
import TAF.pages.MainPage;
import TAF.pages.ResultPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TestSuite extends TestBase {


    MainPage mainPage;
    ResultPage resultPage;


//    Log4j configuration
//    private static final Logger log = (Logger) LogManager.getLogger(TestSuite.class);

    @Test
    @Tag("smoke")
    public void navListButtonTest() throws InterruptedException {

//       log.info("running navigation bar");
        mainPage = new MainPage(driver);
        mainPage.navigationListMenu();
    }


    @Test
    @Tag("regression")
    public void badinUrlTest() {

//        log.info("running google search test");
        mainPage = new MainPage(driver);
        mainPage.badinUrlTest();
    }
    @Test
    @Tag("regression")
    public void imageClickTest() {
    driver.get("https://www.badinsoft.com/about-us/");

//    log.info("running google search test");
        resultPage = new ResultPage(driver);
        resultPage.scroll();
    }



}
