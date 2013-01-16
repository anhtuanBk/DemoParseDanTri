package com.dantri.adapters;

import java.util.List;

import com.dantri.items.Item;
import com.example.demoparsedantri.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DanTriAdapter extends ArrayAdapter<Item> {
	private LayoutInflater inflater;

	public DanTriAdapter(Context context, int textViewResourceId,
			List<Item> objects) {
		super(context, textViewResourceId, objects);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Item item = this.getItem(position);
		ViewHolder holder;
		
		if (null == convertView) {
			Log.i("Adapter", "Tao moi khung view");
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.row, null);
			holder.txtTitle = (TextView) convertView
					.findViewById(R.id.txtTitleNews);
			holder.txtDesc = (TextView) convertView
					.findViewById(R.id.txtDescNews);
			holder.txtPubDate = (TextView) convertView
					.findViewById(R.id.txtPubDateNews);
			holder.imgNews = (ImageView) convertView
					.findViewById(R.id.imageNews);
			convertView.setTag(holder);

		} else {
			Log.i("Adapter", "Su dung lai khung de ve lai");
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txtTitle.setText(item.getTitle());
		holder.txtDesc.setText(item.getPosition());
		holder.txtPubDate.setText(item.getDesc());
		
		return convertView;
	}

	private class ViewHolder {
		private ImageView imgNews;
		private TextView txtTitle;
		private TextView txtDesc;
		private TextView txtPubDate;
	}
}
