package leaningcode.leo.com.bottomnavigation_deom.Editor;
import android.graphics.Typeface;
import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import android.widget.TextView;

import leaningcode.leo.com.bottomnavigation_deom.R;

public class Testing_Editor_Fragment extends Fragment
{
	private TextView mAmount_Display;

	private Dialog_For_AmountInput mDialog_For_AmountInput;

	private String DIALOG_TITLE, DIALOG_HINT, FRAGMENT_TAG, TEMP_TEXT;
	private int DIALOG_REQUEST_CODE;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View v = inflater.inflate(R.layout.tseting_editor_main, container, false);
		Find_View(v);
		return v;
	}

	private void Find_View(View v){

		View subview_amount = v.findViewById(R.id.tseting_editor_partof_amount);
		mAmount_Display = subview_amount.findViewById(R.id.editor_amount_display);
		mAmount_Display.setTag("Amount");
		mAmount_Display.setOnClickListener(Item_Onclick_Listener);
	}


	private View.OnClickListener Item_Onclick_Listener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {

			switch (view.getId()){

				case R.id.editor_amount_display:
					DIALOG_REQUEST_CODE = 5;
					FRAGMENT_TAG = view.getTag().toString();
					DIALOG_TITLE = "銀碼";
					DIALOG_HINT = "輸入銀碼";
					TEMP_TEXT = mAmount_Display.getText().toString();

					mDialog_For_AmountInput = new Dialog_For_AmountInput(DIALOG_TITLE, TEMP_TEXT, FRAGMENT_TAG);
					mDialog_For_AmountInput.setTargetFragment(Testing_Editor_Fragment.this, DIALOG_REQUEST_CODE);
					mDialog_For_AmountInput.show(getFragmentManager(), FRAGMENT_TAG);
					break;
			}

		}
	};


}
