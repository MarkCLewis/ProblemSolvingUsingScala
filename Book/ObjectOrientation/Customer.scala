class Customer(
    val id:String, 
    val firstName:String,
    val lastName:String,
    private var _address:List[String],
    private var _phone:String) {
  private var _accounts:List[Account] = Nil

  def accounts = _accounts

  def address:List[String] = _address
  def address_=(newAddr:List[String]):Unit = _address = newAddr

  def phone:String = _phone
  def phone_=(newPhone:String):Unit = _phone = newPhone

  def addAccount(account:Account):Boolean = {
    if (account.customer == this && _accounts.find(_.id == account.id) == None) {
      _accounts ::= account
      true
    } else false
  }

  def removeAccount(accountID:String):Boolean = {
    val index = _accounts.indexWhere(_.id == accountID)
    if (index < 0) false
    else {
      _accounts = _accounts.patch(index, Nil, 1)
      true
    }
  }
}

object Customer {
  private var nextID = 1

  def apply(firstName:String, lastName:String, address:List[String], phone:String):Customer = {
    val id = "0"*(7-nextID.toString.length) + nextID
    nextID += 1
    new Customer(id, firstName, lastName, address, phone)
  }
}
