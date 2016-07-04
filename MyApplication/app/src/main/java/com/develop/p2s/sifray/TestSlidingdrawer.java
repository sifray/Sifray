package com.develop.p2s.sifray;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

public class TestSlidingdrawer extends AppCompatActivity {

    private SlidingDrawer drawer;
    private Button handle, clickme;
    private TextView text1;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_slidingdrawer);

        context = this.getApplicationContext();
        handle = (Button) findViewById(R.id.handle);
        text1 = (TextView) findViewById(R.id.text1);
        clickme = (Button) findViewById(R.id.click);
        drawer = (SlidingDrawer) findViewById(R.id.slidingDrawer);

        drawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                handle.setText(" - ");
                text1.setText("Already Dragged");
            }
        });

        drawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                handle.setText("+");
                text1.setText("For more info drag the button...");
            }
        });

        clickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "The button is clicked", Toast.LENGTH_LONG).show();
            }
        });
    }
}
