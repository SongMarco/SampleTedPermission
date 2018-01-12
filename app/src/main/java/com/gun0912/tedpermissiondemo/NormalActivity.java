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


// 권한 확인을 위한 액티비티. - 수동으로도 가능함
public class NormalActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


    //권한 허부를 파악하는 리스너 가동
    PermissionListener permissionlistener = new PermissionListener() {



      //권한이 허용되었음. - 필요한 메커니즘을 구동하면 됨(카메라 켜기, 갤러리 켜기 등)
      @Override
      public void onPermissionGranted() {
        Toast.makeText(NormalActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
      }


      //권한이 거부되었음. 더 이상 행동하지 않게 됨.
      @Override
      public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        Toast.makeText(NormalActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT)
            .show();
      }


    };


    //
    TedPermission.with(this)
        .setPermissionListener(permissionlistener)
            // 아래 두 문장은 권한을 호출하기 전에 불림. 따로 세팅 안해도 ok
//        .setRationaleTitle(R.string.rationale_title)
//        .setRationaleMessage(R.string.rationale_message)

            //아래 세 줄은 권한이 거부되었을 때 다이얼로그를 띄우게 됨.
            //사용자가 권한을 다시 설정할 기회를 줌. 이래도 안됨 -> permissionListener 의 onPermissionDenied 로 이동함
        .setDeniedTitle("접근 권한이 거부되었습니다.")
        .setDeniedMessage(
            "앱 기능을 이용하시려면 [설정] > [권한] 에서 권한을 허용해야 합니다.")
        .setGotoSettingButtonText("확인")


            // 설정이 필요한 권한들. - 카메라, 저장소 읽기 / 쓰기 등을 설정할 수 있음.
        .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION)
        .check();


  }
}
