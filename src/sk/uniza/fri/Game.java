package sk.uniza.fri;

import sk.uniza.fri.characters.AbstractCharacter;
import sk.uniza.fri.characters.AbstractEnemy;
import sk.uniza.fri.characters.GreenEnemy;
import sk.uniza.fri.characters.ICharacter;
import sk.uniza.fri.characters.Pacman;
import sk.uniza.fri.characters.RedEnemy;
import sk.uniza.fri.field.GameField;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Konsolova hra Pacman
 * SU 3 typy znakov:
 * 1) Pacman - 'P'
 * 2) Cerveny duch - 'R'
 * 3) Zeleny duch - 'G'
 *
 * Ulohou Pacmana je zobrat vsetky bodky v ramci hracieho bludiska a sa nestretnut ziadneho ducha.
 * Bludisko ma steny - '#', chodit cez ktore nemoze ziadny znak.
 *
 * Duchi maju roznu silu.
 * Zeleny vie zjest len Pacmana.
 * Cerveny duch moze zjest aj ineho ducha, aj Pacmana.
 * Duchi robia nahodne kroky
 *
 * SU 6 typov prikazov:
 * I. Na chodenie:
 *  1) RIGHT - posune hraca doprava
 *  2) LEFT - posune hraca dolava
 *  3) UP - posune hraca hore
 *  4) DOWN - posune hraca dole
 *  5) WAIT - nezmeni poziciu
 * II. Pre ukoncenie hry:
 *  1) EXIT - hned zavrie hru
 *
 * 
 *  @version 2021/04/16/1500
 *  @author Anastasia Tsipushtanova
 */
public class Game {
    private final GameField field;
    private final AbstractEnemy redEnemy;
    private final AbstractEnemy greenEnemy;
    private final AbstractCharacter pacman;
    private final HashMap<Character, ICharacter> characters;
    private final ArrayList<ICharacter> forRemove;

    /**
     *Vytvori a inicializuje hru
     */
    public Game() {
        this.field = new GameField();
        this.redEnemy = new RedEnemy(this.field);
        this.greenEnemy = new GreenEnemy(this.field);
        this.pacman = new Pacman(this.field);
        this.characters = new HashMap<>();
        this.forRemove = new ArrayList<>();
    }

    /**
     * Chrani logiku postupu hry
     */
    public void gameSteps() {
        this.addCharacterToHashMap(this.pacman);
        this.addCharacterToHashMap(this.greenEnemy);
        this.addCharacterToHashMap(this.redEnemy);
        for (ICharacter character : this.characters.values()) {
            character.setStartPosition();
            this.field.setCharacterPosition(character.getPosition(), character.getName());
        }
        this.field.drawField();

        do {
            ArrayList<ICharacter> helpArray = new ArrayList<>();
            for (ICharacter character : this.characters.values()) {
                character.step();
                helpArray.addAll(this.meetCharacters());

                if (!helpArray.contains(character)) {
                    this.field.setCharacterPosition(character.getPosition(), character.getName());
                }
            }

            for (ICharacter character : helpArray) {
                if (this.characters.containsKey(character.getName())) {
                    this.removeCharacter(character);
                }
            }

            this.field.drawField();
        } while (!this.isFinal());

    }

    /**
     * Kontroluje co sa stretli dva hraca, ak ano, zapise slabsieho do ArrayListu
     * @return ArrayList<ICharacter> - zoznam hracov na odstranenie
     */
    private ArrayList<ICharacter> meetCharacters() {
        for (ICharacter character : this.characters.values()) {
            if (character instanceof AbstractEnemy) {
                AbstractEnemy enemy = (AbstractEnemy)character;
                for (ICharacter character2 : this.characters.values()) {
                    if (!enemy.equals(character2)) {
                        if (!this.forRemove.contains(character2)) {
                            if (enemy.meetCharacter(character2)) {
                                this.forRemove.add(character2);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return this.forRemove;
    }

    /**
     * Pridava noveho hraca do HashMapu s haracmi
     * @param character - je pridavany hrac
     */
    private void addCharacterToHashMap(ICharacter character) {
        this.characters.put(character.getName(), character);
    }

    /**
     * Odstrani hraca z HashMapu
     * @param character - hrac ktory ma byt odstraneny
     */
    private void removeCharacter(ICharacter character) {
        this.characters.remove(character.getName());
    }

    /**
     * Skontroluje ci je splnena podmienka pre koniec hry
     * @return true, ak podmienka je splnena, inak false
     */
    private boolean isFinal() {
        if (!this.characters.containsValue(this.pacman)) {
            System.out.println();
            System.out.println("********************");
            System.out.println("GAME OVER. YOU LOSE.");
            System.out.println("      HAHAHAHA      ");
            System.out.println("********************");

            return true;
        } else if (this.field.getItem().isEmpty()) {
            System.out.println();
            System.out.println("********************");
            System.out.println("GAME OVER. YOU WIN!!");
            System.out.println("      SUPER!!!      ");
            System.out.println("********************");

            return true;
        }
        return false;
    }

}