package barqsoft.footballscores;// Created by ersin on 11/07/15

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

public class AppWidgetConfigure extends Activity{

//	to provide different views of the widget for different teams/matches

	private int appWidgedId;

	@Override
	protected void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if(extras != null){
			appWidgedId = extras.getInt(
					AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}

		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
		RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.appwidget);

		appWidgetManager.updateAppWidget(appWidgedId, views);

		Intent resultValue = new Intent();
		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgedId);
		setResult(RESULT_OK, resultValue);

		finish();
	}
}
