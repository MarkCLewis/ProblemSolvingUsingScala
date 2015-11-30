object BankBranch {
  def main(args:Array[String]):Unit = {
    val branch = new Bank
    val customer = Customer("Bob", "Builder", 
    List("123 Broadway", "Walawala, WA"), "(123) 456-7890")
    branch.addCustomer(customer)
    val account1 = Account(customer, Account.Checking)
    branch.addAccount(account1)
    val account2 = Account(customer, Account.Savings)
    branch.addAccount(account2)
  }
}
