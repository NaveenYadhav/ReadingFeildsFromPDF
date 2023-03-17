package com.code;

import java.io.File;

import java.io.IOException;
import java.util.Date;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.text.PDFTextStripper;

public class Code {
	
	

	public static void main(String[] args) throws IOException {
		
		
		File file = new File("himanshi dec 21.pdf");
	
		
		PDFParser parser =new PDFParser(new org.apache.pdfbox.io.RandomAccessFile(file, "r"));
		parser.parse();
		
		COSDocument cosdoc = parser.getDocument();
		PDDocument pddoc = new PDDocument(cosdoc);		
		
		PDFTextStripper str = new PDFTextStripper();
		String data = str.getText(pddoc);
		System.out.println(data);
		
		
		 String payeeName = getField(data, "Payee Name");
		 String nickName = getField(data, "Nickname");
		 long creditAccountNo=Long.parseLong(getField(data ,"Credit Account No"));
		 String remarks=getField(data,"Remarks");
		 long debitAccountNo=Long.parseLong(getField(data ,"Debit Account"));
		 String date=getField(data,"Date");
		 String amount=getField(data,"Amount");
		 String paymentType=getField(data,"Payment Type");
		 String payVia=getField(data,"Pay via");
		 	 
		 
		TransactionDetails td=new TransactionDetails(payeeName,nickName,creditAccountNo,remarks,debitAccountNo,date,amount,paymentType,payVia);
		System.out.println(td.getAmount());
		System.out.println(td.getCreditAccount());
		System.out.println(td.getDebitAccount());
		System.out.println(td.getNickName());
		System.out.println(td.getPayeeName());
		System.out.println(td.getPaymentType());
		System.out.println(td.getPayVia());
		System.out.println(td.getTransactionDate());
		System.out.println(td.getRemarks());
		

	}
	
	
	 private static String getField(String text, String field) {
	        int startIndex = text.indexOf(field);
	        System.out.println(startIndex);
	        if (startIndex == -1) {
	            return null;
	        }
	        int endIndex = text.indexOf("\n", startIndex);
	        System.out.println(endIndex);
	        if (endIndex == -1) {
	            endIndex = text.length();
	            
	        }
	        return text.substring(startIndex + field.length()+1, endIndex).trim();
	    }

}
