package com.example.demo.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN8Writer;

import java.awt.image.BufferedImage;

public class BarCodeService {

    public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
        EAN8Writer barcodeWriter = new EAN8Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_8, 300, 150);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
