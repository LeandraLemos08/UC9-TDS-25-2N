import { JogoGame } from "./JogoGame";
import { Cliente } from "./Cliente";

export class Locacao {
  public jogo: JogoGame;
  public cliente: Cliente;
  public dataLocacao: string;
  public dataDevolucao: string;
 
  constructor(
    jogo: JogoGame,
    cliente: Cliente,
    dataLocacao: string,
    dataDevolucao: string
  ) {
    this.jogo = jogo;
    this.cliente = cliente;
    this.dataLocacao = dataLocacao;
    this.dataDevolucao = dataDevolucao;
 
  
    this.jogo.marcarComoLocado();
  }
 
  
  estaAtrasada(dataAtual: string): boolean {
    return new Date(dataAtual) > new Date(this.dataDevolucao);
  }


  diasDeAtraso(dataAtual: string): number {
    const diffMs = new Date(dataAtual).getTime() - new Date(this.dataDevolucao).getTime();
    const diffDias = Math.ceil(diffMs / (1000 * 60 * 60 * 24));
    return diffDias > 0 ? diffDias : 0;
  }

  calcularMulta(dataAtual: string): number {
    const dias = this.diasDeAtraso(dataAtual);
    return dias * 5;
  }
 
  
  calcularValorPrevisto(): number {
    const diffMs =
      new Date(this.dataDevolucao).getTime() - new Date(this.dataLocacao).getTime();
    const dias = Math.max(1, Math.round(diffMs / (1000 * 60 * 60 * 24)));
    return dias * this.jogo.valorDiaria;
  }
 
  exibirResumo(dataAtual: string): void {
    const atrasada = this.estaAtrasada(dataAtual);
 
    console.log("====================================");
    console.log("🎮 RESUMO DA LOCAÇÃO");
    console.log("====================================");
    console.log(`Jogo locado: ${this.jogo.nome}`);
    console.log(`Plataforma: ${this.jogo.plataforma}`);
    console.log(`Gênero: ${this.jogo.genero}`);
    console.log(`Cliente: ${this.cliente.nome}`);
    console.log(`Data da locação: ${this.dataLocacao}`);
    console.log(`Previsão de devolução: ${this.dataDevolucao}`);
    console.log(`Valor previsto: R$ ${this.calcularValorPrevisto().toFixed(2)}`);
 
    if (atrasada) {
      console.log(`🚨 Situação: Locação atrasada!`);
      console.log(`💸 Multa por atraso: R$ ${this.calcularMulta(dataAtual).toFixed(2)}`);
      console.log("Alerta máximo. O jogo foi para outra dimensão. 🚨🎮");
    } else {
      console.log(`✅ Situação: Locação dentro do prazo.`);
      console.log("Cliente responsável. Merece desconto e respeito. 🏆");
    }
    console.log("====================================\n");
  }
}