package com.baixd.app.framework.opengl;


import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by baixd on 2015/5/11 011.
 * 实现渲染器 Renderer
 */
public class ThreeDGl implements GLSurfaceView.Renderer {


    /**
     * 窗口创建时被调用，可以在这里做一些初始化工作
     *
     * @param gl
     * @param config
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glShadeModel(GL10.GL_SMOOTH);//启用阴影平滑
        gl.glClearColor(0, 0, 0, 0);//黑色背景，色彩值范围从0.0f-1.0f
        gl.glClearDepthf(1.0f);//设置深度缓存
        gl.glEnable(GL10.GL_DEPTH_TEST);//启用深度测试
        gl.glDepthFunc(GL10.GL_LEQUAL);//所做深度测试的类型
        //对透视进行修正，使用它有轻微性能影响，但透视图会更好看
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

    }

    /**
     * 窗口改变时调用
     *
     * @param gl
     * @param width
     * @param height
     */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置OpenGL场景的大小
        gl.glViewport(0, 0, width, height);

        //设置投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //重置投影矩阵
        gl.glLoadIdentity();
        //设置视窗的大小
        gl.glFrustumf(-1, 1, -1, 1, 1, 10);
        //选择模型观察矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        //重置模型观察矩阵
        gl.glLoadIdentity();
    }


    @Override
    public void onDrawFrame(GL10 gl) {
        //清除屏幕和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        //重置当前的模型观察矩阵
        gl.glLoadIdentity();

    }


}
