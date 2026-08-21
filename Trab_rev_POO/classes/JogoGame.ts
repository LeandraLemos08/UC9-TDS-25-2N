export class JogoGame {
  constructor(
    public nome: string,
    public plataforma: string,
    public genero: string,
    public valorDiaria: number,
    public disponivel: boolean = true
  ) {}
 

  marcarComoLocado(): void {
    this.disponivel = false;
  }
 
  
  marcarComoDisponivel(): void {
    this.disponivel = true;
  }
 

  exibirInfo(): void {
    console.log(
      `🎮 ${this.nome} | Plataforma: ${this.plataforma} | Gênero: ${this.genero} | ` +
        `Diária: R$ ${this.valorDiaria.toFixed(2)} | ` +
        `${this.disponivel ? "Disponível ✅" : "Locado ❌"}`
    );
  }
}