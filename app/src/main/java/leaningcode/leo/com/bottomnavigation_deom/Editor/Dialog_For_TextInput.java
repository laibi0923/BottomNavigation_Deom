package leaningcode.leo.com.bottomnavigation_deom.Editor;
import android.annotation.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;
import leaningcode.leo.com.bottomnavigation_deom.R;

import android.support.v4.app.DialogFragment;


@SuppressLint("ValidFragment")
public class Dialog_For_TextInput extends DialogFragment
{

	private TextView TextInput_Title;
	private EditText TextInput_EditText;
	private ImageView TextInput_BackBtn;
	private RelativeLayout TextInput_VoiceBtn, TextInput_SaveBtn;
	
	private String Title, Hint, Temp_Text, FRAGMENT_TAG;
	public static final String RESPONSE = "response";

	public Dialog_For_TextInput(String Title, String Hint, String Temp_Text , String FRAGMENT_TAG){
		this.Title = Title;
		this.Hint = Hint;
		this.Temp_Text = Temp_Text;
		this.FRAGMENT_TAG = FRAGMENT_TAG;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onViewCreated(view, savedInstanceState);
		TextInput_EditText.post(new Runnable(){

				@Override
				public void run()
				{
					// TODO: Implement this method
					Open_keybord(getActivity());
				}
			});
	}


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogStyle);
		//Open_keybord(getActivity());
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View v = inflater.inflate(R.layout.editor_dialog_edittext, container, false);
		findview(v);
		return v;
	}
	
	private void findview(View v){
		
		TextInput_Title = v.findViewById(R.id.TextInput_Title);
		TextInput_Title.setText(Title);
		
		TextInput_EditText = v.findViewById(R.id.TextInput_EditText);
		if(FRAGMENT_TAG.equals("Name_Layout")){
			
			TextInput_EditText.setSingleLine(true);
			
		}else if(FRAGMENT_TAG.equals("Amount_Layout")){
			
			TextInput_EditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
			TextInput_EditText.setSelection(TextInput_EditText.getText().length());
//			TextInput_EditText.setEnabled(false);
			TextInput_EditText.setFocusable(false);
			TextInput_EditText.setText("0.00");
			TextInput_EditText.addTextChangedListener(DecimalTextWatcher);

		
		}
		
		if(Temp_Text.length() != 0){
			TextInput_EditText.setText(Temp_Text);
		}
		TextInput_EditText.setHint(Hint);
		TextInput_EditText.setFocusable(true);
		TextInput_EditText.setFocusableInTouchMode(true);
		TextInput_EditText.requestFocus();
		
		
		TextInput_BackBtn = v.findViewById(R.id.TextInput_BackBtn);
		TextInput_BackBtn.setOnClickListener(Button_OnClickListener);
		
		TextInput_VoiceBtn = v.findViewById(R.id.TextInput_VoiceBtn);
		TextInput_VoiceBtn.setOnClickListener(Button_OnClickListener);
		
		TextInput_SaveBtn = v.findViewById(R.id.TextInput_SaveBtn);
		TextInput_SaveBtn.setOnClickListener(Button_OnClickListener);
	}
	
	
	private View.OnClickListener Button_OnClickListener = new View.OnClickListener(){

		@Override
		public void onClick(View v)
		{
			// TODO: Implement this method
			switch(v.getId()){
				
				case R.id.TextInput_BackBtn:
					dismiss();
					break;
					
				case R.id.TextInput_VoiceBtn:
					break;
					
				case R.id.TextInput_SaveBtn:
					
					if(getTargetFragment() == null){
						return;
					}else{
						Intent mIntent = new Intent();
						mIntent.putExtra(RESPONSE, TextInput_EditText.getText().toString());
						getTargetFragment().onActivityResult(Fragement_Editor.DIALOG_REQUEST_CODE, Activity.RESULT_OK, mIntent);
					}
					
					dismiss();
					break;
					
			}
		}
		
	};
	
	// Open Soft keybord event
	public void Open_keybord(Context context){
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(TextInput_EditText, 0);
	}

	// Close Soft keybord event
	public void Close_keybord(Context context){
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(TextInput_EditText.getWindowToken(), 0);
	}

	// Text Watcher for Amount Input
	private TextWatcher DecimalTextWatcher = new TextWatcher(){
		boolean ignoreChange = false;
		
		@Override
		public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
		{
			// TODO: Implement this method
		}

		@Override
		public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
		{
			Integer xchangeInt = 0;
			// TODO: Implement this method
			if (!ignoreChange) {

				String xchangeString = p1.toString();

				xchangeString = xchangeString.replace(".", "");
				xchangeString = xchangeString.replace(" ", "");

				if (xchangeString.length() > 8){
					Log.e("Error Msg", "value was over 8 digit");
				}
				else {

					xchangeInt = Integer.parseInt(xchangeString);
					xchangeString = xchangeInt.toString();

					Log.e("amount", xchangeString);
					Log.e("amount2", xchangeInt.toString());

					if (xchangeString.length() == 0 | xchangeString.toString().isEmpty())
						xchangeString = "0.00";
					else if (xchangeString.length() == 1)
						xchangeString = "0.0" + xchangeString;
					else if (xchangeString.length() == 2)
						xchangeString = "0." + xchangeString;
					else if (xchangeString.length() > 2)
						xchangeString = xchangeString.substring(0, xchangeString.length() - 2) + "." + xchangeString.substring(xchangeString.length() - 2, xchangeString.length());
					ignoreChange = true;
					TextInput_EditText.setText(xchangeString);
					TextInput_EditText.setSelection(TextInput_EditText.getText().length());
					ignoreChange = false;

				}
            }
		}

		@Override
		public void afterTextChanged(Editable p1)
		{
			// TODO: Implement this method
		}
	};
}
