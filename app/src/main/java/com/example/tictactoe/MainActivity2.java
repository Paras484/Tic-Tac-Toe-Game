package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {
    boolean gameActive = true;
    String winnerStr;
    int flag=0;
    int sc=0;
    int s=0;
//    0=X
//    1=O
//    State meanings
//    0 - X
//    1 - o
//    2 - empty
    int activePlayer = 0;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winPositions = {{0,1,2},{3,4,5},{6,7,8},        {0,3,6},{1,4,7},{2,5,8},        {0,4,8},{2,4,6}};


    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        for(int i=0; i<9; i++){
            if(gameState[i]!=2){
                sc++;
            }
        }
        if(gameActive && flag==0 && sc==9){
            winnerStr = "Draw";
            finishes();
        }

        if (!gameActive ) {
            finishes();
        }

        if (gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.o);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.x);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(100);
        }

        //  Finding the winner

        for (int[] winPosition : winPositions) {
            for (int i=0; i<gameState.length; i++) {
                if (gameState[i]!=2 && flag==0) {
                    s++;
                }
            }
            if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[1]] != 2) {
                flag = 1;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "O has won the match";
                } else {
                    winnerStr = "X has won the match";
                }
//                    else{
//                        winnerStr = "Match is Draw";
//                    }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }

            else if(s==9){
                gameActive = false;
                winnerStr = "Draw";


            }
        }


//        if (flag == 0) {
//            for (int j = 0; j < 8; j++) {
//
//                winnerStr = "Draw";
//                finish(view);
//            }
//        }

    }


    private void gameReset() {
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView11)).setImageResource(0);
        TextView status = findViewById(R.id.status);

    }
    private void finishes() {
        new AlertDialog.Builder(this).setTitle("Result").setMessage(winnerStr).setPositiveButton("Restart", (dialog, which) -> gameReset()).setCancelable(false).show();
//        status.setText("O's Turn - Tap to play");
    }

//        Toast.makeText(this, , Toast.LENGTH_SHORT).show();
//        new AlertDialog.Builder(this).setTitle("Score").setMessage("Your score is " + score).setPositiveButton("Restart", ((dialog, which) -> restartQuiz())).setCancelable(false).show();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        if(s==9){
            gameActive = false;
            winnerStr = "Draw";
            Toast.makeText(this, "Match Draw", Toast.LENGTH_SHORT).show();
            finishes();

        }
    }


}