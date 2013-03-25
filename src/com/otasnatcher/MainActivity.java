package com.otasnatcher;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.otasnatcher.MainActivity;
import com.otasnatcher.R;
import android.os.Build;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button button;
	String cpuType = Build.CPU_ABI2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.main);
		addListenerOnBtnSnatchOTA();
		addListenerOnBtnBatakang();
		copyAssets();
		
		try{
			Process p = Runtime.getRuntime().exec("su"); //Asking Root Permission
		} catch (IOException e) { 
			 
		}finally{ 
			
		}

	}
		
	
	public void copyAssets() { //Copies all files from /assets/ folder of .APK into /data/data/com.init.d/istaller/
	    AssetManager assetManager = getAssets();
	    String[] files = null;
	    try {
	        files = assetManager.list("");
	    } catch (IOException e) {
	    }
	    for(String filename : files) {
	        InputStream in = null;
	        OutputStream out = null;
	        try {
	          in = assetManager.open(filename); //Ignore warning about hardcoding to /data/data/.
	          out = new FileOutputStream("/data/data/" + this.getPackageName() + "/" + filename);
	          copyFile(in, out);
	          in.close();
	          in = null;
	          out.flush();
	          out.close();
	          out = null;
	        } catch(IOException e) {
	        }       
	    }
	}
	private void copyFile(InputStream in, OutputStream out) throws IOException {
	    byte[] buffer = new byte[1024];
	    int read;
	    while((read = in.read(buffer)) != -1){
	      out.write(buffer, 0, read);
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
					
					//Now we will give them the 'cp' binary if they don't already have it.
					//if
						if(cpuType.contentEquals("armeabi")){
							
							String mount = "mount -o remount,rw /system";
							
							String mkdir = "/data/local/tmp/busybox mkdir "+Environment.getExternalStorageDirectory().getPath()+"/OTA/";
							
							String armeabi = "cat /data/data/" + getApplicationContext().getPackageName() + "/armeabi > /data/local/tmp/busybox";
							
							String permissionCP = "chmod 755 /data/local/tmp/busybox";
							
							String processCP = "/data/local/tmp/busybox cp /cache/* "+Environment.getExternalStorageDirectory().getPath()+"/OTA/";
							
							os.writeBytes(mount + "\n");
							os.writeBytes(armeabi + "\n");
							os.writeBytes(permissionCP + "\n");
							os.writeBytes(mkdir + "\n"); 
							os.writeBytes(processCP + "\n"); 
							Toast.makeText(MainActivity.this, "Copying OTA to "+Environment.getExternalStorageDirectory().getPath()+"/OTA/", Toast.LENGTH_LONG).show();
							Toast.makeText(MainActivity.this, "Copying started. Depending on the OTA this may take a minute or two.", Toast.LENGTH_LONG).show();
							Toast.makeText(MainActivity.this, "If you can open the .ZIP on your computer or with an app it is completed.", Toast.LENGTH_LONG).show();
							os.flush();

							
						}

						if(cpuType.contentEquals("x86")){
							
							String mount = "mount -o remount,rw /system";
							
							String mkdir = "/data/local/tmp/busybox mkdir "+Environment.getExternalStorageDirectory().getPath()+"/OTA/";
							
							String x86 = "cat /data/data/" + getApplicationContext().getPackageName() + "/x86 > /data/local/tmp/busybox";
							
							String permissionCP = "chmod 755 /data/local/tmp/busybox";
							
							String processCP = "/data/local/tmp/busybox cp /cache/* "+Environment.getExternalStorageDirectory().getPath()+"/OTA/";
							
							 
							os.writeBytes(mount + "\n"); 
							os.writeBytes(x86 + "\n");
							os.writeBytes(permissionCP + "\n");
							os.writeBytes(mkdir + "\n");
							os.writeBytes(processCP + "\n"); 
							Toast.makeText(MainActivity.this, "Copying OTA to "+Environment.getExternalStorageDirectory().getPath()+"/OTA/", Toast.LENGTH_LONG).show();
							Toast.makeText(MainActivity.this, "Copying started. Depending on the OTA this may take a minute or two.", Toast.LENGTH_LONG).show();
							Toast.makeText(MainActivity.this, "If you can open the .ZIP on your computer or with an app it is completed.", Toast.LENGTH_LONG).show();
							os.flush();

							
							
						}
						
					
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
