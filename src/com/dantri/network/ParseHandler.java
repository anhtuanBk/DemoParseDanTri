package com.dantri.network;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.dantri.items.Item;

public class ParseHandler extends DefaultHandler {
	private ArrayList<Item> datas;
	private Item item;
	private boolean insideItemTag;
	private StringBuffer buff = null;
	private boolean buffering;
	private boolean insideTitleTag;

	public ArrayList<Item> getDatas() {
		return datas;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		datas = new ArrayList<Item>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (localName.equals("item")) {
			item = new Item();
			insideItemTag = true;
		} else if (localName.equals("title") && insideItemTag) {
			buff = new StringBuffer("");
			buffering = true;
			insideTitleTag = true;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		if (buffering) {
			buff.append(ch, start, length);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.equalsIgnoreCase("title") && insideTitleTag) {
			item.setTitle(buff.toString());
			insideTitleTag = false;
			buffering = false;
			buff.delete(0, buff.length());
		}else if(localName.equalsIgnoreCase("item")){
			datas.add(item);
			insideItemTag = false;
		}
	}
}
