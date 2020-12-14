public class Main {
    public static void main(String[] args) {
        Pokemon pikachu = new Pokemon(new Leaf(new Water(new DefaultAttribute())));
        int damage = pikachu.getDamage(new Fire(new DefaultAttribute()));
        System.out.println(damage);
    }
}
