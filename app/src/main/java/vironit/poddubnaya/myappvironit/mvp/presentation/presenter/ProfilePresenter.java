package vironit.poddubnaya.myappvironit.mvp.presentation.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;

import com.arellomobile.mvp.InjectViewState;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import vironit.poddubnaya.myappvironit.App;
import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.mvp.model.manager.interfaces.ResourcesManager;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BaseAppPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.base.BaseActivity;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.IProfileView;
import vironit.poddubnaya.myappvironit.utils.ShowDialogUtil;

@InjectViewState
public class ProfilePresenter extends BaseAppPresenter<IProfileView> {

    public ProfilePresenter() {
        App.getsAppComponent().inject(this);
    }

    public static final int CAMERA_REQUEST_CODE = 123;
    public static final int GALLERY_REQUEST_CODE = 987;


    public static final int MY_PERMISSIONS_REQUEST_GALLERY = 30;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 31;

    private boolean allowCamera = false;
    private boolean allowGallery = false;
    private String mCurrentPhotoPath;


    public void changePhoto(@NonNull Fragment fragment) {
        getViewState().showDialogWithOptions(mResourcesManager.getString(R.string.change_photo),
                mResourcesManager.getString(R.string.change_photo_messsage),
                mResourcesManager.getString(R.string.gallery),
                mResourcesManager.getString(R.string.camera),
                (dialog, which) -> startGallery(fragment),
                (dialog, which) -> startCamera(fragment), true);
    }


    private void startCamera(@NonNull Fragment fragment) {
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


    private void startGallery(@NonNull Fragment fragment) {
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

        if (image != null) {
            mCurrentPhotoPath = image.getAbsolutePath();
        }
        return image;
    }

    private File getImageFromResult(@NonNull Activity activity, int requestCode,
                                    int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE: {
                File file = new File(mCurrentPhotoPath);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, @NonNull BaseActivity activity) {
        super.onActivityResult(requestCode, resultCode, data, activity);
        if (resultCode == Activity.RESULT_OK) {
            File file = getImageFromResult(activity, requestCode, resultCode, data);
            if (file != null) {
                getViewState().setPhoto(file);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, @NonNull BaseActivity activity) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, activity);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    allowCamera = true;
                } else {
                    allowCamera = false;
                }
                break;
            }
            case MY_PERMISSIONS_REQUEST_GALLERY: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    allowGallery = true;
                } else {
                    allowGallery = false;
                }
                break;
            }
            default:
                break;
        }
    }
}
