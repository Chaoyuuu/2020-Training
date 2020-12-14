public class Fire extends Type {
    public Fire(Attribute next) {
        super(next);
    }

    @Override
    public int damage(Attribute attackAttribute) {
        int damage = 0;
        if(attackAttribute instanceof Water){
            damage = this.next.damage(attackAttribute) * 2;
        } else if(attackAttribute instanceof Leaf){
            damage = this.next.damage(attackAttribute) / 2;
        }
        System.out.println(damage + " (fire)");
        return damage;
    }
}
