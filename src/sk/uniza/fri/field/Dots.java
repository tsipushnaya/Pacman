package sk.uniza.fri.field;

import sk.uniza.fri.characters.position.Position;

public class Dots {
    private final GameField gameField;
    private final char[][] dotsField;

    /**
     * Inicializuje a nastavuje pole bodok v suvislosti s hracim polem
     * @param gameField je aktualne hracie pole pre ktore nastavujeme bodky
     */
    public Dots(GameField gameField) {
        this.gameField = gameField;
        int x = gameField.getX();
        int y = gameField.getY();
        this.dotsField = new char[x][y];
        this.drawDotsField();
    }

    /**
     * Pridava hodnoty prvkam pola
     */
    public void drawDotsField() {
        for (int i = 0; i < this.dotsField.length; i++) {
            for (int j = 0; j < this.dotsField[i].length; j++) {

                if (i == 0 || i == this.dotsField.length - 1 ||
                        j == 0 || j == this.dotsField.length - 1) {
                    this.dotsField[i][j] = 0;
                } else if (this.gameField.isWall(new Position(i, j))) {
                    this.dotsField[i][j] = 0;
                } else {
                    this.dotsField[i][j] = '.';
                }

            }
        }
    }

    /**
     * Vrati hodnotu prvka pola
     * @param x je cislo riadku
     * @param y je cislo stlpcu
     * @return hodnotu prvka pola
     */
    public char getDotsFieldValue(int x, int y) {
        return this.dotsField[x][y];
    }

    /**
     * Ak Pakman obsadil prvok pola, metoda odstrani bodku
     * @param position je pozicia Pakmana v poli
     */
    public void setPacmanOnPosition(Position position) {
        this.dotsField[position.getX()][position.getY()] = 0;
    }

    /**
     * Skontroluje ci je pole prazdne
     * @return true, ak ano, inak false
     */
    public boolean isEmpty() {
        for (char[] rows : this.dotsField) {
            for (char column : rows) {
                if (column != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}