

class Pista(
    val carros: List<Carro>,
    val voltas: Int,
    val clima: String
) {
    fun calcularResultado(): List<Pair<Carro, Int>> {

        val resultado = mutableListOf<Pair<Carro, Int>>()

        for (carro in carros) {
            var pontuacao = 0

            // base equilibrada
            pontuacao += carro.velocidade * 2
            pontuacao += carro.aceleracao * 2
            pontuacao += carro.freio
            pontuacao += carro.resistencia
            pontuacao += carro.piloto.habilidade * 2

            // combustível
            var combustivel = carro.tanque
            for (i in 1..voltas) {
                combustivel -= carro.consumo
                if (combustivel <= 0) pontuacao -= 10
            }

            // clima
            when (clima.lowercase()) {
                "chuva" -> {
                    pontuacao -= carro.velocidade
                    pontuacao += carro.freio * 2
                }
                "sol" -> pontuacao += carro.velocidade
            }

            // desgaste
            pontuacao -= voltas * 2

            // sorte controlada
            pontuacao += (-10..10).random()
            resultado.add(carro to pontuacao)

        }
        return resultado.sortedByDescending { it.second }
    }

    fun mostrarPlacar() {
        val resultado = calcularResultado()

        println("-----------RESULTADO DA CORRIDA-----------")

        for ((index, item) in resultado.withIndex()) {
            val carro = item.first
            val pontos = item.second

            println("${index + 1}º lugar: ${carro.modelo} - Piloto: ${carro.piloto.nome} | Pontos: $pontos")
        }
    }
}
