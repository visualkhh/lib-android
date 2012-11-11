package khh.android.broadcast;

import khh.android.file.FileUtil;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

public class BroadCastUtil {
	//이미지 리마운트 
	//브로드캐스트를 통해 새로운 파일을 검색하도록하여 갤러리에서 저장된 그림 파일을 확인할 수 있게 해준다.
//	sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
//	위와 같이 전체 미디어 스캐닝을 돌려버리게 되면 SD카드가 remount되면서
//	로컬 mp3파일을 재생중이거나 할 경우 음악이 멈춰버리는 사태가 발생해버리는데요
//	sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+ "폴더위치"+"파일이름"+".파일확장자")));
//	요렇게 해주시면 해당 파일만 스캐닝 되어 아무 문제 없이 해결이 가능합니다 ㅎㅎ
	public static void reMountedForMedia(Context context){
	    context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
		          Uri.parse("file://" + FileUtil.getStorageDirectory())));
//		          Uri.parse("file://" + Environment.getExternalStorageDirectory())));
	}
	
	public static void sendBroadcast(Context context,Intent intent){
		 context.sendBroadcast(intent);
	}
}
