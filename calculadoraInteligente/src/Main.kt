fun main() {

    // pede pro usuario digitar uma expressão matemática
    println("Digite a sua expressão matemática (mínimo 9 caracteres):")

    // lê o que o usuario digitou e remove os espaços (tipo "2 + 2" vira "2+2")
    var exp = readln().replace(" ", "")

    // regra do trabalho: precisa ter no minimo 9 caracteres
    if (exp.length < 9) {
        println("Erro: A expressão precisa ter pelo menos 9 caracteres.")
        return // para o programa aqui mesmo
    }

    // aqui resolve um problema comum:
    // quando a pessoa digita tipo "2(3+4)" ao invés de "2*(3+4)"
    // então a gente automaticamente transforma isso em multiplicação
    exp = exp.replace(Regex("(\\d)\\("), "$1*(")

    // chama a função principal que faz toda a conta
    val resultadoFinal = calcularExpressao(exp)

    // mostra o resultado final pro usuario
    println("O resultado da expressão é: $resultadoFinal")
}


// === FUNÇÃO PRINCIPAL ===
// essa função é o "coração" da calculadora
fun calcularExpressao(exp: String): Double {

    // aqui usamos duas pilhas:
    // uma pra guardar numeros
    val valores = ArrayDeque<Double>()

    // outra pra guardar operadores (+ - * /)
    val operadores = ArrayDeque<Char>()

    var i = 0 // controla em qual posição da string estamos

    // percorre toda a expressão
    while (i < exp.length) {

        val c = exp[i] // pega o caractere atual

        // se for um "(" a gente só guarda na pilha de operadores
        if (c == '(') {
            operadores.addLast(c)
        }

        // se for numero (ou ponto decimal tipo 2.5)
        else if (c.isDigit() || c == '.') {

            var numeroTexto = ""

            // aqui junta todos os digitos pra formar o numero completo
            // ex: "123" ao invés de "1", "2", "3"
            while (i < exp.length && (exp[i].isDigit() || exp[i] == '.')) {
                numeroTexto += exp[i]
                i++
            }

            // converte o texto pra numero e guarda na pilha
            valores.addLast(numeroTexto.toDouble())

            i-- // volta um passo porque o while avançou demais
        }

        // se encontrar ")"
        else if (c == ')') {

            // resolve tudo até encontrar o "(" correspondente
            while (operadores.isNotEmpty() && operadores.last() != '(') {
                resolverTopoDaPilha(valores, operadores)
            }

            operadores.removeLast() // remove o "("
        }

        // se for operador matemático
        else if (c in setOf('+', '-', '*', '/')) {

            // aqui respeita a prioridade das operações
            // tipo: multiplicação antes de soma
            while (
                operadores.isNotEmpty() &&
                precedencia(operadores.last()) >= precedencia(c)
            ) {
                resolverTopoDaPilha(valores, operadores)
            }

            // adiciona o operador na pilha
            operadores.addLast(c)
        }

        i++ // vai pro próximo caractere
    }

    // depois que terminou de ler tudo, resolve o que sobrou
    while (operadores.isNotEmpty()) {
        resolverTopoDaPilha(valores, operadores)
    }

    // no final sobra só um valor, que é o resultado
    return valores.last()
}


// === FUNÇÕES AUXILIARES ===

// define a prioridade dos operadores
fun precedencia(op: Char): Int {
    return when (op) {
        '+', '-' -> 1 // menor prioridade
        '*', '/' -> 2 // maior prioridade
        else -> 0
    }
}


// essa função faz a conta de verdade pegando os valores da pilha
fun resolverTopoDaPilha(
    valores: ArrayDeque<Double>,
    operadores: ArrayDeque<Char>
) {

    // pega os dois ultimos numeros
    val val2 = valores.removeLast()
    val val1 = valores.removeLast()

    // pega o operador
    val op = operadores.removeLast()

    // faz a conta dependendo do operador
    val resultadoParcial = when (op) {
        '+' -> val1 + val2
        '-' -> val1 - val2
        '*' -> val1 * val2
        '/' -> val1 / val2
        else -> 0.0
    }

    // guarda o resultado de volta na pilha
    valores.addLast(resultadoParcial)
}