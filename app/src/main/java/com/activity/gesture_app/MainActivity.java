package com.activity.gesture_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat mDetector;

    private static final String DEBUG_TAG = "Gestures";
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private TextView counter;
    public Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fetch ListView from xml file
        //@SuppressLint({"MissingInflatedId", "LocalSuppress"}) ListView lst = findViewById(R.id.listNavigation);

        // Un tableau contenant les titres devront s’afficher dans le menu flottant
        //String [] tableau = {"Un", "Deux", "Trois"};

        //alimenter le tableau
        //lst.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tableau));

        LinearLayout screen = findViewById(R.id.ecran);

       /* screen.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View header = getLayoutInflater().inflate(R.layout.header_menu,null);
                TextView pro = header.findViewById(R.id.profile_image);
                lst.addHeaderView(header);
            }
        });*/

        b = findViewById(R.id.btnTest);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pp = new PopupMenu(MainActivity.this, b);
                pp.getMenuInflater().inflate(R.menu.pop_up_menu, pp.getMenu());
                pp.show();
                pp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("1")){screen.setBackgroundColor(Color.RED);}
                        if(item.getTitle().equals("2")){screen.setBackgroundColor(Color.BLUE);}
                        if(item.getTitle().equals("3")){screen.setBackgroundColor(Color.YELLOW);}
                        return false;
                    }
                });
            }
        });

        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener(){

            @Override
            public void onLongPress(MotionEvent e) {

                Log.i(DEBUG_TAG, "onLongPress: ");
                counter = (TextView)findViewById(R.id.my_counter);
                String value = counter.getText().toString();
                int counterV = Integer.parseInt(value);
                counterV=0;
                counter.setText(String.valueOf(counterV));
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float v1, float v2){

                boolean result=false;

                float x1 = e1.getX();
                float y1 = e1.getY();
                float x2 = e2.getX();
                float y2 = e2.getY();
                float diffX = x2 - x1;
                float diffY = y2 - y1;

                counter = (TextView)findViewById(R.id.my_counter);
                String value = counter.getText().toString();
                int counterV = Integer.parseInt(value);

                Log.d("---onFling---", diffX + diffY + "");

                try {
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(v1) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                Log.d(DEBUG_TAG, "onFling: Right Swipe");
                                counterV++;
                                counter.setText(String.valueOf(counterV));

                            } else {
                                Log.d(DEBUG_TAG, "onFling: Left Swipe");
                                counterV--;
                                counter.setText(String.valueOf(counterV));
                            }
                            result = true;
                        }
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(v2) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            Log.d(DEBUG_TAG, "onFling: Down Swipe");
                            counterV--;
                            counter.setText(String.valueOf(counterV));

                        } else {
                            Log.d(DEBUG_TAG, "onFling: Up Swipe");
                            counterV++;
                            counter.setText(String.valueOf(counterV));

                        }
                        result = true;
                    }
                } catch (Exception e) {
                    // nothing
                }

                return result;
            }

        };

        //GestureDetector gesture = new GestureDetector(this,listener);
        mDetector = new GestureDetectorCompat(this,listener);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.mDetector.onTouchEvent(event)) {

            return true;
        }
        return super.onTouchEvent(event);
    }
}

