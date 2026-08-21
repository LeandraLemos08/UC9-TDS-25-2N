import readlineSync from "readline-sync";
import { Locadora } from "./Locadora";
import { JogoGame } from "./JogoGame";
import { Cliente } from "./Cliente";
 
const locadora = new Locadora("GameZone Locações");
 

locadora.adicionarJogo(
  new JogoGame("The Legend of Zelda: Tears of the Kingdom", "Nintendo Switch", "Aventura", 10)
);
locadora.adicionarJogo(new JogoGame("Marvel's Spider-Man 2", "PS5", "Ação", 15));
locadora.adicionarCliente(new Cliente("Lucas Pereira", 22, "(11) 98888-1234", "lucas@email.com"));
locadora.adicionarCliente(new Cliente("Ana Souza", 27, "(11) 97777-5678", "ana@email.com"));
 
function cadastrarJogo(): void {
  console.log("\n--- Cadastro de Jogo ---");
  const nome = readlineSync.question("Nome do jogo: ");
  const plataforma = readlineSync.question("Plataforma: ");
  const genero = readlineSync.question("Gênero: ");
  const valorDiaria = Number(readlineSync.question("Valor da diária (R$): "));
 
  locadora.adicionarJogo(new JogoGame(nome, plataforma, genero, valorDiaria));
}
 

function cadastrarCliente(): void {
  console.log("\n--- Cadastro de Cliente ---");
  const nome = readlineSync.question("Nome do cliente: ");
  const idade = Number(readlineSync.question("Idade: "));
  const telefone = readlineSync.question("Telefone: ");
  const email = readlineSync.question("E-mail: ");
 
  locadora.adicionarCliente(new Cliente(nome, idade, telefone, email));
}
 
function registrarLocacaoMenu(): void {
  console.log("\n--- Registrar Locação ---");
  locadora.listarJogos();
  const nomeJogo = readlineSync.question("\nNome do jogo a locar: ");
  locadora.listarClientes();
  const nomeCliente = readlineSync.question("\nNome do cliente: ");
  const dataLocacao = readlineSync.question("Data da locação (AAAA-MM-DD): ");
  const dataDevolucao = readlineSync.question("Previsão de devolução (AAAA-MM-DD): ");
 
  locadora.registrarLocacao(nomeJogo, nomeCliente, dataLocacao, dataDevolucao);
}
 

function listarLocacoesMenu(): void {
  const dataAtual = readlineSync.question(
    "\nInforme a data atual para o cálculo (AAAA-MM-DD): "
  );
  locadora.listarLocacoes(dataAtual);
}
 

function verificarAtrasosMenu(): void {
  const dataAtual = readlineSync.question(
    "\nInforme a data atual para verificação (AAAA-MM-DD): "
  );
  locadora.verificarAtrasos(dataAtual);
}
 

function iniciarMenu(): void {
  let opcao = "";
 
  while (opcao !== "6") {
    console.log("\n====================================");
    console.log(`🎮 MENU - ${locadora.nome}`);
    console.log("====================================");
    console.log("1. Cadastrar jogo");
    console.log("2. Cadastrar cliente");
    console.log("3. Registrar locação");
    console.log("4. Listar locações");
    console.log("5. Verificar atrasos");
    console.log("6. Sair");
 
    opcao = readlineSync.question("\nEscolha uma opção: ");
 
    switch (opcao) {
      case "1":
        cadastrarJogo();
        break;
      case "2":
        cadastrarCliente();
        break;
      case "3":
        registrarLocacaoMenu();
        break;
      case "4":
        listarLocacoesMenu();
        break;
      case "5":
        verificarAtrasosMenu();
        break;
      case "6":
        console.log("\n👋 Até logo! Não esqueça de devolver os jogos no prazo!");
        break;
      default:
        console.log("\n❌ Opção inválida. Tente novamente.");
    }
  }
}
 
iniciarMenu();