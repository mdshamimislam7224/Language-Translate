package com.mdshamimislam.languagetranslate.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.mdshamimislam.languagetranslate.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class MainActivity2 extends AppCompatActivity {
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Bundle extras= result.getData().getExtras();
                Bitmap imageBitmap=(Bitmap) extras.get("data");

                WeakReference<Bitmap> results= new WeakReference<>(Bitmap.createScaledBitmap(imageBitmap,
                        imageBitmap.getHeight(),imageBitmap.getWidth(),false).copy(
                                Bitmap.Config.RGB_565,true));


            }
        });


    }
    private Uri saveImage(Bitmap image, Context context)
    {
        File imagesFolder=new File(context.getCacheDir(),"Images");
        Uri uri=null;
        try
        {
           imagesFolder.mkdir();
           File file= new File(imagesFolder,"captured_image.jpg");
            FileOutputStream stream= new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();
            uri= FileProvider.getUriForFile(context.getApplicationContext(),"com.mdshamimislam.fileprovider",file);


        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return uri;
    }
}