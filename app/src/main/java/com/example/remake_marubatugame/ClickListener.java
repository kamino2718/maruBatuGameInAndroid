package com.example.remake_marubatugame;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ClickListener implements View.OnClickListener {
    static int[] clickedButton;

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.resetButton) {
            MainActivity.reset();
        } else {
            if(MainActivity.judge1.winner != 0) {

            } else {
                clickedButton = getButtonNumber((Button)view);
                MainActivity.putStone(MainActivity.grid, clickedButton[0], clickedButton[1], MainActivity.firstOrSecond);
                MainActivity.judge(MainActivity.grid, MainActivity.judge1);
                MainActivity.firstOrSecond = MainActivity.changeTurn(MainActivity.firstOrSecond);
                if(MainActivity.firstOrSecond == 1) {
                    MainActivity.countOfTurn++;
                    MainActivity.outputTurn(MainActivity.countOfTurn);
                }
                if(MainActivity.judge1.winner != 0) {
                    MainActivity.displayButtom.setText(MainActivity.firstOrSecondSymbol[MainActivity.judge1.winner - 1].concat("の勝ち"));
                    MainActivity.changeButtonBackground(MainActivity.judge1, MainActivity.buttons, 255, 0, 0);
                } else if(MainActivity.judgeFilledGrid(MainActivity.grid) == 1) {
                    MainActivity.displayButtom.setText("引き分け");
                }
            }
        }
    }

    public int[] getButtonNumber(Button b) {
        int coordinateOfClickedButton[] = new int[2];
        switch (b.getId()) {
            case R.id.button00:
                coordinateOfClickedButton[0] = 0;
                coordinateOfClickedButton[1] = 0;
                break;

            case R.id.button01:
                coordinateOfClickedButton[0] = 0;
                coordinateOfClickedButton[1] = 1;
                break;

            case R.id.button02:
                coordinateOfClickedButton[0] = 0;
                coordinateOfClickedButton[1] = 2;
                break;

            case R.id.button10:
                coordinateOfClickedButton[0] = 1;
                coordinateOfClickedButton[1] = 0;
                break;

            case R.id.button11:
                coordinateOfClickedButton[0] = 1;
                coordinateOfClickedButton[1] = 1;
                break;

            case R.id.button12:
                coordinateOfClickedButton[0] = 1;
                coordinateOfClickedButton[1] = 2;
                break;

            case R.id.button20:
                coordinateOfClickedButton[0] = 2;
                coordinateOfClickedButton[1] = 0;
                break;

            case R.id.button21:
                coordinateOfClickedButton[0] = 2;
                coordinateOfClickedButton[1] = 1;
                break;

            case R.id.button22:
                coordinateOfClickedButton[0] = 2;
                coordinateOfClickedButton[1] = 2;
                break;
        }
        return coordinateOfClickedButton;
    }
}
