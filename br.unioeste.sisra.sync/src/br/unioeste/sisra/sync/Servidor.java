/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.sync;

import br.unioeste.sisra.controle.ContaControle;
import br.unioeste.sisra.controle.FuncionarioControle;
import br.unioeste.sisra.controle.MesaControle;
import br.unioeste.sisra.modelo.execao.ValidacaoException;
import br.unioeste.sisra.modelo.listener.IContaListener;
import br.unioeste.sisra.modelo.listener.IFuncionarioListener;
import br.unioeste.sisra.modelo.listener.IMesaListener;
import br.unioeste.sisra.modelo.to.ContaTO;
import br.unioeste.sisra.modelo.to.FuncionarioTO;
import br.unioeste.sisra.modelo.to.MesaTO;
import br.unioeste.sisra.utils.Codigo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charlinho
 */
public class Servidor {

    private ServerSocket socket;
    private int porta;

    public Servidor(int porta) throws IOException {
        System.out.println("Iniciadondo servidor.");
        this.porta = porta;
        // Cria um SocketServer (Socket característico de um servidor)
        this.socket = new ServerSocket(this.porta);
    }

    public void iniciarServidor() throws ClassNotFoundException, IOException {
        System.out.println("Conexão inciada na porta : " + this.porta + ".");
        while (true) {
            Socket conexao = null;
            try {
                conexao = socket.accept();
                System.out.println("Cliente conectado.");
                // Cria uma buffer que irá armazenar as informações enviadas pelo cliente
                ObjectInputStream entradaDados = new ObjectInputStream(conexao.getInputStream());
                //new BufferedReader(new InputStreamReader(conexao.getInputStream()));

                // Cria uma stream de sáida para retorno das informações ao cliente
                ObjectOutputStream saidaDados = new ObjectOutputStream(conexao.getOutputStream());

                //--------------------------------------
                //  Inicio da leitura de dados
                //--------------------------------------
                // int entidadeCodigo = entradaDados.read();
                // int tipoAcessCodigo = entradaDados.read();
                HashMap<String, Serializable> params = (HashMap<String, Serializable>) entradaDados.readObject();
                int entidadeCodigo = (int) params.get(Codigo.Parametros.ENTIDADE);
                int tipoAcessCodigo = (int) params.get(Codigo.Parametros.TIPO_ACESSO);
                String query = (String) params.get(Codigo.Parametros.QUERY);
                Serializable obj = params.get(Codigo.Parametros.OBJETO);
                //String query = "";
                //String query = (String) entradaDados.readObject();
                System.out.println("Capturado dados com sucesso");
                switch (entidadeCodigo) {
                    case Codigo.Entidade.FUNCIONARIO:
                        entradaDadosFuncionario(entradaDados, saidaDados, tipoAcessCodigo, query);
                        break;
                    case Codigo.Entidade.MESA:
                        entradaDadosMesa(entradaDados, saidaDados, tipoAcessCodigo, query);
                        break;
                    case Codigo.Entidade.CONTA:
                        entradaDadosConta(entradaDados, saidaDados, tipoAcessCodigo, query, obj);
                        break;
                    default:
                        System.err.println("Codigo de entidade : \"" + entidadeCodigo + "\" não configurado.");
                        break;
                }
                System.out.println("Conexão com cliente fechado.");
            } catch (IOException e) {
                //ignora tudo
                e.printStackTrace();
            } finally {
                if (conexao != null) {
                    conexao.close();
                }
            }
        }

    }

    public void fecharConexao() throws IOException {
        System.out.println("Fechando servidor...");
        if (this.socket != null) {
            this.socket.close();
            System.out.println("Servidor fechado.");
        }
    }

