package leaningcode.leo.com.bottomnavigation_deom.Editor;
import android.widget.*;
import android.widget.TextView.*;
import android.content.*;
import android.util.*;
import android.content.res.*;
import android.text.*;

/* 
	Create on 26 Jul 2018
	自定義 EditText, 當有文字嘅時候顯示 Padding, 反之不顯示 Padding
*/

public class PaddingTopEditText extends EditText
{

	public PaddingTopEditText(Context context){
		super(context);
	}
	

    public PaddingTopEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        
    }

    public PaddingTopEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        
    }

    
	
	
	public void setTextWithPadding(CharSequence text)
	{
		// TODO: Implement this method
		super.setText(text);
		
		if(text.length() != 0){
			this.setPadding(0, 40, 0, 0);
		}else{
			this.setPadding(0, 0, 0, 0);
		}
	}

	@Override
	public Editable getText()
	{
		// TODO: Implement this method
		return super.getText();
	}

	
	
}
