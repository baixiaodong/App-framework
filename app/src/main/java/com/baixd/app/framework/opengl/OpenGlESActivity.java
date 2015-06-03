package com.baixd.app.framework.opengl;

import android.opengl.GLSurfaceView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.baixd.app.framework.R;

public class OpenGlESActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        GLSurfaceView.Renderer renderer = new ThreeDGl();

        GLSurfaceView.Renderer renderer = new GLRender();

        GLSurfaceView gView = new GLSurfaceView(this);
        gView.setRenderer(renderer);
        setContentView(gView);

    }
}
