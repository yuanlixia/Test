package Listener;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

/**
 * Created by 袁丽霞 on 17/5/26.
 * * 对截图类的测试  以后所有用截图监听功能的方法都需要继承这个类 这里设置一个公用的参数driver 可以在DotTestListener中使用
 * 继承他的类里可以各自分别定义
 */
@Listeners(DotTestListener.class) // 添加监听器
public class TestBase {
    public WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }
    public void setDriver(WebDriver driver1) {
         this.driver=driver1;
    }


}
