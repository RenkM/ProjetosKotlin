fun main(){
    val piloto1 = Piloto("Betinha", 37, 95)
    val piloto2 = Piloto("Sigma da bahia", 69, 90)
    val piloto3 = Piloto("clone do huilson", 25, 88)
    val piloto4 = Piloto("Taffebot", 4, 78)

    val carro1 = Carro("Lamborghini", 90, 76, 80, 100, 10, 85, piloto1)
    val carro2 = Carro("Carro de Palhaço", 67, 87, 72, 100, 15, 80, piloto2)
    val carro3 = Carro("McLaren", 92, 90, 78, 100, 11, 88, piloto3)
    val carro4 = Carro("Detonator", 100, 100, 92, 200, 30, 55, piloto4)
    val listaCarros = listOf(carro1, carro2, carro3, carro4)

    val pista = Pista(listaCarros, voltas = 10, clima = "sol")

    pista.mostrarPlacar()
}