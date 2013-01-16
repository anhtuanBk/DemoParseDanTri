package com.dantri.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.xml.sax.SAXException;

import android.util.Log;
import android.util.Xml;

import com.dantri.items.Item;
import com.dantri.mylog.MyLog;

public class NetworkHandler {
	private static NetworkHandler instance;
	private final String urlDantri = "http://vnexpress.net/rss/gl/xa-hoi.rss";
	private final String urlSport = "http://thethao.vnexpress.net/rss/tin-moi-nhat.rss";	
	private OnDataReceiver listener;
	private static StringBuffer buffer;
	
	
	public static NetworkHandler getInstance() {
		if (null == instance) {
			Log.i("Single","Khoi tao lan dau");
			instance = new NetworkHandler();
		}else{
			Log.i("Single","Da ton tai");
		}
		return instance;
	}

	public void requestRSS() {
		Thread thread = new Thread(reqRss);
		thread.start();
		
		Thread thread1 = new Thread(reqRssSport);
		thread1.start();
	}
	
	public void requestRSSSport(){
		Thread thread = new Thread(reqRssSport);
		thread.start();
	}
	
	
	Runnable reqRssSport = new Runnable() {
		@Override
		public void run() {
			try {
				URL url = new URL(urlSport);
				URLConnection urlConnection =  url.openConnection();
				urlConnection.connect();
				InputStream is = urlConnection.getInputStream();
				ParseHandler myXMLHandler = new ParseHandler();
				Xml.parse(is, Xml.Encoding.UTF_8, myXMLHandler);
				ArrayList<Item> datas = myXMLHandler.getDatas();
				listener.onData(datas);
				MyLog.i("Network", "Get Data DONE");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}
	};
	

	Runnable reqRss = new Runnable() {
		private String data;

		@Override
		public void run() {
			try {
				URL url = new URL(urlDantri);
				URLConnection urlConnection =  url.openConnection();
				urlConnection.connect();
				InputStream is = urlConnection.getInputStream();
				ParseHandler myXMLHandler = new ParseHandler();
				Xml.parse(is, Xml.Encoding.UTF_8, myXMLHandler);
				
				ArrayList<Item> datas = myXMLHandler.getDatas();
				listener.onData(datas);
				Log.i("Network", "Get Data DONE");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}

			// Parse XML
			// Su dung Handler de update nguoc ve UI
		}
	};
	
	public void setOnDataRecieverListener(OnDataReceiver listener) {
		this.listener = listener;
	}

	public void destroy() {
		instance = null;
	}
}
