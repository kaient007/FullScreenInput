package com.fullscreen.input.example;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class KeyboardLayout extends RelativeLayout {

    private KeyboardLayoutListener mListener;
    private boolean mIsKeyboardActive = false;
    private int mKeyboardHeight = -1;

    public KeyboardLayout(Context context) {
        this(context, null, 0);
    }

    public KeyboardLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 监听布局变化
        getViewTreeObserver().addOnGlobalLayoutListener(new KeyboardOnGlobalChangeListener());
    }

    private class KeyboardOnGlobalChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {

        int mScreenHeight = 0;
        Rect mRect = new Rect();

        private int getScreenHeight() {
            if (mScreenHeight > 0) {
                return mScreenHeight;
            }
            mScreenHeight = ((WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getHeight();
            return mScreenHeight;
        }

        @Override
        public void onGlobalLayout() {

            getWindowVisibleDisplayFrame(mRect);

            int screenHeight = getScreenHeight();
            int keyboardHeight = screenHeight - mRect.bottom;
            boolean isActive = false;
            if (mKeyboardHeight == keyboardHeight) {
                return;
            }
            mKeyboardHeight = keyboardHeight;
            if (Math.abs(keyboardHeight) > screenHeight / 5) {
                isActive = true;
            }
            mIsKeyboardActive = isActive;
            if (mListener != null) {
                mListener.onKeyboardStateChanged(isActive, keyboardHeight);
            }
        }
    }

    public void setKeyboardListener(KeyboardLayoutListener listener) {
        mListener = listener;
    }

    public interface KeyboardLayoutListener {
        /**
         * @param isActive       输入法是否激活
         * @param keyboardHeight 输入法面板高度
         */
        void onKeyboardStateChanged(boolean isActive, int keyboardHeight);
    }

}
