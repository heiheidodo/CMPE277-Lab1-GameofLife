package com.example.lab1_gameoflife;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity{
    private LinearLayout layout;
    private Board board;
    private final int size = 15;
    private RelativeLayout rlayout;
    private Boolean refresh = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (LinearLayout)findViewById(R.id.linearLayout);
        rlayout = (RelativeLayout) findViewById(R.id.activity_main);
        //setContentView(new Board(this, 15));
        /*reset.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                dialogBox();
            }
        });*/
        Button next = (Button) findViewById(R.id.next);
        Button reset = (Button) findViewById(R.id.reset);
        board = new Board(this, size);

        layout.addView(board);
        //layout.invalidate();
        //setContentView(R.layout.activity_main);

        reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dialogBox(view);

                //layout.invalidate();

            }
        });

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // do algorithm
                board.calculate();
                //layout.invalidate();
                //System.out.println(board.getTouched());
            }
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }

    public void dialogBox(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to CLEAR the board?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        refresh = true;
                        if (refresh)
                        {
                            board.refresh();
                            refresh = false;
                        }
                        // layout.invalidate();
                        // startActivity(getIntent());
                        //layout.removeAllViews();
                        //layout.addView(board);
                        //layout.invalidate();
                        //rlayout.invalidate();
                        //setContentView(R.layout.activity_main);
                        //layout.removeAllViews();
                        //layout.addView(board);
                        //Intent intent = getIntent();
                        //finish();
                        //startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("cancel",
                new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        // do nothing
                        refresh = false;
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
/*
    @Override
    protected void onStop()
    {

    }

    @Override
    protected void onPause()
    {

    }

    @Override
    protected void onResume()
    {

    }*/
}
