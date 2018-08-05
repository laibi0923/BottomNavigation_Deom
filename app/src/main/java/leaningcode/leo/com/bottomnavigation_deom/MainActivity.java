package leaningcode.leo.com.bottomnavigation_deom;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*; 

import android.support.v4.app.*;

import leaningcode.leo.com.bottomnavigation_deom.Editor.Fragement_Editor;
import leaningcode.leo.com.bottomnavigation_deom.Main.BottomNavigationViewHelper;
import leaningcode.leo.com.bottomnavigation_deom.R;
import leaningcode.leo.com.bottomnavigation_deom.Review.Fragment_Review;
import leaningcode.leo.com.bottomnavigation_deom.Setting.Fragment_Setting;
import leaningcode.leo.com.bottomnavigation_deom.Editor.*;

public class MainActivity extends AppCompatActivity {

    //private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation);

        init_BottomNavigationView();

		getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new TestingFragment()).commit();

    }

    final void init_BottomNavigationView(){
		/*
        mBottomNavigationView = findViewById(R.id.mBottomNavigationView);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

		mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

				@Override
				public boolean onNavigationItemSelected(MenuItem menu)
				{
					// TODO: Implement this method
					if(!menu.isChecked()){

						switch (menu.getItemId()){

							case R.id.item1:
								Fragment_Trans(new Fragment_Review());
								break;

							case R.id.item2:
								Fragment_Trans(new Fragement_Editor());
								break;

							case R.id.item3:
								Fragment_Trans(new Fragment_Setting());
								break;
						}

						menu.setChecked(true);
					}

					return true;
				}
			});

		Fragment_Trans(new Fragement_Editor());
		mBottomNavigationView.getMenu().getItem(1).setChecked(true);
		*/
    }


	public void Fragment_Trans(Fragment fm){
		getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, fm).commit();
	}


}
