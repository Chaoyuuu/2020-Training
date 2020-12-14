public class Leaf extends Type {
    public Leaf(Attribute next) {
        super(next);
    }

    @Override
    public int damage(Attribute attackAttribute) {
        int damage = 0;
        if(attackAttribute instanceof Fire){
            damage = this.next.damage(attackAttribute) * 2;
        } else if(attackAttribute instanceof Water){
            damage = this.next.damage(attackAttribute) / 2;
        }
        System.out.println(damage + " (leaf)");
        return damage;
    }
}
