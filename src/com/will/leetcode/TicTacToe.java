package com.will.leetcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class TicTacToe {
	//0 is empty, 9 is user, 1 is computer.
//	int [][] data = new int[][]{{9,9,1},{0,1,0},{0,0,0}};
	int [][] data = new int[][]{{0,0,0},{0,0,0},{0,0,0}};
	String winner = "";
	
	public static void main(String[] args) throws Exception {
		TicTacToe t = new TicTacToe();
		System.out.println("Enter Your Move: ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(! t.isOver()) {
			t.print();
			String[] choice = in.readLine().split(",");
			t.move(Integer.valueOf(choice[0]),Integer.valueOf(choice[1]));
		}
		t.print();
		System.out.println(t.winner);
		
	}

	public void print() {
		for (int[] row : data) {
			for (int num : row) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}

	public boolean isOver() {
		//8 scenario:
		boolean result = false;
		for (int i = 0; i < 3; i++) {
			result = this.win(data[i][0] + data[i][1] + data[i][2]);
			if (result == true) return true;
			result = this.win(data[0][i] + data[1][i] + data[2][i]);
			if (result == true) return true;
		}
		result = this.win(data[0][0] + data[1][1] + data[2][2]);
		if (result == true) return true;
		result = this.win(data[0][2] + data[1][1] + data[2][0]);
		if (result == true) return true;
		
		
		for(int[] row: data) for (int cell:row) {
			if (cell == 0) {
				return false;
			}
		}
		this.winner = "Tie";
		return true;
	}
	

	
	private boolean win(int sum) {
		if (sum == 3) {
		this.winner = "Computer is winning";
		return true;
		} else if (sum == 27) {
			this.winner = "User is winning";
			return true;
		}
		return false;
	}

	public void move(int i, int j) {
		if (data[i][j] != 0)  {
			System.out.println("invalid move,choose another one"); 
		} else {
		data[i][j] = 9;
		if (!this.isOver()) {
			List<String> moves = new ArrayList<String>();
			this.print();
			this.computerMove(true, 0, moves);
			String[] keys = moves.get(moves.size()-1).split("_");
			data[Integer.valueOf(keys[0])][Integer.valueOf(keys[1])] = 1;
		}
		//computer move:
		}
	}
	
	public List<String> findOptions() {
		List<String> options = new ArrayList<String>();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j] == 0) {
					options.add(i+"_"+j);
				}
			}
		}
		return options;
	}
	
	public int computerMove(boolean isComputer,int start, List<String> moves) {
		int score =  this.score();
		List<String> options = this.findOptions();
		if (score == 10 || score == -10) {
			return score;
		} else if (options.size() == 0) {
			return score;
		}
		
		if (isComputer) {
			int bestValue = Integer.MIN_VALUE;
			String bestMove = "";
			for (int i = 0; i < options.size(); i++) {
			//computer move Max
				String[] keys = options.get(i).split("_");
				int f = Integer.valueOf(keys[0]);
				int s = Integer.valueOf(keys[1]);
				
				this.data[f][s] = 1;
				if (this.score() == 10) {
					bestValue = 10;
					bestMove = options.get(i);
					this.data[f][s] = 0;
					break;
				}
				int possibleBestMoveFromOpponent = this.computerMove(false, start+1, moves);
//				if (start == 0) System.out.println("Computer possibleBestMoveFromOpponent" + possibleBestMoveFromOpponent + options.get(i));
				if (possibleBestMoveFromOpponent > bestValue) {
					bestValue = possibleBestMoveFromOpponent;
					bestMove = options.get(i);
				}
				
				this.data[f][s] = 0;
			}
			moves.add(bestMove);
			if (start == 0) System.out.println(bestMove);
//			System.out.println(isComputer + " " + bestMove + " " + bestValue + " | " + start);
			return bestValue;
		} else {//user move min value
			int bestValue = Integer.MAX_VALUE;
			String bestMove = "";
			for (int i = 0; i < options.size(); i++) {
			//user move min
				String[] keys = options.get(i).split("_");
				int f = Integer.valueOf(keys[0]);
				int s = Integer.valueOf(keys[1]);
				
				this.data[f][s] = 9;
				if (this.score() == -10) {
					bestValue = -10;
					bestMove = options.get(i);
					this.data[f][s] = 0;
					break;
				}
				int possibleBestMoveFromOpponent = this.computerMove(true, start+1, moves);
//				if (start == 0) System.out.println("User possibleBestMoveFromOpponent" + possibleBestMoveFromOpponent + options.get(i));
				if (possibleBestMoveFromOpponent < bestValue) {
					bestValue = possibleBestMoveFromOpponent;
					bestMove = options.get(i);
				}
				this.data[f][s] = 0;
			}
			if (start == 0) System.out.println(bestMove);
//			System.out.println(isComputer + " " + bestMove + " " + bestValue + " | " + start);
			moves.add(bestMove);
			return bestValue;
		}
			
	}
	
	
	public int score() {
		//8 scenario:
		boolean result = false;
		for (int i = 0; i < 3; i++) {
			int sum = data[i][0] + data[i][1] + data[i][2];
			if (sum == 3) return 10;//computer
			if (sum == 27) return -10;//user
			
			sum = data[0][i] + data[1][i] + data[2][i];
			if (sum == 3) return 10;//computer
			if (sum == 27) return -10;//user
		}
		
		int sum = data[0][0] + data[1][1] + data[2][2];
		if (sum == 3) return 10;//computer
		if (sum == 27) return -10;//user
		sum = data[0][2] + data[1][1] + data[2][0];
		if (sum == 3) return 10;//computer
		if (sum == 27) return -10;//user
		
		return 0;
	}
	

}
