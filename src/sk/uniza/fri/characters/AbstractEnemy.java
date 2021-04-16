package sk.uniza.fri.characters;

import sk.uniza.fri.field.GameField;
import sk.uniza.fri.characters.position.Destination;
import sk.uniza.fri.characters.position.Position;

public abstract class AbstractEnemy extends AbstractCharacter {
    public AbstractEnemy(GameField field) {
        super(field);
    }

    @Override
    public abstract char getName();

    /**
     * Nastavuje zaciatocnu poziciu na prazny prvok hracieho pola
     */
    @Override
    public void setStartPosition() {
        Position newPosition;
        int min = 1;
        int max = 8;
        int x;
        int y;
        do {
            x = min + (int)(Math.random() * max);
            y = min + (int)(Math.random() * max);
            newPosition = new Position(x, y);
        } while (!this.getField().isPositionFree(newPosition));

        this.setPosition(newPosition);
    }

    /**
     * Realizuje nahodne kroki, ak novou poziciou nie je stena
     */
    @Override
    public void step() {
        this.getField().removeCharacterPosition(this.getPosition());
        Destination destination = Destination.UP;
        Position newPosition;
        do {
            destination = destination.getRandom();
            newPosition = destination.changePosition(this.getPosition());
        } while (this.getField().isWall(newPosition));

        this.setPosition(newPosition);
    }

    public abstract boolean meetCharacter(ICharacter secondChar);

}
