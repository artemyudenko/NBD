import java.text.DateFormatSymbols
import java.util.Locale

object Tasks extends App {
  val task1 = new Task1
  task1.run()
  val rest = new RestOfTheTasks
  rest.run()
}

class Task1 {
  def run() = {
    val names = DateFormatSymbols.getInstance(Locale.US).getWeekdays
    val tempList = names.toList
    // remove first element as it was an empty string
    val list = tempList.drop(1)

    println("A")
    for (name <- list) {
      print(name + " ")
    }

    println()
    println("B")
    for (name <- list) {
      //niema w angielskiem dni tygodna od P
      //wypisuję na S
      if (name.startsWith("S")) {
        print(name + " ")
      }
    }

    println()
    println("C")
    list.foreach(name => print(name + " "))

    println()
    println("D")
    var i = 0
    val j = list.length
    while (i < j) {
      print(list(i) + " ")
      i+=1
    }

    println()
    println("E")
    def recursion1(i : Int) : Unit = {
      if (list.length == i) {
        return
      }
      print(list(i) + " ")

      recursion1(i+1)
    }
    recursion1(0)

    println()
    println("F")
    def recursion2(i : Int) : Unit = {
      if (i < 0) {
        return
      }
      print(list(i) + " ")
      recursion2(i-1)
    }
    recursion2(list.length - 1)

    println()
    println("G")
    val allDays1 = list.foldLeft("") {
      (res, name) => {
        name + " " + res
      }
    }

    println(allDays1)

    val allDays2 = list.foldRight("") {
      (res, name) => res  + " " + name
    }
    println()
    println(allDays2)

    println()
    println("H")
    val allDays3 = list.foldLeft("") {
      (res, name) => {
        //niema w angielskiem dni tygodna od P
        //wypisuję na S
        if (name.startsWith("S")) {
          name + " " + res
        }
        else {
          res
        }
      }
    }
    println(allDays3)
  }
}

class RestOfTheTasks {
  def run (): Unit = {
    println()
    println("Task 2")
    val productPriceMap = Map("Banana" -> 12, "Apple" -> 10, "Orange" -> 8)
    val test = productPriceMap map{ case(name, price) => (name, price * 0.9)}
    println(test)

    println()
    println("Task 3")
    task3("Test", 1, true)

    println()
    println("Task 4")
    val optionBanana = productPriceMap.get("Banana")
    if (optionBanana.isDefined) {
      println(optionBanana.get)
    }

    val optionNone = productPriceMap.get("Brain")
    if (optionNone.isEmpty) {
      println(optionNone)
    }

    println()
    println("Task 5")
    println(task5("S"))
    println(task5("Sunday"))
    println(task5("Saturday"))
    println(task5("Monday"))
    println(task5("Tuesday"))

    println()
    println("Task 6")

    val bankAccount = new BankAccount(100)
    println(bankAccount.accountState)
    bankAccount.payment(20)
    bankAccount.payment(-4)
    println(bankAccount.accountState)

    bankAccount.withDraw(-5)
    bankAccount.withDraw(130)
    bankAccount.withDraw(120)
    println(bankAccount.accountState)

    bankAccount.payment(10)
    println(bankAccount.accountState)

    val bankAccount2 = new BankAccount()
    println(bankAccount2.accountState)

    println()
    println("Task 7")

    val person1 = Person("Artem", "Yudenko")
    val person2 = Person("Some", "Other")
    val person3 = Person("Test", "Test")
    val person4 = Person("Test", "Test")

    println(task7(person1))
    println(task7(person2))
    println(task7(person3))
    println(task7(person4))

    println()
    println("Task 8")

    val list = List(5 , 2, 4, 0, 0, 3, 30, 20, 0)
    val modifiedList = task8(list)
    println(modifiedList)

    println()
    println("Task 9")

    val increaseByOneList = task9(list)
    println(increaseByOneList)

    println()
    println("Task 10")
    val listDoubles = List(-6.0 , -5.5, -5.0, -4.9, -1.1, -1.1, 0.0, 1.2, 35.0, 12.0, 11.1, 11.5, 10.0, 5.0)
    val range = task10(listDoubles)
    println(range)
  }

  def task3 (typeString : String, typeInt : Int, typeBoolean : Boolean)  = {
    println(typeString)
    println(typeInt)
    println(typeBoolean)
  }

  def task5(day : String) = day match {
    case sunday if day.equals("Sunday") || day.equals("Saturday") => "Weekend"
    case p if getListOfDays.contains(day) => "Praca"
    case d => "Nie ma takiego dnia"
  }
  /**
    * Class for task 7
    */
  case class Person(name: String, surname : String)

  def task7 (p : Person) = p match {
    case Person("Artem", "Yudenko") => "Hello"
    case Person("Some", "Other") => "It's me"
    case Person(name, surname)  => s"Hi, $name $surname"
  }

  def task8(list : List[Int]) = {
    list.filter(_ != 0)
  }

  def task9(list: List[Int]) = {
    list map {case number => number + 1}
  }

  def task10(list: List[Double]) = {
    list.filter(n => n <= 12.0 && n >= -5.0)
  }

  /**
    * Helper function for task 5
    *
    */
  def getListOfDays : Set[String] = {
    val names = DateFormatSymbols.getInstance(Locale.US).getWeekdays
    val tempList = names.toSet
    // remove first element as it was an empty string
    tempList.drop(1)
  }

}

/**
  * Class for task 6
  */
class BankAccount() {
  private var _accountState = 0.0
  private var setToZero = false

  def this(accountState : Double) {
    this
    _accountState = accountState
  }

  def accountState : Double =_accountState

  def payment(sum: Double) = {
    if (sum <= 0.0) {
      println("Sum cannot be smaller then 0")
    } else {
      this._accountState = sum + this.accountState
      println("Success")
    }
  }

  def withDraw(sum : Double): Unit = {
    if (sum <= 0.0) {
      println("Withdraw sum cannot be smaller then 0")
    } else {
      val currentAccountState = this.accountState - sum

      if (currentAccountState < 0.0) {
        println("Cannot be done, less then 0 is on account")
      } else {
        this._accountState = currentAccountState
      }
    }
  }
}