package chess.controller;

import java.io.IOException;

import chess.view.MainWindow;

public class ApplicationController {

    private static ApplicationController instance;

    private MainWindow view;

    private ApplicationController() {};

    public static ApplicationController getInstance() {
        if (instance == null) {
            instance = new ApplicationController();
        }
        return instance;
    }

    public void createView() throws IOException {
        view = new MainWindow();
    }

    public void refreshLogs() {
        if (view != null) {
            view.refreshLogs();
        }
    }

    public void refreshView() {
        if (view != null) {
            view.repaint();
        }
    }
}
