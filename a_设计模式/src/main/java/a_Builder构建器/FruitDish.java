package a_Builder构建器;

public class FruitDish {
    private final int money;// final:数值一旦在初始化之后便不能更改
    private int banana;
    private int apple;
    private int pear;

    public static class Builder {
        // 必选字段
        private final int money;

        private int banana;
        private int apple;
        private int pear;


        public Builder(int money) {
            this.money = money;
        }

        public Builder banana(int val) {
            banana = val;
            return this;
        }
        public Builder apple(int val) {
            apple = val;
            return this;
        }
        public Builder pear(int val) {
            pear = val;
            return this;
        }

        public FruitDish build() throws OutOfMoneyException {
            int total = 0;
            total = banana + apple + pear;
            if (total > money) {
                throw new OutOfMoneyException("money is not enough");
            }
            return new FruitDish(this);
        }

        // 异常的构建
        class OutOfMoneyException extends Exception {
            public OutOfMoneyException(String message) {
                super(message);
            }
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(money).append(" ");
        sb.append(banana).append(" ");
        sb.append(apple).append(" ");
        sb.append(pear).append("\n");
        return sb.toString();
    }

    private FruitDish(Builder builder) {
        money = builder.money;
        banana = builder.banana;
        apple = builder.apple;
        pear = builder.pear;
    }

    public static void main(String[] args) throws Builder.OutOfMoneyException {
        Builder builder = new Builder(20);
        FruitDish fruitDish = builder.apple(3).pear(4).build();
        System.out.println(fruitDish);
        FruitDish fruitDish1 = builder.build();
        System.out.println(fruitDish1);
//        FruitDish fruitDish2 = new Builder(10).banana(7).pear(4).build();
    }
}
