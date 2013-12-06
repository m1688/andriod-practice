package m.ui.android;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import m.ui.android.adapter.WeiboListAdapter;

import org.apache.http.impl.cookie.DateUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * @author wei.xinw
 * 
 */
public class PersonalWeiboActivity extends Activity implements OnClickListener {

	private ListView listView;
	private TextView contentView;
	private Button send;
	private WeiboListAdapter listAdapter;

	private int lastIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weibo_list);
		contentView = (TextView) findViewById(R.id.weiboContent);

		send = (Button) findViewById(R.id.sendWeibo);
		send.setOnClickListener(this);

		listAdapter = new WeiboListAdapter(this);

		listView = (ListView) findViewById(R.id.weiboList);
		listView.setAdapter(listAdapter);
		listView.setOnScrollListener(new LoadMoreListener(this));

	}

	@Override
	public void onClick(View arg0) {
		String content = contentView.getText().toString();
		contentView.setText("");
		LinkedList<HashMap<String, Object>> weiboList = (LinkedList<HashMap<String, Object>>) listAdapter
				.getData();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", "��");
		map.put("time", DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		map.put("info", content);
		map.put("img", R.drawable.ic_launcher);
		weiboList.addFirst(map);
		listAdapter.refresh(weiboList);
	}

	public class LoadMoreListener implements AbsListView.OnScrollListener {

		private Context context;

		public LoadMoreListener(Context context) {
			this.context = context;
		}

		@Override
		public void onScrollStateChanged(AbsListView absListView, int i) {

		}

		@Override
		public void onScroll(AbsListView absListView, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

			boolean loadMore = firstVisibleItem + visibleItemCount >= totalItemCount;
			lastIndex = listAdapter.getCount() + 1;
			if (loadMore) {
				List<HashMap<String, Object>> list = listAdapter.getData();
				HashMap<String, Object> map = null;
				for (int i = lastIndex; i <= lastIndex + 5; i++) {
					map = new HashMap<String, Object>();
					map.put("title", "����" + i);
					map.put("time",
							DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
					map.put("info", "������Ǻܼ�ѽ��");
					map.put("img", R.drawable.ic_launcher);
					list.add(map);
				}
				listAdapter.refresh(list);
			}

		}
	}
}
