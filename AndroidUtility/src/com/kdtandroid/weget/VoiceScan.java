package com.kdtandroid.weget;

import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.widget.Toast;

public class VoiceScan {
	List<ResolveInfo> activities = null;
	public static final int REQUEST_CODE = 57187;
	Activity activity = null;
	Activity callbackActivity = null;
	public VoiceScan(Activity activity,Activity callbackActivity) {
		this.activity = activity;
		this.callbackActivity = callbackActivity;
		PackageManager pm =  this.activity.getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
	}
	
	public boolean isUse(){
		if (activities == null || activities.size() != 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public void voiceScan(){
		 try{
	            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
	            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Free Form Language Model Demo");
	            callbackActivity.startActivityForResult(intent, REQUEST_CODE);
	        } catch (ActivityNotFoundException ex) {
	            Toast.makeText(this.activity, "Activity Not Found", Toast.LENGTH_LONG).show();
	        }
	}
	
	
	
	//사용법 받는곳에서 오버라이딩해서 사용.
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // TODO Auto-generated method stub
//        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
//            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            for(int i = 0 ; i <matches.size();i++){
//            	text.setText(text.getText()+"\n"+matches.get(i));
//            }
//        }
//         
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
