import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ramiro
 */
public class ConvFuncionarioControl {
    
    PreparedStatement pstm1;
    ResultSet rs1;
    
    String consultaRegistro = "select * from conv_funcionarios where nome like ? order by nome";
    
    String consultaFuncionarios = "select * from conv_funcionarios where cod_empresa = ?";
    
    String gravaRegistro = "insert into conv_funcionarios (nome, matricula, cod_empresa, rg, expedidor, uf, expedicao,"
            + "cpf, sexo, telefone, celular, email, aniversario, endereco, bairro, situacao)"
            + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    String alteraRegistro = "update conv_funcionarios set nome=?, matricula=?, cod_empresa=?, rg=?, expedidor=?, uf=?, expedicao=?,"
            + "cpf=?, sexo=?, telefone=?, celular=?, email=?, aniversario=?, endereco=?, "
            + "bairro=?, situacao=? where codfuncionario=?";
    
    
    public List<ConvFuncionarioBean> listarFuncionariosEmpresa(Integer cod_empresa){
        List<ConvFuncionarioBean> funcionarios = new ArrayList();
        try{
            ConexaoBD con_funcionario = new ConexaoBD();
            pstm1 = con_funcionario.conecta().prepareStatement(consultaFuncionarios);
            pstm1.setInt(1, cod_empresa);
            rs1 = pstm1.executeQuery();
            ConvFuncionarioBean func;
            while (rs1.next()){
                func = new ConvFuncionarioBean();
                func.setCodfuncionario(rs1.getInt("codfuncionario"));    
                func.setNome(rs1.getString("nome"));
                func.setMatricula(rs1.getString("matricula"));
                func.setCod_empresa(rs1.getInt("cod_empresa"));
                func.setRg(rs1.getString("rg"));
                func.setExpedidor(rs1.getString("expedidor"));
                func.setUf(rs1.getString("uf"));
                func.setExpedicao(rs1.getString("expedicao"));
                func.setCpf(rs1.getString("cpf"));
                func.setSexo(rs1.getString("sexo"));
                func.setTelefone(rs1.getString("telefone"));
                func.setCelular(rs1.getString("celular"));
                func.setEmail(rs1.getString("email"));
                func.setAniversario(rs1.getString("aniversario"));
                func.setEndereco(rs1.getString("endereco"));
                func.setBairro(rs1.getString("bairro"));
                func.setSituacao(rs1.getString("situacao"));
                funcionarios.add(func);
                con_funcionario.desonecta();                
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não Existe Registro!");
        }
        return funcionarios;
    }
    
    public List<ConvFuncionarioBean> listarFuncionarios (String nome) { 
        List<ConvFuncionarioBean> funcionarios = new ArrayList();
        try {
            ConexaoBD con_funcionario = new ConexaoBD();
            pstm1 = con_funcionario.conecta().prepareStatement(consultaRegistro);
            pstm1.setString(1, nome);
            rs1 = pstm1.executeQuery();
            ConvFuncionarioBean func;
            while (rs1.next()){
                func = new ConvFuncionarioBean();
                func.setCodfuncionario(rs1.getInt("codfuncionario"));
                func.setNome(rs1.getString("nome"));
                func.setMatricula(rs1.getString("matricula"));
                func.setCod_empresa(rs1.getInt("cod_empresa"));
                func.setRg(rs1.getString("rg"));
                func.setExpedidor(rs1.getString("expedidor"));
                func.setUf(rs1.getString("uf"));
                func.setExpedicao(rs1.getString("expedicao"));
                func.setCpf(rs1.getString("cpf"));
                func.setSexo(rs1.getString("sexo"));
                func.setTelefone(rs1.getString("telefone"));
                func.setCelular(rs1.getString("celular"));
                func.setEmail(rs1.getString("email"));
                func.setAniversario(rs1.getString("aniversario"));
                func.setEndereco(rs1.getString("endereco"));
                func.setBairro(rs1.getString("bairro"));
                func.setSituacao(rs1.getString("situacao"));
                funcionarios.add(func);
                con_funcionario.desonecta();
         } 
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Existe Registro!");
            e.printStackTrace();
        }
        return funcionarios;

    }
    

    public void gravarRegistro(ConvFuncionarioBean func) {
        ConexaoBD con_funcionario = new ConexaoBD();
        try {
            pstm1 = con_funcionario.conecta().prepareStatement(gravaRegistro);
            pstm1.setString(1, func.getNome());
            pstm1.setString(2, func.getMatricula());
            pstm1.setInt(3, func.getCod_empresa());
            pstm1.setString(4, func.getRg());
            pstm1.setString(5, func.getExpedidor());
            pstm1.setString(6, func.getUf());
            pstm1.setString(7, func.getExpedicao());
            pstm1.setString(8, func.getCpf());
            pstm1.setString(9, func.getSexo());
            pstm1.setString(10, func.getTelefone());
            pstm1.setString(11, func.getCelular());
            pstm1.setString(12, func.getEmail());
            pstm1.setString(13, func.getAniversario());
            pstm1.setString(14, func.getEndereco());
            pstm1.setString(15, func.getBairro());
            pstm1.setString(16, func.getSituacao());
            pstm1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Gravado com Sucesso!");
            con_funcionario.desonecta();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Foi Possível Gravar o Registro!");
            e.printStackTrace();
        }


    }
    
    public void alterarRegistro(ConvFuncionarioBean func){
                ConexaoBD con_funcionario = new ConexaoBD();
        try {
            pstm1 = con_funcionario.conecta().prepareStatement(alteraRegistro);
            pstm1.setString(1, func.getNome());
            pstm1.setString(2, func.getMatricula());
            pstm1.setInt(3, func.getCod_empresa());
            pstm1.setString(4, func.getRg());
            pstm1.setString(5, func.getExpedidor());
            pstm1.setString(6, func.getUf());
            pstm1.setString(7, func.getExpedicao());
            pstm1.setString(8, func.getCpf());
            pstm1.setString(9, func.getSexo());
            pstm1.setString(10, func.getTelefone());
            pstm1.setString(11, func.getCelular());           
            pstm1.setString(12, func.getEmail());
            pstm1.setString(13, func.getAniversario());
            pstm1.setString(14, func.getEndereco());
            pstm1.setString(15, func.getBairro());
            pstm1.setString(16, func.getSituacao());
            pstm1.setInt(17, func.getCodfuncionario());
            pstm1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso!");
            con_funcionario.desonecta();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Foi Possível Alterar o Registro!");
            e.printStackTrace();
        }

    }

    
}
