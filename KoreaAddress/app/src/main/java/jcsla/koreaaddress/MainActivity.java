package jcsla.koreaaddress;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.mocoplex.adlib.AdlibActivity;
import com.mocoplex.adlib.AdlibConfig;


public class MainActivity extends AdlibActivity {

    // 애드립 광고를 테스트 하기 위한 키 입니다.
    private String ADLIB_API_KEY = "554db5c90cf240de434c6bb3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAds();

        // 각 애드립 액티비티에 애드립 앱 키값을 필수로 넣어주어야 합니다.
        this.setAdlibKey(ADLIB_API_KEY);
        this.setAdsContainer(R.id.ads);

        // Get tracker.
        Tracker t = ((KoreaAddress) getApplication()).getTracker(KoreaAddress.TrackerName.APP_TRACKER);
        // Set screen name.
        t.setScreenName("KoreaAddress MainActivity");
        // Send a screen view.
        t.send(new HitBuilders.AppViewBuilder().build());

        setLayout();
    }

    // AndroidManifest.xml에 권한과 activity를 추가하여야 합니다.
    protected void initAds()
    {
        // AdlibActivity 를 상속받은 액티비티이거나,
        // 일반 Activity 에서는 AdlibManager 를 동적으로 생성한 후 아래 코드가 실행되어야 합니다. (AdlibTestProjectActivity4.java)

        // 광고 스케줄링 설정을 위해 아래 내용을 프로그램 실행시 한번만 실행합니다. (처음 실행되는 activity에서 한번만 호출해주세요.)
        // 광고 subview 의 패키지 경로를 설정합니다. (실제로 작성된 패키지 경로로 수정해주세요.)

        // 쓰지 않을 광고플랫폼은 삭제해주세요.
        AdlibConfig.getInstance().bindPlatform("ADAM","jcsla.koreaaddress.ads.SubAdlibAdViewAdam");
        AdlibConfig.getInstance().bindPlatform("ADMOB","jcsla.koreaaddress.ads.SubAdlibAdViewAdmob");
        AdlibConfig.getInstance().bindPlatform("CAULY","jcsla.koreaaddress.ads.SubAdlibAdViewCauly");
        AdlibConfig.getInstance().bindPlatform("TAD","jcsla.koreaaddress.ads.SubAdlibAdViewTAD");
        AdlibConfig.getInstance().bindPlatform("SHALLWEAD","jcsla.koreaaddress.ads.SubAdlibAdViewShallWeAd");
        AdlibConfig.getInstance().bindPlatform("INMOBI", "jcsla.koreaaddress.ads.SubAdlibAdViewInmobi");
        // 쓰지 않을 플랫폼은 JAR 파일 및 test.adlib.project.ads 경로에서 삭제하면 최종 바이너리 크기를 줄일 수 있습니다.

        // SMART* dialog 노출 시점 선택시 / setAdlibKey 키가 호출되는 activity 가 시작 activity 이며 해당 activity가 종료되면 app 종료로 인식합니다.
        // adlibr.com 에서 발급받은 api 키를 입력합니다.
        // ADLIB - API - KEY 설정

        // 각 애드립 액티비티에 애드립 앱 키값을 필수로 넣어주어야 합니다.
        setAdlibKey(ADLIB_API_KEY);
    }

    private void setLayout()
    {
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/address.html");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        //Back키 눌렀을 때 dialog 닫히는것 막기
        if(keyCode == KeyEvent.KEYCODE_BACK){
            // 종료 대화상자 광고를 노출하기 위해서 호출합니다.
            showAdDialog("취소", "확인", "App 을 정말로 종료하시겠습니까?");

			/*
			 * 필요시 아래와 같이 색상과 클릭 액션을 변경할 수 있습니다.
			 * backgroundColor, backgroundColor(click), textColor, textColor, textColor(click), lineColor
			 */
//			int[] colors = new int[]{0xffffffff, 0xffa8a8a8, 0xff404040, 0xff404040, 0xffdfdfdf};
//			showAdDialog("취소", "확인", "App 을 정말로 종료하시겠습니까?", colors, new AdlibDialogAdListener(){
//
//				@Override
//				public void onLeftClicked() {
//				}
//
//				@Override
//				public void onRightClicked() {
//				}
//
//			});
        }
        return super.onKeyDown(keyCode, event);
    }
}
