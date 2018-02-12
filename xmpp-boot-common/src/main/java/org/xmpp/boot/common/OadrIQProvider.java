package org.xmpp.boot.common;

import java.io.IOException;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.provider.IQProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class OadrIQProvider extends IQProvider<OadrIQ> {

	private Logger log = LoggerFactory.getLogger(OadrIQProvider.class);

	private final String tag;
	
	public OadrIQProvider(String tag) {
		this.tag = tag;
	}

	@Override
	public OadrIQ parse(XmlPullParser parser, int initialDepth) throws XmlPullParserException, IOException, SmackException {
		OadrIQ iq = new OadrIQ();

		if (parser.getName().equals("oadrPayload")) {
			int eventType = parser.next();
			if (eventType == XmlPullParser.TEXT) {
				log.info("[{}] IQスタンザパース oadrPayload: {}", tag, parser.getText());
			}
		}

		return iq;
	}

}
