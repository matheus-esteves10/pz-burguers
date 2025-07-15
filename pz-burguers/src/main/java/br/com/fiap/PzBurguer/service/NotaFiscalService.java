package br.com.fiap.PzBurguer.service;

import br.com.fiap.PzBurguer.dto.result.Result;
import br.com.fiap.PzBurguer.model.ItemPedido;
import br.com.fiap.PzBurguer.model.Pedido;
import br.com.fiap.PzBurguer.producer.NotaFiscalProducer;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NotaFiscalService {

    public File gerarComprovante(Pedido pedido) {
        final String nomeArquivo = "comprovantes/nota-pedido-" + pedido.getId() + ".pdf";

        try {
            FileOutputStream outputStream = new FileOutputStream(nomeArquivo);
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);

            document.open();

            document.add(new Paragraph("==== NOTA FISCAL - PZBURGUER ===="));
            document.add(new Paragraph("ID Pedido: " + pedido.getId()));
            document.add(new Paragraph("Cliente: " + pedido.getUsuario().getNome()));
            document.add(new Paragraph("Endereço: " + pedido.getEnderecoEntrega()));
            document.add(new Paragraph("Status Pagamento: " + pedido.getStatusPagamento()));
            document.add(new Paragraph("Data: " + dataFormatter(pedido.getDataPedido())));
            document.add(new Paragraph("Itens:"));

            for (ItemPedido item : pedido.getItens()) {
                document.add(new Paragraph("- " + item.getItem().getNome() + " x" + item.getQuantidade()));
            }

            document.add(new Paragraph("Total: R$ " + pedido.getValorTotal()));

            document.close();
            outputStream.close();

            System.out.println("✅ Comprovante gerado: " + nomeArquivo);
            return new File(nomeArquivo);

        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar comprovante: " + e.getMessage());
            return null;
        }
    }

    private String dataFormatter(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return data.format(formatter);
    }


}


