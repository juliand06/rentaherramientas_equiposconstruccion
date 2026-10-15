package com.rentatools.application.billing.port;

import java.math.BigDecimal;
import java.util.UUID;

public interface PdfGeneratorPort {
    byte[] generateInvoicePdf(UUID invoiceId, String invoiceNumber, String clientName, BigDecimal totalAmount);
}
