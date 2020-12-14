public class Pokemon {
    private Attribute attribute;

    public Pokemon(Attribute attribute) {
        this.attribute = attribute;
    }

    public int getDamage(Attribute attackAttribute){
        return this.attribute.damage(attackAttribute);
    }
}
