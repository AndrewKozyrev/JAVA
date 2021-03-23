package DAY_3_HT.exercise_19;

class Hood extends Character {
    Hood() {
        super("Красная шапочка");
    }
}

class Grandmother extends Character {
    Grandmother() {
        super("бабушка");
    }
}

class Patty extends Character {
    Patty() {
        super("пирожок");
    }
}

class Woodman extends Character {
    Woodman() {
        super("дровосек");
    }
}

class Wolf extends Character {
    Wolf() {
        super("волк");
    }
}

public class Narrative {
    public static void main(String[] args){
        Character hood = new Hood();
        Character grandma = new Grandmother();
        Character patty = new Patty();
        Character woodman = new Woodman();
        Character wolf = new Wolf();

        wolf.eats(grandma);
        wolf.eats(hood);
        woodman.kills(wolf);
        grandma.eats(patty); hood.eats(patty); woodman.eats(patty);
    }
}
