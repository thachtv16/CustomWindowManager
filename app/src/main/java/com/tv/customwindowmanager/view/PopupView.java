package com.tv.customwindowmanager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.tv.customwindowmanager.R;

public class PopupView extends LinearLayout {

    private WindowManager.LayoutParams popupParams;

//    private YouTubeResizeListener listener;
    private OnHandlePopupViewListener listener;

    private LinearLayout groupPopup;
    private ImageView popupClose;
    private YouTubePlayerView popupPlayer;
    private ImageView popupZoom;

    private float lastX, lastY, dx, dy;

    private float centerX, centerY;
    private boolean moving;

    /*public void setListener(YouTubeResizeListener listener) {
        this.listener = listener;
    }*/

    public PopupView(Context context) {
        super(context);
//        init();
    }

    public PopupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_popup_view, this);
        groupPopup = findViewById(R.id.group_popup);
        popupClose = findViewById(R.id.popup_close);
        popupPlayer = findViewById(R.id.popup_player);
        popupZoom = findViewById(R.id.popup_zoom);

        /*popupParams = new WindowManager.LayoutParams(
                DimensUtils.convertDpToPx(getContext(), 210),
                DimensUtils.convertDpToPx(getContext(), 114),
                DimensUtils.getParamsTypes(),
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT
        );
        String className = "android.view.WindowManager$LayoutParams";
        try {
            Class layoutParamsClass = Class.forName(className);
            Field privateFlags = layoutParamsClass.getField("privateFlags");
            Field noAnim = layoutParamsClass.getField("PRIVATE_FLAG_NO_MOVE_ANIMATION");
            int privateFlagsValue = privateFlags.getInt(popupParams);
            int noAnimFlag = noAnim.getInt(popupParams);
            privateFlagsValue |= noAnimFlag;
            privateFlags.setInt(popupParams, privateFlagsValue);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        handleResize();*/
    }
//
//    public WindowManager.LayoutParams getPopupParams() {
//        return popupParams;
//    }

    public interface OnHandlePopupViewListener {

        void showFullView();
    }

    /*public void updateLayout(WindowManager windowManager) {
        try {
            centerX = popupParams.x + popupParams.width / 2;
            centerY = popupParams.y + popupParams.height / 2;
            windowManager.updateViewLayout(this, popupParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                if (listener != null) {
                    listener.onResizingStart();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                dx = event.getRawX() - lastX;
                dy = event.getRawY() - lastY;
                if (!moving && (dx > 10 || dy>10)) {
                    moving = true;
                }
                if (moving) {
                    if (listener != null) {
                        listener.onPopupResizeMove(centerX + dx, centerY + dy);
                    }
                }
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
        }
        return super.onTouchEvent(event);
    }*/

    /*public interface YouTubeResizeListener {
        void onPopupResizeMove(float x, float y);

        void onResizingStart();
    }*/
}
