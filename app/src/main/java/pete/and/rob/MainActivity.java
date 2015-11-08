package pete.and.rob;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import pete.and.rob.common.application.PRApp;
import pete.and.rob.modules.videoList.VideoLIstFragment;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((PRApp)getApplication()).getComponent().inject(this);

        Fragment contentFragment = getSupportFragmentManager().findFragmentById(R.id.contentLayout);
        if (contentFragment == null) {
            contentFragment = new VideoLIstFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.contentLayout, contentFragment).commit();
        }

        setupToolbar();
        getSupportActionBar().setTitle("Pete & Rob");
    }

    private void setupToolbar() {
        Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
