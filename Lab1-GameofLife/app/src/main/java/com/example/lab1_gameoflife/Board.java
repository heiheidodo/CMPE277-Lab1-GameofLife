package com.example.lab1_gameoflife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Rect;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazhitu on 2/23/17.
 */

public class Board extends View
{
    private int size = 0;
    private Button bt;
    private List<Rect> tiles = new ArrayList<>();
    private int exact = 0;
    private List<Boolean> touched = new ArrayList<>();
    private Algorithm cal;
    private Boolean next = false;
    private List<Boolean> state = new ArrayList<>();
    private Boolean reset = false;

    public Board(Context context, int size)
    {
        //bitmap = Bitmap.createBitmap(1920,1080, Bitmap.Config.ARGB_8888);
        super(context);
        // Canvas canvas = new Canvas(bitmap);
        this.size = size;

        for (int i = 0; i < size*size; i++)
        {
            touched.add(false);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth()/size;
        int height = canvas.getHeight()/size;
        this.exact = (width > height? height : width);

        if (!reset) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Rect piece = new Rect();
                    piece.set(j * exact, i * exact, j * exact + exact, i * exact + exact);

                    tiles.add(piece);

                    Paint paint = new Paint();

                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(Color.LTGRAY);
                    canvas.drawRect(piece, paint);

                    paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(Color.BLACK);
                    paint.setStrokeWidth(2);
                    canvas.drawRect(piece, paint);

                    //if (!next)
                    {
                        if (touched.get(i * size + j))
                        {
                            //System.out.println(i*size + j);
                            //System.out.println(touched.get(i*size+j));
                            paint.setStyle(Paint.Style.FILL);
                            paint.setColor(Color.RED);
                            canvas.drawCircle(j * exact + exact / 2, i * exact + exact / 2,
                                    exact / 2, paint);
                        } else
                        {
                            touched.set(i * size + j, false);
                            paint.setStyle(Paint.Style.FILL);
                            paint.setColor(Color.LTGRAY);
                            canvas.drawRect(piece, paint);
                        }
                    }
                }
            }
        }
        else
        {
            int count = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Rect piece = new Rect();
                    piece.set(j * exact, i * exact, j * exact + exact, i * exact + exact);

                    tiles.add(piece);

                    Paint paint = new Paint();

                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(Color.LTGRAY);
                    canvas.drawRect(piece, paint);

                    paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(Color.BLACK);
                    paint.setStrokeWidth(2);
                    canvas.drawRect(piece, paint);

                    touched.set(count, false);
                    count++;
                }
            }
            reset = false;
        }
    }

    public int getRecId()
    {
        return 0;
    }

    public boolean onTouchEvent (MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                for (Rect rect : tiles)
                {
                    if (rect.contains((int) x, (int) y))
                    {
                        int id = tiles.indexOf(rect);
                        System.out.println("Touching rec id" + id);
                        //Paint pt = new Paint();
                        //pt.setColor(Color.RED);

                        //canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), this.exact, pt);
                        if (touched.get(id) == true)
                        {
                            touched.set(id, false);
                            invalidate();
                            break;
                        }
                        else
                        {
                            touched.set(id, true);
                            invalidate();
                            break;
                        }
                    }
                }

                break;

        }

        return true;
    }

    public List<Boolean> getTouched()
    {
        return this.touched;
    }

    public int getSize()
    {
        return this.size;
    }

    public void calculate()
    {
        next = true;
        cal = new Algorithm(this.touched, this.size);
        int[][] board = cal.gameOfLife(cal.getBoard());
        //System.out.println();
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                if (board[i][j] == 1)
                {
                    touched.set(i * size + j, true);
                }
                else
                {
                    touched.set(i * size + j, false);
                }
                //System.out.println(board[i][j] + " ");
            }
            //System.out.println();
        }
        //System.out.println(touched);
        invalidate();

        //int[][] board = cal.getBoard();
        /*
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        */
    }

    public void refresh ()
    {
        reset = true;
        invalidate();
    }
}
