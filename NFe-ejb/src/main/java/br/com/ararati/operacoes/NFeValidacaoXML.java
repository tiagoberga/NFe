/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.operacoes;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author tiago
 */
public class NFeValidacaoXML implements ErrorHandler {

    private List<String> listaComErrosDeValidacao;

    public NFeValidacaoXML() {
        this.listaComErrosDeValidacao = new ArrayList<String>();
    }

    /**
     * Método valida XML de TEnviNFe.
     *
     * @param xsdDirectory diretório do arquivo enviNFe_v3.10.xsd
     * @param xmlFile xml a ser validado
     * @return String mensagens de erro
     */
    public String validaXMLEnviNFe(String xsdDirectory, String xmlFile) {
        return validaXml(xsdDirectory.concat("enviNFe_v3.10.xsd"), xmlFile);
    }

    /**
     * Método valida XML de TInutNFe.
     *
     * @param xsdDirectory diretório do arquivo inutNFe_v3.10.xsd
     * @param xmlFile xml a ser validado
     * @return String mensagens de erro
     */
    public String validaXMLInutNFe(String xsdDirectory, String xmlFile) {
        return validaXml(xsdDirectory.concat("inutNFe_v3.10.xsd"), xmlFile);
    }

    private String validaXml(String arquivoXsd, String arquivoXml) {
        StringBuilder messages = new StringBuilder();
        try {
            NFeValidacaoXML validacaoXML = new NFeValidacaoXML();
            List<String> errosValidacao = validacaoXML.validateXml(normalizeXML(arquivoXml), arquivoXsd);
            if ((errosValidacao != null) && (errosValidacao.size() > 0)) {
                for (String msgError : errosValidacao) {
                    messages.append(msgError);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages.toString();
    }

    private static String normalizeXML(String xml) {
        if ((xml != null) && (!"".equals(xml))) {
            xml = xml.replaceAll("\\r\\n", "");
            xml = xml.replaceAll(" standalone=\"no\"", "");
        }
        return xml;
    }

    private List<String> validateXml(String xml, String xsd) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(true);
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", xsd);
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            builder.setErrorHandler(this);
        } catch (ParserConfigurationException ex) {
            error("| validateXml():");
            throw new Exception(ex.toString());
        }

        org.w3c.dom.Document document;
        try {
            document = builder.parse(new ByteArrayInputStream(xml.getBytes()));
            org.w3c.dom.Node rootNode = document.getFirstChild();
            info("| Validando Node: " + rootNode.getNodeName());
            return this.getListaComErrosDeValidacao();
        } catch (Exception ex) {
            error("| validateXml():");
            throw new Exception(ex.toString());
        }
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        if (isError(exception)) {
            listaComErrosDeValidacao.add(tratamentoRetorno(exception.getMessage()));
        }
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        listaComErrosDeValidacao.add(tratamentoRetorno(exception.getMessage()));
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        listaComErrosDeValidacao.add(tratamentoRetorno(exception.getMessage()));
    }

    private String tratamentoRetorno(String message) {
        message = message.replaceAll("cvc-enumeration-valid:", "");
        message = message.replaceAll("cvc-type.3.1.3:", "");
        message = message.replaceAll("cvc-complex-type.2.4.a:", "");
        message = message.replaceAll("cvc-complex-type.2.4.b:", "");
        message = message.replaceAll("The value", "O valor");
        message = message.replaceAll("of element", "do campo");
        message = message.replaceAll("is not valid", "não é valido");
        message = message.replaceAll("Invalid content was found starting with element", "Encontrado o campo");
        message = message.replaceAll("One of", "Campo(s)");
        message = message.replaceAll("is expected", "é obrigatorio");
        message = message.replaceAll("\\{", "");
        message = message.replaceAll("\\}", "");
        message = message.replaceAll("\"", "");
        message = message.replaceAll("http://www.portalfiscal.inf.br/nfe:", "");

        // altera nome dos campos  
        message = message.replaceAll("pICMSInter", "% ICMS Interestadual");
        message = message.replaceAll("cUF", "Código da UF do emitente");
        message = message.replaceAll("natOp", "Descrição do CFOP");
        message = message.replaceAll("IndPag", "Forma de Pagamento");
        message = message.replaceAll("nNF", "Número da Nota Fiscal");
        message = message.replaceAll("dEmi", "Data de Emissão da NF-e");
        message = message.replaceAll("dSaiEnt", "Data da Nota Fiscal");
        message = message.replaceAll("tpNF", "Tipo da Operação");
        message = message.replaceAll("cMunFG", "Código do Municipio do emitente");
        message = message.replaceAll("tpImp", "Formato de impressão do DANFE");
        message = message.replaceAll("tpEmis", "Tipo de Emissão da NF-e");
        message = message.replaceAll("finNFe", "Finalidade da emissão da NF-e");
        message = message.replaceAll("xNome", "Razão Social");
        message = message.replaceAll("xFant", "Nome Fantasia");
        message = message.replaceAll("xLgr", "Logradouro");
        message = message.replaceAll("nro", "Número");
        message = message.replaceAll("xCpl", "Complemento");
        message = message.replaceAll("xBairro", "Bairro");
        message = message.replaceAll("cMun", "Código do Município");
        message = message.replaceAll("xMun", "Nome do Município");
        message = message.replaceAll("cPais", "Código do País");
        message = message.replaceAll("xPais", "Nome do País");
        message = message.replaceAll("IE", "Inscrição Estadual");
        message = message.replaceAll("CRT", "Código de Regime Tributário");
        message = message.replaceAll("nItem", "Número de Itens");
        message = message.replaceAll("cProd", "Código do Produto");
        message = message.replaceAll("xProd", "Descrição do Produto");
        message = message.replaceAll("NCM", "Classificação Fiscal");
        message = message.replaceAll("uCom", "Unidade");
        message = message.replaceAll("qCom", "Quantidade");
        message = message.replaceAll("vUnCom", "Valor Unitário");
        message = message.replaceAll("vProd", "Valor Total dos Produtos");
        message = message.replaceAll("uTrib", "Unidade");
        message = message.replaceAll("qTrib", "Quantidade");
        message = message.replaceAll("vUnTrib", "Valor Unitário");
        message = message.replaceAll("pCredSN", "I.C.M.S.");
        message = message.replaceAll("vCredICMSSN", "Valor de Crédito do ICMS");
        message = message.replaceAll("vNF", "Valor Total da Nota");
        return message.trim();
    }

    private List<String> getListaComErrosDeValidacao() {
        return listaComErrosDeValidacao;
    }

    private boolean isError(SAXParseException exception) {
        if (exception.getMessage().startsWith("cvc-pattern-valid")
                || exception.getMessage().startsWith("cvc-maxLength-valid")
                || exception.getMessage().startsWith("cvc-datatype")) {
            return false;
        }
        return true;
    }

    private static void error(String error) {
        System.out.println("ERROR: " + error);
    }

    private static void info(String info) {
        System.out.println("INFO: " + info);
    }

}
