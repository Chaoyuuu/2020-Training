public class Water extends Type{
    public Water(Attribute next) {
        super(next);
    }

    @Override
    public int damage(Attribute attackAttribute) {
        int damage = 0;
        if(attackAttribute instanceof Leaf){
            damage = this.next.damage(attackAttribute) * 2;
        } else if(attackAttribute instanceof Fire){
            damage = this.next.damage(attackAttribute) / 2;
        }
        System.out.println(damage + " (water)");
        return damage;
    }
}
