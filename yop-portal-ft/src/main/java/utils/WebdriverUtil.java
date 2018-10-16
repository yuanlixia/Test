package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by 袁丽霞 on 18/9/25.
 * 主要是用来封装一些常用的操作 如输入关键字  点击提交按钮  查找及检查关键字   查找元素等等功能
 */
public class WebdriverUtil {
    WebDriver webDriver;

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public static Logger logger = Logger.getLogger(WebdriverUtil.class.getName());

    public WebdriverUtil() {
    }

    //    启动浏览器并打开

//    通过各种方法获取元素


    //提供标签值及需要输入的值
    public void ById(String indentify, String value) {
        WebElement name = webDriver.findElement(By.id(indentify));
        name.clear();
        name.sendKeys(value);
    }

    public void BycssSelector(String indentify, String value) {
        WebElement name = webDriver.findElement(By.cssSelector(indentify));
        name.clear();
        name.sendKeys(value);
    }

    //    找不到的时候需要用xpath来定位
    public void ByXpath(String indentify, String value) {
        WebElement name = webDriver.findElement(By.xpath(indentify));
        name.click();
        name.sendKeys(value);
    }

    public void ByName(String indentify, String value) {
        WebElement name = webDriver.findElement(By.name(indentify));
        name.clear();
        name.sendKeys(value);
    }

    public WebElement BylinkText(String linkvalue) {
        return webDriver.findElement(By.linkText(linkvalue));
    }

    public void click(String indentify, String type) throws Exception {
        if (type == "xpath") {
            WebElement button = webDriver.findElement(By.xpath(indentify));
            Thread.sleep(2000);
            button.click();
        } else if (type == "css") {
            WebElement button = webDriver.findElement(By.cssSelector(indentify));
            button.click();
        } else if (type == "id") {
            WebElement button = webDriver.findElement(By.id(indentify));
            button.click();

        } else if (type == "linktext") {
            WebElement button = webDriver.findElement(By.linkText(indentify));
            button.click();
        }

    }

    private boolean waitForElement(final By elementLocator, WebDriver webDriver) {
        try {
            WebDriverWait driverWait = (WebDriverWait) new WebDriverWait(webDriver, 5, 500).ignoring(
                    StaleElementReferenceException.class).withMessage("元素在30秒内没有出现!");
            return driverWait.until(new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driver) {
                    try {
                        if (driver.findElement(elementLocator).isDisplayed()) {
                            return false;
                        }
                    } catch (IndexOutOfBoundsException e) {
                    } catch (NoSuchElementException e) {
                    }
                    return true;
                }
            });
        } catch (Exception e) {
            return false;
        }
    }

    //        判断某个元素有没有出现   这个经常使用
    public boolean isContentAppeared(String content) {
        boolean flag = false;
        try {
            webDriver.findElement(By.xpath("//*[contains(.,'" + content + "')]"));  //如果定位不到元素的话 会抛出异常  此时flage设置为false
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    //    最大化浏览器
    public void maxWindow() {
        logger.info("最大化浏览器 ！");
        webDriver.manage().window().maximize();
    }

    //    设置浏览器框框的大小
    public void setBrowserSize(int width, int height) {
        webDriver.manage().window().setSize(new Dimension(width, height));
    }

    //    打开浏览器
    public void get(String url) {
        webDriver.get(url);
        logger.info("打开测试页面:[" + url + "]");
    }

    //    关闭
    public void close() {
        webDriver.close();
    }

    public void quit() {
        webDriver.quit();
    }


    //    刷新
    public void refresh() {
        webDriver.navigate().refresh();
        logger.info("刷新页面成功！");
    }

    //    后退
    public void back() {
        webDriver.navigate().back();
    }

    //    前进
    public void forward() {
        webDriver.navigate().forward();
    }

    //    获得标题
    public String getTitle() {
        return webDriver.getTitle();
    }

    //    等待alert出现
    public Alert switchToPromptedAlertAfterWait(long waitMillisecondsForAlert) throws NoAlertPresentException {
        final int ONE_ROUND_WAIT = 200;
        NoAlertPresentException lastException = null;

        long endTime = System.currentTimeMillis() + waitMillisecondsForAlert;

        for (long i = 0; i < waitMillisecondsForAlert; i += ONE_ROUND_WAIT) {

            try {
                Alert alert = webDriver.switchTo().alert();
                return alert;
            } catch (NoAlertPresentException e) {
                lastException = e;
            }
            pause(ONE_ROUND_WAIT);

            if (System.currentTimeMillis() > endTime) {
                break;
            }
        }
        throw lastException;
    }

    //    当操作的是windows GUI的弹出框时  要求输入用户名和密码 selenium不能直接操作 需要借助http://modifyusername:modifypassword@yoururl方法 具体如下
    public void loginOnWindowsGui(String username, String password, String url) {
        webDriver.get(username + ":" + password + "@" + url);
    }

    //    判断文本是不是跟需求要求的文档一致
    public void isTextCorrect(String actural, String expected) {
        try {
            Assert.assertEquals(actural, expected);
        } catch (AssertionError e) {
            logger.error("期望的关键字是[" + expected + "] 但是找到了 [" + actural + "]");
            Assert.fail("期望的关键字是[" + expected + "] 但是找到了 [" + actural + "]");
        }
        logger.info("找到了期望的关键字[" + expected + "]");

    }

    public void isTextCorrect(boolean actural, boolean expected) {
        try {
            Assert.assertEquals(actural, expected);
        } catch (AssertionError e) {
//            logger.error("期望的关键字是[" + expected + "] 但是找到了 [" + actural + "]");
            Assert.fail("期望的关键字是[" + expected + "] 但是找到了 [" + actural + "]");
        }
        logger.info("找到了期望的关键字[" + expected + "]");

    }


    //    暂停当前用例的执行  暂停的时间是sleepTime
    private void pause(int sleepTime) {
        if (sleepTime <= 0) {
            return;
        }
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //    添加cookie  做自动登录的必要方法  自动填充变量
    public void addCookies(String cookiename) {
//        pause(sleepTime);
        Set<Cookie> cookies = webDriver.manage().getCookies();
        for (Cookie c : cookies) {
            System.out.println(c.getName() + "--------->" + c.getValue());
            if (c.getName().equals(cookiename)) {
                Cookie cookie = new Cookie(cookiename, c.getValue());
                webDriver.manage().addCookie(cookie);
                System.out.println(c.getName() + "--------->" + c.getValue());
                System.out.println("添加成功！");
            } else {
                System.out.println("没有找到" + cookiename + "的cookie值，无法设置");
                logger.info("没有找到" + cookiename + "的cookie值，无法设置");

            }
        }
    }

    //    最长等待时间
    public void implicitlyWait(int timeout) {
        webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }


}
