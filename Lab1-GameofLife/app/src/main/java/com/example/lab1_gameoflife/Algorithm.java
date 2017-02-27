package com.example.lab1_gameoflife;

import java.util.List;

/**
 * Created by kazhitu on 2/23/17.
 */

public class Algorithm
{
    private int[][] board;
    private int size;

    public Algorithm(List<Boolean> state, int size)
    {
        this.size = size;
        board = new int[size][size];
        int count = 0;
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                // dead / nothing
                if (state.get(count) == false)
                {
                    board[i][j] = 0;
                }
                else
                {
                    board[i][j] = 1;
                }
                // System.out.print(board[i][j] + " ");
                count++;
            }
            //System.out.println();
        }
        //gameOfLife(board);
    }

    public int[][] getBoard()
    {
        //gameOfLife(board);

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        return this.board;
    }

    public int[][] gameOfLife(int[][] board)
    {
        if (board == null || board.length == 0) return null;

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                int lives = liveNeighbors(board, m, n, i, j);

                if (board[i][j] == 0 && lives == 3)
                {
                    board[i][j] = 2;
                }

                if (board[i][j] == 1 && lives >=2 && lives <= 3)
                {
                    board[i][j] = 3;
                }
            }
        }

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                board[i][j] >>= 1;
            }
        }

        return board;
    }

    public int liveNeighbors(int[][] board, int m, int n, int i, int j)
    {
        int lives = 0;
        for (int x = Math.max(i-1, 0); x <= Math.min(i+1, m-1); x++)
        {
            for (int y = Math.max(j-1, 0); y <= Math.min(j+1, n-1); y++)
            {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }
}
