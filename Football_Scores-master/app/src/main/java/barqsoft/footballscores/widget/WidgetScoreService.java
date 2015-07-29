package barqsoft.footballscores.widget;// Created by ersin on 27/07/15

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import barqsoft.footballscores.R;
import barqsoft.footballscores._R;

public class WidgetScoreService extends RemoteViewsService{
	@Override
	public RemoteViewsFactory onGetViewFactory(final Intent intent){
		return new ScoreRemoteViewsFactory(this.getApplicationContext(), intent);

	}
}

class ScoreRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{
	private static final int mCount = 10;
	private List<WidgetItem> mWidgetItems = new ArrayList<WidgetItem>();
	private Context mContext;
	private int mAppWidgetId;
	public ScoreRemoteViewsFactory(Context context, Intent intent){
		mContext = context;
		mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);
	}
	public void onCreate(){


		for(int i = 0; i < mCount; i++){mWidgetItems.add(new WidgetItem(_R.getScores));}

		try{ Thread.sleep(2000);}
		catch(InterruptedException e){e.printStackTrace();}

	}
	public void onDestroy(){mWidgetItems.clear();}

	public int getCount(){return mCount;}

	public RemoteViews getViewAt(int position){

		RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
		rv.setTextViewText(R.id.widget_item, mWidgetItems.get(position).text);

		Bundle extras = new Bundle();
		extras.putInt(WidgetScoreProvider.EXTRA_ITEM, position);

		Intent fillInIntent = new Intent();
		fillInIntent.putExtras(extras);
		rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent);

		try{
			System.out.println("Loading view " + position);
			Thread.sleep(500);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}

		return rv;
	}
	public RemoteViews getLoadingView(){
		// You can create a custom loading view (for instance when getViewAt() is slow.) If you
		// return null here, you will get the default loading view.
		return null;
	}
	public int getViewTypeCount(){return 1;}

	public long getItemId(int position){return position;}

	public boolean hasStableIds(){ return true;}

	public void onDataSetChanged(){
		// This is triggered when you call AppWidgetManager notifyAppWidgetViewDataChanged
		// on the collection view corresponding to this factory. You can do heaving lifting in
		// here, synchronously. For example, if you need to process an image, fetch something
		// from the network, etc., it is ok to do it here, synchronously. The widget will remain
		// in its current state while work is being done here, so you don't need to worry about
		// locking up the widget.
	}
}
