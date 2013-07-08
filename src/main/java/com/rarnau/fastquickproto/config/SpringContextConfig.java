/**
 *
 */
package com.rarnau.fastquickproto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.DefaultKeyGenerator;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.mongodb.Mongo;
import com.rarnau.fastquickproto.social.MongoUserDetailsService;

/**
 * @author Ramón Arnau Gómez, 2013
 * 
 */
@Configuration
// @EnableMongoRepositories(basePackages = {
// "com.rarnau.fastquickproto.repository" })
@ComponentScan({ "com.rarnau.fastquickproto.service", "com.rarnau.fastquickproto.repository.impl" })
@ImportResource(value = { "classpath:net/bull/javamelody/monitoring-spring.xml", "/WEB-INF/security-context.xml" })
public class SpringContextConfig implements CachingConfigurer {

	public static final String MONGO_DATABASE = "database";

	@Autowired
	@Value(value = "classpath:ehcache.xml")
	private Resource ehCacheXmlResource;

	@Bean
	public Mongo mongo() throws Exception {
		return new Mongo();
	}

	@Bean
	public MongoOperations mongoOperations() throws Exception {
		return new MongoTemplate(mongo(), MONGO_DATABASE);
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), MONGO_DATABASE);
	}

	@Override
	@Bean
	public CacheManager cacheManager() {
		EhCacheCacheManager manager = new org.springframework.cache.ehcache.EhCacheCacheManager();
		manager.setCacheManager(ehCacheManager());
		return manager;
	}

	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager() {
		net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.getInstance();
		return cacheManager;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return new DefaultKeyGenerator();
	}

	@Bean
	public FormattingConversionService conversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);
		conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd-MM-yyyy"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}

	@Bean
	public UserDetailsService userDetailsService() throws Exception {
		return new MongoUserDetailsService(mongoTemplate());
	}

	@Bean(name = "messageSource")
	MessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages");
		source.setDefaultEncoding("utf-8");
		source.setCacheSeconds(5);
		return source;
	}

}
