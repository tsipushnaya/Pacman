package sk.uniza.fri.characters;

import sk.uniza.fri.field.GameField;

public class GreenEnemy extends AbstractEnemy {

    public GreenEnemy(GameField field) {
        super(field);
    }

    @Override
    public char getName() {
        return 'G';
    }

    /**
     * Kontroluje ci tento ma rovnaku poziciu s inym hracom, ktory nie je duchom
     * @param secondChar je iny hrac v poli
     * @return true, ak pozicii su rovnake, inak false
     */
    @Override
    public boolean meetCharacter(ICharacter secondChar) {
        if (secondChar != null) {
            if (!(secondChar instanceof AbstractEnemy)) {
                return this.getPosition().getX() == secondChar.getPosition().getX() &&
                        this.getPosition().getY() == secondChar.getPosition().getY();
            }
        }
        return false;
    }
}
