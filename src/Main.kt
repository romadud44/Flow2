import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.transform

suspend fun main() {
    println("Введите первую букву имени:")
    val firstChar = readln()
    println("Введите возраст:")
    val firstAge = readln()
    persons.getPerson(firstChar, firstAge.toByte())

}

data class Person(val name: String, val age: Byte) {
    override fun toString(): String {
        return "Имя: $name, возраст: $age"
    }
}

val persons = listOf(
    Person("Anna", 20),
    Person("Roman", 33),
    Person("Olga", 29),
    Person("Stanislav", 4),
    Person("Vladislav", 2),
    Person("Liliya", 0),
    Person("Ekaterina", 34),
    Person("Igor", 25),
    Person("Ivan", 45),
    Person("Elena", 27)
).asFlow()

suspend fun Flow<Person>.getPerson(first: String, age: Byte) {
    val personsFlow = persons.transform { person ->
        if (person.name.first().toString() == first.toUpperCase() && person.age == age) {
            emit(person)
        }
    }.collect { person ->
        println(person.toString())
    }
}
