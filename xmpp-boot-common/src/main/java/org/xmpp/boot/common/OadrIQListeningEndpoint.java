package org.xmpp.boot.common;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Stanza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.xmpp.core.AbstractXmppConnectionAwareEndpoint;

public class OadrIQListeningEndpoint extends AbstractXmppConnectionAwareEndpoint {

	private Logger log = LoggerFactory.getLogger(OadrIQListeningEndpoint.class);

	private final StanzaListener stanzaListener = new OadrIQListener();

	public OadrIQListeningEndpoint(XMPPConnection xmppConnection) {
		super(xmppConnection);
	}

	@Override
	public String getComponentType() {
		return "oadr-iq:oadr-iq-inbound-channel-adapter";
	}

	@Override
	protected void doStart() {
		this.xmppConnection.addAsyncStanzaListener(this.stanzaListener, new OadrIQFilter());
	}

	@Override
	protected void doStop() {
		if (this.xmppConnection != null) {
			this.xmppConnection.removeAsyncStanzaListener(this.stanzaListener);
		}
	}

	private class OadrIQListener implements StanzaListener {
		@Override
		public void processPacket(Stanza packet) throws NotConnectedException {
			log.info("IQリスナー(" + packet + ")");
			sendMessage(getMessageBuilderFactory().withPayload(packet).build());
		}
	}
}
