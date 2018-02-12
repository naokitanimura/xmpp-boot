package org.xmpp.boot.common;

import org.springframework.integration.xmpp.config.AbstractXmppInboundChannelAdapterParser;
import org.w3c.dom.Element;

public class OadrIQXmppInboundChannelAdapterParser extends AbstractXmppInboundChannelAdapterParser {

	@Override
	protected String getBeanClassName(Element element) {
		return "org.xmpp.boot.common.OadrIQListeningEndpoint";
	}

}
