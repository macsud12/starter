package com.macs.starter;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Main Application class
 */
@ComponentScan(basePackages = "com.macs.starter")
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(
            @Value("${keystore.file}") final Resource keystoreFile,
            @Value("${keystore.alias}") final String keystoreAlias,
            @Value("${keystore.type}") final String keystoreType,
            @Value("${keystore.pass}") final String keystorePass,
            @Value("${tls.port}") final int tlsPort
    ) {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer factory) {
                if (factory instanceof TomcatEmbeddedServletContainerFactory) {
                    TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) factory;
                    containerFactory.addConnectorCustomizers(new TomcatConnectorCustomizer() {

                        @Override
                        public void customize(Connector connector) {

                            connector.setPort(tlsPort);
                            connector.setSecure(true);
                            connector.setScheme("https");
                            connector.setAttribute("keyAlias", keystoreAlias);
                            connector.setAttribute("keystorePass", keystorePass);
                            String absoluteKeystoreFile;
                            try {
                                absoluteKeystoreFile = keystoreFile.getFile().getAbsolutePath();
                                connector.setAttribute("keystoreFile", absoluteKeystoreFile);
                            } catch (IOException e) {
                                throw new IllegalStateException("Cannot load keystore", e);
                            }
                            connector.setAttribute("clientAuth", "false");
                            connector.setAttribute("sslProtocol", "TLS");
                            connector.setAttribute("SSLEnabled", true);

                            Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
                            proto.setSSLEnabled(true);
                            // proto.setClientAuth();
                            // uncomment this to require the
                            // client to authenticate. Then, you can use X509 support in Spring Security
                            proto.setKeystoreFile(absoluteKeystoreFile);
                            proto.setKeystorePass(keystorePass);
                            proto.setKeystoreType(keystoreType);
                            proto.setKeyAlias(keystoreAlias);
                        }
                    });

                }
            }
        };
    }

}