    private void entradaDadosFuncionario(ObjectInputStream entradaDados, final ObjectOutputStream saidaDados, int tipoAcessCodigo, String query) throws IOException {
        try {
            FuncionarioControle funControle = new FuncionarioControle(new IFuncionarioListener() {
                @Override
                public void exibirBusca(FuncionarioTO[] funcionarios) {
                    try {
                        ArrayList<FuncionarioTO> result = new ArrayList<>();
                        for (FuncionarioTO to : funcionarios) {
                            System.out.println(to.toString());
                            result.add(to);
                        }
                        saidaDados.writeObject(result);
                    } catch (IOException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public void funcionarioExcluidoSucesso(String pk) {
                    System.out.println(pk);
                }
            });

            switch (tipoAcessCodigo) {
                case Codigo.TipoAcesso.SEACH:
                    funControle.buscarFuncionariosPorId("");
                    break;
                case Codigo.TipoAcesso.INSERT:

                    break;
                case Codigo.TipoAcesso.UPDATE:

                    break;
                case Codigo.TipoAcesso.DELETE:

                    break;
                default:
                    System.err.println("Codigo de tipo de acesso : \"" + tipoAcessCodigo + "\" não configurado.");
                    break;
            }

        } catch (ValidacaoException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void entradaDadosMesa(ObjectInputStream entradaDados, final ObjectOutputStream saidaDados, int tipoAcessCodigo, String query) throws IOException {
        try {
            MesaControle funControle = new MesaControle(new IMesaListener() {
                @Override
                public void exibirBusca(MesaTO[] mesas) {
                    try {
                        ArrayList<MesaTO> result = new ArrayList<>();
                        for (MesaTO to : mesas) {
                            result.add(to);
                            System.out.println(to.toString());
                        }
                        saidaDados.writeObject(result);
                    } catch (IOException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public void mesaExcluidaSucesso(String pk) {
                    System.out.println(pk);
                }
            });

            switch (tipoAcessCodigo) {
                case Codigo.TipoAcesso.SEACH:
                    funControle.buscarMesasPorId("");
                    break;
                case Codigo.TipoAcesso.INSERT:

                    break;
                case Codigo.TipoAcesso.UPDATE:

                    break;
                case Codigo.TipoAcesso.DELETE:

                    break;
                default:
                    System.err.println("Codigo de tipo de acesso : \"" + tipoAcessCodigo + "\" não configurado.");
                    break;
            }

        } catch (ValidacaoException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void entradaDadosConta(ObjectInputStream entradaDados, final ObjectOutputStream saidaDados, int tipoAcessCodigo, String query, Serializable obj) {
        try {
            ContaControle controle = new ContaControle(new IContaListener() {
                @Override
                public void exibirBusca(ContaTO[] contas) {
                    try {
                        ArrayList<ContaTO> result = new ArrayList<>();
                        for (ContaTO to : contas) {
                            result.add(to);
                            System.out.println(to.toString());
                        }
                        saidaDados.writeObject(result);
                    } catch (IOException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public void excluidoSucesso(String pk) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });

            switch (tipoAcessCodigo) {
                case Codigo.TipoAcesso.SEACH:
                    if (query.equals(ContaControle.Query.POR_MESA_E_EM_ABERTO)) {

                        if (obj != null && obj instanceof MesaTO) {
                            MesaTO mesa = (MesaTO) obj;
                            controle.buscarContasPorMesa(mesa, true);
                        }
                    } else {
                        controle.buscarContasPorId("");
                    }
                    break;
                case Codigo.TipoAcesso.INSERT:
                    if (obj != null && obj instanceof ContaTO) {
                        ContaTO conta = (ContaTO) obj;
                        controle.gravar(conta, true);
                        ContaTO contaResult = controle.bucarContaPorChave(String.valueOf(conta.getId()));

                        // saidaDados.writeObject(contaResult);
                    }
                    break;
                case Codigo.TipoAcesso.UPDATE:

                    break;
                case Codigo.TipoAcesso.DELETE:

                    break;
                default:
                    System.err.println("Codigo de tipo de acesso : \"" + tipoAcessCodigo + "\" não configurado.");
                    break;
            }

        } catch (ValidacaoException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
