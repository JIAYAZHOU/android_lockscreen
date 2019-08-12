package com.example.lockscreen.lockscreen;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SetTimeActivity extends AppCompatActivity {

    private TimePicker tp;
    private int hour;
    private int min;
    private int alltime;
    private TextView txtime;

    private AlarmManager alarmManager;
    private PendingIntent pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        txtime = (TextView)findViewById(R.id.tmview);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(this, MainActivity.class);
        pi = PendingIntent.getActivity(this, 0, intent, 0);

        tp = (TimePicker)findViewById(R.id.timePicker);
        tp.setIs24HourView(true);
        tp.setCurrentHour(0);
        tp.setCurrentMinute(0);
        tp.setOnTimeChangedListener(
                new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                        hour = timePicker.getCurrentHour();
                        min = timePicker.getCurrentMinute();
                        txtime.setText("倒计时时间为："+hour+"小时"+min+"分钟");
                    }
                }
        );
    }

    public void settime(View v){

        //查看权限
            DevicePolicyManager dpm;
finish();
            dpm = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);

            ComponentName mDeviceAdminSample = new ComponentName(this,MyAdmin.class);
            if(dpm.isAdminActive(mDeviceAdminSample))
            {
                 /*设置闹钟*/

                alltime = (hour*60*60+min*60);
                // ②设置AlarmManager在Calendar对应的时间启动Activity
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+alltime*1000, pi);
                Log.e("HEHE","1");   //这里的时间是一个unix时间戳
                // 提示闹钟设置完毕:
                Toast.makeText(this, "闹钟设置完毕~"+alltime+"秒钟",
                        Toast.LENGTH_SHORT).show();
                //返回桌面
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
            }
            else
            {
                Toast.makeText(this,"请先打开管理员权限",Toast.LENGTH_SHORT).show();
                getadmin();
            }

    }


    public void getadmin(){
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


    @Override
    protected void onRestart() {
        super.onRestart();

        //返回首页
        Intent top = new Intent(this,login.class);
        top.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(top);


    }

}
