package sk.uniza.fri.characters;

import sk.uniza.fri.field.GameField;
import sk.uniza.fri.characters.position.Destination;
import sk.uniza.fri.characters.position.Position;

import java.util.Scanner;

public class Pacman extends AbstractCharacter {
    public Pacman(GameField field) {
        super(field);
    }

    @Override
    public char getName() {
        return 'P';
    }

    /**
     * nastavuje zaciatocnu poziciu a takze vymaze bodku z rovnakej pozicii pola itemov
     */
    @Override
    public void setStartPosition() {
        this.setPosition(new Position(4, 5));
        this.getField().getItem().setPacmanOnPosition(this.getPosition());
    }

    /**
     * Presuva hraca na inu poziciu, ak nie je chyba
     * Vymaze bodku z rovnakej pozicii pola itemov
     */
    @Override
    public void step() {
        this.getField().removeCharacterPosition(this.getPosition());
        Position newPosition = null;
        try {
            newPosition = this.chooseDirection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.step();
        }
        if (newPosition != null) {
            this.setPosition(newPosition);
            this.getField().getItem().setPacmanOnPosition(this.getPosition());
        }
    }

    /**
     * Nacitava z klavesnici novu poziciu, a uklada ju ako novu
     * @return novu pozisiu hraca
     * @throws Exception ak sa pri napisanii novej pozicii z klavecnici hrac pomylil
     */
    private Position chooseDirection() throws Exception {
        Destination destination = null;
        Position newPosition;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("What direction do you choose?");
            System.out.println("Destinations: UP, DOWN, RIGHT, LEFT, WAIT");
            System.out.println("For exit type: EXIT");
            System.out.print("> ");
            switch (scan.nextLine()) {
                case "UP" -> destination = Destination.UP;
                case "DOWN" -> destination = Destination.DOWN;
                case "RIGHT" -> destination = Destination.RIGHT;
                case "LEFT" -> destination = Destination.LEFT;
                case "WAIT" -> destination = Destination.WAIT;
                case "EXIT" -> this.exit();
            }
        } while (destination == null);
        newPosition = destination.changePosition(this.getPosition());

        if (this.getField().isWall(newPosition)) {
            throw new Exception ("You couldn't move in this direction. There is wall.");
        }
        return newPosition;
    }

    /**
     * Ukonci hru so statusom 0
     */
    private void exit() {
        System.out.println("_________________");
        System.out.println("YOU LEFT THE GAME");
        System.out.println("_________________");
        System.exit(0);
    }

}