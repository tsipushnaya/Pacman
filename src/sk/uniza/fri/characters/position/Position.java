package sk.uniza.fri.characters.position;

public class Position {
    private final int x;
    private final int y;

    /**
     * Inicializuje novu poziciu objektu v hraciem poli podla parametrov
     * @param x je cislo riadku pola
     * @param y je cislo stlpcu pola
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter pre cislo riadku
     * @return int x - cislo riadku
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter pre cislo stlpcu
     * @return int y - cislo stlpcu
     */
    public int getY() {
        return this.y;
    }
}
