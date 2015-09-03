package com.gxl.kratos.utils.test;

import java.io.File;
import org.junit.Test;
import com.gxl.kratos.utils.xml.CreateXml;

/**
 * 生成kratos的配置文件测试类
 * 
 * @author gaoxianglong
 */
public class XmlTest {
	/**
	 * 生成核心配置文件
	 * 
	 * @author gaoxianglong
	 */
	public @Test void testCreateCoreXml() {
		CreateXml c_xml = new CreateXml();
		/* 是否控制台输出生成的配置文件 */
		c_xml.setIsShow(true);
		/* 配置分库分片信息 */
		c_xml.setDbSize("64");
		c_xml.setShard("true");
		c_xml.setWr_weight("r0w32");
		c_xml.setShardMode("false");
		c_xml.setConsistent("false");
		c_xml.setDbRuleArray("#userinfo_id|email_hash# % 1024 / 32");
		c_xml.setTbRuleArray("#userinfo_id|email_hash# % 1024 % 32");
		/* 执行配置文件输出 */
		System.out.println(c_xml.createCoreXml(new File("e:/kratos-context.xml")));
	}

	/**
	 * 生成数据源配置文件
	 * 
	 * @author gaoxianglong
	 */
	public @Test void testCreateDadasourceXml() {
		CreateXml c_xml = new CreateXml();
		/* 是否控制台输出生成的配置文件 */
		c_xml.setIsShow(true);
		/* 数据源索引起始 */
		c_xml.setDataSourceIndex(1);
		/* 配置分库分片信息 */
		c_xml.setDbSize("16");
		/* 配置数据源信息 */
		c_xml.setJdbcUrl("jdbc:mysql://114.215.110.169:3306/um");
		c_xml.setUser("${name}");
		c_xml.setPassword("${password}");
		c_xml.setDriverClass("${driverClass}");
		c_xml.setInitialPoolSize("${initialPoolSize}");
		c_xml.setMinPoolSize("${minPoolSize}");
		c_xml.setMaxPoolSize("${maxPoolSize}");
		c_xml.setMaxStatements("${maxStatements}");
		c_xml.setMaxIdleTime("${maxIdleTime}");
		/* 执行配置文件输出 */
		System.out.println(c_xml.createDatasourceXml(new File("e:/dataSource-context.xml")));
	}

	/**
	 * 生成master/slave数据源配置文件
	 * 
	 * @author gaoxianglong
	 */
	public @Test void testCreateMSXml() {
		CreateXml c_xml = new CreateXml();
		c_xml.setIsShow(true);
		/* 生成master数据源信息 */
		c_xml.setDataSourceIndex(1);
		c_xml.setDbSize("32");
		c_xml.setJdbcUrl("jdbc:mysql://114.215.110.169:3306/um");
		c_xml.setUser("${name}");
		c_xml.setPassword("${password}");
		c_xml.setDriverClass("${driverClass}");
		c_xml.setInitialPoolSize("${initialPoolSize}");
		c_xml.setMinPoolSize("${minPoolSize}");
		c_xml.setMaxPoolSize("${maxPoolSize}");
		c_xml.setMaxStatements("${maxStatements}");
		c_xml.setMaxIdleTime("${maxIdleTime}");
		System.out.println(c_xml.createDatasourceXml(new File("e:/masterDataSource-context.xml")));
		/* 生成slave数据源信息 */
		c_xml.setDataSourceIndex(33);
		c_xml.setDbSize("32");
		c_xml.setJdbcUrl("jdbc:mysql://115.215.110.180:3306/um");
		c_xml.setUser("${name}");
		c_xml.setPassword("${password}");
		c_xml.setDriverClass("${driverClass}");
		c_xml.setInitialPoolSize("${initialPoolSize}");
		c_xml.setMinPoolSize("${minPoolSize}");
		c_xml.setMaxPoolSize("${maxPoolSize}");
		c_xml.setMaxStatements("${maxStatements}");
		c_xml.setMaxIdleTime("${maxIdleTime}");
		System.out.println(c_xml.createDatasourceXml(new File("e:/slaveDataSource-context.xml")));
	}
}