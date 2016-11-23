package com.rfachrur.stockhawk.stockservices;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.rfachrur.stockhawk.R;

/**
 * Created by rfachrur on 11/23/16.
 *
 */

public class StockIntentService extends IntentService {

    public StockIntentService() {
        super(StockIntentService.class.getName());
    }

    public StockIntentService(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(StockIntentService.class.getSimpleName(), getResources().getString(R.string.stock_intent_service));
        StockTaskService stockTaskService = new StockTaskService(this);
    }
}
