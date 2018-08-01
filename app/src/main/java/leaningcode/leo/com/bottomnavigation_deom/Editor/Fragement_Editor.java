package leaningcode.leo.com.bottomnavigation_deom.Editor;
import android.app.AlertDialog;
import android.content.*;
import android.os.*;
import android.support.annotation.Nullable;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.util.*;
import android.view.*;
import android.support.v4.widget.*;

import leaningcode.leo.com.bottomnavigation_deom.R;
import android.widget.*;
import android.opengl.*;

public class Fragement_Editor extends Fragment
{
	private LinearLayout Amount_Layout, DollarSide_Layout;
	private TextView Amount_TextView, DollarSide_TextView;
	private TextInputLayout Name_Layout, Remark_Layout, PayMethod_Layout, Account_Layout;
	private PaddingTopEditText Name_EditText, Remark_EditText, PayMethod_EditText, Account_EditText;
	private Dialog_For_TextInput mDialog_For_TextInput;
	private Dialog_For_AmountInput mDialog_For_AmountInput;
	private Dialog_For_RecycleView mDialog_RecycleView;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private View Remarks_underline, PayMethod_underline,Paytime_underline;
	
	public String FRAGMENT_TAG, DIALOG_TITLE, DIALOG_HINT, TEMP_TEXT;
	
