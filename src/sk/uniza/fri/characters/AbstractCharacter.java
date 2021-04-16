package sk.uniza.fri.characters;

import sk.uniza.fri.field.GameField;
import sk.uniza.fri.characters.position.Position;

public abstract class AbstractCharacter implements ICharacter {

    private Position position;
    private final GameField field;

    /**
     * Vytvori a inicializuje hraca
     * @param field je aktualne hracie pole
     */
    public AbstractCharacter(GameField field) {
        this.position = new Position(0, 0);
        this.field = field;
    }

    /**
     * Metoda vrati nazov hraca
     * @return char - pismenko nazvu hraca
     */
    public abstract char getName();

    /**
     * Nastavue zaciatocnu poziciu hraca
     */
    public abstract void setStartPosition();

    /**
     * Meni poziciu hraca v poli
     */
    public abstract void step();

    /**
     * Getter pre poziciu hraca v hracem poli
     * @return udaje pozicii hraca v bludisku
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Meni poziciu hraca v poli
     * @param position je nova pozicia
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Getter pre aktualne hracie pole
     * @return udaje hraceho pola
     */
    public GameField getField() {
        return this.field;
    }

}
