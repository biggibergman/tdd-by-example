package guru.springframework;

public class Money implements Expression {
    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    protected String currency() {
        return currency;
    }

    public static Money dollar(int value){
        return new Money(value, "USD");
    }
    public static Money franc(int value){
        return new Money(value, "CHF");
    }
    public boolean equals(Object object){
        Money money = (Money) object;
        return amount == money.amount
                && this.currency == money.currency;
    }

    @Override
    public Money reduce(Bank bank, String to){
        return new Money(amount / bank.rate(this.currency, to),to);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
    @Override
    public Expression times(int factor){

        return new Money(this.amount * factor, this.currency);
    }

    @Override
    public Expression plus(Expression addend){
        return new Sum(this, addend);
    }
}

