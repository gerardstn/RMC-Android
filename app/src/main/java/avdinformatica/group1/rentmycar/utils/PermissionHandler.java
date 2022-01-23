package avdinformatica.group1.rentmycar.utils;

import android.app.Activity;
import android.content.Context;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

public final class PermissionHandler {

    private final static Integer REQUEST_CODE = 1234;
    private static final PermissionHandler INSTANCE;

    public static final void permissionCheck(@NotNull Activity activity){
        Intrinsics.checkNotNullParameter(activity, "activity");
        if(ContextCompat.checkSelfPermission((Context) activity, "android.permission.ACCESS_FINE_LOCATION")
            != 0 && !ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_FINE_LOCATION")){
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, REQUEST_CODE);
        }
        if(ContextCompat.checkSelfPermission((Context) activity, "android.permission.ACCESS_COARSE_LOCATION")
                != 0 && !ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_COARSE_LOCATION")){
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.ACCESS_COARSE_LOCATION"}, REQUEST_CODE);
        }
        if(ContextCompat.checkSelfPermission((Context) activity, "android.permission.ACCESS_BACKGROUND_LOCATION")
                != 0 && !ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_BACKGROUND_LOCATION")){
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.ACCESS_BACKGROUND_LOCATION"}, REQUEST_CODE);
        }
        if(ContextCompat.checkSelfPermission((Context) activity, "android.permission.CAMERA")
                != 0 && !ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.CAMERA")) {
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.CAMERA"}, REQUEST_CODE);
        }
    }

    private PermissionHandler(){

    }

    static {
        PermissionHandler permissionHandler = new PermissionHandler();
        INSTANCE = permissionHandler;
    }
}
