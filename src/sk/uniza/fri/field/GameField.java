package sk.uniza.fri.field;

import sk.uniza.fri.characters.position.Position;

public class GameField {
    private final int x;
    private final int y;
    private final char[][] field;
    private final char wall = '#';
    private final Dots dots;

    /**
     * Vytvori a inicializuje hracie pole
     */
    public GameField() {
        this.x = 10;
        this.y = 10;
        this.field = new char[this.x][this.y];
        this.wallColumns();
        this.dots = new Dots(this);
    }

    /**
     * Metoda nastavuje steny
     */
    private void wallColumns() {
        this.field[1][7] = this.wall;

        this.field[2][2] = this.wall;
        this.field[3][2] = this.wall;

        this.field[2][5] = this.wall;
        this.field[3][4] = this.wall;
        this.field[3][5] = this.wall;
        this.field[3][6] = this.wall;
        this.field[4][4] = this.wall;
        this.field[4][6] = this.wall;
        this.field[4][7] = this.wall;

        this.field[6][1] = this.wall;
        this.field[6][2] = this.wall;
        this.field[7][2] = this.wall;

        this.field[6][4] = this.wall;
        this.field[6][5] = this.wall;
        this.field[6][6] = this.wall;
        this.field[7][6] = this.wall;

        this.field[8][4] = this.wall;
    }

    /**
     * Metoda zaplni a vykresli hracie pole na konzolu
     */
    public void drawField() {
        System.out.println();
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field[i].length; j++) {
                if (i == 0 || i == this.field.length - 1 ||
                        j == 0 || j == this.field.length - 1) {
                    this.field[i][j] = this.wall;
                }

                if (this.field[i][j] != 0) {
                    System.out.printf("%2s", this.field[i][j]); // для занятых ячеек
                } else {
                    if (this.dots.getDotsFieldValue(i, j) != 0) {
                        System.out.printf("%2s", this.dots.getDotsFieldValue(i, j));
                    } else {
                        System.out.printf("%2s", "");  // для свободных ячеек
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * Kontroluje, ci vybrana pozicia je prazdna
     * @param position je vybrana hracom nova pozicia
     * @return true ak je prazna, inak false
     */
    public boolean isPositionFree(Position position) {
        return this.field[position.getX()][position.getY()] == 0;
    }

    /**
     * Kontroluje, ci na vybranej pozicii nie je stena
     * @param position je vybrana hracom nova pozicia
     * @return true, ak je stena, inak false
     */
    public boolean isWall(Position position) {
        return this.field[position.getX()][position.getY()] == this.wall;
    }

    /**
     * Odstrani zo starej pozicii pismenko hraca
     * @param position je stara pozicia hraca, ktoru on si zmenil
     */
    public void removeCharacterPosition(Position position) {
        this.field[position.getX()][position.getY()] = 0;
    }

    /**
     * Nastavi pismenko hraca na novu poziciu
     * @param position je nova pozicia hraca
     * @param name je pismenko hraca
     */
    public void setCharacterPosition(Position position, char name) {
        this.field[position.getX()][position.getY()] = name;
    }

    /**
     * Getter pre riadok
     * @return int x - cslo riadka
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter pre stlpec
     * @return int y - cslo stlpca
     */
    public int getY() {
        return this.y;
    }

    /**
     * Getter pre pole bodok
     * @return udaje pola bodok
     */
    public Dots getDots() {
        return this.dots;
    }
}
