package com.uryonet.tictactoe.viewmodel;

import android.arch.lifecycle.LiveData;
import android.databinding.ObservableArrayMap;

import com.uryonet.tictactoe.model.Cell;
import com.uryonet.tictactoe.model.Game;
import com.uryonet.tictactoe.model.Player;

import static com.uryonet.tictactoe.utilities.StringUtility.stringFromNumbers;

public class GameViewModel {
    public ObservableArrayMap<String, String> cells;
    private Game game;

    public void init(String player1, String player2) {
        game = new Game(player1, player2);
        cells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = new Cell(game.currentPlayer);
            cells.put(stringFromNumbers(row, column), game.currentPlayer.value);
            if (game.hasGameEnded())
                game.reset();
            else
                game.switchPlayer();
        }
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }
}
