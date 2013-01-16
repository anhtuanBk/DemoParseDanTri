//test git
//test fetch
//rrrrrrrrrrrrrrrrrrrrr
//kkkkkkkkkkkkkkkk
//yyyyyyyyyyy
package com.example.demoparsedantri;

import java.util.ArrayList;

import com.dantri.adapters.DanTriAdapter;
import com.dantri.items.Item;
import com.dantri.network.NetworkHandler;
import com.dantri.network.OnDataReceiver;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
//cont test fetch
//ttttttttttt

public class MainActivity extends Activity implements 
		OnDataReceiver {
	private ListView listView;
	private DanTriAdapter adapter;
	private ArrayList<Item> items;
	private Button button;
	private Button btnSport;
	private Handler handler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.listView1);
		button = (Button) findViewById(R.id.button1);
		btnSport = (Button) findViewById(R.id.button2);
		
		handler = new Handler();
		button.setOnClickListener(onClickRequestSocial);
		btnSport.setOnClickListener(onClickRequestSport);
		
		NetworkHandler.getInstance().setOnDataRecieverListener(this);
		adapter = new DanTriAdapter(getApplicationContext(), 1,
				new ArrayList<Item>());
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	private OnClickListener onClickRequestSport = new OnClickListener() {
		@Override
		public void onClick(View v) {
			NetworkHandler.getInstance().requestRSSSport();
		}
	};
	
	private OnClickListener onClickRequestSocial = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			NetworkHandler.getInstance().requestRSS();
		}
	};
	
	@Override
	public synchronized void onData(final ArrayList<Item> datas) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				for (Item item : datas) {
					adapter.add(item);
				}
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		NetworkHandler.getInstance().destroy();
	}
}
