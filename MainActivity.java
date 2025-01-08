package com.tooroq.asilaajwibav2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataHikam() ;

    }

    public void getDataHikam() {

        System.out.println("ddddddddddddddddddddddddddddddddd");
        // رابط JSON
        String url = "https://tooroq.github.io/adsAppsAndroid/datahikam.json";

        // إنشاء RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // إنشاء طلب JSON
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // جلب البيانات من JSON
                            String banner = response.getString("banner");
                            String inter = response.getString("inter");

                            adBanner(banner) ;
                            startAd(inter) ;

                            Log.d("VolleyResponse", "banner: " + banner + ", inter: " + inter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // إضافة الطلب إلى قائمة الطلبات
        requestQueue.add(jsonObjectRequest);
    }





    public void startAd(String code){
        // تهيئة مكتبة الإعلانات
        MobileAds.initialize(this, initializationStatus -> {});

        // تحميل الإعلان بيني
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, code, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.show(MainActivity.this);
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        mInterstitialAd = null;
                    }
                });
    }


    public void adBanner(String code){
        // تهيئة مكتبة AdMob
        MobileAds.initialize(this, initializationStatus -> {});

        // إنشاء AdView (إعلان بانر)
        mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.BANNER); // تحديد حجم الإعلان
        mAdView.setAdUnitId(code); // استبدل بـ ID الإعلان الخاص بك

        // إضافة AdView إلى واجهة المستخدم
        // يمكنك إضافته إلى Layout الخاص بك أو View معين
        android.widget.LinearLayout layout = findViewById(R.id.adContainer); // استبدل بـ ID الحاوية
        layout.addView(mAdView);

        // تحميل الإعلان
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    public void start(View view) {
        MediaPlayer media1 = MediaPlayer.create(this,R.raw.sound_click);
        media1.start();

        Intent windows_asila = new Intent(this, windows_asila.class);
        windows_asila.putExtra("rtn",false);
        startActivity(windows_asila);
    }

    public void returen(View view) {
        MediaPlayer media2 = MediaPlayer.create(this,R.raw.sound_click);
        media2.start();

        Intent windows_asila = new Intent(this, windows_asila.class);
        windows_asila.putExtra("rtn",true);
        startActivity(windows_asila);
    }

    public void addpoint(View view) {
        MediaPlayer media3 = MediaPlayer.create(this,R.raw.sound_click);
        media3.start();

        Intent addPoin = new Intent(this, addPoint.class);
        startActivity(addPoin);
    }

    public void Share(View view) {
        MediaPlayer media4 = MediaPlayer.create(this,R.raw.sound_click);
        media4.start();

        Intent myintent = new Intent(Intent.ACTION_SEND);
        myintent.setType("text/plain");
        String body = "تطبيق نسألك وانت تجيب رائع  \n" + "\n" +
                "https://play.google.com/store/apps/com.is2all.as2ila_jawab";
        String sub = "تطبيق نسالك وانت تجيب \n";
        myintent.putExtra(Intent.EXTRA_SUBJECT, sub);
        myintent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(myintent, "مشاركة البرنامج"));
    }

    public void about(View view) {
        MediaPlayer media5 = MediaPlayer.create(this,R.raw.sound_click);
        media5.start();

        AlertDialog.Builder builder=new AlertDialog.Builder(this) ;
        builder.setTitle("حول البرنامج");
        builder.setMessage("إصدار البرنامج v1.0   \n  التطبيق بالكامل من إنجاز محمد الحيرش    \n " +
                "إذا كنت تريد اي استفسار أو تغييرات بالتطبيق ، أو تريد مراسلتي فلا تتردد في الاتصال بي عن طريق البريد الالكتروني: \n " +
                "medsikb@gmail.com");
        builder.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        builder.show();
    }
}