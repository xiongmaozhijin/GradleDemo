package com.bbg.gradledemo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    PayPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        String pkgName = getPackageName(this);
        String buildCfgField = BuildConfig.var1;
        String metaData = getApplicationMetadata(this, "KEY_TYPE");
        String versionName = getVersion(this);
        String mode = BuildConfig.mode;

        mPresenter = new PayPresenter();
        String fee = mPresenter.dealPayFee(this);

        String info = String.format("pkgName=%s \n buildField=%s \n metaData=%s \n vName=%s \n  mode=%s \n fee=%s",
                pkgName, buildCfgField, metaData, versionName, mode, fee);
        tvInfo.setText(info);


    }


    public static String getApplicationMetadata(Context context, String metaDataKey) {
        ApplicationInfo info = null;
        try {
            PackageManager pm = context.getPackageManager();
            info = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return String.valueOf(info.metaData.get(metaDataKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static String getVersion(Context context)//获取版本号
    {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "unknown";
        }
    }
}