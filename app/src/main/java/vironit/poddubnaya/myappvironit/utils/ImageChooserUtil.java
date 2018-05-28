package vironit.poddubnaya.myappvironit.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ImageChooserUtil {

    public static final int CAMERA_REQUEST_CODE = 123;
    public static final int GALLERY_REQUEST_CODE = 987;


    public static final int MY_PERMISSIONS_REQUEST_GALLERY = 30;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 31;

    private boolean allowCamera = false;
    private boolean allowGallery = false;

    public void startCamera(@NonNull Fragment fragment) {
        if (fragment.getActivity() != null) {
            getCameraPermission(fragment.getActivity());
            if (allowCamera) {
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (captureIntent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
                    File photo = createImageFile(fragment.getActivity());
                    Uri uri = FileProvider.getUriForFile(fragment.getActivity(), "vironit.poddubnaya.myappvironit.fileprovider", photo);
                    captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    fragment.startActivityForResult(captureIntent, CAMERA_REQUEST_CODE);
                }
            }
        }
    }


    public void startGallery(@NonNull Fragment fragment) {
        if (fragment.getActivity() != null) {
            getGalleryPermission(fragment.getActivity());
            if (allowGallery) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (intent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
                    fragment.startActivityForResult(intent, GALLERY_REQUEST_CODE);
                }

            }
        }
    }

    private File createImageFile(@NonNull Activity activity) {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public File getImageFromResult(Activity activity, int requestCode,
                                   int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE: {
                File file = createImageFile(activity);
                if (file.exists()) {
                    return file;
                } else {
                    return null;
                }
            }

            case GALLERY_REQUEST_CODE: {
                Uri uri = data.getData();
                if (uri != null) {
                    Cursor cursor = activity.getApplicationContext().getContentResolver()
                            .query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                    if (cursor == null) {
                        return null;
                    }

                    cursor.moveToFirst();
                    int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    String file = cursor.getString(index);
                    cursor.close();

                    return new File(file);
                }
            }
        }


        return null;
    }

    private void getCameraPermission(@NonNull Activity activity) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                //TODO
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
            }
        } else {
            allowCamera = true;
        }
    }


    private void getGalleryPermission(@NonNull Activity activity) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //TODO
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_GALLERY);
            }
        } else {
            allowGallery = true;
        }

    }


}