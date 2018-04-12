package high.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.util.BeanUtil;

/**
 * 
 * @包名 :high.config
 * @文件名 :MyBatisConfig.java 
 * TODO 类作用：
 * @系统名称 : springboot集成mybatis基本入口
 * 1)创建数据源
 * 2)创建SqlSessionFactory
 * @Author: 蒋帅锋 
 * @Date: 2017年12月12日 上午11:33:14
 * @版本号   :v0.0.01
 */
@Configuration
@MapperScan(basePackages="high.mapper")
public class Config {
	
	@Autowired
	private Environment vir;
	
	/**
	 * 
	 * TODO 方法作用：创建数据源
	 * @return 数据源
	 * @throws Exception
	 * @Author: 蒋帅锋
	 * @Date: 2017年12月12日 上午11:41:34
	 */
	@Bean
	public DataSource getDataSource() throws Exception{
		Properties prop = new Properties();
		prop.put("driverClassName", vir.getProperty("jdbc.driverClassName"));
		prop.put("url", vir.getProperty("jdbc.url"));
		prop.put("username", vir.getProperty("jdbc.username"));
		prop.put("password", vir.getProperty("jdbc.password"));
		return DruidDataSourceFactory.createDataSource(prop);
	}
	
	/**
	 * 
	 * TODO 方法作用：根据数据源创建SqlSessionFactory
	 * @param ds
	 * @return SqlSessionFactory
	 * @throws Exception
	 * @Author: 蒋帅锋
	 * @Date: 2017年12月12日 上午11:51:07
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(ds);
		fb.setTypeAliasesPackage(vir.getProperty("mybatis.typeAliasesPackage"));
		fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(vir.getProperty("mybatis.mapperLocations")));
		return fb.getObject();
	}
	/**
	 * 
	 * TODO 方法作用：jsp解析器
	 * @return
	 * @Author: 蒋帅锋
	 * @Date: 2017年12月12日 下午4:50:05
	 */
	@Bean
	public ViewResolver getViewResolver(){
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/jsp/");
	    resolver.setSuffix(".jsp");
	    resolver.setViewClass(JstlView.class);
	    return resolver;
	}
}
