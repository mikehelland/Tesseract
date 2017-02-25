package com.monadpad.tesseract;

import android.app.Activity;
import android.os.Bundle;
import com.monadpad.common.MonadPad3DActiFrag;

public class MonadPad3D extends Activity {

    private MonadPad3DActiFrag mActiFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActiFrag = new MonadPad3DActiFrag(this, null, savedInstanceState);

        setContentView(mActiFrag.getView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActiFrag.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mActiFrag.onPause();
    }
}