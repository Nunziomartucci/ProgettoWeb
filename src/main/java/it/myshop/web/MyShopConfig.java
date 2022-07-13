package it.myshop.web;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import it.myshop.web.dao.ClienteDao;
import it.myshop.web.dao.impl.ClienteDaoImpl;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "it.myshop.web.controller")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "it.myshop.web.repository")
public class MyShopConfig {

	@Bean
	public FreeMarkerViewResolver configureResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(true);
		//qui definiremo un FreeMarkerConfigurer
		//che indicherà dove si trovano i file .ftl
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		
		return resolver;
	}
	
	@Bean
	public FreeMarkerConfigurer configureFreeMarker() {
		FreeMarkerConfigurer config = new FreeMarkerConfigurer();
		config.setTemplateLoaderPath("/WEB-INF/view/");
		
		return config;
	}
	
	@Bean
	public DataSource getDbConnection() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.mariadb.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setUrl("jdbc:mariadb://localhost:3307/corso_spring");
		
		return ds;
	}
	
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManager() {
		//specifichiamo che il vendor è Hibernate
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		//tramite questo oggetto indichiamo il tipo di database utilizzato
		//dobbiamo passare come parametro un enum 
		//in questo caso stiamo usando MariaDB quindi selezioniamo MYSQL
		adapter.setDatabase(Database.MYSQL);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		//tramite questo oggetto setto il DataSource creato in getDbConnection()
		factory.setDataSource(getDbConnection());
		factory.setJpaVendorAdapter(adapter);
		//indichiamo il percorso dei packages dove si troveranno le classi Dao 
		//che conterranno le implementazioni e l'utilizzo dell'EntityManager
		//o scriviamo esplicitamente il percorso come stringa
		//oppure tramite getClass() recuperiamo la classe corrente - MyShopConfig
		//tramite getPackage() recuperiamo il package di MyShopConfig - it.myshop.web
		//tramite getClass() recuperiamo il nome del package
		//quindi verrà scannerizzato l'intero package it.myshop.web e tutti i suoi sottopackage
		factory.setPackagesToScan(getClass().getPackage().getName());
		
		return factory;
	}
	
	@Bean(name="transactionManager")
	public PlatformTransactionManager getTransactionManager() {
		JpaTransactionManager jtm = new JpaTransactionManager();
		//settiamo per questo oggetto l'EntityManager factory creato nel metodo getEntityManager()
		//in alternativa possiamo passare l'EntityManager  direttamente come parametro del costruttore
		//JpaTransactionManager jtm = new JpaTransactionManager(getEntityManager().getObject());
		jtm.setEntityManagerFactory(getEntityManager().getObject());
		
		return jtm;
	}
	
	@Bean
	public ClienteDao getClienteService() {
		return new ClienteDaoImpl();
	}
	
}
