class Bank {
  private var accounts:List[Account] = Nil
  private var customers:List[Customer] = Nil

  def addAccount(account:Account):Unit = accounts ::= account
  def removeAccount(accountID:String):Boolean = {
    val index = accounts.indexWhere(_.id == accountID)
    if (index < 0) false
    else {
      accounts = accounts.patch(index, Nil, 1)
      true
    }
  }

  def addCustomer(customer:Customer):Unit = customers ::= customer
  def removeCustomer(customerID:String):Boolean = {
    val index = customers.indexWhere(_.id == customerID)
    if (index < 0) false
    else {
      customers = customers.patch(index, Nil, 1)
      true
    }
  }

  def apply(accountID:String):Option[Account] = accounts.find(_.id == accountID)
  def findCustomer(customerID:String):Option[Customer] = customers.find(_.id == customerID)
}
