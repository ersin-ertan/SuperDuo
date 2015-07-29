package barqsoft.footballscores;// Created by ersin on 22/07/15

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider{
	public static String EXTRA_WORD = "com.nullcognition.barqsoft.footballscore.WORD";

	@Override
	public void onUpdate(Context ctxt, AppWidgetManager appWidgetManager, int[] appWidgetIds){

		final int n = appWidgetIds.length;
		for(int i = 0; i < n; i++){
			Intent svcIntent = new Intent(ctxt, WidgetService.class);

			svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
			svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));

			RemoteViews widget = new RemoteViews(ctxt.getPackageName(), R.layout.appwidget);

			widget.setRemoteAdapter(appWidgetIds[i], R.id.widget_listview, svcIntent);

			Intent clickIntent = new Intent(ctxt, MainActivity.class);
			PendingIntent clickPI = PendingIntent.getActivity(ctxt, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);

			widget.setPendingIntentTemplate(R.id.widget_listview, clickPI);

			appWidgetManager.updateAppWidget(appWidgetIds[i], widget);
		}

		super.onUpdate(ctxt, appWidgetManager, appWidgetIds);
	}
}
