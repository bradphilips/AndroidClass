package com.example.recapapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends Activity {
	Button share = null;

	@Override
	public void onCreate(Bundle onSavedInstance) {
		super.onCreate(onSavedInstance);
		setContentView(R.layout.activity_second);

		share = (Button) findViewById(R.id.shareBtn);
		share.setOnClickListener(shareSomething);
	}

	public View.OnClickListener shareSomething = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, "Sharing with you");
			startActivity(intent);
		}
	};

}
