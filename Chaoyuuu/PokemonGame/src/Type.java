public abstract class Type implements Attribute {
    Attribute next;

    public Type(Attribute next) {
        this.next = next;
    }
}
