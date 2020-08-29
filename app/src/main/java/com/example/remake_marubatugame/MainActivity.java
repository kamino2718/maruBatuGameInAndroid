package com.example.remake_marubatugame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    static Button[][] buttons = new Button[3][3];
    static TextView[][] text_buttons = new TextView[3][3];
    static TextView displayTurn;
    static TextView displayFirstOrSecond;
    static TextView displayButtom;
    static Button resetButton;
    static int[][] grid = new int[3][3];
    static String[] stoneSymbol = {"", "〇", "✕"};
    static String[] firstOrSecondSymbol = {"先攻", "後攻"};
    static int countOfTurn = 1;
    static int firstOrSecond = 1;
    static Judge judge1 = new Judge();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetButton = (Button)findViewById(R.id.resetButton);
        registerButtons(buttons);

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                buttons[i][j].setOnClickListener(new ClickListener());
                text_buttons[i][j] = (TextView)buttons[i][j];
            }
        }


        resetButton.setOnClickListener(new ClickListener());
        displayTurn = (TextView)findViewById(R.id.valueOfTurn);
        displayButtom = (TextView)findViewById(R.id.buttomDisplay);
        displayFirstOrSecond = (TextView)findViewById(R.id.valueOfFirstOrSecond);
        displayTurn.setText(String.valueOf(1));
        judge1.winner = 0;



    }


    public void registerButtons(Button[][] buttons) {
        buttons[0][0] = (Button)findViewById(R.id.button00);
        buttons[0][1] = (Button)findViewById(R.id.button01);
        buttons[0][2] = (Button)findViewById(R.id.button02);
        buttons[1][0] = (Button)findViewById(R.id.button10);
        buttons[1][1] = (Button)findViewById(R.id.button11);
        buttons[1][2] = (Button)findViewById(R.id.button12);
        buttons[2][0] = (Button)findViewById(R.id.button20);
        buttons[2][1] = (Button)findViewById(R.id.button21);
        buttons[2][2] = (Button)findViewById(R.id.button22);
    }

    public static void reloadTurnDisplay() {

    }

    public static void outputGrid(int[][] grid) {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                text_buttons[i][j].setText(stoneSymbol[grid[i][j]]);
            }
        }
    }

    public static void outputTurn(int turnNumber) {
        displayTurn.setText(String.valueOf(turnNumber));
    }

    public static void outputFirstOrSecond(int playerNumber) {
        displayFirstOrSecond.setText(firstOrSecondSymbol[playerNumber]);
    }

    public static void putStone(int[][] grid, int row, int col, int player) {
        grid[row][col] = player;
        text_buttons[row][col].setText(stoneSymbol[grid[row][col]]);
    }

    public static void judge(int[][] grid, Judge judge) {
        for(int i=0;i<3;i++) {
            if(grid[0][i] == 1 && grid[1][i] == 1 && grid[2][i] == 1) {
                judge.coordinateOfSolvedGrid[0][0] = 0;
                judge.coordinateOfSolvedGrid[0][1] = i;
                judge.coordinateOfSolvedGrid[1][0] = 1;
                judge.coordinateOfSolvedGrid[1][1] = i;
                judge.coordinateOfSolvedGrid[2][0] = 2;
                judge.coordinateOfSolvedGrid[2][1] = i;
                judge.winner = 1;
                return;
            } else if(grid[0][i] == 2 && grid[1][i] == 2 && grid[2][i] == 2) {
                judge.coordinateOfSolvedGrid[0][0] = 0;
                judge.coordinateOfSolvedGrid[0][1] = i;
                judge.coordinateOfSolvedGrid[1][0] = 1;
                judge.coordinateOfSolvedGrid[1][1] = i;
                judge.coordinateOfSolvedGrid[2][0] = 2;
                judge.coordinateOfSolvedGrid[2][1] = i;
                judge.winner = 2;
                return;
            }
        }

        for(int i=0;i<3;i++) {
            if(grid[i][0] == 1 && grid[i][1] == 1 && grid[i][2] == 1) {
                judge.coordinateOfSolvedGrid[0][0] = i;
                judge.coordinateOfSolvedGrid[0][1] = 0;
                judge.coordinateOfSolvedGrid[1][0] = i;
                judge.coordinateOfSolvedGrid[1][1] = 1;
                judge.coordinateOfSolvedGrid[2][0] = i;
                judge.coordinateOfSolvedGrid[2][1] = 2;
                judge.winner = 1;
                return;
            } else if(grid[i][0] == 2 && grid[i][1] == 2 && grid[i][2] == 2) {
                judge.coordinateOfSolvedGrid[0][0] = i;
                judge.coordinateOfSolvedGrid[0][1] = 0;
                judge.coordinateOfSolvedGrid[1][0] = i;
                judge.coordinateOfSolvedGrid[1][1] = 1;
                judge.coordinateOfSolvedGrid[2][0] = i;
                judge.coordinateOfSolvedGrid[2][1] = 2;
                judge.winner = 2;
                return;
            }
        }

        if(grid[0][0] == 1 && grid[1][1] == 1 && grid[2][2] == 1) {
            judge.coordinateOfSolvedGrid[0][0] = 0;
            judge.coordinateOfSolvedGrid[0][1] = 0;
            judge.coordinateOfSolvedGrid[1][0] = 1;
            judge.coordinateOfSolvedGrid[1][1] = 1;
            judge.coordinateOfSolvedGrid[2][0] = 2;
            judge.coordinateOfSolvedGrid[2][1] = 2;
            judge.winner = 1;
            return;
        } else if(grid[0][0] == 2 && grid[1][1] == 2 && grid[2][2] == 2) {
            judge.coordinateOfSolvedGrid[0][0] = 0;
            judge.coordinateOfSolvedGrid[0][1] = 0;
            judge.coordinateOfSolvedGrid[1][0] = 1;
            judge.coordinateOfSolvedGrid[1][1] = 1;
            judge.coordinateOfSolvedGrid[2][0] = 2;
            judge.coordinateOfSolvedGrid[2][1] = 2;
            judge.winner = 2;
            return;
        }

        if(grid[0][2] == 1 && grid[1][1] == 1 && grid[2][0] == 1) {
            judge.coordinateOfSolvedGrid[0][0] = 0;
            judge.coordinateOfSolvedGrid[0][1] = 2;
            judge.coordinateOfSolvedGrid[1][0] = 1;
            judge.coordinateOfSolvedGrid[1][1] = 1;
            judge.coordinateOfSolvedGrid[2][0] = 2;
            judge.coordinateOfSolvedGrid[2][1] = 0;
            judge.winner = 1;
            return;
        } else if(grid[0][2] == 2 && grid[1][1] == 2 && grid[2][0] == 2) {
            judge.coordinateOfSolvedGrid[0][0] = 0;
            judge.coordinateOfSolvedGrid[0][1] = 2;
            judge.coordinateOfSolvedGrid[1][0] = 1;
            judge.coordinateOfSolvedGrid[1][1] = 1;
            judge.coordinateOfSolvedGrid[2][0] = 2;
            judge.coordinateOfSolvedGrid[2][1] = 0;
            judge.winner = 2;
            return;
        }
    }

    public static int changeTurn(int player) {
        if(player == 1) {
            displayFirstOrSecond.setText(firstOrSecondSymbol[1]);
            return 2;
        } else if(player == 2) {
            displayFirstOrSecond.setText(firstOrSecondSymbol[0]);
            return 1;
        } else {
            System.out.println("[Error in MainActivity.changeTurn()] player is not 1 or 2!");
            return -1;
        }
    }

    public static int judgeFilledGrid(int[][] grid) {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(grid[i][j] == 0) {
                    return 0;
                }
            }
        }
        return 1;
    }

    public static void changeButtonBackground(Judge judge, Button[][] buttons, int r, int g, int b) {
        int[][] array = new int[3][2];
        for(int i=0;i<3;i++) {
            for(int j=0;j<2;j++) {
                array[i][j] = judge.coordinateOfSolvedGrid[i][j];
            }
        }

        for(int i=0;i<3;i++) {
            buttons[array[i][0]][array[i][1]].setTextColor(Color.rgb(r, g, b));
        }

    }

    public static void reset() {
        //firstOrSecond
        firstOrSecond = 1;
        displayFirstOrSecond.setText(firstOrSecondSymbol[0]);
        //countOfTurn
        countOfTurn = 1;
        displayTurn.setText(String.valueOf(1));
        displayButtom.setText(" ");
        judge1.winner = 0;

        //grid
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                grid[i][j] = 0;
                buttons[i][j].setText(stoneSymbol[0]);
                buttons[i][j].setTextColor(Color.BLACK);
            }
        }

        for(int i=0;i<3;i++) {
            for(int j=0;j<2;j++) {
                MainActivity.judge1.coordinateOfSolvedGrid[i][j] = 0;
            }
        }

    }
}
