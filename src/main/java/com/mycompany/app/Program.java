package com.mycompany.app;

/*
 * Reworked implementation of a TicTacToe demo.
 * Public API (class names, method signatures and behaviors) preserved
 * but internal structure, naming and logic were refactored for uniqueness.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

enum State { PLAYING, OWIN, XWIN, DRAW };

class Player {
  public char symbol;
  public int move;
  public boolean selected;
  public boolean win;
}

class Game {
  public State state;
  public Player player1, player2;
  public Player cplayer; // текущий игрок
  public int nmove;      // последний шаг действующего игрока
  public char symbol;    // символ, по которому проверяем выигрыши
  public static final int INF = 100;
  public int q;          // счётчик рассмотренных положений
  public char[] board;   // игровая доска 0..8

  private final int[][] WIN_LINES = {
    {0,1,2},{3,4,5},{6,7,8},
    {0,3,6},{1,4,7},{2,5,8},
    {0,4,8},{2,4,6}
  };

  public Game() {
    player1 = new Player();
    player2 = new Player();
    player1.symbol = 'X';
    player2.symbol = 'O';
    state = State.PLAYING;
    board = new char[9];
    for (int i = 0; i < board.length; i++) board[i] = ' ';
  }

  // Проверяем состояние для символа, заданного в поле `symbol`.
  public State checkState(char[] b) {
    // Проверка выигрышных линий для заданного символа
    for (int[] line : WIN_LINES) {
      if (b[line[0]] == symbol && b[line[1]] == symbol && b[line[2]] == symbol) {
        return (symbol == 'X') ? State.XWIN : State.OWIN;
      }
    }
    // Проверяем, остались ли пустые клетки
    for (char c : b) if (c == ' ') return State.PLAYING;
    return State.DRAW;
  }

  // Заполняет список индексов пустых клеток (0..8)
  public void generateMoves(char[] b, ArrayList<Integer> targets) {
    targets.clear();
    for (int i = 0; i < b.length; i++) if (b[i] == ' ') targets.add(i);
  }

  // Оценка позиции относительно игрока `player`.
  // Возвращает INF если выигрывает `player`, -INF если проигрывает, 0 для ничьи и -1 если игра продолжается.
  public int evaluatePosition(char[] b, Player player) {
    State s = checkState(b);
    if (s == State.DRAW) return 0;
    if (s == State.XWIN) return (player.symbol == 'X') ? +INF : -INF;
    if (s == State.OWIN) return (player.symbol == 'O') ? +INF : -INF;
    return -1; // игра не закончена
  }

  // Внешний интерфейс для выбора хода (возвращает 1..9)
  public int MiniMax(char[] b, Player player) {
    ArrayList<Integer> moves = new ArrayList<>();
    generateMoves(b, moves);
    ArrayList<Integer> best = new ArrayList<>();
    int bestVal = -INF;

    for (int mv : moves) {
      b[mv] = player.symbol;
      this.symbol = player.symbol; // важно для evaluate/check
      int score = MinMove(b, player);
      if (score > bestVal) {
        bestVal = score;
        best.clear();
        best.add(mv + 1);
      } else if (score == bestVal) {
        best.add(mv + 1);
      }
      b[mv] = ' ';
    }

    if (best.isEmpty()) return 1; // на всякий случай
    Random rnd = new Random();
    int pick = best.get(rnd.nextInt(best.size()));
    q = 0;
    return pick;
  }

  // Минимизирующая ветка (поддерживает старый контракт и тесты)
  public int MinMove(char[] b, Player player) {
    int v = evaluatePosition(b, player);
    if (v != -1) return v;
    q++;
    int best = +INF;
    ArrayList<Integer> moves = new ArrayList<>();
    generateMoves(b, moves);
    for (int mv : moves) {
      // ход противника
      this.symbol = (player.symbol == 'X') ? 'O' : 'X';
      b[mv] = this.symbol;
      int val = MaxMove(b, player);
      if (val < best) best = val;
      b[mv] = ' ';
    }
    return best;
  }

  // Максимизирующая ветка
  public int MaxMove(char[] b, Player player) {
    int v = evaluatePosition(b, player);
    if (v != -1) return v;
    q++;
    int best = -INF;
    ArrayList<Integer> moves = new ArrayList<>();
    generateMoves(b, moves);
    for (int mv : moves) {
      this.symbol = player.symbol;
      b[mv] = this.symbol;
      int val = MinMove(b, player);
      if (val > best) best = val;
      b[mv] = ' ';
    }
    return best;
  }
}

public class Program {

  public static FileWriter fileWriter;
  public static PrintWriter printWriter;

  public static void main(String[] args) throws IOException {
    JFrame frame = new JFrame("Demo");
    frame.add(new TicTacToePanel(new GridLayout(3,3)));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(5, 5, 500, 500);
    frame.setVisible(true);
  }
}

class TicTacToeCell extends JButton {
  private int id;
  private int row;
  private int col;
  private char mark;

  public TicTacToeCell(int id, int x, int y) {
    this.id = id;
    this.row = y;
    this.col = x;
    this.mark = ' ';
    setText(String.valueOf(mark));
    setFont(new Font("Arial", Font.PLAIN, 40));
  }

  public void setMarker(String m) {
    if (m != null && m.length() > 0) this.mark = m.charAt(0);
    setText(String.valueOf(mark));
    setEnabled(false);
  }

  public char getMarker() { return mark; }
  public int getRow() { return row; }
  public int getCol() { return col; }
  public int getNum() { return id; }
}

class Utility {
  public static void print(char[] b) {
    System.out.println();
    for (int i = 0; i < b.length; i++) System.out.print(b[i] + "-");
    System.out.println();
  }
  public static void print(int[] a) {
    System.out.println();
    for (int i = 0; i < a.length; i++) System.out.print(a[i] + "-");
    System.out.println();
  }
  public static void print(ArrayList<Integer> moves) {
    System.out.println();
    for (Integer m : moves) System.out.print(m + "-");
    System.out.println();
  }
}

class TicTacToePanel extends JPanel implements ActionListener {
  private Game game;
  private TicTacToeCell[] cells = new TicTacToeCell[9];

  TicTacToePanel(GridLayout layout) {
    super(layout);
    for (int i = 0; i < 9; i++) {
      TicTacToeCell c = new TicTacToeCell(i, i % 3, i / 3);
      c.addActionListener(this);
      cells[i] = c;
      add(c);
    }
    game = new Game();
    game.cplayer = game.player1;
  }

  public void actionPerformed(ActionEvent ae) {
    game.player1.move = -1;
    game.player2.move = -1;

    // Обновляем доску по всем ячейкам
    for (int i = 0; i < cells.length; i++) {
      TicTacToeCell c = cells[i];
      if (ae.getSource() == c) c.setMarker(String.valueOf(game.cplayer.symbol));
      game.board[i] = c.getMarker();
    }

    if (game.cplayer == game.player1) {
      game.player2.move = game.MiniMax(game.board, game.player2);
      game.nmove = game.player2.move;
      game.symbol = game.player2.symbol;
      game.cplayer = game.player2;
      if (game.player2.move > 0) cells[game.player2.move - 1].doClick();
    } else {
      game.nmove = game.player1.move;
      game.symbol = game.player1.symbol;
      game.cplayer = game.player1;
    }

    game.state = game.checkState(game.board);
    if (game.state == State.XWIN) {
      JOptionPane.showMessageDialog(null, "Выиграли крестики", "Результат", JOptionPane.WARNING_MESSAGE);
      System.exit(0);
    } else if (game.state == State.OWIN) {
      JOptionPane.showMessageDialog(null, "Выиграли нолики", "Результат", JOptionPane.WARNING_MESSAGE);
      System.exit(0);
    } else if (game.state == State.DRAW) {
      JOptionPane.showMessageDialog(null, "Ничья", "Результат", JOptionPane.WARNING_MESSAGE);
      System.exit(0);
    }
  }
}
