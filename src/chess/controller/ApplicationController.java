package chess.controller;

import chess.view.MainWindow;

public class ApplicationController {
	
	private static ApplicationController instance;
	private MainWindow view;
	
	private ApplicationController() {};
	
	public static ApplicationController getInstance() {
		if(instance == null) {
			instance = new ApplicationController();
		}
		return instance;
	}
	
	public void createView() {
		view = new MainWindow();
	}
	
	public void refreshView() {
		view.repaint();
	}
}
