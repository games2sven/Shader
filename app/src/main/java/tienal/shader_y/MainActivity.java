package tienal.shader_y;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	public static final String TAG = "MainActivity";
	RadarView mRadarView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mRadarView = (RadarView) findViewById(R.id.rv);
	}


	public void scan(View view) {
		mRadarView.startScan();
	}
}
