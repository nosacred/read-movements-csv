public class Operation {
    String scoreType, scoreNumber, value, date, reference,property, income, expense;
    Operation(String scoreType, String scoreNumber, String value, String date, String reference,
              String property,String  income, String expense){
        this.scoreType = scoreType;
        this.scoreNumber= scoreNumber;
        this.value = value;
        this.date = date;
        this.reference = reference;
        this.property = property;
        this.income = income;
        this.expense = expense;
    }
}
