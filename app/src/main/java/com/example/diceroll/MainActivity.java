package com.example.diceroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button roll;
    private ImageView iv1;
    private ImageView iv2;
    private int number1, number2;
    private TextView textView, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate:");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = findViewById(R.id.imageView);
        iv2 = findViewById(R.id.imageView2);
        roll = findViewById(R.id.button);
        textView = findViewById(R.id.textView2);
        textView2 = findViewById(R.id.textView);
        textView3 = findViewById(R.id.textView3);

        roll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textView.setText("");
                textView2.setText("");
                textView3.setText("");
                ShowGif(v);
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                sleep(700);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        number1 = diceRoll();
                                        Thread thread = new Thread() {
                                            @Override
                                            public void run() {
                                                try {
                                                    while (true) {
                                                        sleep(700);
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                number1 = diceRoll();
                                                                number2 = diceRoll();
                                                                showResult();
                                                            }
                                                        });
                                                        break;
                                                    }
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        };
                                        thread.start();
                                    }

                                });      
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

});

        }



public void ShowGif(View view) {
        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView2 = findViewById(R.id.imageView2);
        Glide.with(this).load(R.drawable.dicerolling).into(imageView);
        Glide.with(this).load(R.drawable.dicerolling).into(imageView2);

        }

public void showResult() {
        String message1 = String.valueOf(number1);
        String message2 = String.valueOf(number2);
        String message3 = String.valueOf(number1 + number2);

        getImage(number1, iv1);
        getImage(number2, iv2);

        textView.setText(message1);
        textView2.setText(message2);
        textView3.setText(message3);
        }

private void getImage(int result, ImageView img) {

        if (result == 1) {
        img.setImageResource(R.drawable.dice_1);
        } else if (result == 2) {
        img.setImageResource(R.drawable.dice_2);
        } else if (result == 3) {
        img.setImageResource(R.drawable.dice_3);
        } else if (result == 4) {
        img.setImageResource(R.drawable.dice_4);
        } else if (result == 5) {
        img.setImageResource(R.drawable.dice_5);
        } else if (result == 6) {
        img.setImageResource(R.drawable.dice_6);
        }

        }

public static int diceRoll() {
        return (int) (Math.random() * 6) + 1;
        }


public void open10(View view) {
        Intent intent = new Intent(this, RollTen.class);
        startActivity(intent);

        }

        }