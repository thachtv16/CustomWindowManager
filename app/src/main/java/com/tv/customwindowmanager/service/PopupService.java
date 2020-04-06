package com.tv.customwindowmanager.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.tv.customwindowmanager.R;
import com.tv.customwindowmanager.view.FullView;
import com.tv.customwindowmanager.view.PopupView;

public class PopupService extends Service implements View.OnClickListener, View.OnTouchListener {

    private WindowManager windowManager;
    private PopupView popupView;
    private WindowManager.LayoutParams popupLayoutParams;

    private FullView fullView;
    private WindowManager.LayoutParams fullLayoutParams;

    private int previousX;
    private int previousY;

    private float startX;
    private float startY;

    private int state;
    private static final int TYPE_FULL = 0;
    private static final int TYPE_POPUP = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initView();
        return START_STICKY;
    }

    private void initView() {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        
        createPopupView();
        createFullView();
        showPopupView();
    }

    private void createFullView() {
        fullView = new FullView(this);

        View view = View.inflate(this, R.layout.layout_full_view, fullView);
        fullView.setOnTouchListener(this);

        fullLayoutParams = new WindowManager.LayoutParams();
        fullLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        fullLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        fullLayoutParams.gravity = Gravity.CENTER;
        fullLayoutParams.format = PixelFormat.TRANSLUCENT;
        fullLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        fullLayoutParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        fullLayoutParams.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        fullLayoutParams.flags |= WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
    }

    private void showPopupView() {
        try {
            windowManager.removeView(fullView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        windowManager.addView(popupView, popupLayoutParams);
        state = TYPE_POPUP;
    }

    private void showFullView() {
        try {
            windowManager.removeView(popupView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        windowManager.addView(fullView, fullLayoutParams);
        state = TYPE_FULL;
    }

    private void createPopupView() {
        popupView = new PopupView(this);

        View view = View.inflate(this, R.layout.layout_popup_view, popupView);
        LinearLayout groupPopup = view.findViewById(R.id.group_popup);
        ImageView closePopup = view.findViewById(R.id.popup_close);
        YouTubePlayerView playerPopup = view.findViewById(R.id.popup_player);
        playerPopup.setOnClickListener(this);
        playerPopup.setOnTouchListener(this);
        groupPopup.setOnClickListener(this);
        closePopup.setOnClickListener(this);
        groupPopup.setOnTouchListener(this);

        popupLayoutParams = new WindowManager.LayoutParams();
        popupLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        popupLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        popupLayoutParams.gravity = Gravity.CENTER;
        popupLayoutParams.format = PixelFormat.TRANSLUCENT;
        popupLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        popupLayoutParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        popupLayoutParams.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.group_popup:
                showFullView();
                break;
            case R.id.popup_close:
                stopSelf();
                removeView();
                break;
            default:
                break;
        }
    }

    private void removeView() {
        try {
            windowManager.removeView(popupView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (state == TYPE_POPUP) {
                    previousX = popupLayoutParams.x;
                    previousY = popupLayoutParams.y;
                } else {
                    previousX = fullLayoutParams.x;
                    previousY = fullLayoutParams.y;
                }
                startX = event.getRawX();
                startY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                double deltaX = event.getRawX() - startX;
                double deltaY = event.getRawY() - startY;
                if (state == TYPE_POPUP) {
                    popupLayoutParams.x = previousX + (int) deltaX;
                    popupLayoutParams.y = previousY + (int) deltaY;
                    windowManager.updateViewLayout(popupView, popupLayoutParams);
                }
                break;
            case MotionEvent.ACTION_OUTSIDE:
                if (state == TYPE_FULL) {
                    showPopupView();
                }
                break;
        }
        return false;
    }
}
