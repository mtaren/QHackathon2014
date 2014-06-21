package com.devinhe.tesstwoex;

import java.io.File;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;

import com.googlecode.tesseract.android.TessBaseAPI;

public class OCRLayer {
	
	public static String getTextFromImage(String imagePath) throws IOException {
		File imageFile = new File(imagePath);
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
        
        TessBaseAPI baseApi = new TessBaseAPI();
		baseApi.init(Environment.getExternalStorageDirectory().getAbsolutePath() + "/tess", "eng");
		baseApi.setImage(bmp);
		String recognizedText = baseApi.getUTF8Text();
		baseApi.end();
		return recognizedText;
	}
	
}
