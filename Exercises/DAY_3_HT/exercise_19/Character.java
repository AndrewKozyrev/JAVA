package DAY_3_HT.exercise_19;

import java.util.ArrayList;

public class Character implements Base{
    protected ArrayList killed  = new ArrayList();
    protected ArrayList ate     = new ArrayList();
    protected final String name;

    Character(String name){
        this.name = name;
    }

    @Override
    public void eats(Base someone) {
        this.ate.add( ( (Character) someone ).name );
        System.out.println(this.name + " съел " + ((Character) someone ).name);
    }

    @Override
    public void kills(Base someone) {
        this.killed.add( ( (Character) someone ).name );
        System.out.println(this.name + " убил " + ((Character) someone ).name);
    }
}
