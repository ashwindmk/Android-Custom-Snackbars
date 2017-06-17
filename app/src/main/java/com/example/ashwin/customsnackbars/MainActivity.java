package com.example.ashwin.customsnackbars;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private String message = "Sample snackbar";

    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showSimpleSnackbar(View view) {
        Log.d(Constants.DEBUG_LOGGING, TAG + " : showSimpleSnackbar()");
        snackbar = Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.setAction("Action", null);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.dark_grey));
        snackbar.show();
    }

    public void showSingleButtonSnackbar(View view) {
        Log.d(Constants.DEBUG_LOGGING, TAG + " : showSingleButtonSnackbar()");
        snackbar = Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.getView().setBackgroundColor(this.getResources().getColor(R.color.dark_grey));
        snackbar.getView().setPadding(0, 10, 0, 10);
        snackbar.show();
    }

    public void showImageSnackbar(View view) {
        Log.d(Constants.DEBUG_LOGGING, TAG + " : showImageSnackbar()");

        snackbar = Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        View snackbarLayout = snackbar.getView();
        TextView textView = (TextView) snackbarLayout.findViewById(android.support.design.R.id.snackbar_text);
        Drawable drawable = this.getResources().getDrawable(R.drawable.ic_account_circle_white);
        // create our wrapper
        WrappedDrawable wrappedDrawable = new WrappedDrawable(drawable);
        // set bounds on wrapper
        wrappedDrawable.setBounds(0, 0, 80, 80);
        textView.setCompoundDrawablesWithIntrinsicBounds(wrappedDrawable, null, null, null);
        textView.setCompoundDrawablePadding(50);
        snackbar.show();
    }

    public void showImageAndButtonSnackbar(View view) {
        Log.d(Constants.DEBUG_LOGGING, TAG + " : showImageAndButtonSnackbar()");

        snackbar = Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        View snackbarLayout = snackbar.getView();
        TextView textView = (TextView) snackbarLayout.findViewById(android.support.design.R.id.snackbar_text);
        Drawable drawable = this.getResources().getDrawable(R.drawable.ic_account_circle_white);
        // create our wrapper
        WrappedDrawable wrappedDrawable = new WrappedDrawable(drawable);
        // set bounds on wrapper
        wrappedDrawable.setBounds(0, 0, 80, 80);
        textView.setCompoundDrawablesWithIntrinsicBounds(wrappedDrawable, null, null, null);
        textView.setCompoundDrawablePadding(50);
        snackbar.show();
    }

    public void showTwoButtonSnackbar(View view) {
        Log.d(Constants.DEBUG_LOGGING, TAG + " : showTwoButtonSnackbar()");

        // Create the Snackbar
        LinearLayout.LayoutParams objLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        snackbar = Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE);

        // Get the Snackbar layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        // Set snackbar layout params
        int navbarHeight = getNavBarHeight(this);
        FrameLayout.LayoutParams parentParams = (FrameLayout.LayoutParams) layout.getLayoutParams();
        parentParams.setMargins(0, 0, 0, 0 - navbarHeight + 50);
        layout.setLayoutParams(parentParams);
        layout.setPadding(0, 0, 0, 0);
        layout.setLayoutParams(parentParams);

        // Inflate our custom view
        View snackView = getLayoutInflater().inflate(R.layout.two_button_snackbar, null);

        // Configure our custom view
        TextView messageTextView = (TextView) snackView.findViewById(R.id.message_text_view);
        messageTextView.setText(message);

        TextView textViewOne = (TextView) snackView.findViewById(R.id.first_text_view);
        textViewOne.setText("ALLOW");
        textViewOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Constants.DEBUG_LOGGING, TAG + " : showTwoButtonSnackbar() : allow clicked");
                snackbar.dismiss();
            }
        });

        TextView textViewTwo = (TextView) snackView.findViewById(R.id.second_text_view);
        textViewTwo.setText("DENY");
        textViewTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Constants.DEBUG_LOGGING, TAG + " : showTwoButtonSnackbar() : deny clicked");
                snackbar.dismiss();
            }
        });

        // Add our custom view to the Snackbar's layout
        layout.addView(snackView, objLayoutParams);

        // Show the Snackbar
        snackbar.show();
    }

    public static int getNavBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
