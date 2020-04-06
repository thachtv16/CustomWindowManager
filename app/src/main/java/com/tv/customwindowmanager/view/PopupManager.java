package com.tv.customwindowmanager.view;

import android.content.Context;
import android.util.Log;
import android.view.WindowManager;

public class PopupManager /*implements PopupView.YouTubeResizeListener*/ {

    private WindowManager windowManager;

    private PopupView popupView;

    private int screenWidth;
    private int screenHeight;

    private int savedX, savedY, savedWidth, savedHeight;
    private float orgCX, orgCY, orgWidth, orgHeight;

    public PopupManager(Context context) {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        popupView = new PopupView(context);
//        popupView.setListener(this);
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
    }

    /*public void show() {
        popupView.getPopupParams().x = screenWidth / 2 - popupView.getPopupParams().width / 2;
        popupView.getPopupParams().y = screenHeight / 2 - popupView.getPopupParams().height / 2;
        try {
            windowManager.addView(popupView, popupView.getPopupParams());
//            popupView.updateLayout(windowManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void remove() {
        try {
            windowManager.removeViewImmediate(popupView);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void onPopupResizeMove(float x, float y) {
        if (x - popupView.getPopupParams().width / 2 > orgCX && y + popupView.getPopupParams().height / 2 < orgCY) {
            float distanceX = Math.abs(x - orgCX);
            float distanceY = Math.abs(y - orgCY);


//            youTubeContainerView.getFloatingParams().x = (int) (orgCX - distanceX);
//            youTubeContainerView.getFloatingParams().y = (int) (orgCY - distanceY);
//            youTubeContainerView.getFloatingParams().width = (int) (distanceX * 2);
//            youTubeContainerView.getFloatingParams().height = (int) (distanceY * 2);

//            youTubeContainerView.updateLayout(windowManager);
            popupView.getPopupParams().x = (int) x - popupView.getPopupParams().width / 2;
            popupView.getPopupParams().y = (int) y - popupView.getPopupParams().height / 2;
            popupView.updateLayout(windowManager);
        }
    }

    @Override
    public void onResizingStart() {
        Log.e("thachtv", "onResizingStart: zoom" );
    }*/
}
