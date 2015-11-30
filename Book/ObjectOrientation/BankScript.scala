class Account {
  private var _balance = 0

  def deposit(amount:Int):Boolean = {
    if (amount > 0) {
      this._balance += amount
      true
    } else false
  }

  def withdraw(amount:Int):Boolean = {
    if (amount > 0 && amount <= _balance) {
      this._balance -= amount
      true
    } else false
  }

  def balance = _balance
}

val myAccount = new Account
println(myAccount.balance)
myAccount.deposit(100)
println(myAccount.balance)

