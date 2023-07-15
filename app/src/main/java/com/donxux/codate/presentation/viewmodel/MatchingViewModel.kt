package com.donxux.codate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.donxux.codate.domain.model.Field
import com.donxux.codate.domain.model.User
import com.donxux.codate.domain.model.userOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MatchingViewModel : ViewModel() {

    private val _partners: MutableStateFlow<List<User>> = MutableStateFlow(testPartners1)
    val partners: StateFlow<List<User>> get() = _partners.asStateFlow()

    private val _currentPartner: MutableStateFlow<User> = MutableStateFlow(partners.value[0])
    val currentPartner: StateFlow<User> get() = _currentPartner.asStateFlow()

    private var flag: Boolean = false

    fun updatePartners() {
        if (flag) {
            _partners.value = testPartners1
        } else {
            _partners.value = testPartners2
        }
        flag = flag.not()
    }

    fun setCurrentPartner(index: Int) {
        _currentPartner.value = _partners.value[index].copy()
    }
}

private val testPartners1 = listOf(
    userOf(
        name = "test1",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요\nsdfsdfjsdflaksdjflksdaklfjsdaklfjsdaf"
    ),
    userOf(
        name = "test2",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/f3e8d3ff2e9e09c58cf5bb4e1a145f06.js\"></script>",
        bio = "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n" +
            "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요\n"
    ),
    userOf(
        name = "test3",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test4",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test5",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test6",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test7",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test8",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test9",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test10",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    )
)

private val testPartners2 = listOf(
    userOf(
        name = "test11",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test12",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test13",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/f3e8d3ff2e9e09c58cf5bb4e1a145f06.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test14",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test15",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test16",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test17",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test18",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test19",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    ),
    userOf(
        name = "test20",
        age = 23,
        fields = listOf(Field.Android),
        codeUrl = "<script src=\"https://gist.github.com/DONXUX/3df7a8ca9438b1107a7186f92c66f97f.js\"></script>",
        bio = "안녕하세요"
    )
)
