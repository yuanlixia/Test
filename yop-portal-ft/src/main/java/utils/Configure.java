package utils;

import browser.SelectBrowser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by 袁丽霞 on 18/9/27.
 * 配置文件 主要是方便从配置文件中读取
 */
public class Configure {
    static Logger logger = Logger.getLogger(SelectBrowser.class.getName());
    public Properties config;

    public Configure() {
        config = new Properties();
    }

    public Configure(String filePath) {
        config = new Properties();
        try {
            FileInputStream in = new FileInputStream(filePath);
            config.load(in);
        } catch (Exception e) {
            System.out.println("文件读取错误！");
        }
    }

    public String getTestData(String filePath, String key) {
//直接在里面传入路径及key  直接读取值
        try {
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream in;
            if (cl != null) {
                in = cl.getResourceAsStream(filePath);
            } else {
                in = ClassLoader.getSystemResourceAsStream(filePath);
            }
            config.load(in);
        } catch (FileNotFoundException e) {
            System.out.println("配置文件没有读到");
        } catch (Exception e) {
            System.out.println("配置文件读取错误");

        }

        return String.valueOf(config.getProperty(key));

    }

    public String getValue(String key) {
        if (config!=null) {
            String value = config.getProperty(key);
            return value;
        } else {
            return " ";
        }
    }

    public int getValueint(String key) {
        String value = getValue(key);
        int valueInt = 0;
        try {
            valueInt = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return valueInt;
        }
        return valueInt;
    }


}
