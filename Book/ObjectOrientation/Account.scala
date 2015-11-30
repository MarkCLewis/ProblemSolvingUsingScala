class Account private(val id:String, val customer:Customer, accountType:Int) {
  require(accountType == Account.Checking || accountType == Account.Savings || accountType == Account.CD)
  private var _balance = 0

  customer.addAccount(this)

  def deposit(amount:Int):Boolean = {
    if (amount > 0) {
      _balance += amount
      true
    } else false
  }

  def withdraw(amount:Int):Boolean = {
    if (amount > 0 && amount <= _balance) {
      _balance -= amount
      true
    } else false
  }

  def monthlyAdjustment():Unit = {
    accountType match {
      case Account.Checking => _balance = (_balance * 1.01).toInt
      case Account.Savings => _balance = (_balance * 1.03).toInt
      case Account.CD => _balance = (_balance * 1.05).toInt
    }
  }

  def balance = _balance
  def balance_=(b:Int):Unit = {
    if (b >= 0) {
      if (b < _balance) withdraw(_balance - b) else deposit(b - _balance)
    }
  }
}

object Account {
  val Checking = 0
  val Savings = 1
  val CD = 2

  private var nextAccount = 1

  def apply(customer:Customer, accountType:Int):Account = {
    val id = "0"*(7-nextAccount.toString.length) + nextAccount
    nextAccount += 1
    new Account(id, customer, accountType)
  }
}
