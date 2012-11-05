package com.example.recapapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends Activity {
	EditText name = null;
	Button done = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        
        name = (EditText)findViewById(R.id.value);
        done = (Button)findViewById(R.id.doneBtn);
        done.setOnClickListener(doSomething);   
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_first, menu);
        return true;
    }
    
    public View.OnClickListener doSomething = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
		
		Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
		startActivity(intent);
		}
	};
}
