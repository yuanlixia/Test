package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import utils.Configure;

import java.util.logging.Logger;

/**
 * Created by 袁丽霞 on 18/9/25.
 * 选择不同的浏览器  可以根据不同的平台选择不同的浏览器
 */
public class SelectBrowser {
    static Logger logger = Logger.getLogger(SelectBrowser.class.getName());

    FirefoxDriver firefoxDriver;
    ChromeDriver chromeDriver;
    InternetExplorerDriver internetExplorerDriver;
    Configure configure=new Configure();

    public WebDriver getFirefoxDriver() {
        return firefoxDriver;
    }


    public void setFirefoxDriver() {
        System.setProperty("webdriver.firefox.bin", configure.getTestData("config.properties","webdriver.firefox.bin"));
        this.firefoxDriver = new FirefoxDriver();
    }


    public WebDriver getChromeDriver() {
        return chromeDriver;
    }

    public void setChromeDriver() {
//        System.setProperty("webdriver.chrome.driver", "/Applications/Chrome.app/Contents/MacOS/chromedriver");
        System.setProperty("webdriver.chrome.driver", configure.getTestData("config.properties","webdriver.chrome.driver"));

        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Chrome.app/Contents/MacOS/Google Chrome");
        this.chromeDriver = new ChromeDriver(options);

    }


    public InternetExplorerDriver getInternetExplorerDriver() {
        return internetExplorerDriver;
    }

    public void setInternetExplorerDriver() {
        System.setProperty("webdriver.ie.driver", "files\\IEDriverServer.exe");
        this.internetExplorerDriver = new InternetExplorerDriver();
    }
}
