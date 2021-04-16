package sk.uniza.fri.characters;

import sk.uniza.fri.field.GameField;
import sk.uniza.fri.characters.position.Position;

public interface ICharacter {
    char getName();
    void setStartPosition();
    void step();
    Position getPosition();
    void setPosition(Position position);
    GameField getField();
}


