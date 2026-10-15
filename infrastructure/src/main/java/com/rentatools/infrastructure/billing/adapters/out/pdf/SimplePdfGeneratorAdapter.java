package com.rentatools.infrastructure.billing.adapters.out.pdf;

import com.rentatools.application.billing.port.PdfGeneratorPort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class SimplePdfGeneratorAdapter implements PdfGeneratorPort {

    @Override
    public byte[] generateInvoicePdf(UUID invoiceId, String invoiceNumber, String clientName, BigDecimal totalAmount) {
        String content = "FACTURA DE VENTA\n" +
                "Número: " + invoiceNumber + "\n" +
                "Cliente: " + clientName + "\n" +
                "Total: COP $" + totalAmount + "\n" +
                "ID Transacción: " + invoiceId + "\n" +
                "RentaTools S.A.S. - Colombia\n";
        return content.getBytes(StandardCharsets.UTF_8);
    }
}
