package khh.android.file;

import java.io.File;

import android.os.Environment;

public class FileUtil {

	public static File getStorageDirectory(){
		return Environment.getExternalStorageDirectory();
	}
}
