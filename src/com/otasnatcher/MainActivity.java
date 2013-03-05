package com.otasnatcher;

import java.io.DataOutputStream;
import java.io.IOException;

import com.otasnatcher.MainActivity;
import com.otasnatcher.R;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.main);
		addListenerOnBtnSnatchOTA();
		addListenerOnBtnBatakang();
		
		try{
			Process p = Runtime.getRuntime().exec("su"); //Asking Root Permission
		} catch (IOException e) { 
			 
		}finally{ 
			
		}

	}
		
	

	

	public void addListenerOnBtnSnatchOTA() {
		
		button = (Button) findViewById(R.id.btnSnatchOTA);

		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) { // On Button Click Do:
				
			
				try{
					Process p = Runtime.getRuntime().exec("su"); //Asking Root Permission
					DataOutputStream os = new DataOutputStream(p.getOutputStream());
					String mkdir = "mkdir "+Environment.getExternalStorageDirectory().getPath()+"/OTA/";
					String process = "cp /cache/* "+Environment.getExternalStorageDirectory().getPath()+"/OTA/";

					os.writeBytes(mkdir + "\n"); 
					os.writeBytes(process + "\n"); 
					Toast.makeText(MainActivity.this, "Copying OTA to "+Environment.getExternalStorageDirectory().getPath()+"/OTA/", Toast.LENGTH_LONG).show();
					Toast.makeText(MainActivity.this, "Copying started. Depending on the OTA this may take a minute or two.", Toast.LENGTH_LONG).show();
					Toast.makeText(MainActivity.this, "If you can open the .ZIP on your computer or with an app it is completed.", Toast.LENGTH_LONG).show();
					os.flush();

				} catch (IOException e) { 
					 
				}finally{ 
					
				}
		
					}

		});
	}
	
	

	
public void addListenerOnBtnBatakang() {
		
		button = (Button) findViewById(R.id.btnBatakang);

		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) { // On Button Click Do:
				
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.batakang.com/"));
				startActivity(browserIntent);
			}

		});
	}
	
	
}
