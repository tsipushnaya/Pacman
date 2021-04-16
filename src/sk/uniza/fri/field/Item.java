package sk.uniza.fri.field;

import sk.uniza.fri.characters.position.Position;

public class Item {
    private final GameField gameField;
    private final char[][] itemField;

    /**
     * Inicializuje a nastavuje pole bodok v suvislosti s hracim polem
     * @param gameField je aktualne hracie pole pre ktore nastavujeme bodky
     */
    public Item(GameField gameField) {
        this.gameField = gameField;
        int x = gameField.getX();
        int y = gameField.getY();
        this.itemField = new char[x][y];
        this.drawItemField();
    }

    /**
     * Pridava hodnoty prvkam pola
     */
    public void drawItemField() {
        for (int i = 0; i < this.itemField.length; i++) {
            for (int j = 0; j < this.itemField[i].length; j++) {

                if (i == 0 || i == this.itemField.length - 1 ||
                        j == 0 || j == this.itemField.length - 1) {
                    this.itemField[i][j] = 0;
                } else if (this.gameField.isWall(new Position(i, j))) {
                    this.itemField[i][j] = 0;
                } else {
                    this.itemField[i][j] = '.';
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
    public char getItemField(int x, int y) {
        return this.itemField[x][y];
    }

    /**
     * Ak Pakman obsadil prvok pola, metoda odstrani bodku
     * @param position je pozicia Pakmana v poli
     */
    public void setPacmanOnPosition(Position position) {
        this.itemField[position.getX()][position.getY()] = 0;
    }

    /**
     * Skontroluje ci je pole prazdne
     * @return true, ak ano, inak false
     */
    public boolean isEmpty() {
        for (char[] rows : this.itemField) {
            for (char column : rows) {
                if (column != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}