package com.example.WifiManager;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

public class MyActivity extends Activity {


    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private WifiManager mWifiManager;
    private List<ScanResult> mScanResult = null;
    private ListView lv;

    static class ViewCache
    {
        public ImageView img;
        public TextView title;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        mButton1 = (Button)this.findViewById(R.id.button1);//wifi开
        mButton2 = (Button)this.findViewById(R.id.button2);//wifi关
        mButton3 = (Button)this.findViewById(R.id.button3);//寻找wifi
        //创建wifi对象
        mWifiManager = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);

        mWifiManager.startScan();
        mScanResult = mWifiManager.getScanResults();

        //ListView
        lv = (ListView)findViewById(R.id.lv);
        WifiAdapter adapter = new WifiAdapter(this);
        lv.setAdapter(adapter);

        //点击button1 wifi开
        mButton1.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mWifiManager.setWifiEnabled(true);
                    }
                }
        );
        //点击button2 wifi关
        mButton2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mWifiManager.setWifiEnabled(false);
                    }
                }
        );
        //点击button3 扫描wifi(按钮功能尚未实现)
        mButton3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mWifiManager.startScan();
                        mScanResult = mWifiManager.getScanResults();

//                        for(int i=0;i<mScanResult.size();i++) {
//                            System.out.print(mScanResult.get(i).SSID);
//                            Log.v(MyActivity.ACTIVITY_SERVICE,mScanResult.get(i).SSID);
//                        }
                    }
                }
        );


    }


    public class WifiAdapter extends BaseAdapter{
        private LayoutInflater mInflater = null;
        private WifiAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return mScanResult.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        //获取一个在数据集中指定索引的视图来显示数据
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            mWifiManager.startScan();
//            mScanResult = mWifiManager.getScanResults();
            ViewCache viewCache = null;
            if(convertView == null)
            {
                viewCache = new ViewCache();
                convertView = mInflater.inflate(R.layout.list_item, null);
//                viewCache.img = (ImageView)convertView.findViewById(R.id.img);
                viewCache.title = (TextView)convertView.findViewById(R.id.tv);
                convertView.setTag(viewCache);
            }else
            {
                viewCache = (ViewCache)convertView.getTag();
            }
//            viewCache.img.setBackgroundResource((Integer)mScanResult.get(position).get("img"));
            viewCache.title.setText((String)mScanResult.get(position).SSID);

            return convertView;
        }
    }
}

