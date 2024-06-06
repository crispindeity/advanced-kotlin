package day01.generic

fun main() {
    val cage = Cage()
    cage.put(Carp("잉어"))

    /**
     *  as 를 통한 type casting 은 위험한 방법 누군가 실수로 Carp 이 아닌
     *  다른 Animal 을 상속받는 객체를 꺼내는 경우 예외 발생
     *  컴파일 에러가 아닌 런타임 에러가 발생하게 된다.
     *  val carp: Carp = cage.getFirst() as Carp
     */

    /**
     * generic 을 사용해서 type casting 없이 사용이 가능
     */
    val cage2: Cage2<Carp> = Cage2()
    cage2.put(Carp("잉어"))
    val carp: Carp = cage2.getFirst()

    val goldFishCage: Cage2<GoldFish> = Cage2()
    goldFishCage.put(GoldFish("금붕어"))

    /**
     * generic 의 불공변 때문에 별도의 작업 없이 Fish 타입의 Cage 에 GoldFish 넣을 수 없다.
     * val fishCage = Cage2<Fish>()
     * fishCage.moveFrom(goldFishCage)
     */

    /**
     * moveFrom(cage: Cage2<out T>) out 을 붙여 변성을 주어 위에서 발생한 문제를 해결할 수 있다.
     * 단, 변성 어노테이션(out) 이 붙은 파라미터의 경우는 데이터를 조회만 할 수 있다.
     */
    val fishCage = Cage2<Fish>()
    fishCage.moveFrom(goldFishCage)
    val fish: Fish = fishCage.getFirst()

    /**
     * goldFish 타입은 Fish 타입의 하위 타입이지만, generic 의 반공변성을 사용하여
     * goldFish 타입의 Cage 에 fish 타입의 객체를 넣을 수 있게 된다.
     * 상속관계와 generic 관계가 반대되는 상황
     * 반공변성을 주기 위해서는 파라미터에 in 을 붙여주면 된다.
     */
    goldFishCage.moveTo(fishCage)
}
