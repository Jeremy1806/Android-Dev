package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningStates = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0 ;
    boolean game = true ;
    int check=0;
    int flag=0;
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tag = Integer.parseInt(view.getTag().toString());

        Log.i("Tag is", view.getTag().toString());
        if(gameState[tag]==2 && game) {
            gameState[tag] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
                check++;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
                check++;
            }
            counter.setTranslationY(-1500);
            counter.animate().translationYBy(1500).setDuration(300);
            for (int[] winningState : winningStates) {
                if (gameState[winningState[0]] == gameState[winningState[1]] && gameState[winningState[1]] == gameState[winningState[2]] && gameState[winningState[0]] != 2) {

                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    flag=1;
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won!!" );
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                    game = false;
                }

            }
        }
        if(check==9 && flag==0){
            Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
            TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
            winnerTextView.setText("DRAW!!" );
            playAgainButton.setVisibility(View.VISIBLE);
            winnerTextView.setVisibility(View.VISIBLE);
        }
    }
    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView yoo = (ImageView) gridLayout.getChildAt(i);
            yoo.setImageDrawable(null);
        }
        activePlayer = 0 ;
        check=0;
        flag=0;
        game = true ;
        for(int j=0;j<gameState.length;j++){
            gameState[j]=2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}