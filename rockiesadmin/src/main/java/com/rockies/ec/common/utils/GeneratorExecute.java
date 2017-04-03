package com.rockies.ec.common.utils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneratorExecute {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorExecute.class);
    
	public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
		try{
		    List<String> warnings = new ArrayList<String>();  
	        boolean overwrite = true;  
	        File configFile = new File("generator/generatorConfig-yiqi.xml");  
	        ConfigurationParser cp = new ConfigurationParser(warnings);  
	        Configuration config = cp.parseConfiguration(configFile);   
	        DefaultShellCallback callback = new DefaultShellCallback(overwrite);  
	        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);  
	        myBatisGenerator.generate(null);
		}catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+GeneratorExecute.class+".packageAdmin " + e.toString());
        }
	    
		
	}
}
