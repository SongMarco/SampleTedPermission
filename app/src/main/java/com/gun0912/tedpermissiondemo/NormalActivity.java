package com.gun0912.tedpermissiondemo;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import java.util.ArrayList;

/**
 * Created by TedPark on 16. 2. 21..
 */
public class NormalActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    PermissionListener permissionlistener = new PermissionListener() {
      @Override
      public void onPermissionGranted() {
        Toast.makeText(NormalActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        Toast.makeText(NormalActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT)
            .show();
      }


    };


    TedPermission.with(this)
        .setPermissionListener(permissionlistener)
        .setRationaleTitle(R.string.rationale_title)
        .setRationaleMessage(R.string.rationale_message)
        .setDeniedTitle("접근 권한이 거부되었습니다.")
        .setDeniedMessage(
            "앱 기능을 이용하시려면 [설정] > [권한] 에서 권한을 허용해야 합니다.")
        .setGotoSettingButtonText("확인")
        .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION)
        .check();


  }
}
