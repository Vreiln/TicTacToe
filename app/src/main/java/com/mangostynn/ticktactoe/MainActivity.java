package com.mangostynn.ticktactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneName = findViewById(R.id.playerOneName);
        playerTwoName = findViewById(R.id.playerTwoName);

        playerOneLayout = findViewById(R.id.playerOneLayout);
        playerTwoLayout = findViewById(R.id.playerTwoLayout);

        boardTile1 = findViewById(R.id.tile1);
        boardTile2 = findViewById(R.id.tile2);
        boardTile3 = findViewById(R.id.tile3);
        boardTile4 = findViewById(R.id.tile4);
        boardTile5 = findViewById(R.id.tile5);
        boardTile6 = findViewById(R.id.tile6);
        boardTile7 = findViewById(R.id.tile7);
        boardTile8 = findViewById(R.id.tile8);
        boardTile9 = findViewById(R.id.tile9);

        combinationsList.add(new int[]{0,1,2});
        combinationsList.add(new int[]{3,4,5});
        combinationsList.add(new int[]{6,7,8});
        combinationsList.add(new int[]{0,3,6});
        combinationsList.add(new int[]{1,4,7});
        combinationsList.add(new int[]{2,5,8});
        combinationsList.add(new int[]{2,4,6});
        combinationsList.add(new int[]{0,4,8});

        getPlayerOneName = getIntent().getStringExtra("playerOne");
        getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);

        boardTile1.setOnClickListener(e->{
            if(isBoxSelected(0))
                performAction((ImageView)e,0);
        });
        boardTile2.setOnClickListener(e->{
            if(isBoxSelected(1))
                performAction((ImageView)e,1);
        });
        boardTile3.setOnClickListener(e->{
            if(isBoxSelected(2))
                performAction((ImageView)e,2);
        });
        boardTile4.setOnClickListener(e->{
            if(isBoxSelected(3))
                performAction((ImageView)e,3);
        });
        boardTile5.setOnClickListener(e->{
            if(isBoxSelected(4))
                performAction((ImageView)e,4);
        });
        boardTile6.setOnClickListener(e->{
            if(isBoxSelected(5))
                performAction((ImageView)e,5);
        });
        boardTile7.setOnClickListener(e->{
            if(isBoxSelected(6))
                performAction((ImageView)e,6);
        });
        boardTile8.setOnClickListener(e->{
            if(isBoxSelected(7))
                performAction((ImageView)e,7);
        });
        boardTile9.setOnClickListener(e->{
            if(isBoxSelected(8))
                performAction((ImageView)e,8);
        });
    }

    private void performAction(ImageView imageView, int selectedBoxPosition){
        boxPositions[selectedBoxPosition] = playerTurn;

        if(playerTurn==1)
            playerOneAction(imageView);
        else
            playerTwoAction(imageView);
    }
    private void playerOneAction(ImageView imageView){
        imageView.setImageResource(R.drawable.cross);

        if(checkPlayerWin()){
            playerOneWin=true;
            WinDialog winDialog = new WinDialog(
                    MainActivity.this,
                    playerOneName.getText()+" has won the match!",
                    MainActivity.this);
            winDialog.show();
        }
        else if(totalSelectedBoxes == 9){
            WinDialog winDialog = new WinDialog(
                    MainActivity.this,
                    "It is a Draw!",
                    MainActivity.this);
            winDialog.show();
        }
        else{
            changePlayerTurn(2);
            totalSelectedBoxes++;
        }
    }
    private void playerTwoAction(ImageView imageView){
        imageView.setImageResource(R.drawable.circle);
        if(checkPlayerWin()){
            playerTwoWin=true;
            WinDialog winDialog = new WinDialog(
                    MainActivity.this,
                    playerTwoName.getText()+" has won the match!",
                    MainActivity.this);
            winDialog.show();
        }
        else if(totalSelectedBoxes == 9){
            WinDialog winDialog = new WinDialog(
                    MainActivity.this,
                    "It is a Draw!",
                    MainActivity.this);
            winDialog.show();
        }
        else{
            changePlayerTurn(1);
            totalSelectedBoxes++;
        }
    }

    private void changePlayerTurn(int currentPlayerTurn){
        playerTurn = currentPlayerTurn;
        if(playerTurn == 1){
            playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
        else{
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
    }

    private boolean checkPlayerWin(){
        boolean response = false;
        for(int x=0; x<combinationsList.size(); x++){
            final int[] combination = combinationsList.get(x);
            if( boxPositions[combination[0]] == playerTurn &&
                boxPositions[combination[1]] == playerTurn &&
                boxPositions[combination[2]] == playerTurn)
                    response = true;
        }
        return response;
    }

    private boolean isBoxSelected(int boxPosition){
        boolean response = false;

        if(boxPositions[boxPosition] == 0)
            response = true;

        return response;
    }

    public void restartMatch(){
        boxPositions = new int[]{0,0,0,0,0,0,0,0,0};
        playerTurn=1;
        totalSelectedBoxes=1;
        if(playerOneWin){
            boardTile1.setImageResource(R.drawable.frog);
            boardTile2.setImageResource(R.drawable.frog);
            boardTile3.setImageResource(R.drawable.frog);
            boardTile4.setImageResource(R.drawable.frog);
            boardTile5.setImageResource(R.drawable.frog);
            boardTile6.setImageResource(R.drawable.frog);
            boardTile7.setImageResource(R.drawable.frog);
            boardTile8.setImageResource(R.drawable.frog);
            boardTile9.setImageResource(R.drawable.frog);
        }
        else if(playerTwoWin){
            boardTile1.setImageResource(R.drawable.rabbit);
            boardTile2.setImageResource(R.drawable.rabbit);
            boardTile3.setImageResource(R.drawable.rabbit);
            boardTile4.setImageResource(R.drawable.rabbit);
            boardTile5.setImageResource(R.drawable.rabbit);
            boardTile6.setImageResource(R.drawable.rabbit);
            boardTile7.setImageResource(R.drawable.rabbit);
            boardTile8.setImageResource(R.drawable.rabbit);
            boardTile9.setImageResource(R.drawable.rabbit);
        }
        playerOneWin=false;
        playerTwoWin=false;
    }

    private final List<int[]> combinationsList = new ArrayList<>();
    private int[] boxPositions = new int[]{0,0,0,0,0,0,0,0,0};
    private int playerTurn = 1, totalSelectedBoxes = 1;
    private String getPlayerOneName, getPlayerTwoName;
    private LinearLayout playerOneLayout, playerTwoLayout;
    private TextView playerOneName, playerTwoName;
    private ImageView   boardTile1,
                        boardTile2,
                        boardTile3,
                        boardTile4,
                        boardTile5,
                        boardTile6,
                        boardTile7,
                        boardTile8,
                        boardTile9;
    private boolean playerOneWin, playerTwoWin;
}