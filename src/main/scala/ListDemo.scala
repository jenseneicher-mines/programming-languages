class ListDemo[T](implicit ev : T => Ordered[T]) {
  // TODO: this function should implement selection sort (hint: use the
  // provided extractSmallest function).
  // Use only recursion and pattern matching - do NOT use any built-in Scala libraries!
  // Example: selectionSort(List(3,2,4,1,5)) --> List(1,2,3,4,5)
  def selectionSort(l : List[T]) : List[T] = {
    def extractSmallest(l : List[T]) : (T,List[T]) = {
      l match {
        case List(x) => {
          (x,Nil)
        }
        case x::more => {
          extractSmallest(more) match{
            case (small, rest) if small < x => (small, x::rest)
            case (small, rest) => (x, small::rest) 
          }
        }
      }
    }
    l match {
      case Nil => Nil
      case List(x) => List(x)
      case x::more => {
        var (small, rest) = extractSmallest(x::more)
        small::selectionSort(rest)
      }
    }
  }


  // TODO: implement the "map" operation discussed in class.
  // Use only recursion and pattern matching - do NOT use any built-in Scala libraries!
  // Example: map(List(a,b,c,d,e),f) --> List(f(a),f(b),f(c),f(d),f(e))
  def map[U](l : List[T], f : T => U) : List[U] = {
    l match {
      case Nil => Nil
      case x::more => {
        // DONE
        f(x) :: map(more, f)
      }
    }
  }

  // TODO: implement the "fold left" operation discussed in class.
  // Use only recursion and pattern matching - do NOT use any built-in Scala libraries!
  // Example: foldLeft(List(a,b,c,d,e),i,f) --> f(f(f(f(f(i,a),b),c),d),e)
  def foldLeft[U](l : List[T], init : U, f : (U,T)=>U) : U = {
    // DONE
      def fold( l : List[T], leftList: U): U =
        l match{
          case Nil => leftList
          case List() => leftList
          case x +: l => fold(l, f(leftList, x))
      }
      fold(l, init)
  }

  // TODO: implement the "fold right" operation discussed in class.
  // Use only recursion and pattern matching - do NOT use any built-in Scala libraries!
  // Example: foldRight(List(a,b,c,d,e),i,f) --> f(a,f(b,f(c,f(d,f(e,i)))))
  def foldRight[U](l : List[T], init : U, f : (T,U)=>U) : U = {
    // DONE
    l match {
      case Nil => init
      case head :: tail => {
        val n_value = foldRight(tail, init, f)
        f(head, n_value)
      }
    }
  }

  // TODO: implement the "filter" operation discussed in class.
  // Use only recursion and pattern matching - do NOT use any built-in Scala libraries!
  // Example: filter(List(1,4,2,5,3,6),(x:Int)=>(x > 3)) --> List(4,5,6)
  def filter(l : List[T], f : T => Boolean) : List[T] = {
    l match {
      case Nil => Nil
      case head :: tail => {
        if(f(head)) {
          head :: filter(tail, f)
        } else {
          filter(tail, f)
        }
      }
    }
  }

  // TODO: implement list reversal.
  // Use only recursion and pattern matching - do NOT use any built-in Scala libraries!
  // Example: reverse(List(a,b,c,d,e)) --> List(e,d,c,b,a)
  def reverse(l : List[T]) : List[T] = {
    foldLeft(l, Nil, (r : List[T], x : T) => x :: r)
  }
}
