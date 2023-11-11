import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
class MainViewModel: ViewModel() {

    // MutableStateFlow para almacenar el texto de búsqueda
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    // MutableStateFlow para almacenar el estado de búsqueda
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    // MutableStateFlow para almacenar la lista completa de personas
    private val _persons = MutableStateFlow(allPersons)
    // Flow que combina el texto de búsqueda con la lista de personas
    val persons = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_persons) { text, persons ->
            if(text.isBlank()) {
                persons
            } else {
                delay(2000L)
                persons.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        // Almacena el estado del flujo en el ámbito del modelo de vista
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _persons.value
        )

    // Función para manejar cambios en el texto de búsqueda
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}

// Clase de datos que representa una persona
data class Person(
    val firstName: String,
    val lastName: String
) {
    // Función para verificar si la persona coincide con la consulta de búsqueda
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            "${firstName.first()} ${lastName.first()}",
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

// Lista de todas las personas
private val allPersons = listOf(
    Person(
        firstName = "Pitbull",
        lastName = "Bully - Disponible"
    ),
    Person(
        firstName = "Gato persa",
        lastName = "Jezos - Disponible"
    ),
    Person(
        firstName = "Tórtola",
        lastName = "Turca - Disponible"
    ),
    Person(
        firstName = "Gallina",
        lastName = "De Patio - Disponible"
    ),
    Person(
        firstName = "Pez",
        lastName = "Goldfish - Disponible"
    ),
    Person(
        firstName = "Pez",
        lastName = "Betta - Disponible"
    ),
    Person(
        firstName = "Gallina",
        lastName = "De Patio - Disponible"
    ),
    Person(
        firstName = "Pedigree",
        lastName = "Cachorro - Disponible"
    ),
    Person(
        firstName = "Hills",
        lastName = "Adulto - Disponible"
    ),
    Person(
        firstName = "Aime",
        lastName = "Nutri Balance - Disponible"
    ),
    Person(
        firstName = "Layena",
        lastName = "Concentrado - Disponible"
    ),
    Person(
        firstName = "Sera",
        lastName = "Vipagran - Disponible"
    ),

)
