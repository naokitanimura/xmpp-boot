package org.xmpp.boot.common;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.XMPPConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.xmpp.core.AbstractXmppConnectionAwareMessageHandler;
import org.springframework.messaging.Message;

public class OadrIQSendingMessageHandler extends AbstractXmppConnectionAwareMessageHandler {

	private Logger log = LoggerFactory.getLogger(OadrIQSendingMessageHandler.class);

	public OadrIQSendingMessageHandler(XMPPConnection xmppConnection) {
		super(xmppConnection);
	}

	@Override
	protected void handleMessageInternal(Message<?> message) throws Exception {
		log.info("送信IQスタンザハンドル [{}]", message);
		OadrIQ iq = (OadrIQ) message.getPayload();
		if (!this.xmppConnection.isConnected() && this.xmppConnection instanceof AbstractXMPPConnection) {
			((AbstractXMPPConnection) this.xmppConnection).connect();
		}
		log.info("IQスタンザ送信 [{}]", iq);
		this.xmppConnection.sendStanza(iq);
	}

}
