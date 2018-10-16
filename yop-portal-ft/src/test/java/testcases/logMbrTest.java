package testcases;

import org.testng.annotations.Test;
import utils.BaseParpare;

/**
 * Created by 袁丽霞 on 17/5/19.
 */
public class logMbrTest extends BaseParpare {

    //登陆mbr测试  每次@Test测试用例执行时都会先进行登录操作  这里可以判断有没有登陆成功
    @Test
    public void mbr_login() throws Exception {
//如果登陆成功进入首页时  点击进入控制台  查看title是不是登陆后的值   下面两种判断二选一即可
        login.loginMbr(webdriverUtil);
        boolean flag = webdriverUtil.isContentAppeared("文档中心");
        webdriverUtil.isTextCorrect(flag, true);
    }

}


















