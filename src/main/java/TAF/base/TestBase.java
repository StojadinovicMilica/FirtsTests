package TAF.base;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import TAF.utils.ScreenshotWatcher;


public class TestBase {

    protected static WebDriver driver;
    public static Properties envConfig;
    public static WebDriverWait wait;

    @RegisterExtension
    ScreenshotWatcher watcher = new ScreenshotWatcher(driver, "target/screenshots");

    public static final String ENV = System.getProperty("env", "dev");

    private static final String BROWSER = System.getProperty("browser", "Chrome");

    private static final String EXEC_TYPE = System.getProperty("execType","local");

    @BeforeAll
    public static void suiteSetup() throws Exception {

        //Environment specific properties file loading
        InputStream configFile;
        if(EXEC_TYPE.equals("local")){
            configFile = new FileInputStream(System.getProperty("user.dir") +
                    "//src//test//java//TAF//config//" + ENV +  ".properties");
        } else{
            configFile = new FileInputStream(System.getProperty("user.dir") +
                    "/src/test/java/TAF/config/" + ENV +  ".properties");
        }
        envConfig = new Properties();
        envConfig.load(configFile);

        //Browser configuration - can add more browsers and remote driver here
        switch (BROWSER.toLowerCase()) {
            case "firefox":

                if(envConfig.getProperty("execType").toLowerCase().equals("grid")){
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("browserName", "firefox");

                    driver = new RemoteWebDriver(new URL(envConfig.getProperty("gridUrl")), capabilities);
                }else{
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                break;

            case "chrome":
                if(envConfig.getProperty("execType").toLowerCase().equals("grid")){
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("browserName", "chrome");

                    driver = new RemoteWebDriver(new URL(envConfig.getProperty("gridUrl")), capabilities);
                }else {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-notifications");
                    driver = new ChromeDriver(options);
                }
                break;
            case "ie":
                throw new RuntimeException("Browser type unsupported");
        }

        //Setting implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        //Setting WebDriverWait with max timeout value of 20 seconds
        wait = new WebDriverWait(driver, 20);

    }

    @BeforeEach()
    public void loadBaseUrl() {

        driver.get(envConfig.getProperty("baseUrl"));
    }

    @AfterEach
    public void deleteCookies() {
        //Deleting cookies
        driver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void suiteTearDown() {
        System.out.println("Closing drivers");
//        driver.quit();
    }

}
