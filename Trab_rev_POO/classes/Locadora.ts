import { JogoGame } from "./JogoGame";
import { Cliente } from "./Cliente";
import { Locacao } from "./Locacao";

export class Locadora {
  public nome: string;
  public jogos: JogoGame[] = [];
  public clientes: Cliente[] = [];
  public locacoes: Locacao[] = [];
 
  constructor(nome: string) {
    this.nome = nome;
  }
 

  adicionarJogo(jogo: JogoGame): void {
    this.jogos.push(jogo);
    console.log(`✅ Jogo "${jogo.nome}" cadastrado com sucesso!`);
  }
 

  adicionarCliente(cliente: Cliente): void {
    this.clientes.push(cliente);
    console.log(`✅ Cliente "${cliente.nome}" cadastrado com sucesso!`);
  }
 
  registrarLocacao(
    nomeJogo: string,
    nomeCliente: string,
    dataLocacao: string,
    dataDevolucao: string
  ): Locacao | null {
    const jogo = this.jogos.find(
      (j) => j.nome.toLowerCase() === nomeJogo.toLowerCase()
    );
    const cliente = this.clientes.find(
      (c) => c.nome.toLowerCase() === nomeCliente.toLowerCase()
    );
 
    if (!jogo) {
      console.log(`❌ Jogo "${nomeJogo}" não encontrado.`);
      return null;
    }
    if (!cliente) {
      console.log(`❌ Cliente "${nomeCliente}" não encontrado.`);
      return null;
    }
    if (!jogo.disponivel) {
      console.log(`❌ O jogo "${jogo.nome}" não está disponível no momento.`);
      return null;
    }
 
    const locacao = new Locacao(jogo, cliente, dataLocacao, dataDevolucao);
    this.locacoes.push(locacao);
    console.log(`🎮 O jogo ${jogo.nome} foi locado com sucesso!`);
    return locacao;
  }
 
  listarJogos(): void {
    console.log(`\n📚 Catálogo de jogos da ${this.nome}:`);
    if (this.jogos.length === 0) {
      console.log("Nenhum jogo cadastrado.");
      return;
    }
    this.jogos.forEach((jogo) => jogo.exibirInfo());
  }
 

  listarClientes(): void {
    console.log(`\n👥 Clientes da ${this.nome}:`);
    if (this.clientes.length === 0) {
      console.log("Nenhum cliente cadastrado.");
      return;
    }
    this.clientes.forEach((cliente) => cliente.exibirInfo());
  }
 

  listarLocacoes(dataAtual: string): void {
    console.log(`\n📝 Locações registradas na ${this.nome}:`);
    if (this.locacoes.length === 0) {
      console.log("Nenhuma locação registrada.");
      return;
    }
    this.locacoes.forEach((locacao) => locacao.exibirResumo(dataAtual));
  }
 

  verificarAtrasos(dataAtual: string): void {
    const atrasadas = this.locacoes.filter((l) => l.estaAtrasada(dataAtual));
 
    console.log(`\n🚨 Verificação de atrasos (${dataAtual}):`);
    if (atrasadas.length === 0) {
      console.log("Nenhuma locação atrasada. Tudo em dia! 🏆");
      return;
    }
    atrasadas.forEach((l) => {
      console.log(
        `- ${l.jogo.nome} (Cliente: ${l.cliente.nome}) | ` +
          `${l.diasDeAtraso(dataAtual)} dia(s) de atraso | ` +
          `Multa: R$ ${l.calcularMulta(dataAtual).toFixed(2)}`
      );
    });
  }
}