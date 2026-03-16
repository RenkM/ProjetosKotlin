import kotlin.random.Random // biblioteca pra gerar numeros aleatorios

fun main() {

    // aqui a gente cria uma FILA de carros
    // fila significa que o primeiro que entra é o primeiro que sai (tipo fila de mercado)
    val fila = ArrayDeque<Int>()

    // variavel que guarda o estado do semaforo
    // true = aberto
    // false = fechado
    var semaforoAberto = false

    // id pra identificar cada carro que chega
    // cada carro vai ter um numero diferente
    var idCarro = 1


    // =============================
    // THREAD DO SEMAFORO
    // =============================
    val semaforo = Thread {

        // loop infinito pq o semaforo fica funcionando o tempo todo
        while (true) {

            // abre o semaforo
            semaforoAberto = true
            println("\n🟢 Semáforo ABERTO")

            // pega o tempo atual do sistema
            // vamos usar isso pra contar os 5 segundos
            val tempoAbertura = System.currentTimeMillis()

            // enquanto nao passar 5 segundos
            while (System.currentTimeMillis() - tempoAbertura < 5000) {

                // verifica se tem carros esperando
                if (fila.isNotEmpty()) {

                    // tira o primeiro carro da fila
                    // basicamente o carro da frente passa
                    val carro = fila.removeFirst()

                    // mostra qual carro passou
                    println("🚗 Carro $carro passou no semáforo")
                }

                // espera 1 segundo antes de deixar outro carro passar
                // tipo um carro passando por vez
                Thread.sleep(1000)
            }

            // depois dos 5 segundos o semaforo fecha
            semaforoAberto = false
            println("\n🔴 Semáforo FECHADO")

            // agora ele fica fechado por 10 segundos
            // os carros continuam chegando mas nao passam
            Thread.sleep(10000)
        }
    }



    // =============================
    // THREAD QUE GERA OS CARROS
    // =============================
    val chegadaCarros = Thread {

        // loop infinito pq carros podem chegar a qualquer momento
        while (true) {

            // adiciona o carro no final da fila
            // igual quando um carro chega atras do outro no sinal
            fila.addLast(idCarro)

            // mostra que o carro chegou
            println("🚙 Carro $idCarro chegou no semáforo")

            // aumenta o id pro proximo carro
            idCarro++

            // espera um tempo aleatorio entre 1 e 3 segundos
            // isso simula carros chegando em tempos diferentes
            Thread.sleep(Random.nextLong(1000, 3000))
        }
    }



    // =============================
    // INICIANDO AS THREADS
    // =============================

    // aqui inicia a thread que controla o semaforo
    semaforo.start()

    // aqui inicia a thread que cria os carros
    chegadaCarros.start()
}