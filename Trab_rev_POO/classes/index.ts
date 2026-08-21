import { Locadora } from "./Locadora";
import { JogoGame } from "./JogoGame";
import { Cliente } from "./Cliente";

const DATA_ATUAL = "2026-07-10";
 

const locadora = new Locadora("GameZone Locações");
 

const zelda = new JogoGame(
  "The Legend of Zelda: Tears of the Kingdom",
  "Nintendo Switch",
  "Aventura",
  10
);
const spiderman = new JogoGame(
  "Marvel's Spider-Man 2",
  "PS5",
  "Ação",
  15
);
 
locadora.adicionarJogo(zelda);
locadora.adicionarJogo(spiderman);
 

const lucas = new Cliente("Lucas Pereira", 22, "(11) 98888-1234", "lucas@email.com");
const ana = new Cliente("Ana Souza", 27, "(11) 97777-5678", "ana@email.com");
 
locadora.adicionarCliente(lucas);
locadora.adicionarCliente(ana);
 
console.log("")
locadora.registrarLocacao(
  zelda.nome,
  lucas.nome,
  "2026-07-01",
  "2026-07-05"
);
 

locadora.registrarLocacao(
  spiderman.nome,
  ana.nome,
  "2026-07-08",
  "2026-07-15"
);
 

locadora.listarLocacoes(DATA_ATUAL);
locadora.verificarAtrasos(DATA_ATUAL);
locadora.listarJogos();
locadora.listarClientes();