export class Cliente {
  constructor(
    public nome: string,
    public idade: number,
    public telefone: string,
    public email: string
  ) {}
 

  exibirInfo(): void {
    console.log(
      `👤 ${this.nome} | Idade: ${this.idade} | Tel: ${this.telefone} | E-mail: ${this.email}`
    );
  }
}