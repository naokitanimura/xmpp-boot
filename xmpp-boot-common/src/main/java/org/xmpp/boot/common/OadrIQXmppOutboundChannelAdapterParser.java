package org.xmpp.boot.common;

import org.springframework.integration.xmpp.config.AbstractXmppOutboundChannelAdapterParser;

public class OadrIQXmppOutboundChannelAdapterParser extends AbstractXmppOutboundChannelAdapterParser {

	@Override
	protected String getHandlerClassName() {
		return OadrIQSendingMessageHandler.class.getName();
	}

}
