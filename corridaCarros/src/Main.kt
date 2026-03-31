fun main() {

    // aqui estamos criando os pilotos (tipo os "jogadores" da corrida)
    // cada piloto tem nome, idade e habilidade (quanto maior, melhor ele pilota)
    val piloto1 = Piloto("Betinha", 37, 95)
    val piloto2 = Piloto("Sigma da bahia", 69, 90)
    val piloto3 = Piloto("clone do huilson", 25, 88)
    val piloto4 = Piloto("Taffebot", 4, 78)

    // agora criamos os carros
    // cada carro tem varios atributos tipo:
    // nome, velocidade, aceleração, controle, durabilidade (vida), consumo, etc
    // e no final sempre tem o piloto que dirige ele

    val carro1 = Carro("Lamborghini", 90, 76, 80, 100, 10, 85, piloto1)
    val carro2 = Carro("Carro de Palhaço", 67, 87, 72, 100, 15, 80, piloto2)
    val carro3 = Carro("McLaren", 92, 90, 78, 100, 11, 88, piloto3)
    val carro4 = Carro("Detonator", 100, 100, 92, 200, 30, 55, piloto4)

    // aqui criamos uma lista com todos os carros
    // basicamente é tipo uma "fila" com todos que vão participar da corrida
    val listaCarros = listOf(carro1, carro2, carro3, carro4)

    // agora criamos a pista 
    // passamos:
    // - a lista de carros que vão correr
    // - quantidade de voltas da corrida
    // - clima da corrida (isso pode influenciar no desempenho talvez 👀)
    val pista = Pista(listaCarros, voltas = 10, clima = "sol")

    // aqui chamamos uma função que mostra o placar da corrida
    // provavelmente vai exibir quem ganhou, posições, etc
    pista.mostrarPlacar()
}
