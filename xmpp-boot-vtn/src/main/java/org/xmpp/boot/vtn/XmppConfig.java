package org.xmpp.boot.vtn;

import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.xmpp.config.XmppConnectionFactoryBean;
import org.xmpp.boot.common.OadrIQProvider;

@Configuration
@ImportResource("classpath:integration-xmpp.xml")
public class XmppConfig {
	
	@Bean("xmppConnection")
	public XmppConnectionFactoryBean xmppConnectionFactoryBean() {

		ProviderManager.addIQProvider("oadrPayload", "http://oadr", new OadrIQProvider("VTN"));
		
		XMPPTCPConnectionConfiguration connectionConfiguration = XMPPTCPConnectionConfiguration.builder()
				.setServiceName("domain")
				.setHost("host")
				.setPort(5222)
				.setUsernameAndPassword("vtn", "password")
				.setResource("OpenADR")
				.setSecurityMode(SecurityMode.disabled)
				.build();

		XmppConnectionFactoryBean connectionFactoryBean = new XmppConnectionFactoryBean();
		connectionFactoryBean.setConnectionConfiguration(connectionConfiguration);
		connectionFactoryBean.setSubscriptionMode(null);

		return connectionFactoryBean;
	}
}
