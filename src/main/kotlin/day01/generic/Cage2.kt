package day01.generic

class Cage2<T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T {
        return animals.first()
    }

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun moveFrom(cage: Cage2<out T>) {
        /**
         * cage.put(this.getFirst()) 사용 불가
         * out 이 붙은 파라미터의 경우는 생산자 역할만 할 수 있다.
         */
        this.animals.addAll(cage.animals)
    }

    fun moveTo(cage: Cage2<in T>) {
        /**
         * in 이 붙은 파라미터의 경우는 소비자 역할만 할 수 있다.
         */
        cage.animals.addAll(this.animals)
    }
}
