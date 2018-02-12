package org.xmpp.boot.common;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OadrIQFilter implements StanzaFilter {

	private Logger log = LoggerFactory.getLogger(OadrIQFilter.class);

	public boolean accept(Stanza stanza) {
		log.info("IQフィルター(" + stanza + ")");
		if (stanza instanceof OadrIQ) {
			System.out.println("IQフィルター -> OK");
			return true;
		}
		System.out.println("IQフィルター -> NG");
		return false;
	}

}
