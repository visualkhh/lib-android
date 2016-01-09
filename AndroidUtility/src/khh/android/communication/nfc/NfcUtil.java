package khh.android.communication.nfc;

import android.content.Context;
import android.nfc.NfcAdapter;

public class NfcUtil {
	public static NfcAdapter getDefaultAdapter(Context context){
		return NfcAdapter.getDefaultAdapter(context);
	}
}
