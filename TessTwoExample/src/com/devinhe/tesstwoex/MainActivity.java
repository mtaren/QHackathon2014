package com.devinhe.tesstwoex;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

public class MainActivity extends Activity {

	TextView result;
	ImageView image;
	Button start;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		image = (ImageView)findViewById(R.id.image);
		result = (TextView)findViewById(R.id.display_text);
		start = (Button)findViewById(R.id.start_button);
		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				File imageFile = new File(Environment.getExternalStorageDirectory().getPath() + "/image/test.png");

				try{
					Bitmap bmp = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

					ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
		            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
		            int rotate = 0;
		            switch(exifOrientation) {
		            case ExifInterface.ORIENTATION_ROTATE_90: rotate=90; break;
		            case ExifInterface.ORIENTATION_ROTATE_180: rotate=180;break;
		            case ExifInterface.ORIENTATION_ROTATE_270: rotate=270; break;
		            }
		            
		            if(rotate != 0) {
		            	int w = bmp.getWidth();
		            	int h = bmp.getHeight();
		            	
		            	// Setting pre rotate
		                Matrix mtx = new Matrix();
		                mtx.preRotate(rotate);

		                // Rotating Bitmap & convert to ARGB_8888, required by tess
		                bmp = Bitmap.createBitmap(bmp, 0, 0, w, h, mtx, false);
		            }
		            bmp = bmp.copy(Bitmap.Config.ARGB_8888, true);

					image.setImageBitmap(bmp);
					
					TessBaseAPI baseApi = new TessBaseAPI();
					String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tess";
					File tessdata = new File(path + "/tessdata/");
					
					baseApi.init(path, "eng");
					baseApi.setImage(bmp);
					String recognizedText = baseApi.getUTF8Text();
					baseApi.end();
					result.setText(recognizedText);
					
				} catch (IOException e) {
					Log.d("OCR", "IOException occured", e);
				}
			}
		});
		
		
	}
}
