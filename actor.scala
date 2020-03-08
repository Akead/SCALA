import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

case class Message_First(val number : Int)
case class Message_Second(val sequence : List[Int])
case class Second_Printer(val printer : ActorRef)

class Second extends Actor{

    var printer : ActorRef = null
    def receive = {
        case p : Second_Printer => {printer = p.printer}
        case q : Message_Second => {
            println("Second - list sum")
            printer ! Message_First(q.sequence.sum)
        }
        case _ => 
        }
    }

class First extends Actor{
    var prev : Int = 0 
    def receive = {
        case q : Message_First => {
            println("First")
            println(prev + q.number)
            prev = prev + q.number
        }
        case _ =>

    }


}



object lab_10 extends App {

    val system = ActorSystem("Default") 
    val first_printer = system.actorOf(Props[First], name ="Y")
    val adder = system.actorOf(Props[Second], name ="X")

    adder ! Second_Printer(first_printer)
    adder ! Message_Second(List(1,2,3,4,5))
    adder ! Message_Second(List(5,7,8,4))
    adder ! Message_Second(List(2,4,5,7))
    adder ! Message_Second(List(13,24,8,4,5))
    adder ! Message_Second(List(76,7,8,4))
    adder ! Message_Second(List(2,4,4,7))
    adder ! Message_Second(List(1,2,3,4,5))
    adder ! Message_Second(List(5,7,8,4))
    adder ! Message_Second(List(2,4,5,7))
    adder ! Message_Second(List(13,24,8,4,5))
    adder ! Message_Second(List(76,7,8,4))
    adder ! Message_Second(List(2,4,4,7))
    adder ! Message_Second(List(1,2,3,4,5))
    adder ! Message_Second(List(5,7,8,4))
    adder ! Message_Second(List(2,4,5,7))
    adder ! Message_Second(List(13,24,8,4,5))
    adder ! Message_Second(List(76,7,8,4))
    adder ! Message_Second(List(2,4,4,7))
}
