enum Type {
    FIVE(6), FOUR(5), FULL(4), THREE(3), TWOPAIRS(2), PAIR(1), HIGHCARD(0);

    public final int strength;

    Type(int strength) {
        this.strength = strength;
    }
}