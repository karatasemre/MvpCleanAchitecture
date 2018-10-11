package emre.com.cleanarchitecturetutorial.ui.base;

import android.content.Context;
import android.content.Intent;

import emre.com.cleanarchitecturetutorial.data.model.Item;
import emre.com.cleanarchitecturetutorial.ui.detail.DetailActivity;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

public class Navigator {

    public static void navigationToDetailPage(Context context, Item item) {
        if (context != null) {
            Intent intentToLaunch = DetailActivity.getCallingIntent(context, item);
            context.startActivity(intentToLaunch);
        }
    }
}
