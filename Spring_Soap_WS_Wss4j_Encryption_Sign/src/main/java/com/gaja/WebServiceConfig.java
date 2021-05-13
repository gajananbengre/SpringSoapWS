package com.gaja;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.KeyStoreCallbackHandler;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    // bean definitions
	
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
	    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
	    servlet.setApplicationContext(applicationContext);
	    servlet.setTransformWsdlLocations(true);
	    return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}
	
	@Bean
    public KeyStoreCallbackHandler securityCallbackHandler(){
        KeyStoreCallbackHandler callbackHandler = new KeyStoreCallbackHandler();
        callbackHandler.setPrivateKeyPassword("serverpass");
        return callbackHandler;
    }
	
//	@Bean
//	SimplePasswordValidationCallbackHandler securityCallbackHandler() {
//		SimplePasswordValidationCallbackHandler callbackHandler = new SimplePasswordValidationCallbackHandler();
//        Properties users = new Properties();
//        users.setProperty("admin", "pwd123");
//        callbackHandler.setUsers(users);
//        return callbackHandler;
//	}
	
	@Bean
    public CryptoFactoryBean getCryptoFactoryBean() throws IOException {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStorePassword("serverpass");
        cryptoFactoryBean.setKeyStoreLocation(new ClassPathResource("server.jks"));
        return cryptoFactoryBean;
    }
	
	@Bean
    public Wss4jSecurityInterceptor securityInterceptor() throws Exception{
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
        securityInterceptor.setValidationActions("Signature Timestamp Encrypt");
        securityInterceptor.setValidationCallbackHandler(securityCallbackHandler());
        securityInterceptor.setValidationSignatureCrypto(getCryptoFactoryBean().getObject());
        securityInterceptor.setValidationDecryptionCrypto(getCryptoFactoryBean().getObject());

        // encrypt the response
        securityInterceptor.setSecurementEncryptionUser("client");
        securityInterceptor.setSecurementEncryptionParts("{Content}{http://com.tcs.bancs/countries/schema}getCountryResponse");
        securityInterceptor.setSecurementEncryptionCrypto(getCryptoFactoryBean().getObject());

        // sign the response
        securityInterceptor.setSecurementActions("Signature Encrypt");
        securityInterceptor.setSecurementUsername("server");
        securityInterceptor.setSecurementPassword("serverpass");
        securityInterceptor.setSecurementSignatureCrypto(getCryptoFactoryBean().getObject());
        return securityInterceptor;
    }

	@Bean
	PayloadValidatingInterceptor payloadValidatingInterceptor() {
		final PayloadValidatingInterceptor payloadValidatingInterceptor = new PayloadValidatingInterceptor();
		payloadValidatingInterceptor.setSchema(new ClassPathResource("/xsds/countries.xsd"));
		return payloadValidatingInterceptor;
	}

	
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors){
		interceptors.add(payloadValidatingInterceptor());
		try {
			interceptors.add(securityInterceptor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
    
}