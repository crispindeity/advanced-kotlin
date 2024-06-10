package day02

import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class Person2 {
    private val name: String by LazyInitProperty {
        Thread.sleep(2_000L)
        "김수한무"
    }

    val status: String by object : ReadOnlyProperty<Person2, String> {
        private var isGreen: Boolean = false

        override fun getValue(thisRef: Person2, property: KProperty<*>): String {
            return if (isGreen) {
                "Happy"
            } else {
                "Sad"
            }
        }
    }
}

class LazyInitProperty<T>(val init: () -> T) {
    private var _value: T? = null
    private val value: T
        get() {
            if (_value == null) {
                this._value = init()
            }
            return _value!!
        }

    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value
    }
}


class LazyInitProperty2<T>(val init: () -> T) : ReadOnlyProperty<Any, T> {
    private var _value: T? = null
    private val value: T
        get() {
            if (_value == null) {
                this._value = init()
            }
            return _value!!
        }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value
    }
}

class Person3 {
    val name: String by lazy {
        Thread.sleep(2_000L)
        "김수한무"
    }
}

class Person4 {
    var age: Int by Delegates.observable(20) {_, oldValue, newValue ->
        println("이전 값 : $oldValue 새로운 값 : $newValue")
    }
}

class Person5 {
    val name by DelegateProperty1("최태현", "name")
    val country by DelegateProperty1("한국", "country")
}

class DelegateProperty1(
    private val initValue: String,
    propertyName: String,
) : ReadOnlyProperty<Any, String> {
    init {
        if (propertyName != "country") {
            throw IllegalArgumentException("name 에만 사용 가능합니다.")
        }
    }
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return initValue
    }
}

class Person6 {
    val name: String by DelegateProvider("최태현")
    val contury: String by DelegateProvider("한국")
}

class DelegateProvider(
    private val initValue: String
) {
    operator fun provideDelegate(thisRef: Any, property: KProperty<*>): DelegateProperty2 {
        if (property.name != "name") {
            throw IllegalArgumentException("name만 연결 가능합니다. ${property.name}")
        }
        return DelegateProperty2(initValue)
    }
}

class DelegateProperty2(
    private val initValue: String,
) : ReadOnlyProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return initValue
    }
}

fun main() {
    val p = Person4()
    p.age = 30

    Person6()
}
