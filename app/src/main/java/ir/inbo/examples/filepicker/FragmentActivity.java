package ir.inbo.examples.filepicker;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import ir.inbo.libs.filepicker.utils.FragmentUtil;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        initView();
    }

    private void initView() {
        CallerFragment callerFragment = new CallerFragment();
        FragmentUtil.INSTANCE.addFragment(this, R.id.container,callerFragment);
    }
}
