package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.ImageIcon;

import model.DB;
import model.Pipe;
import model.bird;
import view.FlappyBird;

public class acction_FlappyBird implements ActionListener{
	
	private FlappyBird Fl;
	private int velocityX = -4;
	private int velocityY = 0;
	private int gravity = 1;
	
	public int broadWidth = 360;
	public int broadHeight = 640;
	public acction_FlappyBird(FlappyBird flappyBird) {
		// TODO Auto-generated constructor stub
		this.Fl = flappyBird;
		
		
		
	}
	

	public void move() {
		Fl.setVelocityY(Fl.getVelocityY()+gravity);
		Fl.setBY(Math.max(Fl.getB().getY() + Fl.getVelocityY(), 0));
		// pipes
		for (int i = 0; i < Fl.getPipes().size(); i++) {
			Pipe pipe = Fl.getPipes().get(i);
//			Pipe pipe1 = Fl.getPipes().get(i+1);
			pipe.setX(pipe.getX() + velocityX);
			if (!pipe.isPassed() && Fl.getB().getX() > pipe.getX() + pipe.getWidth()) {
				pipe.setPassed(true);
				Fl.setScore(Fl.getScore()+0.5);
			}
			
//			if(i%6==0) {
//				if(pipe.getY()<=broadHeight/4||pipe.getY()>=broadHeight*3/4) {
//					placePipe=-placePipe;
//				}
//				pipe.setY(pipe.getY()+placePipe);
//				pipe1.setY(pipe.getY()+pipe.getHeight()/4+pipe1.getHeight());
//			}
//			
			if (collision(Fl.getB(), pipe)) {
				Fl.setGameOver(true);
			}
		}
		if (Fl.getB().getY() > broadHeight) {
			Fl.setGameOver(true);

		}
	}
	public boolean collision(bird a, Pipe b) {
		return a.getX() < b.getX() + b.getWidth() && a.getX() + a.getBirdWeight() > b.getX()
				&& a.getY() < b.getY() + b.getHeight() && a.getY() + a.getBirdHeight() > b.getY();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		Fl.repaint();
		if (Fl.isGameOver()) {
			Fl.addListPlayer();
			if (Fl.getListPlayer().checkScore()) {
				Fl.addListPlayerDB();
				Fl.setRecords(true);
			}
			Fl.PlacePipesTimerStop();
			Fl.getGameLoopStop();
		}
		
	}
	

}
