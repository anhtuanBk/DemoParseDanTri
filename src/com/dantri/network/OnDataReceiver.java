package com.dantri.network;

import java.util.ArrayList;

import com.dantri.items.Item;

public interface OnDataReceiver {
	public void onData(ArrayList<Item> datas);
}
