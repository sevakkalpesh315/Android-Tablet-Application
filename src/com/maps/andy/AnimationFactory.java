package com.maps.andy;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ViewAnimator;
public class AnimationFactory {
	 public static enum FlipDirection {
         LEFT_RIGHT, 
         RIGHT_LEFT;
         
         public float getStartDegreeForFirstView() {
                 return 0;
         }
         
         public float getStartDegreeForSecondView() {
                 switch(this) {
                 case LEFT_RIGHT:
                         return -90;
                 case RIGHT_LEFT:
                         return 90;
                 default:
                         return 0;
                 }
         }
         
         public float getEndDegreeForFirstView() {
                 switch(this) {
                 case LEFT_RIGHT:
                         return 90;
                 case RIGHT_LEFT:
                         return -90;
                 default:
                         return 0;
                 }
         }
         
         public float getEndDegreeForSecondView() {
                 return 0;
         }
         
         public FlipDirection theOtherDirection() {
                 switch(this) {
                 case LEFT_RIGHT:
                         return RIGHT_LEFT;
                 case RIGHT_LEFT:
                         return LEFT_RIGHT;
                 default:
                         return null;
                 }
         }
	 };
	 
	 public static Animation[] flipAnimation(final View fromView, final View toView, FlipDirection dir, long duration, Interpolator interpolator) {
         Animation[] result = new Animation[2];
         float centerX;
         float centerY;
         
         centerX = fromView.getWidth() / 2.0f;
         centerY = fromView.getHeight() / 2.0f;
         
         result[0] = new FlipAnimation(dir.getStartDegreeForFirstView(), dir.getEndDegreeForFirstView(), centerX, centerY, FlipAnimation.SCALE_DEFAULT, FlipAnimation.ScaleUpDownEnum.SCALE_DOWN);
         result[0].setDuration(duration);
         result[0].setFillAfter(true);
         result[0].setInterpolator(interpolator==null?new AccelerateInterpolator():interpolator);
         
         // Uncomment the following if toView has its layout established (not the case if using ViewFlipper and on first show)
         //centerX = toView.getWidth() / 2.0f;
         //centerY = toView.getHeight() / 2.0f;
         
         result[1] = new FlipAnimation(dir.getStartDegreeForSecondView(), dir.getEndDegreeForSecondView(), centerX, centerY, FlipAnimation.SCALE_DEFAULT, FlipAnimation.ScaleUpDownEnum.SCALE_UP);
         result[1].setDuration(duration);
         result[1].setFillAfter(true);
         result[1].setInterpolator(interpolator==null?new AccelerateInterpolator():interpolator);
         result[1].setStartOffset(duration);
         
         return result;
         
 }
	 
	 public static void flipTransition(final ViewAnimator viewAnimator, FlipDirection dir) { 
         
         final View fromView = viewAnimator.getCurrentView();
         final int currentIndex = viewAnimator.getDisplayedChild();
         final int nextIndex = (currentIndex + 1)%viewAnimator.getChildCount();
         
         final View toView = viewAnimator.getChildAt(nextIndex);

         Animation[] animc = AnimationFactory.flipAnimation(fromView, toView, (nextIndex < currentIndex?dir.theOtherDirection():dir), 500, null);

         viewAnimator.setOutAnimation(animc[0]);
         viewAnimator.setInAnimation(animc[1]);
         
         viewAnimator.showNext(); 
 }
	 public static Animation inFromLeftAnimation(long duration, Interpolator interpolator) {
         Animation inFromLeft = new TranslateAnimation(
                         Animation.RELATIVE_TO_PARENT,  -1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
                         Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
         );
         inFromLeft.setDuration(duration);
         inFromLeft.setInterpolator(interpolator==null?new AccelerateInterpolator():interpolator); //AccelerateInterpolator
         return inFromLeft;
 }
	 
	 public static Animation outToRightAnimation(long duration, Interpolator interpolator) {
         Animation outtoRight = new TranslateAnimation(
                         Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  +1.0f,
                         Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
         );
         outtoRight.setDuration(duration);
         outtoRight.setInterpolator(interpolator==null?new AccelerateInterpolator():interpolator);
         return outtoRight;
 }
	 
	 public static Animation inFromRightAnimation(long duration, Interpolator interpolator) {

         Animation inFromRight = new TranslateAnimation(
                         Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
                         Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
         );
         inFromRight.setDuration(duration);
         inFromRight.setInterpolator(interpolator==null?new AccelerateInterpolator():interpolator);
         return inFromRight;
 }
	 
