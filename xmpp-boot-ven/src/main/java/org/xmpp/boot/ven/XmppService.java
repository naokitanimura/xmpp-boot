package org.xmpp.boot.ven;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import org.xmpp.boot.common.OadrIQ;

@Component
public class XmppService {

	private Logger log = LoggerFactory.getLogger(XmppService.class);

	@Autowired
	@Qualifier("xmppConnection")
	private XMPPConnection conn;

	@ServiceActivator(inputChannel = "xmppInbound", outputChannel = "xmppOutbound")
	public OadrIQ set(OadrIQ iq) throws NotConnectedException {
		log.info("[VEN] IQスタンザ受信[{}]", iq);

		OadrIQ iqResult = new OadrIQ();
		iqResult.setType(IQ.Type.result);
		iqResult.setStanzaId(iq.getStanzaId());
		iqResult.setTo(iq.getFrom());
		iqResult.setFrom(iq.getTo());
		return iqResult;
	}
}
