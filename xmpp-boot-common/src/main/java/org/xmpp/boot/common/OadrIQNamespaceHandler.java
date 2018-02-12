package org.xmpp.boot.common;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class OadrIQNamespaceHandler extends NamespaceHandlerSupport {
	
	@Override
	public void init() {
		registerBeanDefinitionParser("oadr-iq-inbound-channel-adapter", new OadrIQXmppInboundChannelAdapterParser());
		registerBeanDefinitionParser("oadr-iq-outbound-channel-adapter", new OadrIQXmppOutboundChannelAdapterParser());
	}
}
