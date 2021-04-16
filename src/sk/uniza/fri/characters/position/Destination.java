package sk.uniza.fri.characters.position;

public enum Destination {
    UP,
    DOWN,
    RIGHT,
    LEFT,
    WAIT;

    /**
     * Presuva hraca na novu poziciu
     * @param lastPosition je stara pozicia, ktoru menime podla smeru
     * @return nova pozicia hraca
     */
    public Position changePosition(Position lastPosition) {
        return switch (this) {
            case UP -> new Position(lastPosition.getX()  - 1, lastPosition.getY());
            case DOWN -> new Position(lastPosition.getX() + 1, lastPosition.getY());
            case RIGHT -> new Position(lastPosition.getX(), lastPosition.getY() + 1);
            case LEFT -> new Position(lastPosition.getX(), lastPosition.getY() - 1);
            case WAIT -> new Position(lastPosition.getX(), lastPosition.getY());
        };
    }

    /**
     * Metoda urcuje randomny smer pohybovania hraca
     * @return smer
     */
    public Destination getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
