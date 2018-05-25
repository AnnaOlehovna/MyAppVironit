package vironit.poddubnaya.myappvironit.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;

import java.io.File;

public class ImageChooserUtil {

    public static final int CAMERA_REQUEST_CODE = 123;
    public static final int GALERIA_REQUEST_CODE = 987;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 35;

    public static void startCamera(@NonNull Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //проверяет наличие такого Intent в телефоне - в данном случае, есть ли камера
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            File photo = getCameraFile(activity);
            Uri uri = FileProvider.getUriForFile(activity, "anna.poddubnaya.presentation.utils.MyFileProvider", photo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            activity.startActivityForResult(intent, CAMERA_REQUEST_CODE);

        }

    }

    private boolean getPermissions(@NonNull Activity activity) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
        return false;
    }

    public static Intent getPickUpIntent(@NonNull Activity activity) {
        return null;
    }

    public static void startGallery(Activity activity) {
//        RxPermissions rxPermissions = new RxPermissions(activity);
//        rxPermissions
//                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
//                .subscribe(granted -> {
//                    if (granted) {
//                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        if (intent.resolveActivity(activity.getPackageManager()) != null) {
//                            activity.startActivityForResult(intent, GALERIA_REQUEST_CODE);
//                        }
//                    } else {
//                        // Oups permission denied
//                    }
//                });


    }

    private static File getCameraFile(Activity activity) {
        File root = activity.getExternalFilesDir(null);
        if (root == null) {
            root = activity.getFilesDir();
        }
        File myDir = new File(root.getAbsoluteFile() + "/myDir");
        if (!myDir.exists())
            myDir.mkdir();
        return new File(myDir.getAbsoluteFile() + "/" + "123.jpg");
    }


    public static File getImageFromResult(Activity activity, int requestCode,
                                          int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE: {
                File file = getCameraFile(activity);
                if (file.exists()) {
                    return file;
                } else {
                    return null;
                }
            }
            case GALERIA_REQUEST_CODE: {
                Uri uri = data.getData();
                Cursor cursor = activity.getApplicationContext().getContentResolver()
                        .query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (cursor == null) {
                    return null;
                }

                cursor.moveToFirst();
                int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                String file = cursor.getString(index);
                return new File(file);
            }
        }


        return null;
    }
}