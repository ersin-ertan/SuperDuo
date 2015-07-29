package barqsoft.footballscores;// Created by ersin on 22/07/15

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService{

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent){
		return (new ViewsFactory(this.getApplicationContext(), intent));
	}
}