	 public static Animation outToLeftAnimation(long duration, Interpolator interpolator) {
         Animation outtoLeft = new TranslateAnimation(
                         Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  -1.0f,
                         Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
         );
         outtoLeft.setDuration(duration);
         outtoLeft.setInterpolator(interpolator==null?new AccelerateInterpolator():interpolator);
         return outtoLeft;
 } 
	 public static Animation inFromTopAnimation(long duration, Interpolator interpolator) {
         Animation infromtop = new TranslateAnimation(
                         Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                         Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
         );
         infromtop.setDuration(duration);
         infromtop.setInterpolator(interpolator==null?new AccelerateInterpolator():interpolator);
         return infromtop;
 } 
	 
	 public static Animation outToTopAnimation(long duration, Interpolator interpolator) {
         Animation outtotop = new TranslateAnimation(
                         Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
                         Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT, -1.0f
         );
         outtotop.setDuration(duration); 
         outtotop.setInterpolator(interpolator==null?new AccelerateInterpolator():interpolator); 
         return outtotop;
 } 
	 public static Animation fadeInAnimation(long duration, long delay) {  
         
         Animation fadeIn = new AlphaAnimation(0, 1);
         fadeIn.setInterpolator(new DecelerateInterpolator());  
         fadeIn.setDuration(duration);
         fadeIn.setStartOffset(delay);
         
         return fadeIn;
 }
	 public static Animation fadeOutAnimation(long duration, long delay) {   

         Animation fadeOut = new AlphaAnimation(1, 0);
         fadeOut.setInterpolator(new AccelerateInterpolator());
         fadeOut.setStartOffset(delay);
         fadeOut.setDuration(duration);

         return fadeOut;
 }
	 public static Animation fadeInAnimation(long duration, final View view) { 
         Animation animation = fadeInAnimation(500, 0); 

     animation.setAnimationListener(new AnimationListener() { 
                 @Override
                 public void onAnimationEnd(Animation animation) {
                         view.setVisibility(View.VISIBLE);
                 } 
                 
                 @Override
                 public void onAnimationRepeat(Animation animation) { 
                 }  
                 
                 @Override
                 public void onAnimationStart(Animation animation) {
                         view.setVisibility(View.GONE); 
                 } 
     });
     
     return animation;
 }
	 public static Animation fadeOutAnimation(long duration, final View view) {
         
         Animation animation = fadeOutAnimation(500, 0); 

     animation.setAnimationListener(new AnimationListener() { 
                 @Override
                 public void onAnimationEnd(Animation animation) {
                         view.setVisibility(View.GONE);
                 } 
                 
                 @Override
                 public void onAnimationRepeat(Animation animation) { 
                 }  
                 
                 @Override
                 public void onAnimationStart(Animation animation) {
                         view.setVisibility(View.VISIBLE); 
                 } 
     });
     
     return animation;
         
 }
	 public static Animation[] fadeInThenOutAnimation(long duration, long delay) {  
         return new Animation[] {fadeInAnimation(duration,0), fadeOutAnimation(duration, duration+delay)};
 }  
	 public static void fadeOut(View v) { 
         if (v==null) return;  
     v.startAnimation(fadeOutAnimation(500, v)); 
 } 
	 public static void fadeIn(View v) { 
         if (v==null) return;
         
     v.startAnimation(fadeInAnimation(500, v)); 
 }
	 public static void fadeInThenOut(final View v, long delay) {
         if (v==null) return;
          
         v.setVisibility(View.VISIBLE);
         AnimationSet animation = new AnimationSet(true);
         Animation[] fadeInOut = fadeInThenOutAnimation(500,delay); 
     animation.addAnimation(fadeInOut[0]);
     animation.addAnimation(fadeInOut[1]);
     animation.setAnimationListener(new AnimationListener() { 
                 @Override
                 public void onAnimationEnd(Animation animation) {
                         v.setVisibility(View.GONE);
                 } 
                 @Override
                 public void onAnimationRepeat(Animation animation) { 
                 }  
                 @Override
                 public void onAnimationStart(Animation animation) {
                         v.setVisibility(View.VISIBLE); 
                 } 
     });
     
     v.startAnimation(animation); 
 }
}
