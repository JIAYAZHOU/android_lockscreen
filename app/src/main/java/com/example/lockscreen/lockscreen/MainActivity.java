package com.example.lockscreen.lockscreen;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActivityChooserView;
import android.util.AndroidException;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DevicePolicyManager dpm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dpm = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);

        ComponentName mDeviceAdminSample = new ComponentName(this,MyAdmin.class);
        if(dpm.isAdminActive(mDeviceAdminSample))
        {
            dpm.lockNow();
            //dpm.resetPassword("123",0);
        }
        else
        {
            Toast.makeText(this,"请先打开管理员权限",Toast.LENGTH_SHORT).show();
        }

        /*
        //返回桌面
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);*/
        finish();

    }

    public void getadmin(View v){
        //创建意图，添加管理员
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        ComponentName mDeviceAdminSample = new ComponentName(this,MyAdmin.class);
        //我要激活
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminSample);
        //劝说激活

        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
               "请同意打开管理员权限，否则可能导致应用无法正常执行");
        //intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
        //       mActivity.getString(R.string.add_admin_extra_app_text));
        startActivity(intent);
    }

    public void lockscreen(View v){
        ComponentName mDeviceAdminSample = new ComponentName(this,MyAdmin.class);
        if(dpm.isAdminActive(mDeviceAdminSample))
        {
            dpm.lockNow();
            //dpm.resetPassword("123",0);
        }
        else
        {
            Toast.makeText(this,"请先打开管理员权限",Toast.LENGTH_SHORT).show();
        }

    }


    public void rmadmin(View v){
        ComponentName mDeviceAdminSample = new ComponentName(this,MyAdmin.class);
        dpm.removeActiveAdmin(mDeviceAdminSample);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        //返回首页
        /*Intent top = new Intent(this,login.class);
        top.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(top);*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
