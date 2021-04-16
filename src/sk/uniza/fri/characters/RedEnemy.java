package sk.uniza.fri.characters;

import sk.uniza.fri.field.GameField;

public class RedEnemy extends AbstractEnemy {

    public RedEnemy(GameField field) {
        super(field);
    }

    @Override
    public char getName() {
        return 'R';
    }

    /**
     * Kontroluje ci akykolvek hrac ma rovnaku poziciu s inym
     * @param secondChar je iny hrac v poli
     * @return true, ak pozicii su rovnake, inak false
     */
    @Override
    public boolean meetCharacter(ICharacter secondChar) {
        if (secondChar != null) {
            return this.getPosition().getX() == secondChar.getPosition().getX() &&
                    this.getPosition().getY() == secondChar.getPosition().getY();
        }
        return false;
    }

}
