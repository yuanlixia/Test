package utils;


import Listener.TestBase;
import browser.SelectBrowser;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Created by 袁丽霞 on 18/10/10.
 * 用于测试的开始和结尾  封装常见的@Beforeclass和@Afterclass的方法  想改变参数的话可以直接在子类里进行覆盖即可
 */
public class BaseParpare extends TestBase {
    static Logger logger = Logger.getLogger(BaseParpare.class.getName());
    protected SelectBrowser selectBrowser = new SelectBrowser();
    protected WebdriverUtil webdriverUtil = new WebdriverUtil();
    protected Configure configure;
    protected Login login;
    String mbr_users="src/main/resources/mbr_users.properties";
    String boss_users="src/main/resources/boss_users.properties";
    String portal_users="src/main/resources/portal_users.properties";


    @BeforeClass
    public void beforeTest() {

        configure = new Configure(mbr_users);
        String username = configure.getValue("user");
        String password = configure.getValue("pass");//以上几行都是为了获得用户名 密码 都存到了properties中
        selectBrowser.setFirefoxDriver(); //设置使用的浏览器
        driver = selectBrowser.getFirefoxDriver();
        webdriverUtil.setWebDriver(driver);
        login = new Login(username, password);//用已经用户名密码登陆url所表示的平台
    }

    @AfterClass
    /**结束测试关闭浏览器*/
    public void afterTest() {
        if (webdriverUtil.webDriver != null) {
            //退出浏览器
            webdriverUtil.close();
            webdriverUtil.quit();
        } else {
            logger.error("浏览器driver没有获得对象,退出操作失败");
            Assert.fail("浏览器driver没有获得对象,退出操作失败");
        }
    }
}
