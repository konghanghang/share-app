package com.ysla.api.utils.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成器
 * @author konghang
 * https://segmentfault.com/a/1190000016525887
 */
public class Generator {

    public static void main( String[] args ) throws Exception {
        // TODO 未成功
        List<String> warnings = new ArrayList<>();
        System.out.println(System.getProperty("user.dir"));
        System.out.println(Generator.class.getResource(""));
        System.out.println(Generator.class.getResource("/"));

        ConfigurationParser cp = new ConfigurationParser(warnings);

        /*ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("generatorConfig.xml");
        Configuration config = cp.parseConfiguration(is);
        */

        URL configFileUrl = Generator.class.getClassLoader().getResource("generatorConfig.xml");
        File configFile = new File(configFileUrl.toURI());
        Configuration config = cp.parseConfiguration(configFile);

        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

}
