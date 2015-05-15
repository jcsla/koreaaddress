package jcsla.koreaaddress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.urqa.clientinterface.URQAController;

/**
 * Created by jcsla on 15. 5. 8..
 */
public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        URQAController.InitializeAndStartSession(getApplicationContext(), "5D6A2082");

        Handler h = new Handler() {
            public void handleMessage(Message msg) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        };
        h.sendEmptyMessageDelayed(0, 1500);
    }
}
