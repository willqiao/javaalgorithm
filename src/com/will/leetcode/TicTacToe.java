package com.will.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class TicTacToe {
	//0 is empty, 9 is user, 1 is computer.
	int [][] data = new int[][]{{0,0,0},{0,0,0},{0,0,0}};
	String winner = "";
	
	public static void main(String[] args) throws Exception {
		TicTacToe t = new TicTacToe();
		t.print();
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
		data[i][j] = 9;
		if (!this.isOver()) {
			this.computerMove();
		}
		//computer move:
		
	}
	
	public void computerMove() {
		//offence
		int sum1 = data[0][0] + data[1][1] + data[2][2];
		if (sum1 == 2) {
			if (data[0][0] == 0) data[0][0] = 1;
			if (data[1][1] == 0) data[1][1] = 1;
			if (data[2][2] == 0) data[2][2] = 1;
			return;
		} 
		
		int sum2 = data[0][2] + data[1][1] + data[2][0];
		if (sum2 == 2) {
			if (data[0][2] == 0) data[0][2] = 1;
			if (data[1][1] == 0) data[1][1] = 1;
			if (data[2][0] == 0) data[2][0] = 1;
			return;
		} 
		
		for (int i = 0; i < 3; i++) {
			int sum3 = data[i][0] + data[i][1] + data[i][2];
			if (sum3 == 2) {
				if (data[i][0] == 0) data[i][0] = 1;
				if (data[i][1] == 0)data[i][1]= 1;
				if (data[i][2]== 0)data[i][2] = 1;
				return;
			}
			
			int sum4 = data[0][i] + data[1][i] + data[2][i];
			if (sum4 == 2) {
				if (data[0][i]== 0) data[0][i] = 1;
				if (data[1][i] == 0) data[1][i] = 1;
				if (data[2][i] == 0) data[2][i]= 1;
				return;
			}
		}
		
		//defence move
		sum1 = data[0][0] + data[1][1] + data[2][2];
		if (sum1 == 18) {
			if (data[0][0] == 0) data[0][0] = 1;
			if (data[1][1] == 0) data[1][1] = 1;
			if (data[2][2] == 0) data[2][2] = 1;
			return;
		} 
		
		sum2 = data[0][2] + data[1][1] + data[2][0];
		if (sum2 == 18) {
			if (data[0][2] == 0) data[0][2] = 1;
			if (data[1][1] == 0) data[1][1] = 1;
			if (data[2][0] == 0) data[2][0] = 1;
			return;
		} 
		
		for (int i = 0; i < 3; i++) {
			int sum3 = data[i][0] + data[i][1] + data[i][2];
			if (sum3 == 18) {
				if (data[i][0] == 0) data[i][0] = 1;
				if (data[i][1] == 0)data[i][1]= 1;
				if (data[i][2]== 0)data[i][2] = 1;
				return;
			}
			
			int sum4 = data[0][i] + data[1][i] + data[2][i];
			if (sum4 == 18) {
				if (data[0][i]== 0) data[0][i] = 1;
				if (data[1][i] == 0) data[1][i] = 1;
				if (data[2][i] == 0) data[2][i]= 1;
				return;
			}	
		}
		
		sum1 = data[0][0] + data[1][1] + data[2][2];
		if (sum1 == 0) {
			data[0][0] = 1;
			return;
		} else if (sum1 == 1 ) {
			if (data[2][2] == 0) data[2][2] = 1; else  data[1][1] = 1;
			return;
		} 
		
		sum2 = data[0][2] + data[1][1] + data[2][0];
		if (sum2 == 0) {
			data[0][2] = 1;
			return;
		} else if (sum2 == 1) {
			if (data[2][0] == 0) data[2][0] = 1; else  data[1][1] = 1;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int sum3 = data[i][0] + data[i][1] + data[i][2];
			if (sum3 == 0) {
				data[i][0] = 1;
				return;
			}	 else if (sum3 == 1 ) {
				if (data[i][2]== 0) data[i][2] = 1; else  data[i][1] = 1;
				return;
			}
			
			int sum4 = data[0][i] + data[1][i] + data[2][i];
			if (sum4 == 0) {
				data[0][i]= 1;
				return;
			}	 else if (sum4 == 1 ) {
				if (data[2][i] == 0)data[2][i] = 1; else  data[1][i]= 1;
				return;
			}
		}
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j] == 0) {
					data[i][j] = 1;
					return;
				}
			}
		} 
	}

}