	public static int DIALOG_REQUEST_CODE;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
    }

    // Get Value form Dialog Fragment
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO: Implement this method
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode){
			
			// Amount
			case 5:
				String Dialog_Result_Amount = data.getStringExtra(Dialog_For_AmountInput.RESPONSE);
				Amount_TextView.setText(Dialog_Result_Amount);
				break;
			
			// Name
			case 1:
				String Dialog_Result_Name = data.getStringExtra(Dialog_For_TextInput.RESPONSE);
				Name_EditText.setTextWithPadding(Dialog_Result_Name);
				break;
				
			// Dollar Side
			case 6:
				String DollarSide_Result_Name = data.getStringExtra(Dialog_For_TextInput.RESPONSE);
				DollarSide_TextView.setText(DollarSide_Result_Name);
				break;
				
			// Remark
			case 2:
				String Dialog_Result_Remark = data.getStringExtra(Dialog_For_TextInput.RESPONSE);
				Remark_EditText.setTextWithPadding(Dialog_Result_Remark);
				break;

			// Account
			case 3:
				String Dialog_Result_Account = data.getStringExtra(Dialog_For_TextInput.RESPONSE);
				Account_EditText.setTextWithPadding(Dialog_Result_Account);
				break;

			// PayMethod
			case 4:
				String Dialog_Result_PayMethod = data.getStringExtra(Dialog_For_TextInput.RESPONSE);
				PayMethod_EditText.setTextWithPadding(Dialog_Result_PayMethod);
				break;
		}
		
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View v = inflater.inflate(R.layout.editor_main, container, false);
		find_view(v);
		return v;
	}



	private void find_view(final View v){

		// Pull Down to clear data
		mSwipeRefreshLayout = v.findViewById(R.id.swipe_container);
		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Log.e("action", "Refresh");

						mAlerDialog(v);

						mSwipeRefreshLayout.setRefreshing(false);
					}
				},1);
			}
		});
		
		// Find View by Amount
		Amount_Layout = v.findViewById(R.id.editor_amount);
		Amount_Layout.setTag("Amount_Layout");
		Amount_Layout.setOnClickListener(Item_Onclick_Listener);
		
		Amount_TextView = v.findViewById(R.id.editor_amount_text);


		
		// Find View by Name
		View sublayout_name = v.findViewById(R.id.include_name);
		
		Name_Layout = sublayout_name.findViewById(R.id.editor_textinputlayout);
		Name_Layout.setTag("Name_Layout");
		Name_Layout.setHint("名稱");
		Name_Layout.setOnClickListener(Item_Onclick_Listener);
		
		Name_EditText = sublayout_name.findViewById(R.id.editor_layout_text);
		Name_EditText.setSingleLine(true);
		
		// Drollar Side
		DollarSide_Layout = v.findViewById(R.id.editor_dollarside);
		DollarSide_Layout.setTag("DollarSide_Layout");
		DollarSide_Layout.setOnClickListener(Item_Onclick_Listener);
		
		DollarSide_TextView = v.findViewById(R.id.editor_doallarside_text);

		// Find View by Remarks
		View sublayout_remark = v.findViewById(R.id.include_remark);
		
		Remark_Layout = sublayout_remark.findViewById(R.id.editor_textinputlayout);
		Remark_Layout.setTag("Remark_Layout");
		Remark_Layout.setHint("備忘");
		Remark_Layout.setOnClickListener(Item_Onclick_Listener);
		
		Remark_EditText = sublayout_remark.findViewById(R.id.editor_layout_text);
		
		Remarks_underline = sublayout_remark.findViewById(R.id.editor_underline);
		Remarks_underline.setVisibility(View.GONE);
		


		// Find View by Account
		View sublayout_account = v.findViewById(R.id.include_account);

		Account_Layout = sublayout_account.findViewById(R.id.editor_textinputlayout);
		Account_Layout.setTag("Account_Layout");
		Account_Layout.setHint("付款帳戶");
		Account_Layout.setOnClickListener(Item_Onclick_Listener);

		Account_EditText = sublayout_account.findViewById(R.id.editor_layout_text);


		// Find View by PayMethod
		View sublayout_paymethod = v.findViewById(R.id.include_paymethod);
		
		PayMethod_Layout = sublayout_paymethod.findViewById(R.id.editor_textinputlayout);
		PayMethod_Layout.setTag("PayMethod_Layout");
		PayMethod_Layout.setHint("付款類別");
		PayMethod_Layout.setOnClickListener(Item_Onclick_Listener);
		
		PayMethod_EditText = sublayout_paymethod.findViewById(R.id.editor_layout_text);
		
		PayMethod_underline = sublayout_paymethod.findViewById(R.id.editor_underline);
		PayMethod_underline.setVisibility(View.GONE);
	}
	

	// Action when on click items
	private View.OnClickListener Item_Onclick_Listener = new View.OnClickListener(){

		@Override
		public void onClick(View view)
		{
			Log.e("Tag Name", view.getTag().toString());
			
			
			// TODO: Implement this method
			switch (view.getTag().toString()){
				
				case "Amount_Layout":
					DIALOG_REQUEST_CODE = 5;
					FRAGMENT_TAG = view.getTag().toString();
					DIALOG_TITLE = "銀碼";
					DIALOG_HINT = "輸入銀碼";
					TEMP_TEXT = Amount_TextView.getText().toString();

					mDialog_For_AmountInput = new Dialog_For_AmountInput(DIALOG_TITLE, TEMP_TEXT, FRAGMENT_TAG);
					mDialog_For_AmountInput.setTargetFragment(Fragement_Editor.this, DIALOG_REQUEST_CODE);
					mDialog_For_AmountInput.show(getFragmentManager(), FRAGMENT_TAG);
					break;
				
				case "Name_Layout":
					DIALOG_REQUEST_CODE = 1;
					FRAGMENT_TAG = view.getTag().toString();
					DIALOG_TITLE = "名稱";
					DIALOG_HINT = "輸入名稱";
					TEMP_TEXT = Name_EditText.getText().toString();
					
					mDialog_For_TextInput = new Dialog_For_TextInput(DIALOG_TITLE, DIALOG_HINT, TEMP_TEXT, FRAGMENT_TAG);
					mDialog_For_TextInput.setTargetFragment(Fragement_Editor.this, DIALOG_REQUEST_CODE);
					mDialog_For_TextInput.show(getFragmentManager(), FRAGMENT_TAG);
					break;
					

				case "DollarSide_Layout":
					DIALOG_REQUEST_CODE = 6;
					FRAGMENT_TAG = view.getTag().toString();
					DIALOG_TITLE = "選擇貨幣";

					mDialog_RecycleView= new Dialog_For_RecycleView(DIALOG_TITLE, PayMethod_EditText.getText().toString(), FRAGMENT_TAG);
					mDialog_RecycleView.setTargetFragment(Fragement_Editor.this, DIALOG_REQUEST_CODE);
					mDialog_RecycleView.show(getFragmentManager(), FRAGMENT_TAG);
					break;
					
					
				case "Account_Layout":
					DIALOG_REQUEST_CODE = 3;
					FRAGMENT_TAG = view.getTag().toString();
					DIALOG_TITLE = "付款帳戶";

					mDialog_RecycleView= new Dialog_For_RecycleView(DIALOG_TITLE, PayMethod_EditText.getText().toString(), FRAGMENT_TAG);
					mDialog_RecycleView.setTargetFragment(Fragement_Editor.this, DIALOG_REQUEST_CODE);
					mDialog_RecycleView.show(getFragmentManager(), FRAGMENT_TAG);
					break;
					
				case "PayMethod_Layout":
					DIALOG_REQUEST_CODE = 4;
					FRAGMENT_TAG = view.getTag().toString();
					DIALOG_TITLE = "支付類別";

					mDialog_RecycleView= new Dialog_For_RecycleView(DIALOG_TITLE, PayMethod_EditText.getText().toString(), FRAGMENT_TAG);
					mDialog_RecycleView.setTargetFragment(Fragement_Editor.this, DIALOG_REQUEST_CODE);
					mDialog_RecycleView.show(getFragmentManager(), FRAGMENT_TAG);
					break;
				
				case "Remark_Layout":
					DIALOG_REQUEST_CODE = 2;
					FRAGMENT_TAG = view.getTag().toString();
					DIALOG_TITLE = "備忘";
					DIALOG_HINT = "輸入備忘";
					TEMP_TEXT = Remark_EditText.getText().toString();
					
					mDialog_For_TextInput = new Dialog_For_TextInput(DIALOG_TITLE, DIALOG_HINT, TEMP_TEXT, FRAGMENT_TAG);
					mDialog_For_TextInput.setTargetFragment(Fragement_Editor.this, DIALOG_REQUEST_CODE);
					mDialog_For_TextInput.show(getFragmentManager(), FRAGMENT_TAG);
					break;
					
					
			}
		}
	};


	// Reset all value and clear
	public void Clear_Value(){

		Amount_TextView.setText("$ 0.00");
		Name_EditText.setTextWithPadding("");
		DollarSide_TextView.setText("HKD");
		Remark_EditText.setTextWithPadding("");
		PayMethod_EditText.setTextWithPadding("");
		Account_EditText.setTextWithPadding("");

	}

	private void mAlerDialog(View v){

		final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
		mBuilder.setTitle("");
		mBuilder.setMessage("資料尚未儲存, 是否刪除??");
		mBuilder.setCancelable(true);

		mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				Clear_Value();
				dialogInterface.dismiss();
			}
		});

		mBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.dismiss();
			}
		});

		mBuilder.show();

	}



}
