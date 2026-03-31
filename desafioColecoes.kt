// 1- molde para as mercadorias (tipo uma "forma" de produto msm)
class Mercadoria(var nome: String, var preco: Double)

fun main() {
    // 2 lista mutavel que vai guardar os objetos (tipo um carrinho de compras sla)
    val listaDeCompras = mutableListOf<Mercadoria>()

    // variavel pra contrOlar o loop (se estiver true ainda ta rodando)
    var rodando = true

    // msg inicial pro user
    println("=== Bem-vindo ao Sistema de Supermercado ===")

    // 3 loop que mantem o menu rodando SEM parar
    while (rodando) {

        // menu topzera
        println("\nEscolha uma opção:")
        println("1. Adicionar mercadoria")
        println("2. Remover mercadoria")
        println("3. Alterar preço")
        println("4. Ver lista completa")
        println("5. Sair")
        print("Opção: ")

        // pega o que o cara digitou
        val opcao = readln()

        // decide o que fazer com base na opcao
        when (opcao) {

            // adicionar produto
            "1" -> {
                print("Digite o nome do produto: ")
                val nome = readln() // lendo o nome aqui

                print("Digite o preço (ex: 5.99): ")
                val preco = readln().toDouble() // converte pra double (numero com virgula/flutuante)

                // cria o produto novo
                val novoProduto = Mercadoria(nome, preco)

                // joga ele na lista
                listaDeCompras.add(novoProduto)

                println("'$nome' adicionado com sucesso!")
            }

            // remover produto
            "2" -> {
                print("Digite o nome do produto que deseja remover: ")
                val nomeRemover = readln()

                // remove se achar algum com nome igual (ignora maiusculo/minusculo tbm)
                val removido = listaDeCompras.removeIf {
                    it.nome.equals(nomeRemover, ignoreCase = true)
                }

                // aqui verifica se realmente removeu ou nao
                if (removido) println("Produto removido!")
                else println("Produto não encontrado.")
            }

            // alterar preço
            "3" -> {
                print("Digite o nome do produto para alterar o preço: ")
                val nomeAlterar = readln()

                // tenta achar o produto dentro da lista
                val produto = listaDeCompras.find {
                    it.nome.equals(nomeAlterar, ignoreCase = true)
                }

                // se achou o bendito produto
                if (produto != null) {
                    print("Digite o novo preço para ${produto.nome}: ")

                    // troca o preço direto no objeto msm
                    produto.preco = readln().toDouble()

                    println("Preço atualizado com sucesso!")
                } else {
                    // se nao achou ja era kk
                    println("Produto não encontrado.")
                }
            }

            // mostrar lista inteira
            "4" -> {
                println("\n--- Sua Lista de Compras ---")

                // se nao tem nada na lista
                if (listaDeCompras.isEmpty()) {
                    println("A lista está vazia.")
                } else {

                    // percorre tudo e mostra item por item
                    listaDeCompras.forEach { produto ->
                        println("- ${produto.nome} : R$ ${produto.preco}")
                    }
                }

                println("----------------------------")
            }

            // sair do sistema
            "5" -> {
                println("Saindo do sistema.")

                // aqui mata o loop 
                rodando = false
            }

            // qualquer coisa diferente disso cai aqui
            else -> println("Opção inválida, tente novamente.")
        }
    }
}