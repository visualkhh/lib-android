package khh.android.animation;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewAnimator;

public class AnimationUtil {
	public static Animation getAnimation(Context context,int anim_res) {
		return AnimationUtils.loadAnimation(context,anim_res);
	}
	public static void setAnimation(Context context,ViewAnimator victim,int in_animRrs,int out_animRrs) {
//		victim.setInAnimation(AnimationUtils.loadAnimation(context, animRrs));
			victim.setInAnimation(context, in_animRrs);
			victim.setOutAnimation(context, out_animRrs);
	}
	
}
