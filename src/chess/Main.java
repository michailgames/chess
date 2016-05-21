package chess;

import java.io.IOException;

import chess.controller.ApplicationController;

public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationController.getInstance().createView();
    }

}
