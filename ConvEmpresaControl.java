import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ramiro
 */
public class ConvEmpresaControl {
    
    PreparedStatement pstm2;
    ResultSet rs2;
    
    String consultaRegistro = "select * from conv_empresas where nome like ? order by nome";
    String gravaRegistro = "insert into conv_empresas (razao_social, nome, cnpj, inscest, "
            + "telefone, fax, contato, email, endereco, bairro, situacao)"
            + "values(?,?,?,?,?,?,?,?,?,?,?)";

    String alteraRegistro = "update conv_empresas set razao_social=?, nome=?, cnpj=?,"
            + "inscest=?, telefone=?, fax=?, contato=?, email=?, endereco=?, "
            + "bairro=?, situacao=? where codempresa=?";
    
    
    public List<ConvEmpresaBean> listarEmpresas (String nome) { 
        List<ConvEmpresaBean> empresas = new ArrayList();
        try {
            ConexaoBD con_empresa = new ConexaoBD();
            pstm2 = con_empresa.conecta().prepareStatement(consultaRegistro);
            pstm2.setString(1, nome);
            rs2 = pstm2.executeQuery();
            ConvEmpresaBean emp;
            while (rs2.next()){
                emp = new ConvEmpresaBean();
                emp.setCodempresa(rs2.getInt("codempresa"));
                emp.setRazao_social(rs2.getString("razao_social"));
                emp.setNome(rs2.getString("nome"));
                emp.setCnpj(rs2.getString("cnpj"));
                emp.setInscest(rs2.getString("inscest"));
                emp.setTelefone(rs2.getString("telefone"));
                emp.setFax(rs2.getString("fax"));
                emp.setContato(rs2.getString("contato"));
                emp.setEmail(rs2.getString("email"));
                emp.setEndereco(rs2.getString("endereco"));
                emp.setBairro(rs2.getString("bairro"));
                emp.setSituacao(rs2.getString("situacao"));
                empresas.add(emp);
                con_empresa.desonecta();
         } 
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Existe Registro!");
        }
        return empresas;

    }
    

    public void gravarRegistro(ConvEmpresaBean emp) {
        ConexaoBD con_empresa = new ConexaoBD();
        try {
            pstm2 = con_empresa.conecta().prepareStatement(gravaRegistro);
            pstm2.setString(1, emp.getRazao_social());
            pstm2.setString(2, emp.getNome());
            pstm2.setString(3, emp.getCnpj());
            pstm2.setString(4, emp.getInscest());
            pstm2.setString(5, emp.getTelefone());
            pstm2.setString(6, emp.getFax());
            pstm2.setString(7, emp.getContato());
            pstm2.setString(8, emp.getEmail());
            pstm2.setString(9, emp.getEndereco());
            pstm2.setString(10, emp.getBairro());
            pstm2.setString(11,emp.getSituacao());
            pstm2.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Gravado com Sucesso!");
            con_empresa.desonecta();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Foi Possível Gravar o Registro!");
            e.printStackTrace();
        }


    }
    
    public void alterarRegistro(ConvEmpresaBean emp){
                ConexaoBD con_empresa = new ConexaoBD();
        try {
            pstm2 = con_empresa.conecta().prepareStatement(alteraRegistro);
            pstm2.setString(1, emp.getRazao_social());
            pstm2.setString(2, emp.getNome());
            pstm2.setString(3, emp.getCnpj());
            pstm2.setString(4, emp.getInscest());
            pstm2.setString(5, emp.getTelefone());
            pstm2.setString(6, emp.getFax());
            pstm2.setString(7, emp.getContato());
            pstm2.setString(8, emp.getEmail());
            pstm2.setString(9, emp.getEndereco());
            pstm2.setString(10, emp.getBairro());
            pstm2.setString(11, emp.getSituacao());
            pstm2.setInt(12, emp.getCodempresa());
            pstm2.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso!");
            con_empresa.desonecta();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Foi Possível Alterar o Registro!");
            e.printStackTrace();
        }

    }
    
}
