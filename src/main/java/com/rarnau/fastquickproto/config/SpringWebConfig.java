/**
 *
 */
package com.rarnau.fastquickproto.config;

import javax.inject.Inject;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.rarnau.fastquickproto.service.UsuarioService;
import com.rarnau.fastquickproto.social.ConnectionService;
import com.rarnau.fastquickproto.social.MongoConnectionConverter;
import com.rarnau.fastquickproto.social.MongoConnectionService;
import com.rarnau.fastquickproto.social.MongoSignInAdapter;
import com.rarnau.fastquickproto.social.MongoUsersConnectionRepository;

/**
 * @author Ramón Arnau Gómez, 2013
 *
 */
@Configuration
@EnableWebMvc
@EnableCaching
@ComponentScan("com.rarnau.fastquickproto.web")
public class SpringWebConfig extends WebMvcConfigurerAdapter {

//	@Inject
//	private Environment environment;

	@Inject
	private MongoTemplate mongoTemplate;

	@Inject
	private TextEncryptor textEncryptor;

	@Configuration
	@Profile("dev")
	static class Dev {

		@Bean
		public TextEncryptor textEncryptor() {
			return Encryptors.noOpText();
		}

	}

	@Configuration
	@Profile("prod")
	static class Prod {

		@Inject
		private Environment environment;

		@Bean
		public TextEncryptor textEncryptor() {
			String encryptPassword = environment.getProperty("security.encryptPassword");
			String encryptSalt = environment.getProperty("security.encryptSalt");
			return Encryptors.queryableText(encryptPassword, encryptSalt);
		}

	}

	@Bean(name = "viewResolver")
	public ViewResolver viewResolver(WebApplicationContext context) {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setApplicationContext(context);
		viewResolver.setServletContext(context.getServletContext());
		return viewResolver;
	}

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();

		// String fbCliId = environment.getProperty("facebook.clientId");
		// String fbSecret = environment.getProperty("facebook.clientSecret");
		// String twKey = environment.getProperty("twitter.consumerKey");
		// String twSecret = environment.getProperty("twitter.consumerSecret");
		//
		// registry.addConnectionFactory(new FacebookConnectionFactory(fbCliId, fbSecret));
		// registry.addConnectionFactory(new TwitterConnectionFactory(twKey, twSecret));

		return registry;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
		}
		return usersConnectionRepository().createConnectionRepository(authentication.getName());
	}

	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		return new MongoUsersConnectionRepository(mongoConectionService(), connectionFactoryLocator(), textEncryptor);
	}

	@Bean
	public ConnectionService mongoConectionService() {
		return new MongoConnectionService(mongoTemplate, mongoConectionConverter());
	}

	@Bean
	public MongoConnectionConverter mongoConectionConverter() {
		return new MongoConnectionConverter(connectionFactoryLocator(), textEncryptor);
	}

	/**
	 * http://static.springsource.org/spring-social/docs/1.0.x/reference/html/connecting.html
	 */
	@Bean
	public ConnectController connectController() {
		return new ConnectController(connectionFactoryLocator(), connectionRepository());
	}

	@Bean
	public MongoSignInAdapter mongoSignInAdapter(UsuarioService personaService) {
		return new MongoSignInAdapter(personaService);
	}

	@Bean
	public ProviderSignInController providerSignInController(UsuarioService personaService) {
		ProviderSignInController controller = new ProviderSignInController(connectionFactoryLocator(),
				usersConnectionRepository(), mongoSignInAdapter(personaService));
		return controller;
	}

}
