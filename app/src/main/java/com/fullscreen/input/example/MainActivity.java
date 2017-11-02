package com.fullscreen.input.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import com.fullscreen.input.example.KeyboardLayout.KeyboardLayoutListener;

public class MainActivity extends AppCompatActivity {

    private View mBottomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mBottomLayout = findViewById(R.id.bottom_layout);
        ((KeyboardLayout)findViewById(R.id.root_layout)).setKeyboardListener(new KeyboardLayoutListener() {
            @Override
            public void onKeyboardStateChanged(boolean isActive, int keyboardHeight) {
                mBottomLayout.setTranslationY(isActive ? -keyboardHeight : 0);
            }
        });
    }
}
