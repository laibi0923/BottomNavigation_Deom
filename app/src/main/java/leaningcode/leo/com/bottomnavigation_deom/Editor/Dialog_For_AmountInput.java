package leaningcode.leo.com.bottomnavigation_deom.Editor;

import android.annotation.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;
import leaningcode.leo.com.bottomnavigation_deom.*;

import android.support.v4.app.DialogFragment;
import java.text.*;

@SuppressLint("ValidFragment")
public class Dialog_For_AmountInput extends DialogFragment {

	private TextInputLayout Amount_InputLayout;
    private EditText Amount_Display;
    private ImageView Back_Btn;
	private TextView Title_TextView;
    private RelativeLayout Save_Btn;

    private String Title, Temp_Value, FRAGMENT_TAG;
    public static final String RESPONSE = "response";

    public Dialog_For_AmountInput(String Title, String Temp_Value, String FRAGMENT_TAG){
        this.Title = Title;
        this.Temp_Value = Temp_Value;
        this.FRAGMENT_TAG = FRAGMENT_TAG;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.support.v4.app.DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogStyle);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.editor_dialog_amount, container, false);
        Find_View(v);
        return v;
    }


    private void Find_View(View v){
		
		Title_TextView = v.findViewById(R.id.TextInput_Title);
		Title_TextView.setText(Title);
		
		Amount_InputLayout = v.findViewById(R.id.amount_inputlayout);
		
        Amount_Display = v.findViewById(R.id.Amount_Text);
		Amount_Display.setText(Temp_Value);
        Amount_Display.addTextChangedListener(DecimalTextWatcher);
        Amount_Display.setSelection(Amount_Display.getText().length());
		Amount_Display.setOnClickListener(Item_OnclickListener);
		Amount_Display.setOnLongClickListener(Amount_LongClickListener);

        Back_Btn = v.findViewById(R.id.AmountInput_BackBtn);
        Back_Btn.setOnClickListener(Item_OnclickListener);

        Save_Btn = v.findViewById(R.id.AmountInput_SaveBtn);
        Save_Btn.setOnClickListener(Item_OnclickListener);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Amount_Display.post(new Runnable(){

            @Override
            public void run()
            {
                // TODO: Implement this method
                Open_keybord(getActivity());
            }
        });
    }

    // Item Onclick Listener
    private View.OnClickListener Item_OnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){

                case R.id.AmountInput_BackBtn:
                    dismiss();
                    break;

                case R.id.AmountInput_SaveBtn:
                    if(getTargetFragment() == null){
                        return;
                    }else{
                        Intent mIntent = new Intent();
                        mIntent.putExtra(RESPONSE, Amount_Display.getText().toString());
                        getTargetFragment().onActivityResult(Fragement_Editor.DIALOG_REQUEST_CODE, Activity.RESULT_OK, mIntent);
                    }
                    dismiss();
                    break;
				
				case R.id.Amount_Text:
					Amount_Display.setSelection(Amount_Display.getText().length());
					break;
            }
        }
    };

	private View.OnLongClickListener Amount_LongClickListener = new View.OnLongClickListener(){

		@Override
		public boolean onLongClick(View p1)
		{
			// TODO: Implement this method
			Amount_Display.setSelection(Amount_Display.getText().length());
			return false;
		}
	};


    // Open Soft keybord event
    public void Open_keybord(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(Amount_Display, 0);
    }

    // Close Soft keybord event
    public void Close_keybord(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(Amount_Display.getWindowToken(), 0);
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
			String getCurrentString ;
			Long string_parse_long;
			//String xchangeString;
			
            // TODO: Implement this method
            if (!ignoreChange) {

				try{
					getCurrentString = p1.toString();
					
					Log.e("getstring", getCurrentString);
					
					getCurrentString = getCurrentString.replace("$", "");
					//xchangeString = xchangeString.replace("0.00", "");
					//xchangeString = xchangeString.replace("0.0", "");
					//xchangeString = xchangeString.replace("0.", "");
					getCurrentString = getCurrentString.replaceAll(",", "");
					getCurrentString = getCurrentString.replace(".", "");
					getCurrentString = getCurrentString.replace(" ", "");
					
					
					
					
					string_parse_long = Long.parseLong(getCurrentString);
					getCurrentString = string_parse_long.toString();
					
					
					Log.e("replacestring","Value = " + getCurrentString + "," + "Length = " + getCurrentString.length());
					
					
					if(getCurrentString.length() == 0 | getCurrentString.isEmpty()){
						getCurrentString = "0.00";
					}else if(getCurrentString.length() == 1){
						getCurrentString = "0.0" + getCurrentString;
					}else if(getCurrentString.length() == 2){
						getCurrentString = "0." + getCurrentString;
					}else if(getCurrentString.length() > 2){
						
						DecimalFormat DF = new DecimalFormat("#,###,###");
						long parsetolongval;
						
						if(getCurrentString.length() > 8){
							
							Amount_InputLayout.setError("單筆紀錄上限為 $999,999.99");
							
							parsetolongval = Long.parseLong(getCurrentString.substring(0, 6));

							String Formate_String = DF.format(parsetolongval);
							getCurrentString = Formate_String + "." + getCurrentString.substring(6, 8);
							
						}else{
							
							Amount_InputLayout.setError("");
							
							parsetolongval = Long.parseLong(getCurrentString.substring(0, getCurrentString.length() - 2));

							String Formate_String = DF.format(parsetolongval);
							getCurrentString = Formate_String + "." + getCurrentString.substring(getCurrentString.length()-2, getCurrentString.length());
						}
						
					}
					
					ignoreChange = true;
					Amount_Display.setText("$ " + getCurrentString);
					Amount_Display.setSelection(Amount_Display.getText().length());
					ignoreChange = false;
					
				}catch(Exception e){}
                
				
				
				
				
				
				
				/*

                if (xchangeString.length() == 0){
                    Log.e("Error Msg", "value was less 1 digit");
                    Amount_Display.setText("0.00");
                }else {
                    

                    xchangeInt = Integer.parseInt(xchangeString);
                    xchangeString = xchangeInt.toString();

                    Log.e("MSG", "Now Length : " + xchangeString.length());
                    Log.e("MSG", "Value is : " +  xchangeInt.toString());

                    if (xchangeString.length() == 0 | xchangeString.toString().isEmpty())
                        xchangeString = "0.00";
                    else if (xchangeString.length() == 1)
                        xchangeString = "0.0" + xchangeString;
                    else if (xchangeString.length() == 2)
                        xchangeString = "0." + xchangeString;
                    else if (xchangeString.length() > 8){
                        xchangeString = xchangeString.substring(0,6) + "." + xchangeString.substring(6,8);
						}
                    else if (xchangeString.length() > 2)
                        xchangeString = xchangeString.substring(0, xchangeString.length() - 2) + "." + xchangeString.substring(xchangeString.length() - 2, xchangeString.length());
                    ignoreChange = true;
                    Amount_Display.setText(xchangeString);
                    Amount_Display.setSelection(Amount_Display.getText().length());
                    ignoreChange = false;
                }
				
				*/
            }
        }

        @Override
        public void afterTextChanged(Editable p1)
        {
        }
    };
}

