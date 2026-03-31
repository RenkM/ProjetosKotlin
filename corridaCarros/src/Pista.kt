// classe que representa a pista da corrida
class Pista(

    val carros: List<Carro>, // lista de carros participantes
    val voltas: Int,         // quantidade de voltas
    val clima: String        // clima da corrida (sol, chuva, etc)

) {

    // função que calcula o resultado da corrida
    fun calcularResultado(): List<Pair<Carro, Int>> {

        val resultado = mutableListOf<Pair<Carro, Int>>() // lista com carro + pontuação

        for (carro in carros) {
            var pontuacao = 0 // começa com 0 pontos

            // soma atributos do carro + piloto
            pontuacao += carro.velocidade * 2
            pontuacao += carro.aceleracao * 2
            pontuacao += carro.freio
            pontuacao += carro.resistencia
            pontuacao += carro.piloto.habilidade * 2

            // calcula consumo de combustível
            var combustivel = carro.tanque
            for (i in 1..voltas) {
                combustivel -= carro.consumo
                if (combustivel <= 0) pontuacao -= 10 // penalidade se acabar
            }

            // efeito do clima
            when (clima.lowercase()) {
                "chuva" -> {
                    pontuacao -= carro.velocidade // piora velocidade
                    pontuacao += carro.freio * 2  // freio ajuda mais
                }
                "sol" -> pontuacao += carro.velocidade // clima bom ajuda
            }

            // desgaste ao longo das voltas
            pontuacao -= voltas * 2

            // fator sorte (aleatório)
            pontuacao += (-10..10).random()

            // adiciona carro + pontuação na lista
            resultado.add(carro to pontuacao)
        }

        // ordena do maior para o menor (quem tem mais pontos ganha)
        return resultado.sortedByDescending { it.second }
    }

    // função que mostra o placar final
    fun mostrarPlacar() {
        val resultado = calcularResultado()

        println("-----------RESULTADO DA CORRIDA-----------")

        // percorre a lista e mostra posição, carro, piloto e pontos
        for ((index, item) in resultado.withIndex()) {
            val carro = item.first
            val pontos = item.second

            println("${index + 1}º lugar: ${carro.modelo} - Piloto: ${carro.piloto.nome} | Pontos: $pontos")
        }
    }
}
