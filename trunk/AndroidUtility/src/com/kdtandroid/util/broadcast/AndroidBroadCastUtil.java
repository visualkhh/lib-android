package com.kdtandroid.util.broadcast;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

public class AndroidBroadCastUtil {
	//�̹��� ������Ʈ 
	//��ε�ĳ��Ʈ�� ���� ���ο� ������ �˻��ϵ����Ͽ� ���������� ����� �׸� ������ Ȯ���� �� �ְ� ���ش�.
//	sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
//	���� ���� ��ü �̵�� ��ĳ���� ���������� �Ǹ� SDī�尡 remount�Ǹ鼭
//	���� mp3������ ������̰ų� �� ��� ������ ��������� ���°� �߻��ع����µ���
//	sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+ "������ġ"+"�����̸�"+".����Ȯ����")));
//	�䷸�� ���ֽø� �ش� ���ϸ� ��ĳ�� �Ǿ� �ƹ� ���� ���� �ذ��� �����մϴ� ����
	public static void sendBroadcast_MediaMounted(Context context){
	    context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
		          Uri.parse("file://" + Environment.getExternalStorageDirectory())));
	}
	
}
