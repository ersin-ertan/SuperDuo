//package barqsoft.footballscores;// Created by ersin on 22/07/15
//
//import android.annotation.TargetApi;
//import android.app.IntentService;
//import android.app.PendingIntent;
//import android.appwidget.AppWidgetManager;
//import android.content.ComponentName;
//import android.content.Intent;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Build;
//import android.widget.RemoteViews;
//
//import barqsoft.footballscores.DatabaseContract.scores_table;
//
//public class ScoresWidgetIntentService extends IntentService{
//	private static final String[] SCORES_COLUMNS = {
//			scores_table.HOME_COL,
//			scores_table.AWAY_COL,
//			scores_table.HOME_GOALS_COL,
//			scores_table.AWAY_GOALS_COL,
//			scores_table.MATCH_ID,
//			scores_table.DATE_COL
//	};
//	private static final int INDEX_HOME_COL = 0;
//	private static final int INDEX_AWAY_COL = 1;
//	private static final int INDEX_HOME_GOALS_COL = 2;
//	private static final int INDEX_AWAY_GOALS_COL = 3;
//	private static final int INDEX_MATCH_ID = 4;
//	private static final int INDEX_DATE_COL = 5;
//
//	public ScoresWidgetIntentService(){
//		super("ScoresWidgetIntentService");
//	}
//
//	@Override
//	protected void onHandleIntent(Intent intent){
//		// Retrieve all of the Today widget ids: these are the widgets we need to update
//		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
//		int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, WidgetProvider.class));
//
//
//		Uri uri = DatabaseContract.scores_table.buildScoreWithDate();
//		Cursor cursor = getContentResolver().query(uri, SCORES_COLUMNS, null, new String[]{Utilies.formatDate(System.currentTimeMillis())}, null);
//
//		if(cursor == null){
//			return;
//		}
//		if(!cursor.moveToFirst()){
//			cursor.close();
//			return;
//		}
//
//		int matchId = cursor.getInt(INDEX_MATCH_ID);
//		String date = cursor.getString(INDEX_DATE_COL);
//		String homeTeam = cursor.getString(INDEX_HOME_COL);
//		String awayTeam = cursor.getString(INDEX_AWAY_COL);
//		String homeScore = cursor.getString(INDEX_HOME_GOALS_COL);
//		String awayScore = cursor.getString(INDEX_AWAY_GOALS_COL);
//
//		cursor.close();
//
//		for(int appWidgetId : appWidgetIds){
//			int layoutId = R.layout.appwidget;
//			RemoteViews views = new RemoteViews(getPackageName(), layoutId);
//
//			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){
//				setRemoteContentDescription(views, "List of Scores");
//			}
//			views.setA
//			views.setTextViewText(R.id.widget_high_temperature, formattedMaxTemperature);
//
//			// Create an Intent to launch MainActivity
//			Intent launchIntent = new Intent(this, MainActivity.class);
//			PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);
//			views.setOnClickPendingIntent(R.id.widget, pendingIntent);
//
//			// Tell the AppWidgetManager to perform an update on the current app widget
//			appWidgetManager.updateAppWidget(appWidgetId, views);
//		}
//
//
//		// Perform this loop procedure for each widget
//		for(int appWidgetId : appWidgetIds){
//			RemoteViews views;
//
//			if(hasData){
//				// Find the correct layout based on the widget's width
//				int layoutId;
//				int widgetWidth = getWidgetWidth(appWidgetManager, appWidgetId);
//				int defaultWidth = getResources().getDimensionPixelSize(R.dimen.widget_default_width);
//				int largeWidth = getResources().getDimensionPixelSize(R.dimen.widget_large_width);
//				if(widgetWidth >= largeWidth){
//					layoutId = R.layout.widget_large;
//				}
//				else{
//					layoutId = R.layout.widget_small;
//				}
//				views = new RemoteViews(getPackageName(), layoutId);
//
//				// Add the data to the RemoteViews
//				if(widgetWidth >= largeWidth){
//					views.setImageViewResource(R.id.home_crest, Utilities.getTeamCrestByTeamName(cursor.getString(INDEX_HOME_COL)));
//					views.setImageViewResource(R.id.away_crest, Utilities.getTeamCrestByTeamName(cursor.getString(INDEX_AWAY_COL)));
//					views.setTextViewText(R.id.data_textview, cursor.getString(INDEX_TIME_COL));
//				}
//
//
//				views.setTextViewText(R.id.home_name, cursor.getString(INDEX_HOME_COL));
//				views.setTextViewText(R.id.away_name, cursor.getString(INDEX_AWAY_COL));
//				views.setTextViewText(R.id.score_textview, Utilities.getScores(cursor.getInt(INDEX_HOME_GOALS_COL), cursor.getInt(INDEX_AWAY_GOALS_COL)));
//
//
//				// Create an Intent to launch MainActivity
//				Intent launchIntent = new Intent(this, MainActivity.class);
//				PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);
//				views.setOnClickPendingIntent(R.id.widget, pendingIntent);
//
//
//			}
//			else{
//				views = new RemoteViews(getPackageName(), R.layout.widget_small);
//				views.setTextViewText(R.id.home_name, getString(R.string.empty_list));
//				views.setTextViewText(R.id.away_name, "");
//				views.setTextViewText(R.id.score_textview, "");
//			}
//			// Tell the AppWidgetManager to perform an update on the current app widget
//			appWidgetManager.updateAppWidget(appWidgetId, views);
//		}
//		cursor.close();
//
//
//		//------------------------
//		String location = Utility.getPreferredLocation(this);
//		Uri weatherForLocationUri = WeatherContract.WeatherEntry.buildWeatherLocationWithStartDate(
//				location, System.currentTimeMillis());
//		Cursor data = getContentResolver().query(weatherForLocationUri, FORECAST_COLUMNS, null,
//				null, WeatherContract.WeatherEntry.COLUMN_DATE + " ASC");
//		if(data == null){
//			return;
//		}
//		if(!data.moveToFirst()){
//			data.close();
//			return;
//		}
//
//		// Extract the weather data from the Cursor
//		int weatherId = data.getInt(INDEX_WEATHER_ID);
//		int weatherArtResourceId = Utility.getArtResourceForWeatherCondition(weatherId);
//		String description = data.getString(INDEX_SHORT_DESC);
//		double maxTemp = data.getDouble(INDEX_MAX_TEMP);
//		String formattedMaxTemperature = Utility.formatTemperature(this, maxTemp);
//		data.close();
//
//		// Perform this loop procedure for each Today widget
//		for(int appWidgetId : appWidgetIds){
//			int layoutId = R.layout.widget_today_small;
//			RemoteViews views = new RemoteViews(getPackageName(), layoutId);
//
//			// Add the data to the RemoteViews
//			views.setImageViewResource(R.id.widget_icon, weatherArtResourceId);
//			// Content Descriptions for RemoteViews were only added in ICS MR1
//			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){
//				setRemoteContentDescription(views, description);
//			}
//			views.setTextViewText(R.id.widget_high_temperature, formattedMaxTemperature);
//
//			// Create an Intent to launch MainActivity
//			Intent launchIntent = new Intent(this, MainActivity.class);
//			PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);
//			views.setOnClickPendingIntent(R.id.widget, pendingIntent);
//
//			// Tell the AppWidgetManager to perform an update on the current app widget
//			appWidgetManager.updateAppWidget(appWidgetId, views);
//		}
//	}
//
//	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
//	private void setRemoteContentDescription(RemoteViews views, String description){
//		views.setContentDescription(R.id.widget_icon, description);
//	}
//
//
//}
