package org.xmpp.boot.vtn;

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
	
	@ServiceActivator(inputChannel = "xmppInbound")
	public void set(OadrIQ iq) {
        log.info("[VTN] IQスタンザ受信[{}]", iq);
    }

	@ServiceActivator(inputChannel = "httpInbound", outputChannel = "xmppOutbound")
	public OadrIQ requestXmppSet() throws NotConnectedException {
        log.info("[VTN] IQスタンザ送信HTTPリクエスト受信");

        OadrIQ iqRequest = new OadrIQ();
		iqRequest.setType(IQ.Type.set);
		iqRequest.setTo("ven@172.16.2.132/OpenADR");
		return iqRequest;
    }
}
