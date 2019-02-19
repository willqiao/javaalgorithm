package com.will.leetcode;

import java.util.Arrays;

public class GameOfLine {
    public void gameOfLife(int[][] board) {
        int[][] nextGen = new int[board.length][];
        for (int i = 0; i < nextGen.length; i++) {
			nextGen[i] = Arrays.copyOf(board[i], board[i].length);
		}
        
        for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				int sum = this.findLive(board, i, j);
				if (board[i][j] == 0 && sum == 3) {//Number 4
					nextGen[i][j] = 1;
				}
				if  (board[i][j] == 1 && sum < 2) {//Number 1
					nextGen[i][j] = 0;
				}
				if  (board[i][j] == 1 && sum > 3) {//Number 3
					nextGen[i][j] = 0;
				}
				if  (board[i][j] == 1 && (sum == 2 || sum == 3)) {//Number 2
					nextGen[i][j] = 1;
				}
			}
		}
        for (int i = 0; i < board.length; i++) {
			board[i] = nextGen[i];
		}
    }
    
    int findLive(int[][] board, int i,int j) {
    	
    	int sum = 0;
    	for (int k = -1; k < 2; k++) {
			for (int k2 = -1; k2 < 2; k2++) {
				int first = i+k;
				int second = j + k2;
				if (first >= 0 && second >= 0 && first < board.length && second < board[i].length) {
					if (first == i && second == j ) {
					//skip
					} else {
						sum += board[first][second];
					}
				}
			}
				
		}

    	//i-1, j-1 to j+1
    	//i, j-1 and i,j+1
    	//i+11, j-1 to j+1
    	return sum;
    }
    
	public static void main(String[] args) {
		int[][] board = new int[][] {
			{0,1,0},
			{0,0,1},
			{1,1,1},
			{0,0,0},
			};
			System.out.println(board);
		new GameOfLine().gameOfLife(board);
		System.out.println(board);
		for(int[] b : board) {
			System.out.println(Arrays.toString(b));
		}

	}

}
