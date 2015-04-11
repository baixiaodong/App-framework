package com.baixd.app.framework.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baixd.app.framework.R;


/**
 * 定义一个标题栏，自定义的组件都以UI开头
 * Created by Baixd on 2015/4/6.
 */
public class UITitleBar extends RelativeLayout{

    private Button leftButton, rightButton;
    private TextView titleTextView;

    //左边按钮属性
    private String leftText;
    private int leftTextColor;
    private Drawable leftBackground;

    //右边按钮属性
    private String rightText;
    private int rightTextColor;
    private Drawable rightBackground;

    //标题
    private String titleText;
    private float titleTextSize;
    private int titleTextColor;

    private LayoutParams leftParams, rightParams, titleParams;

    private UITitleBarClickListener mUITitleBarClickListener;

    public interface UITitleBarClickListener{
        public void onLeftClick();
        public void onRightClick();
    }

    public void setUITitleBarClickListener(UITitleBarClickListener listener){
        this.mUITitleBarClickListener = listener;
    }

    public UITitleBar(Context context, AttributeSet attrs) {

        super(context, attrs);
        initFromAttributes(context, attrs);
    }

    /**
     * 初始化参数
     *
     * @param context
     * @param attrs
     */
    private void initFromAttributes(Context context, AttributeSet attrs) {

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.UITitleBar);

        leftText = ta.getString(R.styleable.UITitleBar_leftText);
        leftTextColor = ta.getInt(R.styleable.UITitleBar_leftTextColor, 0);
        leftBackground = ta.getDrawable(R.styleable.UITitleBar_leftBackground);

        rightText = ta.getString(R.styleable.UITitleBar_rightText);
        rightTextColor = ta.getInt(R.styleable.UITitleBar_rightTextColor, 0);
        rightBackground = ta.getDrawable(R.styleable.UITitleBar_rightBackground);

        titleText = ta.getString(R.styleable.UITitleBar_titleText);
        titleTextSize = ta.getDimension(R.styleable.UITitleBar_titleTextSize, 16);
        titleTextColor = ta.getInt(R.styleable.UITitleBar_titleTextColor, 0);

        ta.recycle();

        leftButton = new Button(context);
        rightButton = new Button(context);
        titleTextView = new TextView(context);

        leftButton.setText(leftText);
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);

        rightButton.setText(rightText);
        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);

        titleTextView.setText(titleText);
        titleTextView.setTextColor(titleTextColor);
        titleTextView.setTextSize(titleTextSize);
        titleTextView.setGravity(Gravity.CENTER); //居中显示

        setBackgroundColor(0xFFF69563);

        //设置控件参数并加载视图
        leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(leftButton, leftParams);

        rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(rightButton, rightParams);

        titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(titleTextView, titleParams);

        onLeftButtonClick();
        onRightButtonClick();

    }

    public void onLeftButtonClick(){
        leftButton.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                mUITitleBarClickListener.onLeftClick();;
            }
        });
    }

    public void onRightButtonClick(){
        rightButton.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                mUITitleBarClickListener.onRightClick();
            }
        });
    }



}
